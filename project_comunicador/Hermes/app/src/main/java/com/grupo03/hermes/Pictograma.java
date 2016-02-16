package com.grupo03.hermes;

import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.grupo03.hermes.adaptors.GridAdaptor;
import com.grupo03.hermes.db.Database;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by federico on 16/12/15.
 */
public class Pictograma {

    public static enum MODO {ALUMNO, TERAPEUTA}

    private int id, idCategoria, idContexto;
    private String name;
    private String tab;
    private String imgPath, soundPath;
    private ImageView view;
    private boolean istabAlumno=false;
    private AssetFileDescriptor fileDescriptor;
    private final static int _BORDER_WIDTH = 4;
    private final static String _BORDER_COLOR = "#56FF6D";
    private boolean isHabilitada = false;
    private MODO modo = MODO.ALUMNO;
    private String tamanio;

    public Pictograma(int id, int idCategoria, int idContexto, String name, String tab, String imgPath, String soundPath, boolean isTabAlumno, String tamanio){
        this.id = id;
        this.idCategoria = idCategoria;
        this.idContexto = idContexto;
        this.name = name;
        this.tab = tab;
        this.imgPath = imgPath;
        this.soundPath = soundPath;
        this.istabAlumno= isTabAlumno;
        this.tamanio=tamanio;
    }

    public ImageView getView(Context context){

        if (view != null)
            return view;

        view = new ImageView(context);
        if (tamanio!= null) {
            System.out.println("tamanio"+ tamanio);
            switch (tamanio) {
                case "Grande":
                    view.setMinimumHeight(512);
                    view.setMinimumWidth(512);
                    System.out.println("Grande");
                    break;
                case "Mediano":
                    view.setMinimumHeight(256);
                    view.setMinimumWidth(256);
                    System.out.println("mediano");
                    break;
                case "Chico":
                    view.setMinimumHeight(60);
                    view.setMinimumWidth(60);
                    System.out.println("chco");
                    break;
            }
        }

        view.setAdjustViewBounds(true);
        view.setCropToPadding(true);
        view.setPadding(_BORDER_WIDTH, _BORDER_WIDTH, _BORDER_WIDTH, _BORDER_WIDTH);
       // view.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (isHabilitada){ // puede haberse llamado a showBorder/hideBorder antes de que view != null => muestro borde ahora
            showBorder();
        } else {
            hideBorder();
        }

        try {

            view.setImageDrawable(getAssetImage(MainActivity.applicationContext, "pictogramas/" + tab + "/" + imgPath));

            fileDescriptor = context.getAssets().openFd("pictogramas/" + tab + "/" + soundPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (modo == MODO.ALUMNO) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                        player.prepare();
                        player.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        Notificacion.enviar(Pictograma.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        if (modo == MODO.TERAPEUTA) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (AlumnoActivity._instance.getActiva()) {
                        if (!isHabilitada && !istabAlumno) {
                            Toast.makeText(MainActivity.applicationContext, "Pictograma agregado !", Toast.LENGTH_SHORT).show();
                            int idAlumno = AlumnoActivity._instance.getIntent().getIntExtra("alumno_id", 0);
                            Database db = new Database(MainActivity.applicationContext);
                            db.asignarPictograma(idAlumno, getId());
                            showBorder();
                            return true;
                        } else {
                            Toast.makeText(MainActivity.applicationContext, "Pictograma eliminado !", Toast.LENGTH_SHORT).show();
                            int idAlumno = AlumnoActivity._instance.getIntent().getIntExtra("alumno_id", 0);
                            Database db = new Database(MainActivity.applicationContext);
                            db.eliminarPictogramaAsignado(idAlumno, getId());
                            hideBorder();

                            if (istabAlumno) {
                                view.setVisibility(View.GONE);
                            }

                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        return view;
    }

    public void showBorder(){
        isHabilitada = true;
        if (view != null){
            view.setBackgroundColor(Color.parseColor(_BORDER_COLOR));
        }
    }

    public void hideBorder(){
        isHabilitada = false;
        if (view != null){
            view.setBackgroundColor(0);
        }
    }

    public void setModo(MODO modo){
        this.modo = modo;
    }

    public String getTab() {
        return tab;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdContexto() {
        return idContexto;
    }

    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open(filename)));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public static boolean contains(List<Pictograma> pictogramaList, Pictograma pictograma) {

        for (Pictograma p : pictogramaList) {
            if (p.equals(pictograma))
                return true;
        }

        return false;
    }

    public boolean equals(Pictograma p) {
        return this.getId() == p.getId();
    }
}

package com.grupo03.hermes;

import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.grupo03.hermes.adaptors.GridAdaptor;
import com.grupo03.hermes.db.Database;

import java.io.IOException;
import java.io.InputStream;

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
    private final static String _BORDER_COLOR = "#FF0000";
    private boolean isHabilitada = false;
    private MODO modo = MODO.ALUMNO;

    public Pictograma(int id, int idCategoria, int idContexto, String name, String tab, String imgPath, String soundPath, boolean isTabAlumno){
        this.id = id;
        this.idCategoria = idCategoria;
        this.idContexto = idContexto;
        this.name = name;
        this.tab = tab;
        this.imgPath = imgPath;
        this.soundPath = soundPath;
        this.istabAlumno= isTabAlumno;
    }

    public ImageView getView(Context context){

        if (view != null)
            return view;

        view = new ImageView(context);
        view.setMaxWidth(64);
        view.setMaxHeight(64);
        view.setCropToPadding(true);
        view.setPadding(_BORDER_WIDTH, _BORDER_WIDTH, _BORDER_WIDTH, _BORDER_WIDTH);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        hideBorder();

        try {
            InputStream is = context.getAssets().open("pictogramas/" + tab + "/" + imgPath);
            view.setImageDrawable(Drawable.createFromStream(is, null));

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
        if (view != null){
            view.setBackgroundColor(Color.parseColor(_BORDER_COLOR));
            isHabilitada = true;
        }
    }

    public void hideBorder(){
        if (view != null){
            view.setBackgroundColor(0);
            isHabilitada = false;
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

}

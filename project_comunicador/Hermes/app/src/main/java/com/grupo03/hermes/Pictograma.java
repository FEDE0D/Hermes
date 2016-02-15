package com.grupo03.hermes;

import android.app.Activity;
import android.app.AliasActivity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.grupo03.hermes.db.Database;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by federico on 16/12/15.
 */
public class Pictograma {

    private int id, idCategoria, idContexto;
    private String name;
    private String tab;
    private String imgPath, soundPath;
    private ImageView view;
    private boolean istabAlumno=false;
    private AssetFileDescriptor fileDescriptor;

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

        try {
            InputStream is = context.getAssets().open("pictogramas/" + tab + "/" + imgPath);
            view.setImageDrawable(Drawable.createFromStream(is, null));

            fileDescriptor = context.getAssets().openFd("pictogramas/" + tab + "/" + soundPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (AlumnoActivity._instance.getActiva()) {
                    if (!istabAlumno) {
                        Toast.makeText(MainActivity.applicationContext, "Pictograma agregado !", Toast.LENGTH_SHORT).show();
                        int idAlumno = AlumnoActivity._instance.getIntent().getIntExtra("alumno_id", 0);
                        Database db = new Database(MainActivity.applicationContext);
                        db.asignarPictograma(idAlumno, getId());
                        return true;
                    }
                    else{
                        Toast.makeText(MainActivity.applicationContext, "Pictograma eliminado !", Toast.LENGTH_SHORT).show();
                        int idAlumno = AlumnoActivity._instance.getIntent().getIntExtra("alumno_id", 0);
                        Database db = new Database(MainActivity.applicationContext);
                        db.eliminarPictogramaAsignado(idAlumno, getId());
                        return true;
                    }
                } return false;
            }
        });

        return view;
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

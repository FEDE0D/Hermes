package com.grupo03.hermes;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by federico on 16/12/15.
 */
public class Pictograma {

    private int id, idCategoria;
    private String name;
    private String tab;
    private String imgPath, soundPath;
    private ImageView view;
    private AssetFileDescriptor fileDescriptor;

    public Pictograma(int id, int idCategoria, String name, String tab, String imgPath, String soundPath){
        this.id = id;
        this.idCategoria = idCategoria;
        this.name = name;
        this.tab = tab;
        this.imgPath = imgPath;
        this.soundPath = soundPath;
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

}

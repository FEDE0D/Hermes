package com.grupo03.hermes;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by federico on 16/12/15.
 */
public class Pictograma {

    private String name;
    private String tab;
    private String imgPath, soundPath;
    private ImageView view;
    private AssetFileDescriptor fileDescriptor;

    public Pictograma(String name, String tab, String imgPath, String soundPath){
        this.name = name;
        this.tab = tab;
        this.imgPath = imgPath;
        this.soundPath = soundPath;
    }

    public ImageView getView(Context context){

        if (view != null)
            return view;

        view = new ImageView(context);

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
                }
            }
        });

        return view;
    }


}

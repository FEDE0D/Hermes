package com.grupo03.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AlumnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alumno);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(intent.getStringExtra("alumno_nombre"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alumno, menu);

        String alumn_name = getIntent().getStringExtra("alumno_nombre");
        menu.findItem(R.id.alumn_name).setTitle(alumn_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_alumno);
        toolbar.setTitle("Alumno");

        return true;
    }
}

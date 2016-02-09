package com.grupo03.hermes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.grupo03.hermes.db.Database;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        applicationContext = getApplicationContext();

        try {
            ImageView imageView = (ImageView) findViewById(R.id.cedicaLogoView);
            InputStream is = getAssets().open("cedica.png");
            imageView.setImageDrawable(Drawable.createFromStream(is, null));
        } catch (IOException e) {
            e.printStackTrace();
        }


        cargarAlumnos();


    }
    public void cargarAlumnos(){
        Database database = new Database(getApplicationContext());
        final Cursor alumnos = database.getAlumnos();

        final ListView listView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                alumnos,
                new String[] {"apellido", "nombre"},
                new int[] {android.R.id.text1, android.R.id.text2});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alumnos.moveToPosition(position);
                int alumno_id = alumnos.getInt(1);
                String alumno_nombre = alumnos.getString(2) +" "+ alumnos.getString(3);
                System.out.println("ID ESSSSSSSSSSSSSSSSSSSSS"+ alumnos.getInt(1));
                System.out.println("nombre ESSSSSSSSSSSSSSSSSSSSS"+ alumno_nombre);
                Toast.makeText(getApplicationContext(), "Seleccionado: "+alumno_nombre, Toast.LENGTH_LONG).show();

                // Cambiando a la Activity del alumno
                Intent intent = new Intent(getApplicationContext(), AlumnoActivity.class);
                intent.putExtra("alumno_id", alumno_id);
                intent.putExtra("alumno_nombre", alumno_nombre);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume (){
        super.onResume();
        cargarAlumnos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrarAjustes(View view)
    {

        Intent intent = new Intent(this, AjustesActivity.class);
        intent.putExtra("tipo", "nuevoAlumno");
        startActivity(intent);
    }
}

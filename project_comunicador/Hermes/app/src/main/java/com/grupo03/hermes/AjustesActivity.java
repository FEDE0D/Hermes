package com.grupo03.hermes;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.grupo03.hermes.db.Database;

public class AjustesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ajustes);
        toolbar.setTitle("Ajustes");

        EditText inputIp= (EditText) findViewById(R.id.ip);
        EditText inputPuerto= (EditText) findViewById(R.id.puerto);
        Database database = new Database(getApplicationContext());
        Cursor conf= database.getConfiguracion();
        while (conf.isAfterLast() == false) {
            String clave= conf.getString(conf.getColumnIndex("clave"));
           if(clave.equals( "ipMonitor")){
               String valor = conf.getString(conf.getColumnIndex("valor"));
               inputIp.setText(valor);
           }
           else if(clave.equals("puertoMonitor")){
               String valor = conf.getString(conf.getColumnIndex("valor"));
               inputPuerto.setText(valor);

            }
            conf.moveToNext();
        }
        conf.close();


        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    public void cambiarConfiguracion(View view){
        String ip="",puerto="";
        Boolean error= false;
        EditText inputIp= (EditText) findViewById(R.id.ip);
        if (inputIp!= null){
            ip = inputIp.getText().toString();
            if( inputIp.length() == 0 ){
                inputIp.setError( "El IP es requerido!" ); error=true; }
        }
        EditText inputPuerto= (EditText) findViewById(R.id.puerto);
        if (inputPuerto!= null){
            puerto = inputPuerto.getText().toString();
            if( inputPuerto.length() == 0 ){
                inputPuerto.setError( "El puerto es requerido!" ); error=true; }
        }
        if (!error){
            Database database = new Database(getApplicationContext());
            database.guardarConfiguracion(ip,puerto);
            final Dialog dialog = new Dialog(this); // Context, this, etc.
            dialog.setContentView(R.layout.alert);
            dialog.setTitle("Se ha guardado correctamente la nueva configuración");
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            dialog.show();
        }
    }
    public void agregarAlumno(View view){
        boolean error= false;
        String nombre="", apellido="", sexo="";
        EditText inputNombre= (EditText) findViewById(R.id.inputNombre);
        if (inputNombre!= null){
             nombre = inputNombre.getText().toString();

            if( nombre.length() == 0 ){
                inputNombre.setError( "El nombre es requerido!" ); error=true; }
        }
        EditText inputApellido= (EditText) findViewById(R.id.inputApellido);
        if (inputApellido!= null) {
             apellido = inputApellido.getText().toString();
            if (apellido.length() == 0) {
                inputApellido.setError("El apellido es requerido!");
                error = true;
            }
        }

        CheckBox checkEmociones = (CheckBox) findViewById(R.id.checkEmociones);
        boolean emociones = checkEmociones.isChecked();
        CheckBox checkPista = (CheckBox) findViewById(R.id.checkPista);
        boolean pista = checkPista.isChecked();
        CheckBox checkEstablo = (CheckBox) findViewById(R.id.checkEstablo);
        boolean establo = checkEstablo.isChecked();
        CheckBox checkNecesidades = (CheckBox) findViewById(R.id.checkNecesidades);
        boolean necesidades = checkNecesidades.isChecked();
        String solapas="";
        if (necesidades) solapas= solapas+ "Necesidades,";
        if (pista) solapas= solapas+ "Pista,";
        if (establo) solapas= solapas+ "Establo,";
        if (emociones) solapas= solapas+ "Emociones";
        if (solapas.length() > 0 && solapas.charAt(solapas.length()-1)==',') {
            solapas = solapas.substring(0, solapas.length()-1);
        }

        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton seleccionado;
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId <= 0) {
            seleccionado = (RadioButton) findViewById(R.id.radioMasculino);
            seleccionado.setError("Selecciona un sexo");
            error=true;
        }else {
            seleccionado = (RadioButton) findViewById(selectedId);
            sexo = seleccionado.getText().toString();
        }

        RadioGroup group2 = (RadioGroup) findViewById(R.id.radioGroup2);
        int selectedId2 = group2.getCheckedRadioButtonId();
        RadioButton seleccionado2 = (RadioButton) findViewById(selectedId2);
        String tamanio= seleccionado2.getText().toString();

        Database database = new Database(getApplicationContext());
        System.out.println(sexo);
        System.out.println(solapas);
        System.out.println(tamanio);
        System.out.println(apellido);
        System.out.println(nombre);

       if (!error){
           database.nuevoAlumno(nombre, apellido, sexo, tamanio, solapas);
           final Dialog dialog = new Dialog(this); // Context, this, etc.
           dialog.setContentView(R.layout.alert);
           dialog.setTitle("Se ha agregado correctamente a" + nombre + apellido);
           dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

               @Override
               public void onDismiss(DialogInterface dialog) {
                   finish();
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
               }
           });
           dialog.show();
       }

    }

}

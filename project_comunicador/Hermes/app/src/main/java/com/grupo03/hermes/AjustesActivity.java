package com.grupo03.hermes;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.grupo03.hermes.db.Database;

public class AjustesActivity extends AppCompatActivity {
    public String nombre, apellido, sexo, tamanio,  solapas;
   public int idAlumno;
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
        String tipo = getIntent().getStringExtra("tipo");
        if (tipo.equals("editarAlumno")){
            rellenarInputs(database);
            Button btn= (Button) findViewById(R.id.buttonAgregar);
            btn.setText("Editar");
            btn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    editarAlumno();
                }
            });
        }

        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void editarAlumno(){
        Database database = new Database(getApplicationContext());
        if (!getContentInputs()) {
            database.editarAlumno(this);
            mostrarAlerta("editado", nombre,apellido);
        }
    }
    public void agregarAlumno(View view){
        Database database = new Database(getApplicationContext());
       if (!getContentInputs()){
           database.nuevoAlumno(this);
           mostrarAlerta("creado", nombre, apellido);
       }

    }
    public void mostrarAlerta(String tipo, String nombre, String apellido){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.alert);
        dialog.setTitle("Se ha "+ tipo+ " correctamente a " + nombre + apellido);
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
    public void rellenarInputs(Database database){
        EditText nombre= (EditText) findViewById(R.id.inputNombre);
        this.idAlumno = getIntent().getIntExtra("alumno_id", 0);
        Cursor alumno= database.getAlumno(idAlumno);
        nombre.setText(alumno.getString(alumno.getColumnIndex("nombre")));
        EditText apellido= (EditText) findViewById(R.id.inputApellido);
        apellido.setText(alumno.getString(alumno.getColumnIndex("apellido")));

        String sexo= alumno.getString(alumno.getColumnIndex("sexo"));
        RadioButton radioSexo;
        if (sexo.equals("Masculino"))
            radioSexo= (RadioButton) findViewById(R.id.radioMasculino);
        else radioSexo= (RadioButton) findViewById(R.id.radioFemenino);
        radioSexo.setChecked(true);

        String categorias= alumno.getString(alumno.getColumnIndex("solapasHabilitadas"));
        CheckBox check;
        if (categorias.contains("Pista")){
            check= (CheckBox) findViewById(R.id.checkPista);
            check.setChecked(true);
        }
        if (categorias.contains("Establo")){
            check= (CheckBox) findViewById(R.id.checkEstablo);
            check.setChecked(true);
        }
        if (categorias.contains("Emociones")){
            check= (CheckBox) findViewById(R.id.checkEmociones);
            check.setChecked(true);
        }
        if (categorias.contains("Necesidades")){
            check= (CheckBox) findViewById(R.id.checkNecesidades);
            check.setChecked(true);
        }

        String tamanio= alumno.getString(alumno.getColumnIndex("tamanioPictograma"));
        RadioButton radioTamanio;
        if (tamanio.equals("Chico")) radioTamanio= (RadioButton) findViewById(R.id.radioChico);
        else if (tamanio.equals("Mediano")) radioTamanio= (RadioButton) findViewById(R.id.radioMediano);
        else radioTamanio= (RadioButton) findViewById(R.id.radioGrande);
        radioTamanio.setChecked(true);
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

    public boolean getContentInputs(){
        boolean error=false;
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
        tamanio= seleccionado2.getText().toString();
        return error;
    }
}

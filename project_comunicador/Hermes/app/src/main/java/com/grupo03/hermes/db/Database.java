package com.grupo03.hermes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.grupo03.hermes.AjustesActivity;
import com.grupo03.hermes.Pictograma;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by federico on 09/12/15.
 */
public class Database extends SQLiteAssetHelper {

    public static final String DB_NAME = "hermes88.db";
    public static final int DB_VERSION = 1;

    public Database(Context context){
        super(context, DB_NAME, null, 1);
    }
    public Cursor getAlumno(int idAlumno){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id", "nombre", "apellido", "sexo","tamanioPictograma", "solapasHabilitadas"};
        String sqlTables = "alumno";

        qb.setTables(sqlTables);
        qb.appendWhere("id" + "=" + idAlumno);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }
    public Cursor getAlumnos(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"0 _id","id", "nombre", "apellido", "sexo"};
        String sqlTables = "alumno";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }
    public void editarAlumno(AjustesActivity alumno){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.nombre);
        values.put("apellido", alumno.apellido);
        values.put("sexo", alumno.sexo);
        values.put("tamanioPictograma", alumno.tamanio);
        values.put("solapasHabilitadas", alumno.solapas);
        database.update("alumno", values, "id=" + alumno.idAlumno, null);
        database.close();
    }
    public void nuevoAlumno( AjustesActivity alumno){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.nombre);
        values.put("apellido", alumno.apellido);
        values.put("sexo", alumno.sexo);
        values.put("tamanioPictograma", alumno.tamanio);
        values.put("solapasHabilitadas", alumno.solapas);
        database.insert("alumno", null, values);
        database.close();
    }
    public void guardarConfiguracion(String ip, String puerto){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("configuracion", null, null);
        ContentValues values = new ContentValues();
        values.put("clave", "ipMonitor");
        values.put("valor", ip);
        database.insert("configuracion", null, values);
        ContentValues values2 = new ContentValues();
        values2.put("clave", "puertoMonitor");
        values2.put("valor", puerto);
        database.insert("configuracion", null, values2);
    }
    public Cursor getConfiguracion(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"2 _id", "clave", "valor"};
        String sqlTables = "configuracion";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

    public void addPendiente(String nombre, String apellido, String sexo, String idPictograma, String idContexto, String fecha, String hora){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombreAlumno", nombre);
        cv.put("apellidoAlumno", apellido);
        cv.put("sexoAlumno", sexo);
        cv.put("idPictograma", idPictograma);
        cv.put("idContexto", idContexto);
        cv.put("fecha", fecha);
        cv.put("hora", hora);
        cv.put("enviado", "false");

        db.insert("notificacionespendientes", null, cv);
    }

    public void setPendienteAsSent(String idPendiente) {
        Log.i("HERMES", "Setting as sent: " + idPendiente);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE notificacionespendientes SET enviado = 'true' WHERE id=" + idPendiente);
    }

    public Cursor getPendientes(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id", "nombreAlumno", "apellidoAlumno", "sexoAlumno", "idPictograma", "idContexto", "fecha", "hora", "enviado"};
        String sqlTables = "notificacionespendientes";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, "enviado = 'false'", null, null, null, null);
        c.moveToFirst();
        return c;
    }
    public ArrayList<Pictograma> recorrerCursor(Cursor c){
        c.moveToFirst();
        ArrayList<Pictograma> seleccionados= new ArrayList<Pictograma>();
        while (c.isAfterLast() == false) {
            String carpeta=(c.getString(c.getColumnIndex("carpeta")));
            String nombre= (c.getString(c.getColumnIndex("nombre")));
            String m= nombre.charAt(0)+""; m=m.toUpperCase();
            String nombre2=nombre.replaceFirst(nombre.charAt(0) + "", m);
            if (nombre.equals("triste")) nombre= "triste_f";
            if (nombre.contains("sed")) nombre2= "Sed";
            nombre2 = nombre2.replace(" ", "_");
            seleccionados.add(new Pictograma(nombre, carpeta, nombre + ".png", nombre2 + ".m4a"));

            c.moveToNext();
        }
        return seleccionados;
    }
    public ArrayList<Pictograma> getPictogramasFrom(int idAlumno){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sql="SELECT p.nombre, p.carpeta " +
                   "from pictograma_alumno p_a INNER JOIN pictograma p on (p_a.idPictograma = p.id)" +
                  " where p_a.idAlumno=?";

        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(idAlumno)});
        return recorrerCursor(c);
    }
    public ArrayList<Pictograma> getPictogramasCategoryAndIdAlumno(String categoria, int idAlumno){
        ArrayList<Pictograma> seleccionados= new ArrayList<Pictograma>();
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sql="SELECT p.id, p.nombre, p.carpeta " +
                "from pictograma p " +
                "left join pictograma_alumno p_a on (p_a.idPictograma = p.id)" +
                ""+
                " where p.carpeta=? and p_a.idAlumno=" + idAlumno;
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(categoria)});
        return recorrerCursor(c);
    }
    public ArrayList<Pictograma> getPictogramasCategory(String categoria){
        ArrayList<Pictograma> seleccionados= new ArrayList<Pictograma>();
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sql="SELECT p.id, p.nombre, p.carpeta " +
                "from pictograma p " +
                " where p.carpeta=?";

        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(categoria)});
        return recorrerCursor(c);
    }

    public String getSolapasHabilitadas(int idAlumno){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sql="SELECT a.solapasHabilitadas " +
                "from alumno a " +
                " where a.id=?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(idAlumno)});
        c.moveToFirst();
        return   (c.getString(c.getColumnIndex("solapasHabilitadas")));
    }
}

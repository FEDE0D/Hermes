package com.grupo03.hermes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by federico on 09/12/15.
 */
public class Database extends SQLiteAssetHelper {

    public static final String DB_NAME = "hermes.db";
    public static final int DB_VERSION = 1;

    public Database(Context context){
        super(context, DB_NAME, null, 1);
    }

    public Cursor getAlumnos(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"0 _id", "nombre", "apellido", "sexo"};
        String sqlTables = "alumno";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }
    public void nuevoAlumno( String nombre, String apellido,String sexo, String tamanio, String solapas){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("sexo", sexo);
        values.put("tamanioPictograma", tamanio);
        values.put("solapasHabilitadas", solapas);
        database.insert("alumno", null, values);
        database.close();
    }
    public void guardarConfiguracion(String ip, String puerto){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("configuracion",null,null);
        ContentValues values = new ContentValues();
        values.put("clave", "ipMonitor");
        values.put("valor", ip);
        database.insert("configuracion", null, values);
        ContentValues values2 = new ContentValues();
        values.put("clave", "ipPuerto");
        values.put("valor", puerto);
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

}

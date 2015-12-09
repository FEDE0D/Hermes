package com.grupo03.hermes;

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

    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;

    public Database(Context context){
        super(context, DB_NAME, null, 1);
    }

    public Cursor getAlumnos(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"0 _id", "nombre", "apellido", "sexo"};
        String sqlTables = "paciente";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();

        return c;
    }

}

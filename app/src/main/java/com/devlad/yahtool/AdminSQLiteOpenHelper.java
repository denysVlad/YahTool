package com.devlad.yahtool;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denys Vasquez on 14/05/2018.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table motos(idMoto integer primary key autoincrement,nombre text,marca text,año text,kilometraje integer,modelo text)");
        db.execSQL("create table mantenimiento(idMantenimiento integer primary key autoincrement,idMoto integer,mantenimiento text,kilometraje INTEGER,fecha date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  List<List<String>>  extraerMotos(){

        List<List<String>> motos = new ArrayList<List<String>>();

        String selectQuery = "SELECT  * FROM motos";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        motos.add(new ArrayList<String>());
        motos.add(new ArrayList<String>());
        motos.add(new ArrayList<String>());
        motos.add(new ArrayList<String>());
        motos.add(new ArrayList<String>());
        motos.add(new ArrayList<String>());

        if (cursor.moveToFirst()) {
            do {

                motos.get(0).add(cursor.getString(0));
                motos.get(1).add(cursor.getString(1));
                motos.get(2).add(cursor.getString(2));
                motos.get(3).add(cursor.getString(3));
                motos.get(4).add(cursor.getString(4));
                motos.get(5).add(cursor.getString(5));

            } while (cursor.moveToNext());
        }

        Log.e("Registros",motos.toString());
        cursor.close();
        db.close();

        return motos;
    }
    public  List<List<String>>  extraerMantenimientos(String id){

        List<List<String>> mante = new ArrayList<List<String>>();

        String selectQuery = "SELECT  * FROM mantenimiento where idMoto = " + id;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        mante.add(new ArrayList<String>());
        mante.add(new ArrayList<String>());
        mante.add(new ArrayList<String>());
        mante.add(new ArrayList<String>());
        mante.add(new ArrayList<String>());



        if (cursor.moveToFirst()) {
            do {
                mante.get(0).add(cursor.getString(0));
                mante.get(1).add(cursor.getString(1));
                mante.get(2).add(cursor.getString(2));
                mante.get(3).add(cursor.getString(3));
                mante.get(4).add(cursor.getString(4));


            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return mante;
    }

}

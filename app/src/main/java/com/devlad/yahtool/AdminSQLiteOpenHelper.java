package com.devlad.yahtool;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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


        if (cursor.moveToFirst()) {
            do {
                motos.add(new ArrayList<String>());
                motos.add(new ArrayList<String>());
                motos.get(0).add(cursor.getString(0));
                motos.get(1).add(cursor.getString(1));

            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();

        return motos;
    }

}

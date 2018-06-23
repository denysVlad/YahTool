package com.devlad.yahtool;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class ActivityNuevo extends AppCompatActivity {

    AdminSQLiteOpenHelper admin;
    SQLiteDatabase dbMante;
    String id;
    String nombre;

    Context cont;
    Button button;
    EditText edit8;
    TextView tx1;
    private String title;
    private int page;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        cont = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("nombre"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Log.e("naya ", getIntent().getExtras().getString("id") + " " + getIntent().getExtras().getString("nombre"));
        id = getIntent().getExtras().getString("id");
        admin = new AdminSQLiteOpenHelper(cont, "mantenimiento", null, 1);
        dbMante = admin.getWritableDatabase();


        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        edit8 = findViewById(R.id.editText8);
        edit8.setText(currentDate.format(todayDate));

        button = findViewById(R.id.buttonG);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(id);
            }
        });
    }

    //    @Override
//    public boolean onSupportNavigateUp() {
//        Intent resultData = new Intent();
//        resultData.putExtra("Test", "0");
//        setResult(Activity.RESULT_OK, resultData);
//        finish();
//        return true;
//    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // back button
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    public void guardar(String id) {
        ContentValues registro = new ContentValues();
        EditText mante = findViewById(R.id.editText61);
        EditText klm = findViewById(R.id.editText6);
        EditText fecha = findViewById(R.id.editText8);

        if (mante.getText().toString().equals("") || fecha.getText().toString().equals("")) {
            new SweetAlertDialog(cont, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Faltas datos para poder guardar :(")
                    .show();
        } else {
            registro.put("idMoto", id);
            registro.put("mantenimiento", mante.getText().toString());
            registro.put("kilometraje", klm.getText().toString());
            registro.put("fecha", fecha.getText().toString());
            mante.setText("");
            klm.setText("");

            dbMante.insert("mantenimiento", null, registro);

            new SweetAlertDialog(cont)
                    .setTitleText("Mantenimiento guardado! :D")
                    .show();

        }

    }

}
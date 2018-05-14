package com.devlad.yahtool;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityMismotos extends AppCompatActivity {
    AdminSQLiteOpenHelper admin;
    SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mismotos);
        admin = new AdminSQLiteOpenHelper(this,
                "motos", null, 1);
        bd = admin.getWritableDatabase();
    }
    public void guardar(View v)
    {

        ContentValues registro = new ContentValues();
        EditText año = (EditText)findViewById(R.id.editText);
        EditText modelo = (EditText)findViewById(R.id.editText3);
        EditText nombre = (EditText)findViewById(R.id.editText4);
        EditText kilometraje = (EditText)findViewById(R.id.editText2);

        if (año.getText().toString() != "" || modelo.getText().toString() != "" || nombre.getText().toString() != "" || kilometraje.getText().toString() != "")
        {
            registro.put("nombre", nombre.getText().toString());
            registro.put("marca", ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString());
            registro.put("año", año.getText().toString());
            registro.put("kilometraje", kilometraje.getText().toString());
            registro.put("modelo", modelo.getText().toString());

            bd.insert("motos", null, registro);

            Toast.makeText(getBaseContext(),"Moto guardada! :D",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getBaseContext(),"Faltas datos para poder guardar la moto :(",Toast.LENGTH_SHORT).show();
        }


    }
}

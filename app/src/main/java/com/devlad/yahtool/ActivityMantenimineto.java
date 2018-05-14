package com.devlad.yahtool;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityMantenimineto extends AppCompatActivity {
    Spinner spinner;
    AdminSQLiteOpenHelper db;
    List<String> lables;
    List<List<String>> motos;

    EditText edit8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimineto);
        spinner = (Spinner) findViewById(R.id.spinner2);

        db = new AdminSQLiteOpenHelper(this,
                "motos", null, 1);


        motos = db.extraerMotos();


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, motos.get(1) );


        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_item);


        spinner.setAdapter(dataAdapter);
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        edit8 = (EditText)findViewById(R.id.editText8);
        edit8.setText(currentDate.format(todayDate));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE); /* if you want your item to be white */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void guardarMante(View v)
    {
        Toast.makeText(getBaseContext(),"" +  motos.get(0).get(spinner.getSelectedItemPosition()) ,Toast.LENGTH_SHORT ).show();
    }
}

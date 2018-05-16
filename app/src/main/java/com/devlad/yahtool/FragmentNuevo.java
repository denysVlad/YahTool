package com.devlad.yahtool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class FragmentNuevo extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    AdminSQLiteOpenHelper db;
    AdminSQLiteOpenHelper admin;
    SQLiteDatabase dbMante;

    List<List<String>> motos;
    Spinner spinner;
    Context cont;
    Button button;
EditText edit8;
    TextView tx1;
    // newInstance constructor for creating fragment with arguments
    public static FragmentNuevo newInstance(int page, String title) {
        FragmentNuevo fragmentFirst = new FragmentNuevo();
        return fragmentFirst;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cont = getActivity();
        db = new AdminSQLiteOpenHelper(cont,"motos", null, 1);

        admin = new AdminSQLiteOpenHelper(cont,"mantenimiento", null, 1);
        dbMante = admin.getWritableDatabase();

        motos = db.extraerMotos();
        tx1 = (TextView)getView().findViewById(R.id.textView22);
        if (motos.get(0).size() > 0)
        {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(cont,android.R.layout.simple_spinner_item, motos.get(1) );
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinner = (Spinner)getView(). findViewById(R.id.spinner2);
            spinner.setAdapter(dataAdapter);
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
        else
        {
            tx1.setText("No hay motos");
            button.setEnabled(false);
            new SweetAlertDialog(cont, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("No hay motos guardadas :(")
                    .show();
        }
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        edit8 = (EditText)getView().findViewById(R.id.editText8);
        edit8.setText(currentDate.format(todayDate));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nuevo_mantenimiento, container, false);
        cont = container.getContext() ;
        button = (Button) view.findViewById(R.id.buttonG);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                guardar();
            }
        });
        return view;

    }
        public void guardar()
        {
            ContentValues registro = new ContentValues();
            EditText mante = (EditText)getView().findViewById(R.id.editText61);
            EditText klm = (EditText)getView().findViewById(R.id.editText6);
            EditText fecha = (EditText)getView().findViewById(R.id.editText8);

            if (mante.getText().toString().equals("") || fecha.getText().toString().equals(""))
            {
                 new SweetAlertDialog(cont, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Faltas datos para poder guardar :(")
                    .show();
            }
            else
            {
                registro.put("idMoto", motos.get(0).get(spinner.getSelectedItemPosition()));
                registro.put("mantenimiento",mante.getText().toString() );
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
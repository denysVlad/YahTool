package com.devlad.yahtool;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class FragmentNuevo extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    AdminSQLiteOpenHelper db;
    List<List<String>> motos;
    Spinner spinner;
    Context cont;
EditText edit8;
    TextView tx1;
    // newInstance constructor for creating fragment with arguments
    public static FragmentNuevo newInstance(int page, String title) {
        FragmentNuevo fragmentFirst = new FragmentNuevo();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Add this here:
        cont = getActivity(); //use the instance variable
        db = new AdminSQLiteOpenHelper(cont,"motos", null, 1);
        motos = db.extraerMotos();
        tx1 = (TextView)getView().findViewById(R.id.textView22);
        if (motos.size() > 0)
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

        }
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        edit8 = (EditText)getView().findViewById(R.id.editText8);
        edit8.setText(currentDate.format(todayDate));

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_nuevo, container, false);
        cont = container.getContext() ;
//        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
//        tvLabel.setText(page + " -- " + title);

        Button button = (Button) view.findViewById(R.id.buttonG);
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

            if (mante.getText().toString() != "" || klm.getText().toString() != "" || fecha.getText().toString() != "")
            {
                registro.put("idMoto", motos.get(0).get(spinner.getSelectedItemPosition()));
                registro.put("mantenimiento",mante.getText().toString() );
                registro.put("kilometraje", klm.getText().toString());
                registro.put("fecha", fecha.getText().toString());

                db.getWritableDatabase().insert("mantenimiento", null, registro);

                Toast.makeText(cont,"Mantenimiento guardado! :D",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(cont,"Faltas datos para poder guardar :(",Toast.LENGTH_SHORT).show();
            }

        }

}
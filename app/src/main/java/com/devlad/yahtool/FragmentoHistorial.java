package com.devlad.yahtool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FragmentoHistorial extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    AdminSQLiteOpenHelper dbMotos;
    AdminSQLiteOpenHelper dbMante;
    List<List<String>> motos;
    List<List<String>> mantenimientoBD;
    ArrayList<Mantenimientos> mantenimiento;
    Spinner spinner;
    Context cont;
    ListView list;

    TextView tx1;
    // newInstance constructor for creating fragment with arguments
    public static FragmentoHistorial newInstance(int page, String title) {
        FragmentoHistorial fragmentFirst = new FragmentoHistorial();
//        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
//        fragmentFirst.setArguments(args);
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
        dbMotos = new AdminSQLiteOpenHelper(cont,"motos", null, 1);
        dbMante = new AdminSQLiteOpenHelper(cont,"mantenimiento", null, 1);
        motos = dbMotos.extraerMotos();

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
        list = (ListView)getView().findViewById(R.id.List);

        mantenimientoBD = dbMante.extraerMantenimientos(motos.get(0).get(spinner.getSelectedItemPosition()));

        AdapterViewCustom adapter = new AdapterViewCustom(this, mantenimiento);
        list.setAdapter(adapter);

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento_historial, container, false);
        cont = container.getContext() ;
        return view;
    }
    public void extraerMante(String id)
    {


    }
    public class Mantenimientos {

        public String fecha;
        public String mantenimiento;
        public String klm;

        public Mantenimientos(String fecha, String mante, String klm) {
            this.fecha = fecha;
            this.mantenimiento = mante;
            this.klm = klm;
        }

    }
    public class AdapterViewCustom extends BaseAdapter {

        private Activity context_1;

        private ArrayList<Mantenimientos> Man;
        //List<List<String>>
        public AdapterViewCustom(Activity context,ArrayList<Mantenimientos> mantenimiento) {
            context_1 = context;
            this.Man = mantenimiento;
        }

        @Override
        public int getCount() {
            return Man.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(context_1).inflate(
                        R.layout.lay_adapter_manten, null);
                viewHolder = new ViewHolder();

                viewHolder.txt = (TextView) convertView
                        .findViewById(R.id.textView28);
                viewHolder.txt1 = (TextView) convertView
                        .findViewById(R.id.textView27);
                viewHolder.txt2 = (TextView) convertView
                        .findViewById(R.id.textView26);

                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txt.setText(Man.get(position).fecha);
            viewHolder.txt1.setText(Man.get(position).mantenimiento );
            viewHolder.txt2.setText(Man.get(position).klm);

            return convertView;
        }

        public class ViewHolder {
            public TextView txt;
            public TextView txt1;
            public TextView txt2;

        }
    }
}
package com.devlad.yahtool;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;


public class FragmentoHistorial extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    AdminSQLiteOpenHelper dbMotos;
    AdminSQLiteOpenHelper dbMante;


    List<List<String>> motos;
    List<List<String>> mantenimientoBD;
Button butReload;
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


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Add this here:
        cont = getActivity(); //use the instance variable
        dbMotos = new AdminSQLiteOpenHelper(cont,"motos", null, 1);
        dbMante = new AdminSQLiteOpenHelper(cont,"mantenimiento", null, 1);
        motos = dbMotos.extraerMotos();

        list = (ListView)getView().findViewById(R.id.List);
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
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                    extraerHistorial();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            extraerHistorial();
        }
        else
        {
            tx1.setText("No hay motos");

        }


    }
public void extraerHistorial()
{

    mantenimientoBD = dbMante.extraerMantenimientos(motos.get(0).get(spinner.getSelectedItemPosition()));

    if (mantenimientoBD.get(0).size() > 0) {
        AdapterViewCustom adapter = new AdapterViewCustom(cont, mantenimientoBD);
        list.setAdapter(adapter);
    }
    else
    {
        list.setAdapter(null);


    }
}
    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragmento_historial_mantenimiento, container, false);
        cont = container.getContext() ;
        butReload = view.findViewById(R.id.button3);
        butReload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
extraerHistorial();
            }
        });




        return view;
    }

    public class AdapterViewCustom extends BaseAdapter {

        Context context_1;

        List<List<String>> Man;
        //List<List<String>>
        public AdapterViewCustom(Context context, List<List<String>> mantenimiento) {
            context_1 = context;
            this.Man = mantenimiento;
        }

        @Override
        public int getCount() {
            return Man.get(0).size();
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

            viewHolder.txt.setText(Man.get(4).get(position));
            viewHolder.txt1.setText(Man.get(2).get(position) );
            viewHolder.txt2.setText(Man.get(3).get(position));

            RelativeLayout rL =(RelativeLayout)convertView.findViewById(R.id.layManten);
            rL.setOnClickListener(new View.OnClickListener()
                                  {
                                      @Override
                                      public void onClick(View v)
                                      {
                                          YoYo.with(Techniques.Pulse)
                                                  .duration(700)
                                                  .repeat(2)
                                                  .playOn(convertViewfindViewById(R.id.layManten));
                                      }
                                  }
            );
            return convertView;
        }

        public class ViewHolder {
            public TextView txt;
            public TextView txt1;
            public TextView txt2;

        }
    }
}
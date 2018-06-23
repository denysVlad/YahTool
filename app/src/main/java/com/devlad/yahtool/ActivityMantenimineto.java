package com.devlad.yahtool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ActivityMantenimineto extends AppCompatActivity {

    AdminSQLiteOpenHelper dbMante;
    List<List<String>> mantenimientoBD;
    Button butReload;
    Spinner spinner;
    Context cont;
    ListView list;
    String id;
    TextView tx1;
    String nombre;
    ViewPager vpPager;
    // Store instance variables
    private String title;
    private int page;
    // newInstance constructor for creating fragment with arguments
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento);
        cont = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mantenimientos de " + getIntent().getExtras().getString("nombre"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dbMante = new AdminSQLiteOpenHelper(cont, "mantenimiento", null, 1);


        list = findViewById(R.id.List);
        //tx1 = (TextView)findViewById(R.id.textView22);

        id = getIntent().getExtras().getString("id");

        nombre = getIntent().getExtras().getString("nombre");
        extraerHistorial(id);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMantenimineto.this, ActivityNuevo.class);
                intent.putExtra("id", id);
                intent.putExtra("nombre", nombre);
                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (data != null) {

            Log.e("naya ", id + " " + data);

            extraerHistorial(id);
        }
    }

    public void extraerHistorial(String id) {

        mantenimientoBD = dbMante.extraerMantenimientos(id);
        if (mantenimientoBD.get(0).size() > 0) {
            AdapterViewCustom adapter = new AdapterViewCustom(cont, mantenimientoBD);
            list.setAdapter(adapter);
        } else {
            list.setAdapter(null);


        }
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
            AdapterViewCustom.ViewHolder viewHolder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(context_1).inflate(
                        R.layout.lay_adapter_manten, null);
                viewHolder = new AdapterViewCustom.ViewHolder();

                viewHolder.txt = convertView
                        .findViewById(R.id.textView28);
                viewHolder.txt1 = convertView
                        .findViewById(R.id.textView27);
                viewHolder.txt2 = convertView
                        .findViewById(R.id.textView26);

                convertView.setTag(viewHolder);
            } else {

                viewHolder = (AdapterViewCustom.ViewHolder) convertView.getTag();
            }

            viewHolder.txt.setText(Man.get(4).get(position));
            viewHolder.txt1.setText(Man.get(2).get(position));
            viewHolder.txt2.setText(Man.get(3).get(position));

            RelativeLayout rL = convertView.findViewById(R.id.layManten);
//            rL.setOnClickListener(new View.OnClickListener()
//                                  {
//                                      @Override
//                                      public void onClick(View v)
//                                      {
//                                          YoYo.with(Techniques.Pulse)
//                                                  .duration(700)
//                                                  .repeat(2)
//            //                                      .playOn(convertViewfindViewById(R.id.layManten));
//                                      }
//                                  }
//            );
            return convertView;
        }

        public class ViewHolder {
            public TextView txt;
            public TextView txt1;
            public TextView txt2;

        }
    }
}

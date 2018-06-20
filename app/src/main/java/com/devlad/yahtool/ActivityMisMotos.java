package com.devlad.yahtool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityMisMotos extends AppCompatActivity {
    AdminSQLiteOpenHelper admin;
    SQLiteDatabase bd;

    AdminSQLiteOpenHelper adminMan;
    SQLiteDatabase bdMan;

    ScrollView lynew;
    ListView list;
    List<List<String>> motos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mismotos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lynew = (ScrollView)findViewById(R.id.layNew);
        list = (ListView)findViewById(R.id.listMotos);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                YoYo.with(Techniques.Bounce)
                        .duration(700)
                        .repeat(1)
                        .playOn(findViewById(R.id.layNew));
                lynew.setVisibility(View.VISIBLE );

            }
        });

        extraerHistorial();
    }
    public void ocultar(View v)
    {
        EditText año = (EditText)findViewById(R.id.editText);
        EditText modelo = (EditText)findViewById(R.id.editText3);
        EditText nombre = (EditText)findViewById(R.id.editText4);
        EditText kilometraje = (EditText)findViewById(R.id.editText2);
        lynew.setVisibility(View.INVISIBLE );
        nombre.setText("");
        modelo.setText("");
        kilometraje.setText("");
        año.setText("");
    }
    public void guardar(View v)
    {
        admin = new AdminSQLiteOpenHelper(this,
                "motos", null, 1);
        bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        EditText año = (EditText)findViewById(R.id.editText);
        EditText modelo = (EditText)findViewById(R.id.editText3);
        EditText nombre = (EditText)findViewById(R.id.editText4);
        EditText kilometraje = (EditText)findViewById(R.id.editText2);

        if (año.getText().toString().equals("") || modelo.getText().toString().equals("")|| nombre.getText().toString().equals("") || kilometraje.getText().toString().equals(""))
        {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Faltas datos para poder guardar :(")
                    .show();
        }
        else
        {
            registro.put("nombre", nombre.getText().toString());
            registro.put("marca", ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString());
            registro.put("año", año.getText().toString());
            registro.put("kilometraje", kilometraje.getText().toString());
            registro.put("modelo", modelo.getText().toString());
            bd.insert("motos", null, registro);
            nombre.setText("");
            modelo.setText("");
            kilometraje.setText("");
            año.setText("");
            new SweetAlertDialog(this)
                    .setTitleText("Moto guardada! :D")
                    .show();
            ocultar(null);
            extraerHistorial();

        }


    }
    public class AdapterViewCustomMotos extends BaseAdapter {

        Context context_1;

        List<List<String>> motos;
        //List<List<String>>
        public AdapterViewCustomMotos(Context context, List<List<String>> motos) {
            context_1 = context;
            this.motos = motos;
        }

        @Override
        public int getCount() {
            return motos.get(0).size();
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
                        R.layout.lay_adapter_motos, null);
                viewHolder = new ViewHolder();

                viewHolder.txt = (TextView) convertView
                        .findViewById(R.id.txtnombre);
                viewHolder.txt1 = (TextView) convertView
                        .findViewById(R.id.txtmarca);
                viewHolder.txt2 = (TextView) convertView
                        .findViewById(R.id.txtaño);
                viewHolder.txt3 = (TextView) convertView
                        .findViewById(R.id.txtklm );
                viewHolder.txt4 = (TextView) convertView
                        .findViewById(R.id.txtmodelo);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }
            Log.e("Registros",motos.toString());

            viewHolder.txt.setText(motos.get(1).get(position));
            viewHolder.txt1.setText(motos.get(2).get(position));
            viewHolder.txt2.setText(motos.get(3).get(position));
            viewHolder.txt3.setText(motos.get(4).get(position).toString());
            viewHolder.txt4.setText(motos.get(5).get(position));

            admin = new AdminSQLiteOpenHelper(context_1 ,
                    "motos", null, 1);

            final SQLiteDatabase db = admin.getReadableDatabase();

           RelativeLayout lL = (RelativeLayout)convertView.findViewById(R.id.layAdapterMoto);
            lL.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          YoYo.with(Techniques.Pulse)
                                                  .duration(700)
                                                  .repeat(2)
                                                  .playOn(findViewById(R.id.layAdapterMoto));
                                      }
                                  }
            );





            Button btn = (Button)convertView.findViewById(R.id.button4);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View parentRow = (View) view.getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    final int position = listView.getPositionForView(parentRow);

                    new SweetAlertDialog(context_1, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Estas seguro?")
                            .setContentText("Se eliminara permanente!")
                            .setConfirmText("Si, Eliminar!")
                            .setConfirmClickListener(
                                    new SweetAlertDialog.OnSweetClickListener() {

                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Hecho!")
                                            .setContentText("Eya se ah eliminado!")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                    db.execSQL("DELETE FROM motos WHERE idMoto="+motos.get(0).get(position));
                                    db.execSQL("DELETE FROM mantenimiento WHERE idMoto="+motos.get(0).get(position));
                                    extraerHistorial();
                                }
                            })
                            .show();
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(2)
                            .playOn(findViewById(R.id.button4));

                }
            });

            return convertView;
        }

        public class ViewHolder {
            public TextView txt;
            public TextView txt1;
            public TextView txt2;
            public TextView txt3;
            public TextView txt4;

        }
    }

    public void extraerHistorial()

    {
        admin = new AdminSQLiteOpenHelper(this,
                "motos", null, 1);
        motos = admin.extraerMotos();

        if (motos.get(0).size() > 0) {
            AdapterViewCustomMotos adapter = new AdapterViewCustomMotos(this, motos);
            list.setAdapter(adapter);
        }
        else
        {
            list.setAdapter(null);


        }
    }

}

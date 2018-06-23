package com.devlad.yahtool;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

public class ActivityKawa extends AppCompatActivity {
    Context cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kawa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cont = this;

        final Switch switchAB = findViewById(R.id.actionbar_switch3);
        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ArrayAdapter<CharSequence> adapt;
                ListView lv = findViewById(R.id.listKawa);
                if (switchAB.isChecked()) {

                    adapt = ArrayAdapter.createFromResource(cont, R.array.kawaEnglish, android.R.layout.simple_list_item_1);
                } else {

                    adapt = ArrayAdapter.createFromResource(cont, R.array.kawaEspañol, android.R.layout.simple_list_item_1);
                }

                adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lv.setAdapter(adapt);
                adapt.notifyDataSetChanged();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

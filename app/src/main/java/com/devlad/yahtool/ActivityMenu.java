package com.devlad.yahtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cn.pedant.SweetAlert.SweetAlertDialog;

//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;


public class ActivityMenu extends AppCompatActivity {



    // todo API_KEY should not be stored in plain sight
    private static final String API_KEY = "MY_API_KEY";
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

          ImageView img = (ImageView) findViewById(R.id.imageView2);
          img.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Intent = new Intent(view.getContext(), ActivityYamaha.class );
                  view.getContext().startActivity(Intent);}
          });
          ImageView img2 = (ImageView) findViewById(R.id.imageView4);
          img2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Intent = new Intent(view.getContext(), MainActivity .class );
                  view.getContext().startActivity(Intent);}
          });
          ImageView img3 = (ImageView) findViewById(R.id.imageView3);
          img3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Intent = new Intent(view.getContext(), ActivityMantenimineto.class );
                  view.getContext().startActivity(Intent);}
          });
          ImageView img4 = (ImageView) findViewById(R.id.imageView);
          img4.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  new SweetAlertDialog(view.getContext())
                          .setTitleText("Coming Soon!")
                          .show();
              }
          });

    }




}

package com.devlad.yahtool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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

          ImageView img = findViewById(R.id.imageView2);

          img.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Intent = new Intent(view.getContext(), ActivityYamaha.class );
                  view.getContext().startActivity(Intent);
                  YoYo.with(Techniques.Pulse)
                          .duration(700)
                          .repeat(2)
                          .playOn(findViewById(R.id.imageView2));
              }
          });
          ImageView img2 = findViewById(R.id.imageView4);

          img2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Intent = new Intent(view.getContext(), ActivityMisMotos.class );
                  view.getContext().startActivity(Intent);
                  YoYo.with(Techniques.Pulse)
                          .duration(700)
                          .repeat(2)
                          .playOn(findViewById(R.id.imageView4));
              }
          });


          ImageView img4 = findViewById(R.id.imageView);
          img4.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Intent = new Intent(view.getContext(), ActivityKawa.class);
                  view.getContext().startActivity(Intent);
                  YoYo.with(Techniques.Pulse)
                          .duration(700)
                          .repeat(2)
                          .playOn(findViewById(R.id.imageView));

              }
          });


      }




}

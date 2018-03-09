package com.devlad.yahtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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

          ImageView img = (ImageView) findViewById(R.id.imageView);
          img.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Intent = new Intent(view.getContext(), ActivityDetailCheck.class);
                  view.getContext().startActivity(Intent);}
          });
          ImageView img2 = (ImageView) findViewById(R.id.imageView2);
          img2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent Intent = new Intent(view.getContext(), ActivityDiag.class);
                  view.getContext().startActivity(Intent);}
          });

    }




}

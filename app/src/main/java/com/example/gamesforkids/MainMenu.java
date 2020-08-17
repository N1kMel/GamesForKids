package com.example.gamesforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainMenu extends AppCompatActivity  {
        int X, Y;
        ImageButton game1, game2, game3, game4;
        ImageButton fl;
        ViewGroup background;
        int W ,H;
        Button test;

        static boolean soundSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
         W = displaymetrics.widthPixels;
         H = displaymetrics.heightPixels;

         background = findViewById(R.id.backMenu);
       //  background.setOnTouchListener((View.OnTouchListener) MainMenu.this);




         game1 = findViewById(R.id.Trees);
         game1.setLayoutParams(new LinearLayout.LayoutParams ((int) (H/2.5) , (int) (H/2.5)));
       // game1.setOnTouchListener((View.OnTouchListener) MainMenu.this);
         game2 = findViewById(R.id.game2);
         game2.setLayoutParams(new LinearLayout.LayoutParams((int) (H/2.5) , (int) (H/2.5)));

         game3 = findViewById(R.id.game3);
         game3.setLayoutParams(new LinearLayout.LayoutParams((int) (H/2.5) , (int) (H/2.5)));

         game4 = findViewById(R.id.game4);
         game4.setLayoutParams(new LinearLayout.LayoutParams((int) (H/2.5) , (int) (H/2.5)));

        Functions.ArrayMemSet(Functions.a);
        Functions.ArrayMemSet(Functions.arr);

        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Forest.class);
                startActivity(intent);
                finish();
                 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Savannah.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

    }
/*
        public boolean onTouch(View v, MotionEvent event) {
            X = (int) event.getX();
            Y = (int) event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if(Functions.isNearTouchTo(X,  Y, game1, (int) (2.3*W))){
                        X = 0;Y = 0;
                        Intent intent = new Intent(MainMenu.this, TreesAndAnimals.class);
                        startActivity(intent);
                        finish();
                       // overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    }
                    // нажатие
                    break;

                case MotionEvent.ACTION_UP:

                    X = 0;
                    Y = 0;
                    break;
            }

            return true;
        }

 */
    }
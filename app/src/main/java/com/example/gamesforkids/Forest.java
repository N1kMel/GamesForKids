package com.example.gamesforkids;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Forest extends AppCompatActivity implements View.OnTouchListener {
   private ImageView obstacle1;
   private ImageView obstacle2;
   private ImageView obstacle3;
   private ImageView animal1;
   private ImageView animal2;
   private ImageView animal3;
    Thread AudioAndAnimat;
    ImageView searchPlatform, AnimalWhatUSearch;
    ImageButton backToMenu;
    MediaPlayer bear, fox, hedge, hog, moose, mouse, rabbit, raccon, squi, wolf, congrat, congrat1, congrat2;
    @SuppressLint("ResourceType")
    int X, i, Y;
    private ViewGroup mMoveLayout;
    boolean go = false;

    public boolean onTouch(View v, MotionEvent event) {
        X = (int) event.getX();
        Y = (int) event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                // нажатие
                break;

            case MotionEvent.ACTION_UP:
                X = 0;  Y = 0;
                break;
        }

        return true;
    }

    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trees_and_animals);

       bear = MediaPlayer.create(this, R.raw.bear);
        fox = MediaPlayer.create(this, R.raw.fox);
        hedge = MediaPlayer.create(this, R.raw.hedgehog);
        hog = MediaPlayer.create(this, R.raw.hog);
        moose = MediaPlayer.create(this, R.raw.moose);
      mouse = MediaPlayer.create(this, R.raw.mouse);
       rabbit = MediaPlayer.create(this, R.raw.rabbit);
       raccon = MediaPlayer.create(this, R.raw.raccon);
       squi = MediaPlayer.create(this, R.raw.squi);
        wolf = MediaPlayer.create(this, R.raw.wolf);
      congrat = MediaPlayer.create(this, R.raw.congrat);
       congrat1 = MediaPlayer.create(this, R.raw.congrat3);
       congrat2 = MediaPlayer.create(this, R.raw.congrat4);

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        final int W = displaymetrics.widthPixels;
        final int H = displaymetrics.heightPixels;

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mMoveLayout = (ViewGroup) findViewById(R.id.background);
        mMoveLayout.setOnTouchListener((View.OnTouchListener) Forest.this);

        obstacle1 = findViewById(R.id.tree);
        obstacle2 = findViewById(R.id.tree2);
        obstacle3 = findViewById(R.id.tree3);

        backToMenu = findViewById(R.id.back);
        backToMenu.setX(10);
        backToMenu.setY(10);

        AnimalWhatUSearch = findViewById(R.id.animalSearch);
        AnimalWhatUSearch.setLayoutParams(new RelativeLayout.LayoutParams((int) (H / 3.8), (int) (H / 3.8)));

        searchPlatform = findViewById(R.id.search);
        searchPlatform.setLayoutParams(new RelativeLayout.LayoutParams((int) (H / 3.5), (int) (H / 3.5)));

        animal1 = findViewById(R.id.raccoon);
        animal2 = findViewById(R.id.bear);
        animal3 = findViewById(R.id.fox);

        AnimalWhatUSearch.setX(W/2-H/7+10);
        AnimalWhatUSearch.setY(5*H / 7);
        searchPlatform.setX(W/2-H/7);
        searchPlatform.setY(5*H / 7);

        final int sizeAnimals = (int) (W / 3.9);
        final int sizeObstacles = (int) (W / 3.1);

        Functions.setRightLayoutsParams(animal1, animal2, animal3, sizeAnimals);
        Functions.setRightLayoutsParams(obstacle1, obstacle2, obstacle3, sizeObstacles);

        final HashMap<Integer, Integer> ObstaclesMap = new HashMap<>();
        ObstaclesMap.put(0, R.drawable.tree_normal);
        ObstaclesMap.put(1, R.drawable.tree_normal);
        ObstaclesMap.put(2, R.drawable.tree_normal);
        ObstaclesMap.put(3, R.drawable.busht);
        ObstaclesMap.put(4, R.drawable.busht);
        ObstaclesMap.put(5, R.drawable.busht);

        final HashMap<Integer, Integer> AnimalsMap = new HashMap<>();
        AnimalsMap.put(0, R.drawable.raccoon);
        AnimalsMap.put(1, R.drawable.bear_finish);
        AnimalsMap.put(2, R.drawable.fox);
        AnimalsMap.put(3, R.drawable.woolf2);
        AnimalsMap.put(4, R.drawable.hedgehog2);
        AnimalsMap.put(5, R.drawable.hog5);
        AnimalsMap.put(6, R.drawable.moose);
        AnimalsMap.put(7, R.drawable.mouse);
        AnimalsMap.put(8, R.drawable.rabbit);
        AnimalsMap.put(9, R.drawable.squirrel6);

        final HashMap<Integer, MediaPlayer> NameOfSearchAnimal = new HashMap<>();
        NameOfSearchAnimal.put(R.drawable.raccoon, raccon);
        NameOfSearchAnimal.put(R.drawable.bear_finish, bear);
        NameOfSearchAnimal.put(R.drawable.fox, fox);
        NameOfSearchAnimal.put(R.drawable.woolf2, wolf);
        NameOfSearchAnimal.put(R.drawable.hedgehog2, hedge);
        NameOfSearchAnimal.put(R.drawable.hog5, hog);
        NameOfSearchAnimal.put(R.drawable.moose, moose);
        NameOfSearchAnimal.put(R.drawable.mouse, mouse);
        NameOfSearchAnimal.put(R.drawable.rabbit, rabbit);
        NameOfSearchAnimal.put(R.drawable.squirrel6, squi);

        Functions.CreateSceneObstacles(obstacle1, obstacle2, obstacle3, ObstaclesMap);

        Functions.CreateSceneAnimals(animal1, animal2, animal3, AnimalsMap);

        Functions.setSceneCoordinates(obstacle1, obstacle2, obstacle3, animal1, animal2, animal3, H, W);

        final ArrayList<ImageView> SeachAnimal = new ArrayList<>();
        SeachAnimal.add(animal1);
        SeachAnimal.add(animal2);
        SeachAnimal.add(animal3);
        Collections.shuffle(SeachAnimal);

        AnimalWhatUSearch.setImageResource((Integer) SeachAnimal.get(0).getTag());

        try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        NameOfSearchAnimal.get(SeachAnimal.get(0).getTag()).start();

                final Timer MainTimer = new Timer();
                MainTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if ( i < 3 && Functions.isNearTouchTo(X, Y, SeachAnimal.get(i), W))
                                {
                                    SeachAnimal.get(i).setImageResource(R.drawable.null_picture);
                                    i++;

                                      if (i < 3)
                                      {
                                          NameOfSearchAnimal.get(SeachAnimal.get(i).getTag()).start();
                                          AnimalWhatUSearch.setImageResource((Integer) SeachAnimal.get(i).getTag());
                                      }
                                }

                                if (go)
                                {
                                        X = 0; Y = 0;

                                        Functions.CreateSceneObstacles(obstacle1, obstacle2, obstacle3, ObstaclesMap);

                                        Functions.CreateSceneAnimals(animal1, animal2, animal3, AnimalsMap);

                                        Collections.shuffle(SeachAnimal);

                                         AnimalWhatUSearch.setImageResource((Integer) SeachAnimal.get(0).getTag());
                                         NameOfSearchAnimal.get(SeachAnimal.get(0).getTag()).start();
                                         i = 0; go = false;
                                }


            }
        });
    }

    },1000,15);



        final Thread AudioAndAnimat = new Thread(new Runnable() {
            @Override
            public void run() {
                final Timer AnimationTimer = new Timer();
                AnimationTimer.schedule(new TimerTask() {
                    @Override
                    public void run()
                    {

                        if (i == 3)
                        {
                            Functions.RandomCongrat(congrat1, congrat2, congrat).start();

                            if (!Functions.getRandomCongrat(congrat1, congrat2, congrat).isPlaying())
                            {
                                try { Thread.sleep(1100); } catch (InterruptedException e) { e.printStackTrace(); }
                                go = true; i = 0;
                            }
                        }

                    }
                }, 1000, 5);
            }
        });

        AudioAndAnimat.start();

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forest.this, MainMenu.class);
                startActivity(intent);

                fox.release();bear.release();hedge.release();hog.release();
                moose.release();mouse.release();rabbit.release();raccon.release();
                squi.release();wolf.release();congrat.release();

                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            });


}
}





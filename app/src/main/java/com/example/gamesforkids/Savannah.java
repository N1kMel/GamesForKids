package com.example.gamesforkids;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuWrapperICS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Savannah extends AppCompatActivity implements View.OnTouchListener
{
    private ImageView obstacle1;
    private ImageView obstacle2;
    private ImageView obstacle3;
    private ImageView animal1;
    private ImageView animal2;
    private ImageView animal3;
    private ImageView searchPlatform, AnimalWhatUSearch;
    ImageButton backToMenu;
    int X, i, Y;
    private ViewGroup mMoveLayout;
    boolean go = false;
    MediaPlayer antelope,cheetah, elephant, giraffe,  rhino, hippopotamus, hyena, lion, ostrich, zebra, congrat, congrat1, congrat2;

    @Override
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

        return false;

    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savannah);

        antelope = MediaPlayer.create(this, R.raw.anthelope);
        cheetah = MediaPlayer.create(this, R.raw.chetah);
        elephant = MediaPlayer.create(this, R.raw.elephant);
        giraffe = MediaPlayer.create(this, R.raw.girafe);
        rhino = MediaPlayer.create(this, R.raw.nosor);
        hippopotamus = MediaPlayer.create(this, R.raw.begemot);
        hyena = MediaPlayer.create(this, R.raw.hyena);
        lion = MediaPlayer.create(this, R.raw.lion);
        ostrich = MediaPlayer.create(this, R.raw.srtaus);
        zebra = MediaPlayer.create(this, R.raw.zebra);
        congrat = MediaPlayer.create(this, R.raw.congrat);
        congrat1 = MediaPlayer.create(this, R.raw.congrat3);
        congrat2 = MediaPlayer.create(this, R.raw.congrat4);

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        final int W = displaymetrics.widthPixels;
        final int H = displaymetrics.heightPixels;

        backToMenu = findViewById(R.id.back_s);
        backToMenu.setX(10);
        backToMenu.setY(10);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnimalWhatUSearch = findViewById(R.id.animalSearch_s);
        AnimalWhatUSearch.setLayoutParams(new RelativeLayout.LayoutParams((int) (H / 3.8), (int) (H / 3.8)));

        searchPlatform = findViewById(R.id.search_s);
        searchPlatform.setLayoutParams(new RelativeLayout.LayoutParams((int) (H / 3.5), (int) (H / 3.5)));

        AnimalWhatUSearch.setX(W/2-H/7+10);
        AnimalWhatUSearch.setY(5*H / 7);
        searchPlatform.setX(W/2-H/7);
        searchPlatform.setY(5*H / 7);


        mMoveLayout = (ViewGroup) findViewById(R.id.backgr_s);
        mMoveLayout.setOnTouchListener((View.OnTouchListener) Savannah.this);

        obstacle1 = findViewById(R.id.obstacle1);
        obstacle2 = findViewById(R.id.obstacle2);
        obstacle3 = findViewById(R.id.obstacle3);

        animal1 = findViewById(R.id.animal1);
        animal2 = findViewById(R.id.animal2);
        animal3 = findViewById(R.id.animal3);

        final int sizeAnimals = (int) (W / 3.9);
        final int sizeObstacles = (int) (W / 3.1);

        Functions.setRightLayoutsParams(animal1, animal2, animal3, sizeAnimals);
        Functions.setRightLayoutsParams(obstacle1, obstacle2, obstacle3, sizeObstacles);

        final HashMap<Integer, Integer> ObstaclesMap = new HashMap<>();
        ObstaclesMap.put(0, R.drawable.bush_savannah);
        ObstaclesMap.put(1, R.drawable.bush_savannah);
        ObstaclesMap.put(2, R.drawable.bush_savannah);
        ObstaclesMap.put(3, R.drawable.acacia);
        ObstaclesMap.put(4, R.drawable.acacia);
        ObstaclesMap.put(5, R.drawable.acacia);

        final HashMap<Integer, MediaPlayer> NameOfSearchAnimal = new HashMap<>();
        NameOfSearchAnimal.put(R.drawable.aantelope, antelope);
        NameOfSearchAnimal.put(R.drawable.cheetah, cheetah);
        NameOfSearchAnimal.put(R.drawable.elephant, elephant);
        NameOfSearchAnimal.put(R.drawable.giraffe, giraffe);
        NameOfSearchAnimal.put(R.drawable.rhino, rhino);
        NameOfSearchAnimal.put(R.drawable.hippopotamus, hippopotamus);
        NameOfSearchAnimal.put(R.drawable.hyena2, hyena );
        NameOfSearchAnimal.put(R.drawable.lion, lion);
        NameOfSearchAnimal.put(R.drawable.ostrich, ostrich);
        NameOfSearchAnimal.put(R.drawable.zebra2, zebra);

        final HashMap<Integer, Integer> AnimalsMap = new HashMap<>();
        AnimalsMap.put(0, R.drawable.aantelope);
        AnimalsMap.put(1, R.drawable.cheetah);
        AnimalsMap.put(2, R.drawable.elephant);
        AnimalsMap.put(3, R.drawable.giraffe);
        AnimalsMap.put(4, R.drawable.rhino);
        AnimalsMap.put(5, R.drawable.hippopotamus);
        AnimalsMap.put(6, R.drawable.hyena2);
        AnimalsMap.put(7, R.drawable.lion);
        AnimalsMap.put(8, R.drawable.ostrich);
        AnimalsMap.put(9, R.drawable.zebra2);

        Functions.CreateSceneObstacles(obstacle1, obstacle2, obstacle3, ObstaclesMap);

        Functions.CreateSceneAnimals(animal1, animal2, animal3, AnimalsMap);

        Functions.setSceneCoordinates(obstacle1, obstacle2, obstacle3, animal1, animal2, animal3, H, W);

        final ArrayList<ImageView> SearchAnimal = new ArrayList<>();
        SearchAnimal.add(animal1);
        SearchAnimal.add(animal2);
        SearchAnimal.add(animal3);
        Collections.shuffle(SearchAnimal);

        AnimalWhatUSearch.setImageResource((Integer) SearchAnimal.get(0).getTag());

        try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        NameOfSearchAnimal.get(SearchAnimal.get(0).getTag()).start();


        final Timer MainTimer = new Timer();
        MainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if ( i < 3 && Functions.isNearTouchTo(X, Y, SearchAnimal.get(i), W))
                        {
                            SearchAnimal.get(i).setImageResource(R.drawable.null_picture);
                            i++;


                            if (i < 3)
                            {
                                NameOfSearchAnimal.get(SearchAnimal.get(i).getTag()).start();
                                AnimalWhatUSearch.setImageResource((Integer) SearchAnimal.get(i).getTag());

                            }

                        }

                        if (go)
                        {
                            X = 0; Y = 0;

                            Functions.CreateSceneObstacles(obstacle1, obstacle2, obstacle3, ObstaclesMap);

                            Functions.CreateSceneAnimals(animal1, animal2, animal3, AnimalsMap);

                            Collections.shuffle(SearchAnimal);

                            AnimalWhatUSearch.setImageResource((Integer) SearchAnimal.get(0).getTag());
                            NameOfSearchAnimal.get(SearchAnimal.get(0).getTag()).start();
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
                Intent intent = new Intent(Savannah.this, MainMenu.class);
                startActivity(intent); finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }
}

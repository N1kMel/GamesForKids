package com.example.gamesforkids;


import android.media.MediaPlayer;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class Functions {

  static int rand = (int) Math.round(Math.random()*2);
  static final Integer[] arr = new Integer[10];
  static final Integer[] a = new Integer[6];

    public static void ArrayMemSet(Integer[] ar)
    {
        for (int i = 0; i < ar.length; i++) { ar[i] = i; }
    }

    public static void setRightLayoutsParams(ImageView one, ImageView two, ImageView three, int size)
    {

        one.setLayoutParams(new RelativeLayout.LayoutParams (size, size));
        two.setLayoutParams(new RelativeLayout.LayoutParams (size, size));
        three.setLayoutParams(new RelativeLayout.LayoutParams (size, size));

    }

    public static void setSceneCoordinates(ImageView obstacle1, ImageView obstacle2, ImageView obstacle3, ImageView animal1, ImageView animal2, ImageView animal3, int H, int W)
    {
        int sizeAnimals = (int) (W / 3.9);
        int sizeObstacles = (int) (W / 3.1);

        animal1.setX(sizeAnimals/8);
        animal1.setY(W/2 - (int) (1.3*sizeAnimals));
        animal2.setX(W/2 - sizeObstacles/2);
        animal2.setY((float) (H/2 - sizeObstacles/1.9));
        animal3.setX(sizeObstacles + H/2+50);
        animal3.setY(H/2 - sizeObstacles/3);

        obstacle1.setX(sizeAnimals / 8);
        obstacle1.setY(W/2 - (int) (1.3*sizeAnimals));
        obstacle2.setX(W/2 - sizeObstacles/2);
        obstacle2.setY((float) (H/2 - sizeObstacles/1.9));
        obstacle3.setX(sizeObstacles + H/2+50);
        obstacle3.setY(H/2 - sizeObstacles/3);
    }

    public static void CreateSceneAnimals(ImageView one, ImageView two, ImageView three, HashMap<Integer, Integer> Animals)
    {
        final Animation smooth = new AlphaAnimation((float) 0 , 1);
        smooth.setDuration(700);
        smooth.setFillAfter(true);

        Collections.shuffle(Arrays.asList(arr));

        one.setImageResource(Animals.get(arr[0]));one.startAnimation(smooth);one.setTag(Animals.get(arr[0]));
        two.setImageResource(Animals.get(arr[1]));two.startAnimation(smooth);two.setTag(Animals.get(arr[1]));
        three.setImageResource(Animals.get(arr[2]));three.startAnimation(smooth);three.setTag(Animals.get(arr[2]));

    }

    public static void CreateSceneObstacles(ImageView one, ImageView two, ImageView three, HashMap<Integer, Integer> Obstacles)
    {
        final Animation smooth = new AlphaAnimation((float) 0.3, 1);
        smooth.setDuration(500);
        smooth.setFillAfter(true);

        Collections.shuffle(Arrays.asList(a));

        one.setImageResource(Obstacles.get(a[0])); one.startAnimation(smooth);one.setTag(Obstacles.get(a[0]));
        two.setImageResource(Obstacles.get(a[1]));two.startAnimation(smooth);two.setTag(Obstacles.get(a[1]));
        three.setImageResource(Obstacles.get(a[2]));three.startAnimation(smooth);three.setTag(Obstacles.get(a[2]));

    }

    public static boolean isNearTouchTo(int X, int Y, ImageView ob, int W)
    {
       if(Math.abs(ob.getX()+ob.getWidth()/2 - X) < W/10 && Math.abs(ob.getY() + ob.getHeight()/2 - Y) < W/10 ) return true; return false;
    }

    public static MediaPlayer RandomCongrat(MediaPlayer m1, MediaPlayer m2, MediaPlayer m3)
    {
        switch (rand){
            case 0:
                rand = (int) Math.round(Math.random()*2);
                return m1;

            case 1:
                rand = (int) Math.round(Math.random()*2);
                return m2;


            case 2:
                rand = (int) Math.round(Math.random()*2);
                return m3;

            default: return null;

        }
    }

    public static MediaPlayer getRandomCongrat(MediaPlayer m1, MediaPlayer m2, MediaPlayer m3)
    {
        switch (rand){
            case 0:
                return m1;

            case 1:
                return m2;

            case 2:
                return m3;

            default: return null;

        }

    }

}


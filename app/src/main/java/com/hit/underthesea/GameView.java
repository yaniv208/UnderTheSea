package com.hit.underthesea;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    private Paint paint;
    private Fish fish;
    public static float screenRatioX, screenRatioY;
    private BackgroundGame backgroundGame1, backgroundGame2;
   // private Stone[] stones;
    private ArrayList<Stone> stones = new ArrayList<Stone>();

    int min=1, max=3;
    private Random randomstonespeed;
    int randomstone;
    //int random = new Random().nextInt((max-min)+1)+min;

    public GameView(Context context, int screenX, int screenY){
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920 / screenX;
        screenRatioY = 1920 / screenY;
        backgroundGame1 = new BackgroundGame(screenX, screenY, getResources());
        backgroundGame2 = new BackgroundGame(screenX, screenY, getResources());

        fish= new Fish(screenY, getResources());

        backgroundGame2.x=screenX;

        paint = new Paint();

       // stones = new Stone[random];

        stones.clear();
        randomstone = new Random().nextInt((max-min)+1)+min;

        for(int i = 0; i< randomstone; i++){
            //Stone stone = new Stone(getResources());
            //stones[i] = stone;
            stones.add(new Stone(getResources()));
        }
        //stone = new Stone(getResources());
        randomstonespeed = new Random();
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        backgroundGame1.x -=10 * screenRatioX;
        backgroundGame2.x -=10 * screenRatioX;

        if(backgroundGame1.x + backgroundGame1.background.getWidth() <0) {
            backgroundGame1.x = screenX;
        }
        if(backgroundGame2.x + backgroundGame2.background.getWidth() <0) {
            backgroundGame2.x = screenX;
        }

        for (Stone stone : stones){
            stone.x -= stone.speed;
            if(stone.x + stone.width <0){
                int bound = (int) (30 * screenRatioX);
                stone.speed = randomstonespeed.nextInt(bound);

                if(stone.speed < 10 * screenRatioX)
                    stone.speed =(int) (10 * screenRatioX);
                stone.x = screenX;
                stone.y = randomstonespeed.nextInt(screenY - stone.height);
            }
        }
    }

    private void draw() {

        if(getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backgroundGame1.background , backgroundGame1.x , backgroundGame1.y,paint );
            canvas.drawBitmap(backgroundGame2.background , backgroundGame2.x , backgroundGame2.y,paint );

            canvas.drawBitmap(fish.getFish(), fish.x, fish.y, paint);

            for (Stone stone : stones)
                canvas.drawBitmap(stone.getStone(), stone.x, stone.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void sleep() {
        try{
            Thread.sleep(25);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void resume () {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause () {
        try {
            isPlaying =false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float  ydown = 0, ymove=0;
        float distanceY=0;
        switch (event.getAction()){
            //the user put his finger down on the screen
            case MotionEvent.ACTION_DOWN:
               ydown = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                ymove = event.getY();
                distanceY = ymove - ydown;

                fish.y = distanceY;

                if(fish.y >= screenY - fish.height)
                fish.y = screenY- fish.height;
                break;
        }
        return true;
    }
}

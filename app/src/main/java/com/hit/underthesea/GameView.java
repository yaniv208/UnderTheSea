package com.hit.underthesea;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    private Paint paint;
    private Fish fish;
    public static float screenRatioX, screenRatioY;
    private BackgroundGame backgroundGame1, backgroundGame2;

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

//        if(fish.isGOingUp)
//            fish.y -=30 *screenRatioY;
//        else
//            fish.y +=30 *screenRatioY;
//        if(fish.y <0)
//            fish.y =0;
//        if(fish.y >= screenY - fish.height)
//            fish.y = screenY- fish.height;

    }

    private void draw() {

        if(getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backgroundGame1.background , backgroundGame1.x , backgroundGame1.y,paint );
            canvas.drawBitmap(backgroundGame2.background , backgroundGame2.x , backgroundGame2.y,paint );

            canvas.drawBitmap(fish.getFish(), fish.x, fish.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void sleep() {
        try{
            Thread.sleep(17);
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

    //need to change the next video

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float  ydown = 0, ymove=0;
        float distanceY=0;
        switch (event.getAction()){
            //the user put his finger down on the screen
            case MotionEvent.ACTION_DOWN:
               ydown = event.getY();
              //  fish.isGOingUp = true;
                break;
            case MotionEvent.ACTION_MOVE:
                ymove = event.getY();

                distanceY = ymove - ydown;

                fish.y = distanceY;

                if(fish.y >= screenY - fish.height)
                fish.y = screenY- fish.height;
                    //ydown = ymove;
                break;

          //  case MotionEvent.ACTION_UP:
             //   fish.isGOingUp = false;
             //   break;
        }
        return true;
    }
}

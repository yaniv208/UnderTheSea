package com.hit.underthesea;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hit.underthesea.score.ScoreTable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    public static final int BACKGROUND_MOVEMENT= 10;

    private Context context;
    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY, score = 0, lives=3;
    private Paint paint;
    private Fish fish;
    private Stone stone;
    private Food food;
    private BackgroundGame backgroundGame1, backgroundGame2;
    private ArrayList<Stone> stones = new ArrayList<Stone>();
    private ArrayList<Food> foods = new ArrayList<Food>();
    int min=1, max=3;
    int randomstone=3, randomfood;


    public GameView(Context context, int screenX, int screenY){
        super(context);
        this.context= context;
        this.screenX = screenX;
        this.screenY = screenY;
        backgroundGame1 = new BackgroundGame(screenX, screenY, getResources());
        backgroundGame2 = new BackgroundGame(screenX, screenY, getResources());

        fish= new Fish(screenY, getResources());

        backgroundGame2.x=screenX;

        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);


        //randomstone = new Random().nextInt((max-min)+1)+min;
        // Log.d("howlives", randomstone+"");

        randomfood = new Random().nextInt((max-min)+1)+min;

        for(int i = 0; i< randomstone; i++){
            stones.add(new Stone(getResources()));
        }

        for(int i = 0; i< randomfood; i++){
            foods.add(new Food(getResources()));
        }

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
        backgroundGame1.x-=BACKGROUND_MOVEMENT;
        backgroundGame2.x-=BACKGROUND_MOVEMENT;

        if(backgroundGame1.x + backgroundGame1.background.getWidth() <0) {
            backgroundGame1.x = screenX;
        }
        if(backgroundGame2.x + backgroundGame2.background.getWidth() <0) {
            backgroundGame2.x = screenX;
        }

        for (int i=0; i<stones.size();i++){
            stones.get(i).objectUpdate(screenX,screenY);


            //if stone and fish
            if(Rect.intersects(stones.get(i).getCollisionShape(), fish.getCollisionShape())){
                stones.remove(i);
                lives--;
                stones.add(new Stone(getResources()));
                if(lives==0)
                    isGameOver=true;
                return;
            }
        }
        //if fish and food
        for (Food food : foods) {
            food.objectUpdate(screenX, screenY);
            if(Rect.intersects(food.getCollisionShape(), fish.getCollisionShape())){
                score++;
                food.setX(-500);  //food off the screen
                food.wasEaten = true;
            }
        }
    }

    private void draw() {

        if(getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backgroundGame1.background , backgroundGame1.x , backgroundGame1.y,paint );
            canvas.drawBitmap(backgroundGame2.background , backgroundGame2.x , backgroundGame2.y,paint );

            for (Food food : foods)
                canvas.drawBitmap(food.getObject(), food.getX(), food.getY(), paint);

            //score text
            Paint scoreText = new Paint();
            scoreText.setTextSize(70);
            scoreText.setColor(Color.WHITE);
            scoreText.isFakeBoldText();
            String myScore = "Score: "+score;
            Rect bounds = new Rect();
            scoreText.getTextBounds(myScore, 0, myScore.length(), bounds);

            canvas.drawText(myScore + "", this.getWidth() - bounds.width()-35 , bounds.height()+15 , scoreText);

            //lives
            Bitmap livesRed = BitmapFactory.decodeResource(getResources(), R.drawable.heartred);
            Bitmap livesWhite = BitmapFactory.decodeResource(getResources(),R.drawable.heartwhite);


            for(int j= 0; j<lives ; j++){
                canvas.drawBitmap(livesRed, null, new Rect(this.getWidth()-bounds.width()-60-(3-j)*56, 20, this.getWidth()-bounds.width()-60-(2-j)*56, 61), null);
            }
//
            for(int j=lives; j<3 ; j++){
                canvas.drawBitmap(livesWhite, null, new Rect(this.getWidth()-bounds.width()-60-(3-j)*55, 18, this.getWidth()-bounds.width()-60-(2-j)*55, 59), null);
            }


            if(isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(fish.getDead(), fish.x,fish.y,paint);
                //all the object on the screen disaper
                getHolder().unlockCanvasAndPost(canvas);

                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                        View viewinflater = inflater.inflate(R.layout.game_over, null);
                        builder.setView(viewinflater);
                        AlertDialog finishDialog = builder.create();
                        finishDialog.setCancelable(false);
                        finishDialog.show();

//                        ImageButton ok_btn = viewinflater.findViewById(R.id.finish_btn);
//                        ok_btn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                EditText entername = viewinflater.findViewById(R.id.edit_name);
//                                String username = entername.getText().toString();
//
//                                if(username.matches(""))
//                                    Toast.makeText(((Activity) getContext()), "Enter your nickname",Toast.LENGTH_SHORT).show();
//                                else{
//                                    Intent intent = new Intent(((Activity) getContext()), ScoreTable.class);
//                                    ((Activity) getContext()).startActivity(intent);
//                                    intent.putExtra("score_user",score);
//                                    intent.putExtra("user_name",username);
//
//                                }
//                            }
//                        });
//                        TextView scoreTV = viewinflater.findViewById(R.id.playerscore);
//                        scoreTV.setText(score+"");
//                    }
//                });
                return;
            }

            canvas.drawBitmap(fish.getFish(), fish.x, fish.y, paint);
            for (Stone stone : stones)
                canvas.drawBitmap(stone.getObject(), stone.getX(), stone.getY(), paint);


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
            isPlaying = false;
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
                Log.d("movefinger","mmmmm");
                ymove = event.getY();
                distanceY = ymove - ydown;

                fish.y = (int) distanceY;

                if(fish.y >= screenY - fish.height)
                    fish.y = screenY- fish.height;
                break;
        }
        return true;
    }
}
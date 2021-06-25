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
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hit.underthesea.score.ScoreTable;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    public static final int BACKGROUND_MOVEMENT= 10;

    private Level level;

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

    public GameView(Context context, int screenX, int screenY, Level levelYouAre){
        super(context);

        this.level = new Level(levelYouAre);

        this.context= context;
        this.screenX = screenX;
        this.screenY = screenY;
        backgroundGame1 = new BackgroundGame(screenX, screenY, getResources());
        backgroundGame2 = new BackgroundGame(screenX, screenY, getResources());

        fish= new Fish(screenY, getResources(),level.getFishpic());

        backgroundGame2.x=screenX;

        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);


        for(int i = 0; i< level.getStoneAmount(); i++){
            stones.add(new Stone(getResources(),level.getStonepic(),level.getMinSpeedStone(),level.getMaxSpeedStone()));
        }

        for(int i = 0; i< level.getFoodAmount(); i++){
            foods.add(new Food(getResources(),level.getFoodpic(),level.getMinSpeedStone(),level.getMaxSpeedStone()));
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

            int live = stones.get(i).hit(fish,lives,score);
            if(live!=lives){
                stones.remove(i);
                lives = live;
                stones.add(new Stone(getResources(),level.getStonepic(),level.getMinSpeedStone(),level.getMaxSpeedStone()));
                if(lives==0)
                    isGameOver=true;
                return;
            }
        }

        //if fish and food
        for (Food food : foods){
            food.objectUpdate(screenX, screenY);
            int scoreHelp = food.hit(fish, lives, score);//if i have collision return 1, else 0
            if(scoreHelp!=score) {
                score = scoreHelp;
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

            canvas.drawText(myScore + "", this.getWidth() - bounds.width()-720 , bounds.height()+50 , scoreText);

            //lives
            Bitmap livesRed = BitmapFactory.decodeResource(getResources(), R.drawable.heartred);
            Bitmap livesWhite = BitmapFactory.decodeResource(getResources(),R.drawable.heartwhite);

            for(int j= 0; j<lives ; j++){
                canvas.drawBitmap(livesRed, null, new Rect(this.getWidth()-bounds.width()-260-(3-j)*56, 60, this.getWidth()-bounds.width()-260-(2-j)*56, 101), null);
            }
//
            for(int j=lives; j<3 ; j++){
                canvas.drawBitmap(livesWhite, null, new Rect(this.getWidth()-bounds.width()-260-(3-j)*55, 58, this.getWidth()-bounds.width()-260-(2-j)*55, 99), null);
            }

            if(isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(fish.getDead(), fish.x,fish.y,paint);
                //all the object on the screen disaper
                getHolder().unlockCanvasAndPost(canvas);

                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        if(level.getHighScore()<=score) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                            View viewinflater = inflater.inflate(R.layout.game_over, null);
                            builder.setView(viewinflater);
                            AlertDialog finishDialog = builder.create();
                            finishDialog.setCancelable(false);
                            finishDialog.show();

                            ImageButton ok_btn = viewinflater.findViewById(R.id.finish_btn);
                            ok_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EditText entername = viewinflater.findViewById(R.id.edit_name);
                                    String username = entername.getText().toString();

                                    if (username.matches(""))
                                        Toast.makeText(((Activity) getContext()), "Enter your nickname", Toast.LENGTH_SHORT).show();
                                    else {
                                        Intent intent = new Intent(((Activity) getContext()), ScoreTable.class);
                                        ((Activity) getContext()).startActivity(intent);
                                        intent.putExtra("score_user", score);
                                        intent.putExtra("user_name", username);
                                        intent.putExtra("level_name", level.getNumlevel());
                                        ((Activity) getContext()).startActivity(intent);

                                    }
                                }
                            });

                            TextView scoreTV = viewinflater.findViewById(R.id.playerscore);
                            scoreTV.setText(score + "");
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                            View viewinflater = inflater.inflate(R.layout.loser, null);
                            builder.setView(viewinflater);
                            AlertDialog finishDialog = builder.create();
                            finishDialog.setCancelable(false);
                            finishDialog.show();

                            ImageButton okloser_btn = viewinflater.findViewById(R.id.ok_loser);
                            TextView scoreloserTV = viewinflater.findViewById(R.id.playerscoreloser);
                            TextView needmoreTV = viewinflater.findViewById(R.id.miss_score);
                            needmoreTV.setText((level.getHighScore()-score) + "");
                            scoreloserTV.setText(score + "");
                            okloser_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent intent = new Intent(((Activity) getContext()), PlayMenu.class);
                                    ((Activity) getContext()).startActivity(intent);
                                }
                            });
                        }


                    }

                });
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
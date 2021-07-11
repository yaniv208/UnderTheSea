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


public class GameView extends SurfaceView implements Runnable {

    public static final int BACKGROUND_MOVEMENT = 10;
    private Level level;
    private Context context;
    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY, score = 0, lives = 3;
    private Paint paint;
    private Fish fish;
    private BackgroundGame backgroundGame1, backgroundGame2;
    private ArrayList<Stone> stones = new ArrayList<Stone>();
    private ArrayList<Food> foods = new ArrayList<Food>();

    public GameView(Context context, int screenX, int screenY, Level levelYouAre){
        super(context);

        this.level = new Level(levelYouAre);

        this.context= context;
        this.screenX = screenX;
        this.screenY = screenY;
        backgroundGame1 = new BackgroundGame(screenX, screenY, getResources());
        backgroundGame2 = new BackgroundGame(screenX, screenY, getResources());

        fish= new Fish(screenY, getResources(),level.getFishPic());

        backgroundGame2.x=screenX;

        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);


        for(int i = 0; i< level.getStoneAmount(); i++){
            stones.add(new Stone(getResources(),level.getStonePic(),level.getMinSpeedStone(),level.getMaxSpeedStone()));
        }

        for(int i = 0; i< level.getFoodAmount(); i++){
            foods.add(new Food(getResources(),level.getFoodPic(),level.getMinSpeedFood(),level.getMaxSpeedFood()));
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
        backgroundGame1.x -= BACKGROUND_MOVEMENT;
        backgroundGame2.x -= BACKGROUND_MOVEMENT;

        if((backgroundGame1.x + backgroundGame1.background.getWidth()) < 0) {
            backgroundGame1.x = screenX;
        }
        if((backgroundGame2.x + backgroundGame2.background.getWidth()) < 0) {
            backgroundGame2.x = screenX;
        }

        for (int i = 0; i < stones.size(); i++){
            stones.get(i).objectUpdate(screenX,screenY);
            // If stone and fish

            int live = stones.get(i).hit(fish, lives, score);
            if (live != lives){
                //remove the stone that hit the fish
                stones.remove(i);
                lives = live;
                //create a new stone instead
                stones.add(new Stone(getResources(),level.getStonePic(),level.getMinSpeedStone(),level.getMaxSpeedStone()));
                //check if the user finish all his lives - the game is finish......
                if(lives == 0)
                    isGameOver = true;
                return;
            }
        }

        // if fish and food
        for (Food food : foods){
            food.objectUpdate(screenX, screenY);
            int scoreHelp = food.hit(fish, lives, score); // if collision happens return 1, else 0
            if(scoreHelp != score) {
                score = scoreHelp;
            }
        }
    }

    private void draw() {

        if(getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();

            //background
            canvas.drawBitmap(backgroundGame1.background , backgroundGame1.x , backgroundGame1.y,paint );
            canvas.drawBitmap(backgroundGame2.background , backgroundGame2.x , backgroundGame2.y,paint );

            //food
            for (Food food : foods)
                canvas.drawBitmap(food.getObject(), food.getX(), food.getY(), paint);

            // Score text
            Paint scoreText = new Paint();
            scoreText.setTextSize(70);
            scoreText.setColor(Color.WHITE);
            scoreText.isFakeBoldText();
            String myScore = getContext().getString(R.string.score_in_play) + " " + score;
            Rect bounds = new Rect();
            scoreText.getTextBounds(myScore, 0, myScore.length(), bounds);

            canvas.drawText(myScore + "", this.getWidth() - bounds.width()-screenX/2+40 , bounds.height()+50 , scoreText);

            // lives - red lives or white lives
            Bitmap livesRed = BitmapFactory.decodeResource(getResources(), R.drawable.heartred);
            Bitmap livesWhite = BitmapFactory.decodeResource(getResources(),R.drawable.heartwhite);

            //red lives
            for(int j= 0; j < lives ; j++){
                canvas.drawBitmap(livesRed, null, new Rect(this.getWidth()-bounds.width()-screenX/2-(3-j)*56+30, 60, this.getWidth()-bounds.width()-screenX/2-(2-j)*56+30, 101), null);
            }
            //white lives
            for(int j=lives; j < 3 ; j++){
                canvas.drawBitmap(livesWhite, null, new Rect(this.getWidth()-bounds.width()-screenX/2-(3-j)*55+30, 58, this.getWidth()-bounds.width()-screenX/2-(2-j)*55+30, 99), null);
            }

            //check is the game is over - the user finish all his lives
            if(isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(fish.getDead(), fish.x,fish.y,paint);
                // making all the objects on the screen disappear
                getHolder().unlockCanvasAndPost(canvas);

                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        //check if the user score is big the high score of the his level
                        //if - yes - create the alert dialog that save his name and add this to the high
                        //score table
                        if(level.getHighScore()<=score) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                            View viewInflater = inflater.inflate(R.layout.game_over, null);
                            builder.setView(viewInflater);
                            AlertDialog finishDialog = builder.create();
                            finishDialog.setCancelable(false);
                            finishDialog.show();

                            ImageButton ok_btn = viewInflater.findViewById(R.id.finish_btn);
                            ok_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EditText enterName = viewInflater.findViewById(R.id.edit_name);
                                    String username = enterName.getText().toString();

                                    if (username.matches("")) // In case name is null
                                        Toast.makeText((getContext()), R.string.enter_nickname, Toast.LENGTH_SHORT).show();
                                    else {
                                        finishDialog.dismiss();
                                        Intent intent = new Intent((getContext()), ScoreTable.class);
                                        (getContext()).startActivity(intent);
                                        intent.putExtra("score_user", score);
                                        intent.putExtra("user_name", username);
                                        intent.putExtra("level_name", level.getNumLevel());
                                        (getContext()).startActivity(intent);
                                        ((Activity) getContext()).finish();
                                    }
                                }
                            });

                            TextView scoreTV = viewInflater.findViewById(R.id.player_score);
                            scoreTV.setText(score + "");
                        }
                        //if - not - create the alert dialog that show him his score and
                        //how much point left to him to be write in the score high table
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                            View viewInflater = inflater.inflate(R.layout.loser, null);
                            builder.setView(viewInflater);
                            AlertDialog finishDialog = builder.create();
                            finishDialog.setCancelable(false);
                            finishDialog.show();

                            ImageButton okloser_btn = viewInflater.findViewById(R.id.ok_loser);
                            TextView scoreloserTV = viewInflater.findViewById(R.id.player_score_loser);
                            TextView needmoreTV = viewInflater.findViewById(R.id.miss_score);
                            needmoreTV.setText((level.getHighScore() - score) + "");
                            scoreloserTV.setText(score + "");
                            okloser_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finishDialog.dismiss();

                                    Intent intent = new Intent((getContext()), PlayMenu.class);
                                    (getContext()).startActivity(intent);
                                    ((Activity) getContext()).finish();
                                }
                            });
                        }
                    }
                });
                return;
            }

            //fish
            canvas.drawBitmap(fish.getFish(), fish.x, fish.y, paint);
            //stone
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
        float  yDown = 0, yMove;
        float distanceY;
        switch (event.getAction()){
            // When the user puts his finger down the screen
            //the fish x stay the same
            //the y change according to the finger movement
            case MotionEvent.ACTION_DOWN:
                yDown = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = event.getY();
                distanceY = yMove - yDown;

                fish.y = (int) distanceY;

                if(fish.y >= screenY - fish.height)
                    fish.y = screenY- fish.height;
                break;
        }
        return true;
    }
}
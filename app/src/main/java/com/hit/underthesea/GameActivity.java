package com.hit.underthesea;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    ImageView fishPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        int levelNum = bundle.getInt("num_level");
        ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(new Level(2, 4, R.drawable.fishplayer, R.drawable.food, R.drawable.stone,30,20,20,15));
        levels.add(new Level(3, 3, R.drawable.fishplayer, R.drawable.food, R.drawable.stone, 35,25,25,20));
        levels.add(new Level(4, 2, R.drawable.fishplayer, R.drawable.food, R.drawable.stone,40,30,30,25));


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x,point.y, levels.get(levelNum-1));

        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        gameView.resume();
    }
}

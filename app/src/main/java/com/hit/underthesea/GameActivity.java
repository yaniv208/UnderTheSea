package com.hit.underthesea;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        levels.add(new Level(3, 5, R.drawable.fishplayer, R.drawable.food, R.drawable.stone,35,30,30,25,"1",52));//level1
        levels.add(new Level(4, 4, R.drawable.fishlevel2, R.drawable.food, R.drawable.stone, 45,40,40,35,"2",30));//level2
        levels.add(new Level(5, 3, R.drawable.fishlevel3, R.drawable.food, R.drawable.stone,50,40,40,35,"3",15));//level3

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x,point.y, levels.get(levelNum-1));

//        FloatingActionButton settings = new FloatingActionButton(this);
//        settings.setId(View.generateViewId());
//        settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("DEBUG", "onFabFoo");
//            }
//        });
//        settings.setImageResource(R.drawable.settingbut);
//        settings.setElevation(2);
//        settings.setSize(FloatingActionButton.SIZE_MINI);
//        settings.setFocusable(true);
//        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        lay.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        lay.setMargins(2,2,2,2);
//        settings.setLayoutParams(lay);
//        public FloatingActionButton getFab(Context context, ViewGroup parent) {
//            LayoutInflater inflater = LayoutInflater.from(this);
//            return (FloatingActionButton) inflater.inflate(R.layout.settings, parent, false);
     //   }

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

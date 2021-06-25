package com.hit.underthesea;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hit.underthesea.fragments.MusicService;
import com.hit.underthesea.fragments.SettingsFragment;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    ImageView fishPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

        setContentView(gameView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(this, PlayMenu.class);
                startActivity(intent);
                break;
            case R.id.pause:
                if(item.getIcon().getConstantState().equals(getDrawable(R.drawable.play_button).getConstantState())) {
                    onPostResume();
                    item.setIcon(getDrawable(R.drawable.pause_button));
                }else {
                    onPause();
                    item.setIcon(getDrawable(R.drawable.play_button));
                }
                break;
            case  R.id.sound:
                if(item.getIcon().getConstantState().equals(getDrawable(R.drawable.no_sound_button).getConstantState())) {
                    startService(new Intent(this, MusicService.class));
                    item.setIcon(getDrawable(R.drawable.sound_button));
                } else {
                    stopService(new Intent(this, MusicService.class));
                    item.setIcon(getDrawable(R.drawable.no_sound_button));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
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

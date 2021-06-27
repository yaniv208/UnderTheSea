package com.hit.underthesea;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameActivity extends BaseActivity {

    private GameView gameView;
    ImageView fishPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        Bundle bundle = getIntent().getExtras();
        int levelNum = bundle.getInt("num_level");
        ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(new Level(3, 5, R.drawable.fishplayer, R.drawable.food, R.drawable.stone,35,30,30,25,"EASY",10));//level1
        levels.add(new Level(4, 4, R.drawable.fishlevel2, R.drawable.food, R.drawable.stone, 45,40,40,35,"MEDIUM",30));//level2
        levels.add(new Level(5, 3, R.drawable.fishlevel3, R.drawable.food, R.drawable.stone,50,40,40,35,"HARD",15));//level3

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x,point.y, levels.get(levelNum-1));

        setContentView(gameView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu,menu);

        //when we close the music on setting and enter the game
        AudioManager manager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        if(!manager.isMusicActive())
            menu.findItem(R.id.sound).setIcon(getDrawable(R.drawable.no_sound_button));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(this, PlayMenu.class);
                startActivity(intent);
                finish();
                break;
            case R.id.pause:
                if(item.getIcon().getConstantState().equals(getDrawable(R.drawable.play_game).getConstantState())) {
                    onPostResume();
                    MusicPlayer.getInstance().play(true);
                    item.setIcon(getDrawable(R.drawable.pause_button));
                }else {
                    onPause();
                    MusicPlayer.getInstance().play(true);
                    item.setIcon(getDrawable(R.drawable.play_game));
                }
                break;
            case  R.id.sound:
                if(item.getIcon().getConstantState().equals(getDrawable(R.drawable.no_sound_button).getConstantState())) {
                    MusicPlayer.getInstance().play(true);
                    item.setIcon(getDrawable(R.drawable.sound_button));
                } else {
                    MusicPlayer.getInstance().pause(true);
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

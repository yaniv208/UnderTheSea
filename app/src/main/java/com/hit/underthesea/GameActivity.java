package com.hit.underthesea;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class GameActivity extends BaseActivity {

    private GameView gameView;
    ImageView fishPlayer;
    boolean pressHome = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        Objects.requireNonNull(Objects.requireNonNull(getSupportActionBar())).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        Bundle bundle = getIntent().getExtras();
        int levelNum = bundle.getInt("num_level");
        ArrayList<Level> levels = new ArrayList<Level>();
        levels.add(new Level(3, 5, R.drawable.fish_player, R.drawable.food, R.drawable.stone,35,30,30,25,"EASY",30)); //level1
        levels.add(new Level(4, 4, R.drawable.fishlevel2, R.drawable.food, R.drawable.stone, 45,40,40,35,"MEDIUM",20)); //level2
        levels.add(new Level(5, 3, R.drawable.fishlevel3, R.drawable.food, R.drawable.stone,50,40,40,35,"HARD",10)); //level3

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x,point.y, levels.get(levelNum-1));

        setContentView(gameView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu,menu);

        // Changing the music toggle from settings and entering the game
        AudioManager manager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        if(!manager.isMusicActive())
            menu.findItem(R.id.sound).setIcon(getDrawable(R.drawable.no_sound_button));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                gameView.pause();
                pressHome=true;
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View viewInflater = inflater.inflate(R.layout.exit_game, null);
                builder.setView(viewInflater);
                AlertDialog exitDialog = builder.create();
                exitDialog.setCancelable(false);
                exitDialog.show();

                ImageButton yes_btn = viewInflater.findViewById(R.id.yes_exit);
                yes_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        exitDialog.dismiss();
                        Intent intent = new Intent(GameActivity.this, PlayMenu.class);
                        startActivity(intent);
                        finish();
                    }
                });
                ImageButton no_btn = viewInflater.findViewById(R.id.no_exit);
                no_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pressHome = false;
                        onPostResume();
                        exitDialog.dismiss();
                    }
                });

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
        if(pressHome)
            gameView.pause();
    }
}
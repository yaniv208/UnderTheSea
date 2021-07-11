package com.hit.underthesea;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    //MainActivity, GameActivity, PlayMenu and ScoreTable class extends BaseActivity
    //this class cause the music pause and play in the all pages that extends from her

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        MusicPlayer.getInstance().pause(false);
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MusicPlayer.getInstance().play(false);
    }
}
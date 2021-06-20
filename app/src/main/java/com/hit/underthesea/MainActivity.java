package com.hit.underthesea;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.hit.underthesea.fragments.MusicService;
import com.hit.underthesea.fragments.SettingsFragment;
import com.hit.underthesea.score.ScoreTable;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView=findViewById(R.id.bubble1);
        AnimationDrawable animationDrawable =(AnimationDrawable)imageView.getDrawable();
        animationDrawable.start();
        ImageView imageView2=findViewById(R.id.bubble2);
        AnimationDrawable animationDrawable2 =(AnimationDrawable)imageView2.getDrawable();
        animationDrawable2.start();

        startService(new Intent(this, MusicService.class));

        ImageButton setting = findViewById(R.id.setting_button);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //support חייב להוסיף בגירסא החדשה
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new SettingsFragment(), null).addToBackStack("Settings").commit();

                //FragmentManager fragmentManager = getSupportFragmentManager();
                //FragmentTransaction transaction = fragmentManager.beginTransaction();
                //transaction.add(R.id.fragment_container, new SettingsFragment(), SETTINGS_FRAGMENT_TAG);
                //transaction. addToBackStack(null); מוריד את העסקא האחרונה
                //transaction.commit();

            }
        });

        ImageButton playbtn = findViewById(R.id.play_button);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this , PlayMenu.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
package com.hit.underthesea;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.hit.underthesea.fragments.SettingsFragment;

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

        ImageButton setting = findViewById(R.id.setting_button);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new SettingsFragment(), null).addToBackStack("Settings").commit();
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//
//                View dialogView =  getLayoutInflater().inflate(R.layout.menu,null);
//
//                Switch music = dialogView.findViewById(R.id.switchmusic_btn);
//                Button howToPlay = dialogView.findViewById(R.id.howtoplay_btn);
//
//                AlertDialog.Builder builderHowToPlay = new AlertDialog.Builder(MainActivity.this);
//                View dialogView2 = getLayoutInflater().inflate(R.layout.how_to_play,null);
//                TextView title = dialogView2.findViewById(R.id.title_how_to_play);
//                TextView subtitle = dialogView2.findViewById(R.id.text_how_to_play);
//
//                howToPlay.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        builderHowToPlay.setView(dialogView2).show();
//                        title.setText("How to play");
//                        subtitle.setText("jnev;vmwe;m;rv'v we");
//                    }
//                });
//
//                Button credit = dialogView.findViewById(R.id.credit_btn);
//
//                credit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        builderHowToPlay.setView(dialogView2).show();
//                        title.setText("Credit");
//                        subtitle.setText(";aksocmwooslq;,");
//                    }
//                });
//
//
//                builder.setCancelable(false);
//                builder.setView(dialogView).show();



            }
        });

    }




}
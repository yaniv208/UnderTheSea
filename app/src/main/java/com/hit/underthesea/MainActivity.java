package com.hit.underthesea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.hit.underthesea.fragments.MusicService;
import com.hit.underthesea.fragments.SettingsFragment;
import com.hit.underthesea.score.ScoreTable;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageView imageView=findViewById(R.id.bubble1);
        AnimationDrawable animationDrawable =(AnimationDrawable)imageView.getDrawable();
        animationDrawable.start();

        ImageView imageView2=findViewById(R.id.bubble2);
        AnimationDrawable animationDrawable2 =(AnimationDrawable)imageView2.getDrawable();
        animationDrawable2.start();

        RelativeLayout relativeLayout = findViewById(R.id.buttonAnimation);
        ObjectAnimator animator = ObjectAnimator.ofFloat(relativeLayout,"scaleX",((float)(1.15))).setDuration(250);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(relativeLayout,"scaleY",((float)(1.15))).setDuration(250);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(animator,animator2);
        set1.start();

        startService(new Intent(this, MusicService.class));


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.actions_set){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new SettingsFragment(), null).addToBackStack("Settings").commit();

            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
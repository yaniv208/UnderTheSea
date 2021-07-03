package com.hit.underthesea;

import androidx.annotation.NonNull;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import com.hit.underthesea.fragments.SettingsFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Objects;

public class MainActivity extends BaseActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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

        // startService(new Intent(this, MusicService.class));
        MusicPlayer.getInstance().initialize(this);

        ImageButton playBtn = findViewById(R.id.play_button);
        playBtn.setOnClickListener(new View.OnClickListener() {
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

    public void onBackPressed(){
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
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        ImageButton no_btn = viewInflater.findViewById(R.id.no_exit);
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog.dismiss();
            }
        });
    }
}
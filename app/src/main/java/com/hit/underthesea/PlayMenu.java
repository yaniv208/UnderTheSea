package com.hit.underthesea;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.hit.underthesea.fragments.FirstGuidanceFragment;
import com.hit.underthesea.fragments.SettingsFragment;
import com.hit.underthesea.score.ScoreTable;

import java.util.Objects;

public class PlayMenu extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_menu);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        RelativeLayout relativeLayout1 = findViewById(R.id.level1_background);
        ObjectAnimator animator1scaleX = ObjectAnimator.ofFloat(relativeLayout1,"scaleX",((float)(1.15))).setDuration(250);
        ObjectAnimator animator1scaleY = ObjectAnimator.ofFloat(relativeLayout1,"scaleY",((float)(1.15))).setDuration(250);
        animator1scaleX.setRepeatMode(ValueAnimator.REVERSE);
        animator1scaleX.setRepeatCount(ValueAnimator.INFINITE);
        animator1scaleY.setRepeatMode(ValueAnimator.REVERSE);
        animator1scaleY.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(animator1scaleX,animator1scaleY);
        set1.start();

        RelativeLayout relativeLayout2 = findViewById(R.id.level2_background);
        ObjectAnimator animator2scaleX = ObjectAnimator.ofFloat(relativeLayout2,"scaleX",((float)(1.15))).setDuration(250);
        ObjectAnimator animator2scaleY = ObjectAnimator.ofFloat(relativeLayout2,"scaleY",((float)(1.15))).setDuration(250);
        animator2scaleX.setRepeatMode(ValueAnimator.REVERSE);
        animator2scaleX.setRepeatCount(ValueAnimator.INFINITE);
        animator2scaleY.setRepeatMode(ValueAnimator.REVERSE);
        animator2scaleY.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(animator2scaleX,animator2scaleY);
        set2.start();

        RelativeLayout relativeLayout3 = findViewById(R.id.level3_background);
        ObjectAnimator animator3scaleX = ObjectAnimator.ofFloat(relativeLayout3,"scaleX",((float)(1.15))).setDuration(250);
        ObjectAnimator animator3scaleY = ObjectAnimator.ofFloat(relativeLayout3,"scaleY",((float)(1.15))).setDuration(250);
        animator3scaleX.setRepeatMode(ValueAnimator.REVERSE);
        animator3scaleX.setRepeatCount(ValueAnimator.INFINITE);
        animator3scaleY.setRepeatMode(ValueAnimator.REVERSE);
        animator3scaleY.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet set3 = new AnimatorSet();
        set3.playTogether(animator3scaleX,animator3scaleY);
        set3.start();

        ImageButton scoreBtn = findViewById(R.id.score_btn);
        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayMenu.this , ScoreTable.class);
                startActivity(intent);
            }
        });

        ImageButton easyLevel = findViewById(R.id.level1);
        ImageButton medLevel = findViewById(R.id.level2);
        ImageButton hardLevel = findViewById(R.id.level3);
        ImageButton guidanceFragments = findViewById(R.id.guidance_button);
        easyLevel.setOnClickListener(this);
        medLevel.setOnClickListener(this);
        hardLevel.setOnClickListener(this);

        guidanceFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerplay, new FirstGuidanceFragment(), null).addToBackStack("First Guidance").commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.actions_set){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerplay, new SettingsFragment(), null).addToBackStack("Settings").commit();
            item.setVisible(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int numberLevel=1;
        if(view.getId() == R.id.level2)
            numberLevel = 2;
        else if(view.getId() == R.id.level3){
            numberLevel=3;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("num_level", numberLevel);
        Intent intent = new Intent(PlayMenu.this, GameActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

}

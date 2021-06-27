package com.hit.underthesea.score;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.underthesea.BaseActivity;
import com.hit.underthesea.PlayMenu;
import com.hit.underthesea.R;
import com.hit.underthesea.fragments.SettingsFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreTable extends BaseActivity {

    ArrayList<Score> scores;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_table_list);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("user_name");
        int userScore = intent.getIntExtra("score_user",0);
        String numlevel =intent.getStringExtra("level_name");

        loadData();
        if(userName!=null) {//when its come from the score
            addScoreInHisPosition(userName,numlevel,userScore);
            //scores.add(new Score(userName, "1", userScore));
            saveData();
        }

        ListView listView = findViewById(R.id.score_list);

        ScoreAdapter scoreAdapter = new ScoreAdapter(scores,this);
        listView.setAdapter(scoreAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scoretable_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.actions_setting){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerscoretable, new SettingsFragment(), null).addToBackStack("Settings").commit();
            return true;
        }
        if(item.getItemId()==R.id.home_score){
            Intent intent = new Intent(this, PlayMenu.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(scores);
        editor.putString("task list",json);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list",null);
        Type type = new TypeToken<ArrayList<Score>>(){}.getType();
        scores = gson.fromJson(json,type);

        if(scores == null){//if we dont create array list until now
            scores = new ArrayList<>();
        }
    }
    public void addScoreInHisPosition(String name,String level,int score){
        if(scores.size()==0){//first
            scores.add(new Score(name,level,score));
        }else{
            scores.add(new Score(name,level,score));
            Collections.sort(scores, new Comparator<Score>() {
                @Override
                public int compare(Score s1, Score s2) {
                    return (Integer)s2.getScore() - (Integer)s1.getScore();
                }
            });
        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, PlayMenu.class);
        startActivity(intent);
        finish();
    }
}
package com.hit.underthesea.score;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.underthesea.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreTable extends AppCompatActivity {

    ArrayList<Score> scores;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_table_list);

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
}
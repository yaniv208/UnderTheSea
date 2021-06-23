package com.hit.underthesea.score;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hit.underthesea.R;

import java.util.ArrayList;

public class ScoreTable extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_table_list);

        ListView listView = findViewById(R.id.score_list);
        ArrayList<Score> scores = new ArrayList<>();

        //אם עובר מידע אז מגיע נאל וציון אפס ואם דרך המשחק עובר ציון, אבל השם הוא חשוב

//        Intent intent = getIntent();
//        String userName = intent.getStringExtra("user_name");
//        int userScore = intent.getIntExtra("score_user",0);
//
//        ScoreAdapter scoreAdapter = new ScoreAdapter(scores,this);
//        listView.setAdapter(scoreAdapter);
    }

}

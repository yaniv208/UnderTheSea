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

//        ListView listView = findViewById(R.id.score_list);
//        ArrayList<Score> scores = new ArrayList<>();
////
//       scores.add(new Score("Ofek", "Hard", 66));
////
//        ScoreAdapter scoreAdapter = new ScoreAdapter(scores,this);
//        listView.setAdapter(scoreAdapter);
    }

}

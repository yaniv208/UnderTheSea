package com.hit.underthesea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hit.underthesea.score.ScoreTable;

public class PlayMenu extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_menu);

        Button scorebtn = findViewById(R.id.score_btn);
        scorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayMenu.this , ScoreTable.class);
                startActivity(intent);
            }
        });

        Button easylevel = findViewById(R.id.easy_level);
        easylevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayMenu.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }




}

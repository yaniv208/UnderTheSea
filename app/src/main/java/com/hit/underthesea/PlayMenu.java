package com.hit.underthesea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hit.underthesea.score.ScoreTable;


public class PlayMenu extends AppCompatActivity implements View.OnClickListener{

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

        ImageButton easylevel = findViewById(R.id.level1);
        ImageButton midlevel = findViewById(R.id.level2);
        ImageButton hardlevel = findViewById(R.id.level3);
        easylevel.setOnClickListener(this);
        midlevel.setOnClickListener(this);
        hardlevel.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int numberlevel=1;
        if(view.getId()==R.id.level2)
            numberlevel = 2;
        else if(view.getId()==R.id.level3){
            numberlevel=3;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("num_level", numberlevel);
        Intent intent = new Intent(PlayMenu.this, GameActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

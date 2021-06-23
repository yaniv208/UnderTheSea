package com.hit.underthesea;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    ImageView fishPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x,point.y);

        setContentView(gameView);
    }

  //  public static void AlertGameOver() {
     //   AlertDialog.Builder builder = new AlertDialog.Builder(this);
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


 //   }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        gameView.resume();
    }
}

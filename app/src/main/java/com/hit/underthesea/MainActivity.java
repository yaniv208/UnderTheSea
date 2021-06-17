package com.hit.underthesea;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView=findViewById(R.id.bubble1);
        AnimationDrawable animationDrawable =(AnimationDrawable)imageView.getDrawable();
        animationDrawable.start();
        ImageView imageView2=findViewById(R.id.bubble2);
        AnimationDrawable animationDrawable2 =(AnimationDrawable)imageView2.getDrawable();
        animationDrawable2.start();
    }


}
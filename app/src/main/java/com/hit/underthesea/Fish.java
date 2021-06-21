package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.hit.underthesea.GameView.screenRatioX;
import static com.hit.underthesea.GameView.screenRatioY;

public class Fish {

    int width, height, wingCounter=0;
    float x,y;
    Bitmap fish;

    Fish (int screenY, Resources res){
        fish = BitmapFactory.decodeResource(res, R.drawable.fishplayer);

        width = fish.getWidth();
        height = fish.getHeight();

        width /=5;
        height /=5;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        fish = Bitmap.createScaledBitmap(fish, width, height,false);

    }

    Bitmap getFish(){

        return fish;

    }
}

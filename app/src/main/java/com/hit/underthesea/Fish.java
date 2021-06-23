package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Fish {

    int width, height, wingCounter=0;
    int x,y;
    Bitmap fish, dead;

    Fish (int screenY, Resources res){
        fish = BitmapFactory.decodeResource(res, R.drawable.fishplayer);

        width = fish.getWidth();
        height = fish.getHeight();

        width /=5;
        height /=5;

        fish = Bitmap.createScaledBitmap(fish, width, height,false);

        dead = BitmapFactory.decodeResource(res, R.drawable.fishplayer);
        dead = Bitmap.createScaledBitmap(fish, width, height,false);

    }

    Bitmap getFish(){

        return fish;

    }

    Rect getCollisionShape (){
        return new Rect(x, y, x+width, y+height);
    }

    Bitmap getDead(){
        return dead;
    }
}

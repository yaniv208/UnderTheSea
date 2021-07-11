package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Fish {
    int width, height;
    int x,y;
    Bitmap fish, dead;

    Fish (int screenY, Resources res, int pic){
        fish = BitmapFactory.decodeResource(res, pic);

        width = fish.getWidth();
        height = fish.getHeight();

        width /= 7;
        height /= 7;

        fish = Bitmap.createScaledBitmap(fish, width, height,false);

        dead = BitmapFactory.decodeResource(res, R.drawable.fish_player);
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
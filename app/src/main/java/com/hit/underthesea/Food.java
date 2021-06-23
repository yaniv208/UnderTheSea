package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Food extends ObjectView {
    Bitmap food;
    public boolean wasEaten = true;

    public Food(Resources res) {
        super(res, R.drawable.food);
    }

    Bitmap getFood(){
        return food;

    }

    Rect getCollisionShape (){
        return new Rect(x, y, x+width, y+height);
    }
}

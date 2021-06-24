package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;

public class Stone extends ObjectView{

    Bitmap stone;

    public Stone(Resources res,int picstone, int minSpeed, int maxSpeed) {
        super(res, picstone, minSpeed,maxSpeed);
    }

    Bitmap getStone(){
        return stone;
    }

    @Override
    public int hit(Fish fish, int lives, int score) {
        int resultIntersect = super.hit(fish, lives, score);
        Log.d("check", resultIntersect+"");
        if(resultIntersect==1) {
            lives--;
        }
        return lives;
    }
}

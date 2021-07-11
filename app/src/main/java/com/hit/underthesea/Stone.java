package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Stone extends ObjectView{
    Bitmap stone;

    public Stone(Resources res,int picStone, int minSpeed, int maxSpeed) {
        super(res, picStone, minSpeed,maxSpeed);
    }

//    Bitmap getStone(){
//        return stone;
//    }

    @Override
    public int hit (Fish fish, int lives, int score) {
        int resultIntersect = super.hit(fish, lives, score);
        if(resultIntersect == 1) {
            lives--;
        }
        return lives;
    }
}

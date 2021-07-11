package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Stone extends ObjectView{
    Bitmap stone;

    public Stone(Resources res,int picStone, int minSpeed, int maxSpeed) {
        super(res, picStone, minSpeed,maxSpeed);
    }

    //polymorphism - change the hit function of object view according to the stone object
    @Override
    public int hit (Fish fish, int lives, int score) {
        int resultIntersect = super.hit(fish, lives, score);
        //if the stone and the fish intersect - the super function return 1
        if(resultIntersect == 1) {
            lives--;//decreases the lives in one point
        }
        //return the update lives to the game view
        return lives;
    }
}
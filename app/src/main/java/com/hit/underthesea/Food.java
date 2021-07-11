package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Food extends ObjectView {
    Bitmap food;
    public boolean wasEaten = true;

    public Food (Resources res,int foodPic, int minSpeed, int maxSpeed) {
        super(res, foodPic, minSpeed,maxSpeed);
    }

    //polymorphism - change the hit function of object view according to the food object
    @Override
    public int hit(Fish fish, int lives, int score) {
        int resultIntersect = super.hit(fish,lives,score);
        //if the food and the fish intersect - the super function return 1
        if(resultIntersect == 1) {
            score++;//increases the score in one point
            this.setX(-500);//take out the food from the screen
            this.wasEaten = true;
        }
        //return the update score to the game view
        return score;
    }

}

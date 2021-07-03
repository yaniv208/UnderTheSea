package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Food extends ObjectView {
    Bitmap food;
    public boolean wasEaten = true;

    public Food(Resources res,int foodPic, int minSpeed, int maxSpeed) {
        super(res, foodPic, minSpeed,maxSpeed);
    }

    Bitmap getFood(){
        return food;
    }

    @Override
    public int hit(Fish fish, int lives, int score) {
        int resultIntersect = super.hit(fish,lives,score);
        if(resultIntersect == 1) {
            score++;
            this.setX(-500);
            this.wasEaten = true;
        }
        return score;
    }

}

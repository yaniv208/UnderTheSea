package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import static com.hit.underthesea.GameView.screenRatioX;
import static com.hit.underthesea.GameView.screenRatioY;

public class ObjectView {
    public int x=0,y, width,height;
    Bitmap object;
    public int speed=20;
    Random randomstonespeed = new Random();


    public ObjectView(Resources res, int pic) {
        object = BitmapFactory.decodeResource(res, pic);

        width = object.getWidth();
        height = object.getHeight();

        width /= 2.5;
        height /=2.5;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        object = Bitmap.createScaledBitmap(object, width, height,false);

        y = -height;

    }

    public void objectUpdate(int screenX, int screenY) {
        this.x -= this.speed;

        if (this.x + this.width < 0) {
            int bound = (int) (30 * screenRatioX);
            this.speed = randomstonespeed.nextInt(bound);

            if (this.speed < 10 * screenRatioX)
                this.speed = (int) (20 * screenRatioX);
            this.x = screenX;
            this.y = randomstonespeed.nextInt(screenY - this.height);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

        Bitmap getObject(){
        return object;

    }
}
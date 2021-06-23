package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class ObjectView {
    private int x=0,y, width,height;
    Bitmap object;
    private int speed,maxSpeed=30,minSpeed=20;
    Random randomstonespeed = new Random();

    public ObjectView(Resources res, int pic) {
        object = BitmapFactory.decodeResource(res, pic);

        width = object.getWidth();
        height = object.getHeight();

        width /= 2.5;
        height /=2.5;

        object = Bitmap.createScaledBitmap(object, width, height,false);
        this.speed = randomstonespeed.nextInt((maxSpeed-minSpeed)+1)+minSpeed;

        y = -height;

    }

    public void objectUpdate(int screenX, int screenY) {
        this.x -= this.speed;

        if (this.x + this.width < 0) {//the stone get new speed
            updateSpeed(screenX,screenY);
        }
    }
    public void updateSpeed(int screenX,int screenY){
        //int bound = 30;
        this.speed = randomstonespeed.nextInt((maxSpeed-minSpeed)+1)+minSpeed;

        // if (this.speed < 10)
        //   this.speed = (int) (20);
        this.x = screenX;
        this.y = randomstonespeed.nextInt(screenY - this.height);
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

    Bitmap getObject(){ return object; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) { this.height = height; }

    public void setObject(Bitmap object) { this.object = object; }

    public void setSpeed(int speed) { this.speed = speed; }
}
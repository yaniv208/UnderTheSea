package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class ObjectView {
    //stone and food classes extends from objectView
    private int x = 0, y, width, height;
    private int speed, maxSpeed, minSpeed;
    Random randomSpeed = new Random();
    Bitmap object;

    public ObjectView(Resources res, int pic, int minSpeedS, int maxSpeedS) {
        //create the object bitmap
        object = BitmapFactory.decodeResource(res,pic);
        this.maxSpeed = maxSpeedS;
        this.minSpeed = minSpeedS;

        //const the size of the object
        width = object.getWidth();
        height = object.getHeight();

        width /= 2.5;
        height /= 2.5;

        object = Bitmap.createScaledBitmap(object, width, height,false);
        //random the object speed - between his level speed (min and max)
        this.speed = randomSpeed.nextInt((maxSpeed-minSpeed)+1)+minSpeed;

        y =- height;

    }


    public void objectUpdate(int screenX, int screenY) {
        this.x -= this.speed; //change the position of object - according the X

        if (this.x + this.width < 0) {  //the object arrived to the end of screen
            //get new speed
            this.speed = randomSpeed.nextInt((maxSpeed-minSpeed) + 1) + minSpeed;
            //put the object on the start of screen
            this.x = screenX;
            this.y = randomSpeed.nextInt(screenY - this.height);
        }
    }

    public int hit(Fish fish, int lives, int score){
        //check if the fish and object(stone or food) collision
        if(Rect.intersects(this.getCollisionShape(), fish.getCollisionShape())) {
            return 1;
        }
        return 0;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getSpeed() {return speed;}
    Bitmap getObject(){ return object; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public void setObject(Bitmap object) { this.object = object; }public void setSpeed(int speed) { this.speed = speed; }

    //return the frame/shape of the object for collision in the function hit
    Rect getCollisionShape () {
        return new Rect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight());
    }

}
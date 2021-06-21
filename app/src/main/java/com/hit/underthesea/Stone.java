package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.hit.underthesea.GameView.screenRatioX;
import static com.hit.underthesea.GameView.screenRatioY;

public class Stone {
    int x=0,y, width,height;
    Bitmap stone1;
    public int speed=20;

    public Stone(Resources res) {
        stone1 = BitmapFactory.decodeResource(res, R.drawable.stone);

        width = stone1.getWidth();
        height = stone1.getHeight();

        width /= 2.5;
        height /=2.5;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        stone1 = Bitmap.createScaledBitmap(stone1, width, height,false);

        y = -height;

    }

    Bitmap getStone(){

        return stone1;

    }
}

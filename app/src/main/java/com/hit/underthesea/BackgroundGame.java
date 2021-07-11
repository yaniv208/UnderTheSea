package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BackgroundGame {

    int x,y;
    Bitmap background;

    BackgroundGame (int screenX, int screenY , Resources res){
        //create the background bitmap
        background = BitmapFactory.decodeResource(res, R.drawable.backgroundlevel);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}
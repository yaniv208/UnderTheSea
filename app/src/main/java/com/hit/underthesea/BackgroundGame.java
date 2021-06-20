package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BackgroundGame {

    int x,y;
    Bitmap background;

    BackgroundGame (int screenX, int screenY , Resources res){
        background = BitmapFactory.decodeResource(res, R.drawable.levelbackground);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}

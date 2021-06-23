package com.hit.underthesea;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Stone extends ObjectView{

    Bitmap stone;

    public Stone(Resources res) {
        super(res, R.drawable.stone);
    }

    Bitmap getStone(){
        return stone;
    }

    Rect getCollisionShape (){
        return new Rect(this.getX(), this.getY(), this.getX()+this.getWidth(), this.getY()+this.getHeight());
    }
}

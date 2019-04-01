package com.sp.xwjlibrary.util;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by vinson on 2018/11/9.
 */

public class AnimationUtil {

    public static AnimationDrawable anim(Context context,int[] imgResId, int speed){
        AnimationDrawable ad = new AnimationDrawable();
        ad.setOneShot(false);
        for (int img : imgResId) {
            Drawable drawable = ContextCompat.getDrawable(context, img);
            if (drawable != null)
                ad.addFrame(drawable,speed);
        }
        return ad;
    }


}

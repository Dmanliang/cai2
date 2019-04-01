package com.sp.xwjlibrary.widget;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 需要在Application中调用静态方法init(..)初始化IconView
 * Created by vinson on 2017/11/4.
 */

public class IconView extends AppCompatTextView {

    private static Typeface iconTypeFace;

    public IconView(Context context) {
        super(context);
        this.setTypeface(iconTypeFace);
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(iconTypeFace);
    }

    /**
     * 需要在Application中调用
     * @param iconTypeFace
     */
    public static void init(Typeface iconTypeFace){
        IconView.iconTypeFace = iconTypeFace;
    }

}

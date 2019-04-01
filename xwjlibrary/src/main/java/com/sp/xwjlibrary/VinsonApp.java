package com.sp.xwjlibrary;

import android.app.Application;
import android.graphics.Typeface;

import com.sp.xwjlibrary.widget.DoubleTextView;
import com.sp.xwjlibrary.widget.IconEditClearView;
import com.sp.xwjlibrary.widget.IconView;
import com.sp.xwjlibrary.widget.TabHostView;

import org.xutils.x;

/**
 * Created by vinson on 2018/3/19.
 */

public class VinsonApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Typeface iconTypeFace = Typeface.createFromAsset(getAssets(), "font/iconfont.ttf");
        IconEditClearView.init(iconTypeFace);
        IconView.init(iconTypeFace);
        TabHostView.init(iconTypeFace);
        DoubleTextView.init(iconTypeFace);
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}

package com.sp.xwjlibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ViewDialog extends AlertDialog {

    private Window window;
    private WindowManager.LayoutParams attributes;

    public ViewDialog(Context context,View view) {
        super(context);
        initUI(view);
    }

    private void initUI(View contentView){
        this.show();
        // 设置点击在区域外不消失
        this.cancel();
        // 获取dialog窗体对象
        window = this.getWindow();
        // 设置dialog窗体样式
        assert window != null;
        window.setContentView(contentView);
        attributes = window.getAttributes();
    }

    public ViewDialog setGravity(int gravity){
        attributes.gravity = gravity;
        window.setAttributes(attributes);
        return this;
    }

    public ViewDialog setMaxWidth(){
        window.setBackgroundDrawableResource(android.R.color.transparent);
        DisplayMetrics outMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        attributes.width = outMetrics.widthPixels;
        window.setAttributes(attributes);
        return this;
    }

    public ViewDialog setBothSideSpace(int space){
        DisplayMetrics outMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        attributes.width = outMetrics.widthPixels - space;
        window.setAttributes(attributes);
        return this;
    }

    public ViewDialog setBgTransparent(){
        window.setBackgroundDrawableResource(android.R.color.transparent);
        return this;
    }

}

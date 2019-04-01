package com.sp.xwjlibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.sp.xwjlibrary.R;


/**
 * Created by vinson on 2018/11/7.
 */

public class PopupDialog extends Dialog {

    private Context context;
    private Window window;
    private View llyShade;

    public PopupDialog(Context context, View contentView) {
        super(context);
        this.context = context;
        initUI(contentView);
    }

    private void initUI(View contentView){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.show();
        // 设置点击在区域外不消失
        this.cancel();
        // 获取dialog窗体对象
        window = this.getWindow();
        // 设置dialog窗体样式
        assert window != null;
        window.setContentView(R.layout.custom_dialog);
        window.setDimAmount(0);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout llyContent = window.findViewById(R.id.lly_dialog_content);
        llyShade = window.findViewById(R.id.lly_shade);
        llyShade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        llyContent.addView(contentView);
    }

    public void show(View view) {
        // 获取dialog窗体对象
        WindowManager.LayoutParams attributes = window.getAttributes();
        int notifyHeight = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
        int[] location = new  int[2] ;
        view.getLocationInWindow(location);
        attributes.x = 0;
        attributes.y = location [1] + view.getHeight() - notifyHeight;
        attributes.gravity = Gravity.TOP;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        attributes.height = dm.heightPixels - (location [1] + view.getHeight());
        window.setAttributes(attributes);
        super.show();
    }

    public void show(View view,int width,int gravity,int offset){
        WindowManager.LayoutParams attributes = window.getAttributes();
        int notifyHeight = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
        int[] location = new  int[2] ;
        view.getLocationInWindow(location);
        attributes.x = offset;
        attributes.y = location [1] + view.getHeight() - notifyHeight;
        attributes.gravity = gravity;
        attributes.width = width;
        attributes.height = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight() - (location [1] + view.getHeight());
        window.setAttributes(attributes);
        llyShade.setBackgroundColor(Color.TRANSPARENT);
        super.show();
    }

    public void performClick(int viewId){
        findViewById(viewId).performClick();

    }
}

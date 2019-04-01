package com.sp.caitwo.ui.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.util.BitmapTool;
import com.sp.util.Utility;
import com.sp.util.ViewUtil;


/**
 * Created by hang on 2014/11/24.
 * dialog vip class
 */
public abstract class CommonDialog implements OnDismissListener {

    private final int heightBotom;
    public Context context;
    public PopupWindow dlg;
    public View dlgView;
    private boolean isInitComplete;

    public CommonDialog(Context context, int layoutId) {
        this(context, layoutId, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    public CommonDialog(Context context, int layoutId, int width, int height, boolean isChangeBg) {
        this.context = context;
        int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        dlgView = LayoutInflater.from(context).inflate(layoutId, null);
        dlgView.setSystemUiVisibility(flag);
        int w = width;
        int h = height;
        if (width >= 0)
            w = BitmapTool.dp2px(context, width);
        if (height >= 0)
            h = BitmapTool.dp2px(context, height);
        dlg = new PopupWindow(dlgView, w, h, true);
        //dlg.setAnimationStyle(R.style.anim_window_fade);
        dlg.setBackgroundDrawable(new BitmapDrawable());
        dlg.setOutsideTouchable(true);
        dlg.setOnDismissListener(this);
        dlg.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#30000000")));
        setBackgroundAlpha(context, 1);
        isInitComplete = false;
        //获取自身的长宽高
        dlgView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        heightBotom = ViewUtil.getNavigationBarHeight(context);
    }
    public CommonDialog(Context context, int layoutId, int width, int height) {
        this.context = context;
        int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        dlgView = LayoutInflater.from(context).inflate(layoutId, null);
        dlgView.setSystemUiVisibility(flag);
        int w = width;
        int h = height;
        if (width >= 0)
            w = BitmapTool.dp2px(context, width);
        if (height >= 0)
            h = BitmapTool.dp2px(context, height);
        dlg = new PopupWindow(dlgView, w, h, true);
        dlg.setAnimationStyle(R.style.anim_window_fade);
        dlg.setBackgroundDrawable(new BitmapDrawable());
        dlg.setOutsideTouchable(true);
        dlg.setOnDismissListener(this);
        dlg.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setBackgroundAlpha(context, 1);
        isInitComplete = false;
        //获取自身的长宽高
        dlgView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        heightBotom = ViewUtil.getNavigationBarHeight(context);
    }

    public void setWindowAnimation(int styleId) {
        dlg.setAnimationStyle(styleId);
    }

    public void setBackgroundAlpha(Context context, float alpha) {
        try {
            WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
            lp.alpha = alpha;
            ((Activity) context).getWindow().setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void show() {
        show(Gravity.CENTER);
    }

    public void showAtBottom() {
        if (heightBotom > 0) {
            showAtBottom(0, heightBotom);
        } else {
            setWindowAnimation(R.style.anim_slide_bottom);
            show(Gravity.BOTTOM);
        }

    }

    public void showAtBottom(int x, int y) {
        setWindowAnimation(R.style.anim_slide_bottom);
        show(Gravity.BOTTOM, null, x, y);
    }
    public void show(int gravity) {
        show(gravity, null);
    }

    public void show(int gravity, View parent) {
        dismiss();
        if (dlg != null) {
            if (!isInitComplete) {
                initDlgView();
                isInitComplete = true;
            }
            if (parent == null)
                parent = ((ViewGroup) ((Activity) context).findViewById(android.R.id.content)).getChildAt(0);
            dlg.showAtLocation(parent, gravity, 0, 0);
            setBackgroundAlpha(context, 0.7f);
        }
        Utility.hideSoftInput((Activity) context);
    }

    public void show(int gravity, View parent, int x, int y) {
        dismiss();
        if (dlg != null) {
            if (!isInitComplete) {
                initDlgView();
                isInitComplete = true;
            }
            if (parent == null)
                parent = ((ViewGroup) ((Activity) context).findViewById(android.R.id.content)).getChildAt(0);
            dlg.showAtLocation(parent, gravity, x, y);
            setBackgroundAlpha(context, 0.7f);
        }
        Utility.hideSoftInput((Activity) context);
    }
    public void showUp(View v) {
        showAtLocation(v);
    }

    public void showAtLocation(View parent) {
        setWindowAnimation(R.style.anim_slide_bottom);
        dismiss();
        if (dlg != null) {
            if (!isInitComplete) {
                initDlgView();
                isInitComplete = true;
            }
            dlg.showAtLocation(parent, Gravity.CENTER, 0, 0);
            setBackgroundAlpha(context, 0.7f);
        }
        Utility.hideSoftInput((Activity) context);
    }

    public void shows(int gravity, Activity activity) {
        if (dlg != null) {
            if (!isInitComplete) {
                initDlgView();
                isInitComplete = true;
            }
            dlg.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            setBackgroundAlpha(context, 0.7f);
        }
        Utility.hideSoftInput((Activity) context);
    }

    public void showAsDropDown(View anchor) {
        //setWindowAnimation(R.style.anim_slide_bottom);
        dismiss();
        if (dlg != null) {
            if (!isInitComplete) {
                initDlgView();
                isInitComplete = true;
            }
            dlg.showAsDropDown(anchor);
            setBackgroundAlpha(context, 0.7f);
        }
        Utility.hideSoftInput((Activity) context);
    }

    public void showAtLocation(View anchor, int x, int y) {
        //setWindowAnimation(R.style.anim_slide_bottom);
        dismiss();
        if (dlg != null) {
            if (!isInitComplete) {
                initDlgView();
                isInitComplete = true;
            }
            dlg.showAtLocation(anchor, Gravity.TOP, x, y);
            setBackgroundAlpha(context, 0.7f);
        }
        Utility.hideSoftInput((Activity) context);
    }

    public void dismiss() {
        if (dlg != null && dlg.isShowing())
            dlg.dismiss();
    }

    @Override
    public void onDismiss() {
        setBackgroundAlpha(context, 1);
    }

    public abstract void initDlgView();

    public void setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
    }

    public void setText(int viewId, int textId) {
        TextView tv = getView(viewId);
        tv.setText(textId);
    }

    public void setImageResource(int viewId, int drawableId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(drawableId);
    }

    public void setImageBitmap(int viewId, Bitmap bm) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bm);
    }


    public <T extends View> T getView(int viewId) {
        View view = dlgView.findViewById(viewId);
        return (T) view;
    }


}

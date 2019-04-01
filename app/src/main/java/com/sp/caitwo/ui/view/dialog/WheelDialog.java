package com.sp.caitwo.ui.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.sp.caitwo.R;

public class WheelDialog extends AlertDialog {

    private Context context;
    private View view;

    public WheelDialog(Context context, View view) {
        super(context);
        this.context = context;
        this.view = view;
        this.show();
        this.cancel();
        initUI();

    }

    private void initUI() {
        Window window = this.getWindow();
        window.setContentView(view);
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.setWindowAnimations(R.style.bottomEnterAndExit);

        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
    }
}

package com.sp.xwjlibrary.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.xwjlibrary.R;


/**
 * Created by vinson on 2018/3/22.
 */

public class SingleTextView extends LinearLayout {

    private static Typeface iconTypeFace;
    private final TextView textView;
    private int color;
    private final int selColor;
    private String text;
    private final String selText;
    private final ColorStateList enableColor;
    private boolean isChecked;
    private final boolean isClickChange;
    private final Drawable bgDrawable;
    private final Drawable bgSelDrawable;

    public SingleTextView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SingleTextView);
        text = ta.getString(R.styleable.SingleTextView_sText);
        selText = ta.getString(R.styleable.SingleTextView_sSelText);
        int size = ta.getInteger(R.styleable.SingleTextView_sSize,16);
        color = ta.getColor(R.styleable.SingleTextView_sColor, ContextCompat.getColor(context, R.color.text_def_gray));
        selColor = ta.getColor(R.styleable.SingleTextView_sSelColor,0);
        bgDrawable = ta.getDrawable(R.styleable.SingleTextView_stvBgColor);
        bgSelDrawable = ta.getDrawable(R.styleable.SingleTextView_stvBgSelColor);
        isClickChange = ta.getBoolean(R.styleable.SingleTextView_sIsClickChange, false);
        enableColor = ta.getColorStateList(R.styleable.DoubleTextView_enableColor);
        Drawable drawableSpace = ta.getDrawable(R.styleable.DoubleTextView_drawableSpace);
        int orientation = ta.getInteger(R.styleable.DoubleTextView_orientation, 0);
        ta.recycle();

        textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
        textView.setTextColor(color);
        if (iconTypeFace != null)
            textView.setTypeface(iconTypeFace);

        this.setBackground(bgDrawable);

        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (orientation == 0) {
            this.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            this.setOrientation(LinearLayout.VERTICAL);
        }
        this.addView(textView,params);
        this.setDividerDrawable(drawableSpace);
        this.setShowDividers(SHOW_DIVIDER_MIDDLE);

        if (enableColor != null) {
            textView.setTextColor(enableColor);
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果程序中使用到点击监听会拦截该控件的事件,需要调用该方法恢复点击效果
                onResumeClickChange();
            }
        });
    }

    /**
     * 如果程序中使用到点击监听会拦截该控件的事件,需要调用该方法恢复点击效果
     */
    public void onResumeClickChange(){
        if (isClickChange) {
            setChecked(isChecked = !isChecked);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        textView.setEnabled(enabled);
    }

    public void setChecked(boolean checked){
        isChecked = checked;
        if (checked) {
            if (selColor != 0) {
                textView.setTextColor(selColor);
            }
            if (!TextUtils.isEmpty(selText)) {
                textView.setText(selText);
            }
            this.setBackground(bgSelDrawable);
        }else {
            textView.setTextColor(color);
            textView.setText(text);
            this.setBackground(bgDrawable);
        }
    }

    public void setText(String text){
        this.text = text;
        textView.setText(text);
    }

    public void setColor(int color){
        this.color = color;
        textView.setTextColor(color);
    }

    /**
     * 需要在Application中调用
     * @param iconTypeFace
     */
    public static void init(Typeface iconTypeFace){
        SingleTextView.iconTypeFace = iconTypeFace;
    }
}

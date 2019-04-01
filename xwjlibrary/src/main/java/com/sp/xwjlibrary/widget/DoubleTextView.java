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
import com.sp.xwjlibrary.myinterface.OnDoubleTextViewListener;


/**
 * Created by vinson on 2018/3/22.
 */

public class DoubleTextView extends LinearLayout {

    private static Typeface iconTypeFace;
    private final TextView tvOne;
    private final Drawable oneBgDrawable;
    private final Drawable twoBgDrawable;
    private int oneColor;
    private final int oneSelColor;
    private String oneText;
    private final String oneSelText;
    private final TextView tvTwo;
    private int twoColor;
    private final int twoSelColor;
    private String twoText;
    private final String twoSelText;
    private final ColorStateList enableColor;
    private boolean isChecked;
    private final boolean isClickChange;
    private final Drawable bgDrawable;
    private final Drawable bgSelDrawable;

    public DoubleTextView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DoubleTextView);
        oneText = ta.getString(R.styleable.DoubleTextView_oneText);
        oneSelText = ta.getString(R.styleable.DoubleTextView_oneSelText);
        int oneSize = ta.getInteger(R.styleable.DoubleTextView_oneSize,16);
        oneColor = ta.getColor(R.styleable.DoubleTextView_oneColor, ContextCompat.getColor(context, R.color.text_def_gray));
        oneSelColor = ta.getColor(R.styleable.DoubleTextView_oneSelColor,0);
        twoText = ta.getString(R.styleable.DoubleTextView_twoText);
        twoSelText = ta.getString(R.styleable.DoubleTextView_twoSelText);
        int twoSize = ta.getInteger(R.styleable.DoubleTextView_twoSize,16);
        twoColor = ta.getColor(R.styleable.DoubleTextView_twoColor, ContextCompat.getColor(context, R.color.text_def_gray));
        twoSelColor = ta.getColor(R.styleable.DoubleTextView_twoSelColor,0);
        bgDrawable = ta.getDrawable(R.styleable.DoubleTextView_dtvBgColor);
        bgSelDrawable = ta.getDrawable(R.styleable.DoubleTextView_dtvBgSelColor);
        oneBgDrawable = ta.getDrawable(R.styleable.DoubleTextView_oneBgColor);
        twoBgDrawable = ta.getDrawable(R.styleable.DoubleTextView_twoBgColor);
        isClickChange = ta.getBoolean(R.styleable.DoubleTextView_isClickChange, false);
        enableColor = ta.getColorStateList(R.styleable.DoubleTextView_enableColor);
        Drawable drawableSpace = ta.getDrawable(R.styleable.DoubleTextView_drawableSpace);
        int orientation = ta.getInteger(R.styleable.DoubleTextView_orientation, 0);
        ta.recycle();

        tvOne = new TextView(context);
        tvTwo = new TextView(context);
        tvOne.setText(oneText);
        tvOne.setTextSize(TypedValue.COMPLEX_UNIT_SP,oneSize);
        tvOne.setTextColor(oneColor);
        tvOne.setBackground(oneBgDrawable);
        if (iconTypeFace != null) {
            tvOne.setTypeface(iconTypeFace);
        }

        tvTwo.setText(twoText);
        tvTwo.setTextSize(TypedValue.COMPLEX_UNIT_SP,twoSize);
        tvTwo.setTextColor(twoColor);
        tvTwo.setBackground(twoBgDrawable);
        if (iconTypeFace != null)
            tvTwo.setTypeface(iconTypeFace);

        this.setBackground(bgDrawable);

        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (orientation == 0) {
            this.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            this.setOrientation(LinearLayout.VERTICAL);
        }
        this.addView(tvOne,params);
        this.addView(tvTwo,params);
        this.setDividerDrawable(drawableSpace);
        this.setShowDividers(SHOW_DIVIDER_MIDDLE);

        if (enableColor != null) {
            tvTwo.setTextColor(enableColor);
            tvOne.setTextColor(enableColor);
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
        tvOne.setEnabled(enabled);
        tvTwo.setEnabled(enabled);
    }

    public void setChecked(boolean checked){
        isChecked = checked;
        if (checked) {
            if (oneSelColor != 0) {
                tvOne.setTextColor(oneSelColor);
            }
            if (!TextUtils.isEmpty(oneSelText)) {
                tvOne.setText(oneSelText);
            }
            if (twoSelColor != 0) {
                tvTwo.setTextColor(twoSelColor);
            }
            if (!TextUtils.isEmpty(twoSelText)) {
                tvTwo.setText(twoSelText);
            }
            this.setBackground(bgSelDrawable);
        }else {
            tvOne.setTextColor(oneColor);
            tvOne.setText(oneText);
            tvTwo.setTextColor(twoColor);
            tvTwo.setText(twoText);
            this.setBackground(bgDrawable);
        }
    }

    public void setOneText(String text){
        oneText = text;
        tvOne.setText(text);
    }

    public void setTwoText(String text){
        twoText = text;
        tvTwo.setText(text);
    }

    public void setOneColor(int color){
        oneColor = color;
        tvOne.setTextColor(color);
    }

    public void setTwoColor(int color){
        twoColor = color;
        tvTwo.setTextColor(color);
    }

    /**
     * 需要在Application中调用
     * @param iconTypeFace
     */
    public static void init(Typeface iconTypeFace){
        DoubleTextView.iconTypeFace = iconTypeFace;
    }
}

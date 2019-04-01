package com.sp.xwjlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sp.xwjlibrary.R;


/**
 * Created by vinson on 2018/3/19.
 */

public class ActionBarLayout extends FrameLayout {

    public static final int ID_LEFT_TEXT_VIEW = 0x0001;
    public static final int ID_RIGHT_TEXT_VIEW = 0x0002;

    private static Typeface typeface;
    private final int leftSize;
    private final int leftColor;
    private final String leftText;
    private final int titleSize;
    private final int titleColor;
    private final String titleText;
    private final int rightSize;
    private final int rightColor;
    private final String rightText;
    private final int bgColor;
    @Nullable
    private OnClickListener listener;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvTitle;

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        listener = l;
        super.setOnClickListener(l);
    }

    public ActionBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ActionBarLayout);
        leftSize = ta.getInteger(R.styleable.ActionBarLayout_leftSize,24);
        leftColor = ta.getColor(R.styleable.ActionBarLayout_leftColor, ContextCompat.getColor(context,R.color.textBlackColor));
        leftText = ta.getString(R.styleable.ActionBarLayout_leftText);

        titleSize = ta.getInteger(R.styleable.ActionBarLayout_titleSize,20);
        titleColor = ta.getColor(R.styleable.ActionBarLayout_titleColor, ContextCompat.getColor(context,R.color.textBlackColor));
        titleText = ta.getString(R.styleable.ActionBarLayout_titleText);

        rightSize = ta.getInteger(R.styleable.ActionBarLayout_rightSize,24);
        rightColor = ta.getColor(R.styleable.ActionBarLayout_rightColor, ContextCompat.getColor(context,R.color.textBlackColor));
        rightText = ta.getString(R.styleable.ActionBarLayout_rightText);

        bgColor = ta.getColor(R.styleable.ActionBarLayout_bgActionBarColor, Color.WHITE);
        ta.recycle();

        this.setBackgroundColor(bgColor);
        init(context);
    }

    private void init(Context context) {
        tvLeft = new TextView(context);
        LayoutParams leftParam = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParam.gravity = Gravity.CENTER_VERTICAL;
        tvLeft.setTypeface(typeface);
        tvLeft.setText(leftText);
        tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP,leftSize);
        tvLeft.setTextColor(leftColor);
        tvLeft.setPadding(30,30,30,30);
        tvLeft.setId(ID_LEFT_TEXT_VIEW);
        this.addView(tvLeft,leftParam);

        tvRight = new TextView(context);
        LayoutParams rightParam = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParam.gravity = Gravity.CENTER_VERTICAL|Gravity.RIGHT;
        tvRight.setTypeface(typeface);
        tvRight.setText(rightText);
        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP,rightSize);
        tvRight.setTextColor(rightColor);
        tvRight.setPadding(30,30,30,30);
        tvRight.setId(ID_RIGHT_TEXT_VIEW);
        this.addView(tvRight,rightParam);

        tvTitle = new TextView(context);
        LayoutParams titleParam = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleParam.gravity = Gravity.CENTER;
        tvTitle.setText(titleText);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,titleSize);
        tvTitle.setTextColor(titleColor);
        tvTitle.setGravity(Gravity.CENTER);
        float leftW = 0;
        float rightW = 0;
        if (!TextUtils.isEmpty(tvLeft.getText().toString())) {
            TextPaint paint = tvLeft.getPaint();
            paint.setTextSize(tvLeft.getTextSize());
            leftW = paint.measureText(tvLeft.getText().toString()) +
                    tvLeft.getPaddingLeft() + tvLeft.getPaddingRight();
        }
        if (!TextUtils.isEmpty(tvRight.getText().toString())){
            TextPaint paint = tvRight.getPaint();
            paint.setTextSize(tvRight.getTextSize());
            rightW = paint.measureText(tvRight.getText().toString()) +
                    tvRight.getPaddingLeft() + tvRight.getPaddingRight();
        }
        float width = leftW > rightW ? leftW : rightW;
        titleParam.leftMargin = (int) width;
        titleParam.rightMargin = (int) width;
        this.addView(tvTitle,titleParam);

        tvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) listener.onClick(view);
            }
        });
        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) listener.onClick(view);
            }
        });
    }

    public TextView getView(int index){
        switch(index){
            case 0:
                return tvLeft;
            case 1:
                return tvTitle;
            case 2:
                return tvRight;
        }
        return null;
    }

    public static void init(Typeface typeface){
        ActionBarLayout.typeface = typeface;
    }
}

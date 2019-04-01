package com.sp.caitwo.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class NestXRecycleView extends XRecyclerView {
    public NestXRecycleView(Context context) {
        super(context);
    }

    public NestXRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestXRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec
                (Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}

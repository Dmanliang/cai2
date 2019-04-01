package com.sp.xwjlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class NestedGridView extends GridView {
	public NestedGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 重写该方法，达到使GridView适应ScrollView的效果
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec
			(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}

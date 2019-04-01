package com.sp.xwjlibrary.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.sp.xwjlibrary.R;

public final class ToastUtil {
	
	private static Toast toast;
	private static Context mContext;

	public static void Toast(final Context context, final String text){
		mContext = context;
		if(toast != null){
			toast.cancel();
		}
		toast = new Toast(mContext);
		TextView textView = new TextView(mContext);
		textView.setPadding(50,20,50,20);
		textView.setTextSize(14f);
		textView.setTextColor(Color.WHITE);
		textView.setBackgroundResource(R.drawable.bg_transparent_black_radius);
		textView.setText(text);

		// 第一个参数对齐方式
		// 第二个参数距离对齐方式位置的x轴距离
		// 第二个参数距离对齐方式位置的y轴距离
		toast.setGravity(Gravity.BOTTOM,0,120);
		toast.setView(textView);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}

}

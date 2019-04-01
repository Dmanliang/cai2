package com.sp.xwjlibrary.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class ScreenUtil{

	/**
	 * 获取屏幕宽度,单位px
	 * 
	 * @param context
	 * @return
	 */
	public static int getWidthPixel(Context context){
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度,单位px
	 * 
	 * @param context
	 * @return
	 */
	public static int getHeightPixel(Context context){
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 获取屏幕宽度,单位dp
	 * 
	 * @param context
	 * @return
	 */
	@Deprecated
	public static int getWidthDensity(Context context){
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}

	/**
	 * 获取屏幕高度,单位dp
	 * 
	 * @param context
	 * @return
	 */
	@Deprecated
	public static int getHeightDensity(Context context){
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();
	}

	/**
	 * 获取ActionBar的高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getActionBarHeight(Activity activity){
		TypedValue tv = new TypedValue();
		int actionBarHeight = 0;
		if(activity.getTheme().resolveAttribute(android.R.attr.actionBarSize,tv,true)){
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,activity
					.getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}

	// *****************************************************************//

	/**
	 * 获取屏幕宽度和高度，单位为px
	 * 
	 * @param context
	 * @return
	 */
	public static Point getScreenMetrics(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		int h_screen = dm.heightPixels;
		return new Point(w_screen,h_screen);

	}

	/**
	 * 获取屏幕长宽比
	 * 
	 * @param context
	 * @return
	 */
	public static float getScreenRate(Context context){
		Point P = getScreenMetrics(context);
		float H = P.y;
		float W = P.x;
		return(H / W);
	}
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

}

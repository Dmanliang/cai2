package com.sp.xwjlibrary.util;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HolderUtil {

	private static ArrayList<View> subControlslist = new ArrayList<>();
	private static ArrayList<View> views = new ArrayList<>();

	/**
	 * 返回控件的宽高
	 */
	public interface HolderCallback{
		void onHolderCallback(int viewWidth, int viewHeight);
	}

	/**
	 * 获取控件的宽高
	 * @param view
	 * @param callback 返回控件的宽高
	 */
	public static void getWidthAndHeight(final View view,final HolderCallback callback) {
		view.post(new Runnable() {
			@Override
			public void run() {
				int viewWidth = view.getMeasuredWidth();
				int viewHeight = view.getMeasuredHeight();
				callback.onHolderCallback(viewWidth,viewHeight);
			}
		});
	}

	/**
	 * 设置控件比例
	 * @param view
	 * @param scale 格式:1/2,表示高度=宽度的二分之一
	 */
	public static void setHolderScale(final View view,String scale){
		String[] split = scale.split("/");
		final int numerator = Integer.valueOf(split[0]);
		final int denominator = Integer.valueOf(split[1]);
		view.post(new Runnable() {
			@Override
			public void run() {
				int viewWidth = view.getMeasuredWidth();
				int viewHeight = viewWidth * numerator / denominator;
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
				params.width = viewWidth;
				params.height = viewHeight;
				view.setLayoutParams(params);
			}
		});
	}

	/**
	 * 设置控件缩放
	 * @param view
	 * @param zoom >0放大,<0缩小
	 */
	public static void setHolderZoom(final View view, final float zoom){
		view.post(new Runnable() {
			@Override
			public void run() {
				int viewWidth = (int) (view.getMeasuredWidth() * zoom + 0.5f);
				int viewHeight = (int) (view.getMeasuredHeight() * zoom + 0.5f);
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
				params.width = viewWidth;
				params.height = viewHeight;
				view.setLayoutParams(params);
			}
		});
	}

	public static void setHolderSize(final View view,final int width,final int height){
		view.post(new Runnable() {
			@Override
			public void run() {
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
				params.width = width;
				params.height = height;
				view.setLayoutParams(params);
			}
		});
	}

	public static void setMargin(final View view,final int left,final int top,final int right,final int bottom){
		view.post(new Runnable() {
			@Override
			public void run() {
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
				params.leftMargin = left;
				params.topMargin = top;
				params.rightMargin = right;
				params.bottomMargin = bottom;
				view.setLayoutParams(params);
			}
		});
	}

	/**
	 * 获取根布局
	 * @param activity
	 * @return
	 */
	public static ViewGroup getRootLayout(Activity activity){
		return (ViewGroup) ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0);
	}

	/**
	 * 是否点击区域外
	 * @return true点击区域外,false点击区域内
	 */
	public static boolean isClickOutsideArea(View view, MotionEvent event){
		if(view != null){
			int[] etPos = new int[2];
			view.getLocationInWindow(etPos);
			int startX = etPos[0];
			int startY = etPos[1];
			int endX = startX + view.getMeasuredWidth() + 500;
			int endY = startY + view.getMeasuredHeight();

			if(event.getX() > startX && event.getX() < endX &&
					event.getY() > startY && event.getY() < endY){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

	/**
	 * 禁用布局中全部子控件
	 * @param viewGroup
	 */
	public static void disableSubControls(ViewGroup viewGroup) {
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			View v = viewGroup.getChildAt(i);
			if (v instanceof ViewGroup){
				disableSubControls((ViewGroup) v);
			}else {
				v.setEnabled(false);
				v.setClickable(false);
			}
		}
	}

	/**
	 * 获取ViewGroup内全部子控件
	 */
	public static ArrayList<View> getSubControls(ViewGroup viewGroup) {
		views.clear();
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			View v = viewGroup.getChildAt(i);
			if (v instanceof ViewGroup){
				getSubControls((ViewGroup) v);
			}else {
				subControlslist.add(v);
			}
		}
		views.addAll(subControlslist);
		subControlslist.clear();
		return views;
	}

}

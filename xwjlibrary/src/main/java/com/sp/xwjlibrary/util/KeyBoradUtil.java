package com.sp.xwjlibrary.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyBoradUtil {
	
	public interface OnKeyBoradListener{
		void onKeyBoradHeight(int height);
	}

	/**
	 * 显示软键盘
	 * 
	 * @param context
	 * @param et
	 */
	public static void showKeyBoard(Context context,EditText et) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(
				Context.INPUT_METHOD_SERVICE);
		//显示软件盘
		imm.showSoftInput(et, 0);
	}
	/**
	 * 隐藏软键盘
	 * @param context
	 * @param et
	 */
	public static void hideKeyBoard(Context context,EditText et){
		InputMethodManager imm = (InputMethodManager) context.getSystemService(
				Context.INPUT_METHOD_SERVICE);
		//隐藏软键盘
		imm.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	/**
	 * 获取软键盘高度
	 * @param activity
	 * @param view
	 * @param listener
	 */
	public static void getKeyBoardHeight(final Activity activity,View view,
			final OnKeyBoradListener listener){
		view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(){
            //当键盘弹出隐藏的时候会 调用此方法。
			//getDecorView() 获取的是整个应用的view 包括标题栏，但是不包括状态栏，r.top就是状态栏的高度
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight =  activity.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;
                listener.onKeyBoradHeight(heightDifference);
            }
            
        });
	}
	
	/**
	 * 是否点击区域外
	 * @return true点击区域外,false点击区域内
	 */
	public static boolean isClickOutsideArea(EditText et, MotionEvent event){
		if(et != null){
			int[] etPos = new int[2];
			et.getLocationInWindow(etPos);
			int etStartX = etPos[0];
			int etStartY = etPos[1];
			int etEndX = etStartX + et.getMeasuredWidth() + 500;
			int etEndY = etStartY + et.getMeasuredHeight();
			
			if(event.getX() > etStartX && event.getX() < etEndX && 
					event.getY() > etStartY && event.getY() < etEndY){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

}














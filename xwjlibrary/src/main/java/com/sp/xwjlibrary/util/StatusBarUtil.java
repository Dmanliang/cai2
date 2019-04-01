package com.sp.xwjlibrary.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

public class StatusBarUtil {

	/**
	 * 设置状态栏透明
	 * @param activity
	 * @param on
	 * @return
	 */
	public static boolean setTransucentStatus(Activity activity,boolean on){
		//activity.getWindow().getDecorView()获取当前activity最顶层的布局对象
		activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
			Window window = activity.getWindow();
			WindowManager.LayoutParams winParams = window.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			if(on){
				winParams.flags |= bits;
			}else{
				winParams.flags &= ~bits;
			}
			window.setAttributes(winParams);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 设置状态栏颜色
	 * @param activity
	 * @param color
	 */
	public static void setStatusBarTint(Activity activity,int color){
		setTransucentStatus(activity, true);
		
		SystemBarTintManager tintManager = new SystemBarTintManager(activity);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintColor(color);

	}
	
	/**
	 * 获取状态栏的高度
	 * @param activity
	 * @return
	 */
	public static int getStatusBarHeight(Activity activity){
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight){
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
	
}














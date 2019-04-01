package com.sp.xwjlibrary.util;

import android.app.Activity;
import android.os.Handler;
import android.view.KeyEvent;

import com.sp.xwjlibrary.R;

public class DoubleClickExit {

	private static boolean isExit;
	public static boolean isExit(Activity activity,int keyCode){
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(!isExit){
				isExit = true;
				ToastUtil.Toast(activity, "再次点击退出");
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						isExit = false;
					}
				}, 2000);
				return false;
			}
		}
		activity.finish();
		activity.overridePendingTransition(R.anim.no_action, R.anim.exit_scale_lessen);
		System.exit(0);
		return false;
	}
	
	
}

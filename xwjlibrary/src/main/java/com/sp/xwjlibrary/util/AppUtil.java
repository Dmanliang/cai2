package com.sp.xwjlibrary.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.HashMap;
import java.util.Locale;

public class AppUtil{

	public static final String LANGUAGE = "com.lib.vinson.util.language";
	public static final String COUNTRY = "com.lib.vinson.util.country";

	/**
	 * 重启APP
	 */
	public static void restartApp(Context mActivity){
		Intent i = mActivity.getPackageManager()
				.getLaunchIntentForPackage(mActivity.getPackageName());
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		mActivity.startActivity(i);
	}

	/**
	 * 重启Activity
	 * @param act
	 */
	public static void restartActivity(Activity act){
		Intent intent = new Intent();
		intent.setClass(act, act.getClass());
		act.startActivity(intent);
		act.finish();

	}

	/**
	 * 注销activity
	 * @param context
	 * @param intentAct
	 */
	public static void logoutApp(Context context,Class<?> intentAct){
		Intent intent = new Intent(context, intentAct);
		intent.putExtra("Class",context.getClass().getSimpleName());
		//清空其他activity
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * 回退到指定页,清掉跳转页上层页
	 * @param context
	 * @param intentAct
	 */
	public static void backAct(Context context,Class<?> intentAct){
		Intent intent = new Intent(context, intentAct);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}

	/**
	 * 获取APP语言
	 * @param context
	 * @return
	 */
	public static HashMap<String, String> getAppLanguage(Context context){
		String language;
		String country;
		HashMap<String,String> map = new HashMap<>();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		language = sp.getString(LANGUAGE,"");
		country = sp.getString(COUNTRY,"");

		if (TextUtils.isEmpty(language)){
			Locale locale = Locale.getDefault();
			language = locale.getLanguage();
			country = locale.getCountry();
		}

		map.put(LANGUAGE,language);
		map.put(COUNTRY,country);

		return map;
	}

	/**
	 * 改变APP语言,修改后要页面重载才会切换
	 * @param context
	 * @param locale
	 */
	public static void changeAppLanguage(final Context context, Locale locale){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		Configuration configuration = resources.getConfiguration();
		configuration.setLocale(locale);

		context.createConfigurationContext(configuration);
		resources.updateConfiguration(configuration, metrics);

		if (context instanceof Application) return;

		//保存语言类型
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor edit = sp.edit();
		edit.putString(LANGUAGE, locale.getLanguage()).apply();
		edit.putString(COUNTRY, locale.getCountry()).apply();
		edit.commit();
	}

	/**
	 * 获取版本号
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context){
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取版本名
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context){
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

}

package com.sp.caitwo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;

import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.bean.UserInfoBean;
import com.sp.caitwo.info.RegionInfo;
import com.sp.caitwo.ui.activity.sysm.LauncherActivity;
import com.sp.xwjlibrary.VinsonApp;
import com.sp.xwjlibrary.util.ACache;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.util.PermissionUtil;
import com.sp.xwjlibrary.util.StatusBarUtil;

import java.util.ArrayList;

import xwj.sqlite.SqliteUtil;

public class App extends VinsonApp {

    public static ArrayList<RegionInfo> provinceList = new ArrayList<>();
    public static ArrayList<RegionInfo> cityList = new ArrayList<>();
    public static UserInfoBean userInfoBean = new UserInfoBean();
    public static HomeWanFaBean homeWanFaBean = new HomeWanFaBean();
    public static String customServerAddr = "";

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycle());
        UserInfoBean userInfo = (UserInfoBean) ACache.get(this).getAsObject("UserInfo");
        if (userInfo != null) {
            App.userInfoBean = userInfo;
        }
    }

    public static void readCityDb(Context context) {
        SqliteUtil.openSqlite(context, R.raw.city_cn, "db", "city_cn.db", "region", new SqliteUtil.onOpenSqlDataCallback() {
            @Override
            public void onSqlData(Cursor cursor) {
                boolean moveToFirst = cursor.moveToFirst();
                while (moveToFirst) {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    int parentId = cursor.getInt(cursor.getColumnIndex("parent_id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    if (parentId == 1) {
                        provinceList.add(new RegionInfo(id,parentId,name));
                    }
                    if (parentId % 10000 == 0) {
                        cityList.add(new RegionInfo(id,parentId,name));
                    }
                    moveToFirst = cursor.moveToNext();
                }
            }
        });
    }

    private class ActivityLifecycle implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (activity instanceof MainActivity)
                //获取AndroidManifest.xml中的权限,并请求(Android6以上要主动请求)
                PermissionUtil.requestPermission(activity);
            //设置固定竖屏
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            StatusBarUtil.setTransucentStatus(activity,true);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            try{
                if (!(activity instanceof LauncherActivity))
                //设置根目录的padding为状态栏高度
                HolderUtil.getRootLayout(activity).getChildAt(0).setPadding(0,StatusBarUtil.getStatusBarHeight(activity),0,0);
            }catch (NullPointerException e){}
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }

}

package com.sp.xwjlibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinson on 2017/9/6.
 */

public class PermissionUtil {

    public static void requestPermission(Activity activity){
        String[] permissions = getManifestPermission(activity);

        if (permissions == null){
            return;
        }

        String needAuth = "";
        int needCount = 0;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission)!= PackageManager.PERMISSION_GRANTED) {
                if(needCount == 0){
                    needAuth = permission;
                }else{
                    needAuth = needAuth + "," + permission;
                }
                needCount ++;
            }
        }
        if(!TextUtils.isEmpty(needAuth)){
            ActivityCompat.requestPermissions(activity,needAuth.split(","),0);
        }
    }

    /**
     * 获取AndroidManifest.xml中的权限
     * @param activity
     * @return
     */
    private static String[] getManifestPermission(Activity activity) {
        String[] permissions = null;
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (packageInfo != null) {
                permissions = packageInfo.requestedPermissions;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return permissions;
    }

}

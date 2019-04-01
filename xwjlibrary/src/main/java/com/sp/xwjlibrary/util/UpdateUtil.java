package com.sp.xwjlibrary.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.RemoteViews;

import com.sp.xwjlibrary.R;

import org.xutils.XCallback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class UpdateUtil{

    private static NotificationUtil notificationUtil;
    private static RemoteViews remoteViews;

    public synchronized static boolean isNeedUpdate(Context context, int serverVersion){
        try {
            int versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS).versionCode;
            if (versionCode < serverVersion){
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized static void downloadApk(final Context context, String apkUrl){
        String appName = context.getResources().getString(context.getApplicationInfo().labelRes);

        notificationUtil = new NotificationUtil(context, R.layout.downnotice);
        notificationUtil.setBuilder(false,"download");
        remoteViews = notificationUtil.getRemoteViews();
        remoteViews.setTextViewText(R.id.tv_header,appName);
        remoteViews.setImageViewResource(R.id.iv_icon,context.getApplicationInfo().icon);

        String filePath = Environment.getExternalStorageDirectory().getPath() + "/" + context.getPackageName() + "/";
        RequestParams params = new RequestParams(apkUrl);
        params.setSaveFilePath(filePath);
        params.setAutoRename(true);
        params.setCancelFast(true);

        x.http().post(params,new XCallback<File>(){
            @Override
            public void onSuccess(File result) {
                notificationUtil.cancle(1);
                remoteViews.setTextViewText(R.id.tv_rate,"0");
                remoteViews.setProgressBar(R.id.progress_bar, 100,0,false);
                installNormal(context,result);
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                String progress = (int) ((float) current / (float) total * 100) + "%";
                remoteViews.setTextViewText(R.id.tv_rate,progress);
                remoteViews.setProgressBar(R.id.progress_bar, (int)total,(int)current,false);
                notificationUtil.send(1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                notificationUtil.cancle(1);
                remoteViews.setTextViewText(R.id.tv_rate,"0");
                remoteViews.setProgressBar(R.id.progress_bar, 100,0,false);
            }
        });
    }

    //普通安装
    public synchronized static void installNormal(Context context, File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", apkFile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

}

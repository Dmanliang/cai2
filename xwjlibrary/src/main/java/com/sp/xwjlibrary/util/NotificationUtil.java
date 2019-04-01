package com.sp.xwjlibrary.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

public class NotificationUtil{

	private RemoteViews mRemoteViews;
	private Context context;
	private NotificationManager notificationManager;
	private NotificationCompat.Builder mBuilder;

	public NotificationUtil(Context context,int layoutId) {
		this.context = context;
		mRemoteViews = new RemoteViews(context.getPackageName(), layoutId);
	}

	public RemoteViews getRemoteViews(){
		return mRemoteViews;
	}

	public void setBuilder(boolean isVibrate,CharSequence name){
		int hintStyle;
		if(!isVibrate){
			hintStyle = Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS;
		}else{
			hintStyle = Notification.DEFAULT_ALL;
		}

		String channelId = context.getPackageName() + "." + context.getClass().getName();
		notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(context, channelId);
		mBuilder.setContent(mRemoteViews);
		//ture，设置他为一个正在进行的通知。
		// 他们通常是用来表示一个后台任务,
		// 用户积极参与(如播放音乐)或以某种方式正在等待,
		// 因此占用设备(如一个文件下载,同步操作,主动网络连接)
		mBuilder.setOngoing(true);
		mBuilder.setWhen(System.currentTimeMillis());
		mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
		mBuilder.setDefaults(hintStyle);
		//必要
		mBuilder.setSmallIcon(context.getApplicationInfo().icon);

		if (isVibrate) {
            //设置震动
            long[] vibrates = {0, 1000, 1000, 1000};
            mBuilder.setVibrate(vibrates);
        }

		//Android8(O)以上版本
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			NotificationChannel channel = new NotificationChannel(channelId,name, NotificationManager.IMPORTANCE_HIGH);
			notificationManager.createNotificationChannel(channel);
		}
	}

	public void send(int id){
		notificationManager.notify(id, mBuilder.build());
	}

	public void cancle(int id){
		notificationManager.cancel(id);
	}

}

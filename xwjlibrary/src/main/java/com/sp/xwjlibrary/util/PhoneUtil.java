package com.sp.xwjlibrary.util;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by vinson on 2017/9/14.
 */

public class PhoneUtil {

    /**
     * 获取手机是否root信息
     * &lt uses-permission android:name="android.permission.READ_PHONE_STATE" /&gt
     * @return
     */
    public static boolean isRoot() {
        boolean bool = false;
        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;
            } else {
                bool = true;
            }
        } catch (Exception e) {
        }
        return bool;
    }

    /**
     * 获取基本信息,IMEI号,IESI号,手机型号,手机品牌,手机号码(有的可得，有的不可得)
     * &lt uses-permission android:name="android.permission.READ_PHONE_STATE" /&gt
     * @param context
     * @return 按数组顺序, IMEI号, IESI号, 手机型号, 手机品牌, 手机号码
     */
    public static String[] getBaseInfo(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String[] infos = new String[5];
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return infos;
        }
        String imei = mTm.getDeviceId();
        String imsi = mTm.getSubscriberId();
        String mtype = android.os.Build.MODEL; // 手机型号
        String mtyb= android.os.Build.BRAND;//手机品牌
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        infos[0] = TextUtils.isEmpty(imei) ? "0" : imei;
        infos[1] = TextUtils.isEmpty(imsi) ? "0" : imsi;
        infos[2] = TextUtils.isEmpty(mtype) ? "0" : mtype;
        infos[3] = TextUtils.isEmpty(mtyb) ? "0" : mtyb;
        infos[4] = TextUtils.isEmpty(numer) ? "0" : numer;
        return infos;
    }

    /**
     * 获取wifi信息
     * 只有手机开启wifi才能获取
     * &lt uses-permission android:name="android.permission.ACCESS_WIFI_STATE" /&gt
     * @return 按数组顺序,SSID,ip,mac,BSSID
     */
    public static String[] getWifiInfo(Context context){
        String[] infos = new String[4];
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        //未连接wifi
        if (wifiInfo == null) return new String[]{};
        int ip = wifiInfo.getIpAddress();
        String strIp = (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);

        String ssid = wifiInfo.getSSID();
        if (ssid.startsWith("\"")){
            ssid = ssid.substring(1);
        }
        if (ssid.endsWith("\"")){
            ssid = ssid.substring(0,ssid.length() - 1);
        }

        infos[0] = ssid;
        infos[1] = strIp;
        infos[2] = getMacAddress();
        infos[3] = wifiInfo.getBSSID();
        return infos;
    }

    /**
     * 真机可行,获取MAC地址
     * @return
     */
    public static String getMacAddress() {
        String address = null;
        // 把当前机器上的访问网络接口的存入 Enumeration集合中
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface netWork = interfaces.nextElement();
                // 如果存在硬件地址并可以使用给定的当前权限访问，则返回该硬件地址（通常是 MAC）。
                byte[] by = netWork.getHardwareAddress();
                if (by == null || by.length == 0) {
                    continue;
                }
                StringBuilder builder = new StringBuilder();
                for (byte b : by) {
                    builder.append(String.format("%02X:", b));
                }
                if (builder.length() > 0) {
                    builder.deleteCharAt(builder.length() - 1);
                }
                String mac = builder.toString();
                // 从路由器上在线设备的MAC地址列表，可以印证设备Wifi的 name 是 wlan0
                if (netWork.getName().equals("wlan0")) {
                    address = mac;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return address;
    }


    public static void callOut(Activity activity,String phoneNum){
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent it = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        activity.startActivity(it);
    }

    public static void startContactAct(Activity activity,int requestCode){
        activity.startActivityForResult(new Intent(
                Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), requestCode);
    }

    public static HashMap<String, String> getContactInfo(Context context, Intent data){
        HashMap<String,String> map = new HashMap<>();
        ContentResolver resolver = context.getContentResolver();
        Uri contactUri = data.getData();
        Cursor nameCursor = resolver.query(contactUri, null, null, null, null);
        if (nameCursor != null){
            nameCursor.moveToFirst();

            String username = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            map.put("name",username);

            String contactId = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,null,null);

            if (phoneCursor != null) {
                while (phoneCursor.moveToNext()) {
                    String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    map.put("phone",phone);
                }
                phoneCursor.close();
            }
            nameCursor.close();
            return map;
        }
        return null;
    }

    /**
     * 获取缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除缓存
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    // 获取文件大小
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

}

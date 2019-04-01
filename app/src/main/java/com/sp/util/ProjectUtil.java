package com.sp.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.bean.SystemConfigBean;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;

public class ProjectUtil {

    public static int getGameIcon(String wanFaName){
        if (wanFaName.contains("快三")){
            return R.drawable.icon_k3;
        }
        if (wanFaName.contains("28")){
            return R.drawable.icon_pc28;
        }
        if (wanFaName.contains("时时彩")){
            return R.drawable.icon_ssc;
        }
        if (wanFaName.contains("分分彩")){
            return R.drawable.icon_ffc;
        }
        if (wanFaName.contains("京东1.5分彩")){
            return R.drawable.icon_2fc;
        }
        if (wanFaName.contains("幸运飞艇") || wanFaName.contains("北京赛车")){
            return R.drawable.icon_pk10;
        }
        if (wanFaName.contains("11选5")){
            return R.drawable.icon_11x5;
        }
        if (wanFaName.contains("六合彩")){
            return R.drawable.icon_lhc;
        }
        if (wanFaName.contains("福彩3D")){
            return R.drawable.icon_fc3d;
        }
        if (wanFaName.contains("排列3")){
            return R.drawable.icon_pl3;
        }
        if (wanFaName.contains("排列5")){
            return R.drawable.icon_pl5;
        }
        return R.drawable.icon_qb;
    }

    public static String getGameForShort(int gameType){
        if (gameType == 7  || gameType == 3 || gameType == 30){
            return "快三";
        }
        if (gameType == 9  || gameType == 1 || gameType == 2){
            return "28";
        }
        if (gameType == 4  || gameType == 10){
            return "时时彩";
        }
        if (gameType == 14){
            return "分分彩";
        }
        if (gameType == 21){
            return "1.5分彩";
        }
        if (gameType == 5 || gameType == 6){
            return "pk10";
        }
        if (gameType == 25){
            return "11选5";
        }
        if (gameType == 11){
            return "六合彩";
        }
        if (gameType == 12){
            return "福彩3D";
        }
        if (gameType == 13){
            return "排列3";
        }
        if (gameType == 15){
            return "排列5";
        }
        return "";
    }

    public static ArrayList<HomeWanFaBean.DataBean> getWanFaMenu(String wanFaName, HomeWanFaBean bean){
        ArrayList<HomeWanFaBean.DataBean> list = new ArrayList<>();
        for (HomeWanFaBean.DataBean dataBean : bean.getData()) {
            if (dataBean.getName().equals("双色球") || dataBean.equals("七星彩") || dataBean.equals("七乐彩")
                    || dataBean.equals("超级大乐透") || dataBean.equals("天津时时彩")) {
                continue;
            }
            if (wanFaName.contains("快三")) {
                if (dataBean.getName().contains("快三")){
                    list.add(dataBean);
                }
            } else if (wanFaName.contains("28")) {
                if (dataBean.getName().contains("28")){
                    list.add(dataBean);
                }
            } else if (wanFaName.contains("时时彩")) {
                if (dataBean.getName().contains("时时彩")){
                    list.add(dataBean);
                }
            } else if (wanFaName.contains("分分彩") || wanFaName.contains("东京1.5分彩")) {
                if (dataBean.getName().contains("分分彩") || dataBean.getName().contains("东京1.5分彩")){
                    list.add(dataBean);
                }
            } else if (wanFaName.contains("幸运飞艇") || wanFaName.contains("北京赛车")) {
                if (dataBean.getName().contains("幸运飞艇") || dataBean.getName().contains("北京赛车")){
                    list.add(dataBean);
                }
            } else if (wanFaName.contains("11选5")) {
                if (dataBean.getName().contains("11选5")){
                    list.add(dataBean);
                }
            } else if (wanFaName.contains("六合彩")) {
                if (dataBean.getName().contains("六合彩")){
                    list.add(dataBean);
                }
            } else if (wanFaName.contains("福彩3D") || wanFaName.contains("排列3")) {
                if (dataBean.getName().contains("福彩3D") || dataBean.getName().contains("排列3")){
                    list.add(dataBean);
                }
            } else if (wanFaName.contains("排列5")) {
                if (dataBean.getName().contains("排列5")){
                    list.add(dataBean);
                }
            }
        }
        return list;
    }

    public static void copy(Context context,String data){
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText(data,data));
        ToastUtil.Toast(context,"复制成功");
    }

    public static boolean functionIsNeed(SystemConfigBean json,String name) {
        for (SystemConfigBean.DataBean dataBean : json.getData()) {
            if (BaseUtil.equals(dataBean.getName(),name)){
                return BaseUtil.equals(dataBean.getParam_value(),"1");
            }
        }
        return true;
    }

}











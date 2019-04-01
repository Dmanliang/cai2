package com.sp.caitwo.base;

import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.LhcAllWanFaInfo;
import com.sp.util.ViewUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */

public class LotteryCons {

    public static final int DALETOU = 23;//大乐透
    public static final int YDWFC = 21;//1.5分彩彩
    public static final int FENFENCAI = 14;//分分彩
    public static final int PAILIE3 = 13;//排列3
    public static final int PAILIE5 = 15;//排列5
    public static final int FUCAI3D = 12;//福彩3D
    public static final int QILECAI = 19;//七乐彩
    public static final int QIXINCAI = 17;//七星彩
    public static final int SHUANSEQIU = 16;//双色球
    public static final int ELEVENXFIVE = 25;//11选5
    public static final int SIXHECAI = 11;//六合彩

    public static final int XJP28 = 9;//新加坡28
    public static final int JND_28 = 2;//加拿大28
    public static final int BJ_28 = 1;//北京28
    public static final int BJPK10 = 5;//北京pk10
    public static final int XYPK10 = 6;//幸运pk10
    public static final int CQSSC = 4;//重庆时时彩
    public static final int TJSSC = 10;//天津时时彩
    public static final int JSK3 = 7;//江苏快3
    public static final int BJK3 = 3;//北京快3
    public static final int BJL = 8;//百家乐
    public static final int AHK3 = 30;//安徽快3


    public static final int[] iconRes = {0,
            R.drawable.icon_pc28, R.drawable.icon_pc28, R.drawable.icon_k3,
            R.drawable.icon_ssc, R.drawable.icon_pk10, R.drawable.icon_pk10,
            R.drawable.icon_k3, 0, R.drawable.icon_pc28,
            R.drawable.icon_ssc, R.drawable.icon_lhc, R.drawable.icon_fc3d,
            R.drawable.icon_pl3, R.drawable.icon_ffc, R.drawable.icon_pl5,
            R.drawable.icon_ssq, R.drawable.icon_qxc, 0,
            R.drawable.icon_qlc, 0, R.drawable.icon_2fc,
            0, R.drawable.icon_dlt, 0,
            R.drawable.icon_11x5, 0, 0,
            0, 0, R.drawable.icon_k3};


    public static final String REFRESH_DATA = "refresh_data";

    public static void setImageIcn(ImageView view, int game_type) {

        int cateType = getCategory_type(game_type);
        int icon = R.drawable.icon_k3;
        if (game_type == 1 || game_type == 2 || game_type == 9) {
            icon = R.drawable.icon_pc28;
        } else if (game_type == 21) {
            icon = R.drawable.icon_2fc;
        } else if (game_type == 14) {
            icon = R.drawable.icon_ffc;
        } else if (game_type == 11) {
            icon = R.drawable.icon_lhc;
        } else if (game_type == 12) {
            icon = R.drawable.icon_fc3d;
        } else if (game_type == 13) {
            icon = R.drawable.icon_pl3;
        } else if (game_type == 15) {
            icon = R.drawable.icon_pl5;
        } else {
            switch (cateType) {//category_type 1快3，2时时彩，3快乐彩，4 11选5 ， 5低频彩 ，6 游戏彩 ，7体育彩
                case 0:
                    break;
                case 1:
                    icon = R.drawable.icon_k3;
                    break;
                case 2:
                    icon = R.drawable.icon_ssc;
                    break;
                case 3:
                    icon = R.drawable.icon_pk10;
                    break;
                case 4:
                    icon = R.drawable.icon_11x5;
                    break;
                case 5:
                    icon = R.drawable.icon_11x5;
                    break;
            }
        }
        view.setImageResource(icon);

    }

    public static int getCategory_type(int game_type) {

        if (App.homeWanFaBean != null && null != App.homeWanFaBean.getData()) {
            for (HomeWanFaBean.DataBean dataBean : App.homeWanFaBean.getData()) {
                if (game_type == dataBean.getGame_type()) {
                    return dataBean.getCategory_type();
                }
            }
        }

        return 0;
    }

    public static int[] randomOneCommon(int min, int max, int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if ((int) (1 + Math.random() * (max - min)) < 0) {
                result[i] = 0;
            } else if ((int) (1 + Math.random() * (max - min)) >= max) {
                result[i] = (int) (1 + Math.random() * (max - min)) - 1;
            } else {
                result[i] = (int) (1 + Math.random() * (max - min));
            }
        }
        return result;

    }

    /**
     * 随机指定范围内N个重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static Integer[] rerandomCommon(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            result.add(num);
            count++;
        }
        return result.toArray(new Integer[result.size()]);
    }

    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static Integer[] randomCommon(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < result.size(); j++) {
                if (num == result.get(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result.add(num);
                count++;
            }
        }
        return result.toArray(new Integer[result.size()]);
    }


    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min   指定范围最小值
     * @param max   指定范围最大值
     * @param n     随机数个数
     * @param renum 去除重复数
     */
    public static int[] randomCommon(int min, int max, int n, int renum[]) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        if (renum == null) {
            renum = new int[]{};
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            for (int k = 0; k < renum.length; k++) {
                if (num == renum[k]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    public static String getLevelDj(int user_level) {
        String levelDj = "新手";
        switch (user_level) {

            case 1:
                levelDj = "新手";
                break;
            case 2:
                levelDj = "青铜";
                break;
            case 3:
                levelDj = "白银";
                break;
            case 4:
                levelDj = "黄金";
                break;
            case 5:
                levelDj = "铂金";
                break;
            case 6:
                levelDj = "钻石";
                break;

            case 7:
                levelDj = "星耀";
                break;
            case 8:
                levelDj = "王者";
                break;
            case 9:
                levelDj = "彩神";
                break;
        }
        return levelDj;
    }

    /**
     * 积分
     *
     * @param user_level
     * @return
     */
    public static String getLevelJF(int user_level) {
        String levelDj = "0";
        switch (user_level) {

            case 1:
                levelDj = "0";
                break;
            case 2:
                levelDj = "10";
                break;
            case 3:
                levelDj = "200";
                break;
            case 4:
                levelDj = "1000";
                break;
            case 5:
                levelDj = "10000";
                break;
            case 6:
                levelDj = "50000";
                break;

            case 7:
                levelDj = "250000";
                break;
            case 8:
                levelDj = "1000000";
                break;
            case 9:
                levelDj = "5000000";
                break;
        }
        return levelDj;
    }


    /**
     * 晋级奖励(元)
     *
     * @param user_level
     * @return
     */
    public static String getLevelPro(int user_level) {
        String levelDj = "0";
        switch (user_level) {

            case 1:
                levelDj = "0";
                break;
            case 2:
                levelDj = "1";
                break;
            case 3:
                levelDj = "5";
                break;
            case 4:
                levelDj = "10";
                break;
            case 5:
                levelDj = "58";
                break;
            case 6:
                levelDj = "318";
                break;

            case 7:
                levelDj = "1688";
                break;
            case 8:
                levelDj = "6888";
                break;
            case 9:
                levelDj = "38888";
                break;
        }
        return levelDj;
    }


    public static void setWanfaDec(TextView tvWanfaIntroduce, String wanfa_dec, double maxBili) {
        tvWanfaIntroduce.setText(Html.fromHtml(wanfa_dec + String.format("<font color='#E54B55'> 赔率:%s倍</font>", maxBili)));
    }

    public static void setWanfaDec(TextView tvWanfaIntroduce, int titleindex, int tabindex, List<BiliListInfo.DataBean> bililist) {
        String dec = "";
        double maxBill = 0;
        if (!TextUtils.isEmpty(bililist.get(titleindex).getWanfa_dec()) && bililist.get(titleindex).getMaxBili() != 0) {
            dec = bililist.get(titleindex).getWanfa_dec();
            maxBill = bililist.get(titleindex).getMaxBili();
        } else {
            if (bililist.get(titleindex).getList() != null && bililist.get(titleindex).getList().size() > 0) {
                dec = bililist.get(titleindex).getWanfa_dec();
                maxBill = bililist.get(titleindex).getList().get(tabindex).getBili();
            } else if (bililist.get(titleindex).getLists() != null && bililist.get(titleindex).getLists().size() > 0) {
                dec = bililist.get(titleindex).getLists().get(tabindex).getWanfa_dec();
                maxBill = bililist.get(titleindex).getLists().get(tabindex).getWanfa_odds();
            }
            if (bililist.get(titleindex).getMaxBili() != 0) {
                maxBill = bililist.get(titleindex).getMaxBili();
            }
        }
        tvWanfaIntroduce.setText(Html.fromHtml(dec + String.format("<font color='#E54B55'> 赔率:%s倍</font>", maxBill)));
    }

    public static void setLhcWanfaDec(TextView tvWanfaIntroduce, int titleindex, int tabindex, List<LhcAllWanFaInfo.DataBean> bililist) {
        String dec = "";
        double maxBill = 0;
        if (!TextUtils.isEmpty(bililist.get(titleindex).getWanfa_dec())) {
            dec = bililist.get(titleindex).getWanfa_dec();
            maxBill = bililist.get(titleindex).getMax_bili();
        } else {
            if (bililist.get(titleindex).getLists() != null && bililist.get(titleindex).getLists().size() > 0) {
                dec = bililist.get(titleindex).getLists().get(tabindex).getWanfa_dec();
                maxBill = bililist.get(titleindex).getLists().get(tabindex).getMax_bili();
            }
        }
        tvWanfaIntroduce.setText(Html.fromHtml(dec + String.format("<font color='#E54B55'> 赔率:%s倍</font>", maxBill)));
    }


}

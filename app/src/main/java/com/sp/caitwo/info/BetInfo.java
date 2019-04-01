package com.sp.caitwo.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BetInfo implements Serializable {


    public String wanfa_name;
    public String bet_dec;
    public int bet_num;
    public String bet_money;//每注多少钱
    public double bet_point;//每注多少钱
    private List<DataBean> data=new ArrayList<>();

    public int getBet_num() {
        return bet_num;
    }

    public void setBet_num(int bet_num) {
        this.bet_num = bet_num;
    }

    public double getBet_point() {
        return bet_point;
    }

    public void setBet_point(double bet_point) {
        this.bet_point = bet_point;
    }

    public String getWanfa_name() {
        return wanfa_name;
    }

    public void setWanfa_name(String wanfa_name) {
        this.wanfa_name = wanfa_name;
    }

    public String getBet_dec() {
        return bet_dec;
    }

    public void setBet_dec(String bet_dec) {
        this.bet_dec = bet_dec;
    }

    public String getBet_money() {
        return bet_money;
    }

    public void setBet_money(String bet_money) {
        this.bet_money = bet_money;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        for (DataBean datum : data) {
            this.data.add(datum);
        }
    }

    public static class DataBean implements Serializable {
        //增加bet_count注数，multiple_count倍数，
        //unit_type单位模式 1元 2角
        //wanfa_type玩法名称，参数列表的分类，用逗号分隔
        public double point;
        public int bili_id;
        public int bet_count;

        public int multiple_count;
        public int unit_type;
        public String wanfa_type;
        public String betNumbers;//不定位胆使用

        //直投使用
        public int wanfa_id;
        public double money;
        public double totalMoney;
        public String numbers;

        public int min_bet_count;//最少需要几个码才可以投注
        public boolean is_min_bet_count;//是否满足最少需要几个码才投注

        //六合彩使用
        public String wanfa_name;
        public int isbetcount; // 0 自定义下注 1快捷下注
        public int betcount;  //快捷下注




    }


}

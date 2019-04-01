package com.sp.caitwo.info;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BiliListInfo implements Serializable {


    private String timestamp;
    private String request_id;
    private String result_code;
    private String result_desc;
    private List<DataBean> data;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_desc() {
        return result_desc;
    }

    public void setResult_desc(String result_desc) {
        this.result_desc = result_desc;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean implements Serializable {

        private String name;
        private double maxBili;
        private double wanfa_odds;
        private String wanfa_dec;
        private int someNum;
        private int wanfa_id;
        private List<ListBean> list = new ArrayList<>();
        private List<ListBeans> lists = new ArrayList<>();

        public double getWanfa_odds() {
            return wanfa_odds;
        }

        public void setWanfa_odds(double wanfa_odds) {
            this.wanfa_odds = wanfa_odds;
        }

        public int getWanfa_id() {
            return wanfa_id;
        }

        public void setWanfa_id(int wanfa_id) {
            this.wanfa_id = wanfa_id;
        }

        public int getSomeNum() {
            return someNum;
        }

        public void setSomeNum(int someNum) {
            this.someNum = someNum;
        }

        public String getWanfa_dec() {
            return wanfa_dec;
        }

        public void setWanfa_dec(String wanfa_dec) {
            this.wanfa_dec = wanfa_dec;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<ListBeans> getLists() {
            return lists;
        }

        public void setLists(List<ListBeans> lists) {
            this.lists = lists;
        }

        public double getMaxBili() {
            return maxBili;
        }

        public void setMaxBili(double maxBili) {
            this.maxBili = maxBili;
        }

    }

    public static class ListBeans implements Serializable {

        private String name = "";
        private String wanfa_dec;
        private int wanfa_id;
        private double wanfa_odds;
        private double min_money;
        private double max_money;
        private List<ListBean> list = new ArrayList<>();
        private List<ListBean2> lists = new ArrayList<>();
        public int someNum;

        public int getSomeNum() {
            return someNum;
        }

        public void setSomeNum(int someNum) {
            this.someNum = someNum;
        }

        public String getWanfa_dec() {
            return wanfa_dec;
        }

        public void setWanfa_dec(String wanfa_dec) {
            this.wanfa_dec = wanfa_dec;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWanfa_id() {
            return wanfa_id;
        }

        public void setWanfa_id(int wanfa_id) {
            this.wanfa_id = wanfa_id;
        }

        public double getWanfa_odds() {
            return wanfa_odds;
        }

        public void setWanfa_odds(double wanfa_odds) {
            this.wanfa_odds = wanfa_odds;
        }

        public double getMin_money() {
            return min_money;
        }

        public void setMin_money(double min_money) {
            this.min_money = min_money;
        }

        public double getMax_money() {
            return max_money;
        }

        public void setMax_money(double max_money) {
            this.max_money = max_money;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<ListBean2> getLists() {
            return lists;
        }

        public void setLists(List<ListBean2> lists) {
            this.lists = lists;
        }

    }

    public static class ListBean implements Serializable {

        private int id;
        private int game_type;
        private int bili_type;
        private String bili_name;
        private double bili;
        private String result;
        private String result_descX;
        private int area_id;
        private double min_point;
        private double max_point;
        public boolean isFocus;
        private String wanfa_name;
        private int is_disable;
        private double odd;
        private String wanfa_dec;
        public int someNum;

        public int getSomeNum() {
            return someNum;
        }

        public void setSomeNum(int someNum) {
            this.someNum = someNum;
        }

        public String getWanfa_dec() {
            return wanfa_dec;
        }

        public void setWanfa_dec(String wanfa_dec) {
            this.wanfa_dec = wanfa_dec;
        }

        public String getWanfa_name() {
            return wanfa_name;
        }

        public void setWanfa_name(String wanfa_name) {
            this.wanfa_name = wanfa_name;
        }

        public int getIs_disable() {
            return is_disable;
        }

        public void setIs_disable(int is_disable) {
            this.is_disable = is_disable;
        }

        public double getOdd() {
            return odd;
        }

        public void setOdd(double odd) {
            this.odd = odd;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGame_type() {
            return game_type;
        }

        public void setGame_type(int game_type) {
            this.game_type = game_type;
        }

        public int getBili_type() {
            return bili_type;
        }

        public void setBili_type(int bili_type) {
            this.bili_type = bili_type;
        }

        public String getBili_name() {
            if (TextUtils.isEmpty(bili_name)) {
                return wanfa_name;
            }
            return bili_name;
        }

        public void setBili_name(String bili_name) {
            this.bili_name = bili_name;
        }

        public double getBili() {
            return bili;
        }

        public void setBili(double bili) {
            this.bili = bili;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getResult_descX() {
            return result_descX;
        }

        public void setResult_descX(String result_descX) {
            this.result_descX = result_descX;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public double getMin_point() {
            return min_point;
        }

        public void setMin_point(double min_point) {
            this.min_point = min_point;
        }

        public double getMax_point() {
            return max_point;
        }

        public void setMax_point(double max_point) {
            this.max_point = max_point;
        }

        public boolean isFocus() {
            return isFocus;
        }

        public void setFocus(boolean focus) {
            isFocus = focus;
        }

    }

    public static class ListBean2 implements Serializable {

        private String name;
        private int wanfa_id;
        private String wanfa_type;
        private double wanfa_odds;
        private double min_money;
        private double max_money;
        private String wanfa_dec;
        public int someNum;
        private List<ListBean> list = new ArrayList<>();

        public int getSomeNum() {
            return someNum;
        }

        public void setSomeNum(int someNum) {
            this.someNum = someNum;
        }

        public int getWanfa_id() {
            return wanfa_id;
        }

        public void setWanfa_id(int wanfa_id) {
            this.wanfa_id = wanfa_id;
        }

        public String getWanfa_type() {
            return wanfa_type;
        }

        public void setWanfa_type(String wanfa_type) {
            this.wanfa_type = wanfa_type;
        }

        public double getWanfa_odds() {
            return wanfa_odds;
        }

        public void setWanfa_odds(double wanfa_odds) {
            this.wanfa_odds = wanfa_odds;
        }

        public double getMin_money() {
            return min_money;
        }

        public void setMin_money(double min_money) {
            this.min_money = min_money;
        }

        public double getMax_money() {
            return max_money;
        }

        public void setMax_money(double max_money) {
            this.max_money = max_money;
        }

        public String getWanfa_dec() {
            return wanfa_dec;
        }

        public void setWanfa_dec(String wanfa_dec) {
            this.wanfa_dec = wanfa_dec;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

    }
}

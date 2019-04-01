package com.sp.caitwo.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/11/13.
 */

public class DailyBonusBean {


    /**
     * data : [{"user_level":3,"bet_limit":100,"bonus_rate":0.1},{"user_level":3,"bet_limit":100000,"bonus_rate":0.2},{"user_level":9,"bet_limit":500000,"bonus_rate":0.9}]
     * timestamp : 2018-11-14 19:42:32
     * request_id : 1542195752294
     * result_code : 0
     * result_desc : 请求成功
     */

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

    public static class DataBean {
        /**
         * user_level : 3
         * bet_limit : 100
         * bonus_rate : 0.1
         */

        private int user_level;
        private int bet_limit;
        private double bonus_rate;
        private String bonus_rate1;
        private String bonus_rate2 ;
        private String bonus_rate3;

        public String getBonus_rate1() {
            return bonus_rate1;
        }

        public void setBonus_rate1(String bonus_rate1) {
            this.bonus_rate1 = bonus_rate1;
        }

        public String getBonus_rate2() {
            return bonus_rate2;
        }

        public void setBonus_rate2(String bonus_rate2) {
            this.bonus_rate2 = bonus_rate2;
        }

        public String getBonus_rate3() {
            return bonus_rate3;
        }

        public void setBonus_rate3(String bonus_rate3) {
            this.bonus_rate3 = bonus_rate3;
        }

        public int getUser_level() {
            return user_level;
        }

        public void setUser_level(int user_level) {
            this.user_level = user_level;
        }

        public int getBet_limit() {
            return bet_limit;
        }

        public void setBet_limit(int bet_limit) {
            this.bet_limit = bet_limit;
        }

        public double getBonus_rate() {
            return bonus_rate;
        }

        public void setBonus_rate(double bonus_rate) {
            this.bonus_rate = bonus_rate;
        }
    }
}

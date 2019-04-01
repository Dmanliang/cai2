package com.sp.caitwo.bean;

/**
 * Created by Administrator on 2018/11/13.
 */

public class BetBonusBean {


    /**
     * data : {"userBetBonus":100,"userLevel":4,"bonusRate":0,"yesterdayBetMoney":10}
     * timestamp : 2018-11-14 19:49:15
     * request_id : 1542196153626
     * result_code : 0
     * result_desc : 请求成功
     */

    private DataBean data;
    private String timestamp;
    private String request_id;
    private String result_code;
    private String result_desc;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * userBetBonus : 100
         * userLevel : 4
         * bonusRate : 0
         * yesterdayBetMoney : 10
         */

        private double userBetBonus;
        private int userLevel;
        private double bonusRate;
        private double yesterdayBetMoney;
        //获取用户等级晋升奖励
        private String userLevelName;
        private double userLevelPromotionBonus;


        public String getUserLevelName() {
            return userLevelName;
        }

        public void setUserLevelName(String userLevelName) {
            this.userLevelName = userLevelName;
        }

        public double getUserLevelPromotionBonus() {
            return userLevelPromotionBonus;
        }

        public void setUserLevelPromotionBonus(double userLevelPromotionBonus) {
            this.userLevelPromotionBonus = userLevelPromotionBonus;
        }

        public double getUserBetBonus() {
            return userBetBonus;
        }

        public void setUserBetBonus(int userBetBonus) {
            this.userBetBonus = userBetBonus;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public double getBonusRate() {
            return bonusRate;
        }

        public void setBonusRate(int bonusRate) {
            this.bonusRate = bonusRate;
        }

        public double getYesterdayBetMoney() {
            return yesterdayBetMoney;
        }

        public void setYesterdayBetMoney(int yesterdayBetMoney) {
            this.yesterdayBetMoney = yesterdayBetMoney;
        }
    }
}

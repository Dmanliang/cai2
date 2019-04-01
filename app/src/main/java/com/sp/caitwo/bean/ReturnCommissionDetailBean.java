package com.sp.caitwo.bean;

import java.util.List;

public class ReturnCommissionDetailBean {


    /**
     * data : [{"create_day":"2018-11-22","bet_money":1000,"share_profit_money":30,"bet_count":6},{"create_day":"2018-11-03","bet_money":5586,"share_profit_money":50.88,"bet_count":133}]
     * timestamp : 2018-11-26 16:58:48
     * request_id : 1543222728770
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
         * create_day : 2018-11-22
         * bet_money : 1000
         * share_profit_money : 30
         * bet_count : 6
         */

        private String create_day;
        private double bet_money;
        private double share_profit_money;
        private int bet_count;

        public String getCreate_day() {
            return create_day;
        }

        public void setCreate_day(String create_day) {
            this.create_day = create_day;
        }

        public double getBet_money() {
            return bet_money;
        }

        public void setBet_money(double bet_money) {
            this.bet_money = bet_money;
        }

        public double getShare_profit_money() {
            return share_profit_money;
        }

        public void setShare_profit_money(double share_profit_money) {
            this.share_profit_money = share_profit_money;
        }

        public int getBet_count() {
            return bet_count;
        }

        public void setBet_count(int bet_count) {
            this.bet_count = bet_count;
        }
    }
}

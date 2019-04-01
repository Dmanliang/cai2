package com.sp.caitwo.bean;

import java.util.List;

public class SubordinateReportFormsBean {


    /**
     * data : {"total":1,"list":[{"account":"qqqqqq","agent_type":1,"user_level":3,"bet_money":150150,"win_money":51915.72,"share_profit_money":0,"gift_money":0,"win_lost_money":-98234.28}]}
     * timestamp : 2018-11-26 13:51:26
     * request_id : 1543211486676
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
         * total : 1
         * list : [{"account":"qqqqqq","agent_type":1,"user_level":3,"bet_money":150150,"win_money":51915.72,"share_profit_money":0,"gift_money":0,"win_lost_money":-98234.28}]
         */

        private int total;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * account : qqqqqq
             * agent_type : 1
             * user_level : 3
             * bet_money : 150150
             * win_money : 51915.72
             * share_profit_money : 0
             * gift_money : 0
             * win_lost_money : -98234.28
             */

            private String account;
            private int agent_type;
            private int user_level;
            private double bet_money;
            private double win_money;
            private double share_profit_money;
            private double gift_money;
            private double win_lost_money;
            private double recharge_money;
            private double withdraw_money;

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public int getAgent_type() {
                return agent_type;
            }

            public void setAgent_type(int agent_type) {
                this.agent_type = agent_type;
            }

            public int getUser_level() {
                return user_level;
            }

            public void setUser_level(int user_level) {
                this.user_level = user_level;
            }

            public double getBet_money() {
                return bet_money;
            }

            public void setBet_money(double bet_money) {
                this.bet_money = bet_money;
            }

            public double getWin_money() {
                return win_money;
            }

            public void setWin_money(double win_money) {
                this.win_money = win_money;
            }

            public double getShare_profit_money() {
                return share_profit_money;
            }

            public void setShare_profit_money(double share_profit_money) {
                this.share_profit_money = share_profit_money;
            }

            public double getGift_money() {
                return gift_money;
            }

            public void setGift_money(double gift_money) {
                this.gift_money = gift_money;
            }

            public double getWin_lost_money() {
                return win_lost_money;
            }

            public void setWin_lost_money(double win_lost_money) {
                this.win_lost_money = win_lost_money;
            }

            public double getRecharge_money() {
                return recharge_money;
            }

            public void setRecharge_money(double recharge_money) {
                this.recharge_money = recharge_money;
            }

            public double getWithdraw_money() {
                return withdraw_money;
            }

            public void setWithdraw_money(double withdraw_money) {
                this.withdraw_money = withdraw_money;
            }
        }
    }
}

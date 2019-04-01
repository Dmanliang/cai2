package com.sp.caitwo.bean;

public class TeamReportFormsBean {


    /**
     * data : {"bet_money":195554,"win_money":76071.83,"gift_money":9694.86,"share_profit_money":388.12,"win_lost_money":-109776.75,"bet_people":21,"first_recharge_people":0,"register_people":107,"sub_agent_people":146,"team_balance":2061263.89,"recharge_money":529805.84,"withdrawals_money":542,"team_profit_money":10.56}
     * timestamp : 2018-11-26 10:48:40
     * request_id : 1543200520396
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
         * bet_money : 195554
         * win_money : 76071.83
         * gift_money : 9694.86
         * share_profit_money : 388.12
         * win_lost_money : -109776.75
         * bet_people : 21
         * first_recharge_people : 0
         * register_people : 107
         * sub_agent_people : 146
         * team_balance : 2061263.89
         * recharge_money : 529805.84
         * withdrawals_money : 542
         * team_profit_money : 10.56
         */

        private double bet_money;
        private double win_money;
        private double gift_money;
        private double share_profit_money;
        private double win_lost_money;
        private int bet_people;
        private int first_recharge_people;
        private int register_people;
        private int sub_agent_people;
        private double team_balance;
        private double recharge_money;
        private double withdrawals_money;
        private double team_profit_money;

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

        public double getGift_money() {
            return gift_money;
        }

        public void setGift_money(double gift_money) {
            this.gift_money = gift_money;
        }

        public double getShare_profit_money() {
            return share_profit_money;
        }

        public void setShare_profit_money(double share_profit_money) {
            this.share_profit_money = share_profit_money;
        }

        public double getWin_lost_money() {
            return win_lost_money;
        }

        public void setWin_lost_money(double win_lost_money) {
            this.win_lost_money = win_lost_money;
        }

        public int getBet_people() {
            return bet_people;
        }

        public void setBet_people(int bet_people) {
            this.bet_people = bet_people;
        }

        public int getFirst_recharge_people() {
            return first_recharge_people;
        }

        public void setFirst_recharge_people(int first_recharge_people) {
            this.first_recharge_people = first_recharge_people;
        }

        public int getRegister_people() {
            return register_people;
        }

        public void setRegister_people(int register_people) {
            this.register_people = register_people;
        }

        public int getSub_agent_people() {
            return sub_agent_people;
        }

        public void setSub_agent_people(int sub_agent_people) {
            this.sub_agent_people = sub_agent_people;
        }

        public double getTeam_balance() {
            return team_balance;
        }

        public void setTeam_balance(double team_balance) {
            this.team_balance = team_balance;
        }

        public double getRecharge_money() {
            return recharge_money;
        }

        public void setRecharge_money(double recharge_money) {
            this.recharge_money = recharge_money;
        }

        public double getWithdrawals_money() {
            return withdrawals_money;
        }

        public void setWithdrawals_money(double withdrawals_money) {
            this.withdrawals_money = withdrawals_money;
        }

        public double getTeam_profit_money() {
            return team_profit_money;
        }

        public void setTeam_profit_money(double team_profit_money) {
            this.team_profit_money = team_profit_money;
        }
    }
}

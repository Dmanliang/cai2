package com.sp.caitwo.ui.info;

public class ProfitLossInfo {

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

        private int user_id;
        private double point;
        private double bet_point;
        private double get_point;
        private double withdrawals_logs_point;
        private double agent_profit_point;
        private double activity_gift_point;
        private double recharge_point;
        private double profit_loss;
        private double send_red_pack_money;
        private double get_red_pack_money;
        private double return_red_pack_money;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public double getBet_point() {
            return bet_point;
        }

        public void setBet_point(double bet_point) {
            this.bet_point = bet_point;
        }

        public double getGet_point() {
            return get_point;
        }

        public void setGet_point(double get_point) {
            this.get_point = get_point;
        }

        public double getWithdrawals_logs_point() {
            return withdrawals_logs_point;
        }

        public void setWithdrawals_logs_point(double withdrawals_logs_point) {
            this.withdrawals_logs_point = withdrawals_logs_point;
        }

        public double getAgent_profit_point() {
            return agent_profit_point;
        }

        public void setAgent_profit_point(double agent_profit_point) {
            this.agent_profit_point = agent_profit_point;
        }

        public double getActivity_gift_point() {
            return activity_gift_point;
        }

        public void setActivity_gift_point(double activity_gift_point) {
            this.activity_gift_point = activity_gift_point;
        }

        public double getRecharge_point() {
            return recharge_point;
        }

        public void setRecharge_point(double recharge_point) {
            this.recharge_point = recharge_point;
        }

        public double getProfit_loss() {
            return profit_loss;
        }

        public void setProfit_loss(double profit_loss) {
            this.profit_loss = profit_loss;
        }

        public double getSend_red_pack_money() {
            return send_red_pack_money;
        }

        public void setSend_red_pack_money(double send_red_pack_money) {
            this.send_red_pack_money = send_red_pack_money;
        }

        public double getGet_red_pack_money() {
            return get_red_pack_money;
        }

        public void setGet_red_pack_money(double get_red_pack_money) {
            this.get_red_pack_money = get_red_pack_money;
        }

        public double getReturn_red_pack_money() {
            return return_red_pack_money;
        }

        public void setReturn_red_pack_money(double return_red_pack_money) {
            this.return_red_pack_money = return_red_pack_money;
        }
    }
}

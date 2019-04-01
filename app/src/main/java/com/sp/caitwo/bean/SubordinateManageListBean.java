package com.sp.caitwo.bean;

import java.util.List;

public class SubordinateManageListBean {


    /**
     * data : {"total":1,"list":[{"user_id":3058,"sub_count":0,"account":"2558144","agent_level":1,"agent_type":1,"balance":0,"login_time":1541143864000,"kuai_three_profit":6,"shishicai_profit":6,"eleven_five_profit":6,"fucai_profit":6,"array_profit":6,"twenty_eight_profit":10,"pk_ten_profit":6,"lhc_profit":7,"low_frequency_profit":7}]}
     * timestamp : 2018-11-27 16:18:41
     * request_id : 1543306721017
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
         * list : [{"user_id":3058,"sub_count":0,"account":"2558144","agent_level":1,"agent_type":1,"balance":0,"login_time":1541143864000,"kuai_three_profit":6,"shishicai_profit":6,"eleven_five_profit":6,"fucai_profit":6,"array_profit":6,"twenty_eight_profit":10,"pk_ten_profit":6,"lhc_profit":7,"low_frequency_profit":7}]
         */

        private String total;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
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
             * user_id : 3058
             * sub_count : 0
             * account : 2558144
             * agent_level : 1
             * agent_type : 1
             * balance : 0
             * login_time : 1541143864000
             * kuai_three_profit : 6
             * shishicai_profit : 6
             * eleven_five_profit : 6
             * fucai_profit : 6
             * array_profit : 6
             * twenty_eight_profit : 10
             * pk_ten_profit : 6
             * lhc_profit : 7
             * low_frequency_profit : 7
             */

            private String user_id;
            private String sub_count;
            private String account;
            private String agent_level;
            private String agent_type;
            private String balance;
            private long login_time;
            private String kuai_three_profit;
            private String shishicai_profit;
            private String eleven_five_profit;
            private String fucai_profit;
            private String array_profit;
            private String twenty_eight_profit;
            private String pk_ten_profit;
            private String lhc_profit;
            private String low_frequency_profit;
            private String user_level;
            private String agent_profit_mooney;
            private long register_time;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getSub_count() {
                return sub_count;
            }

            public void setSub_count(String sub_count) {
                this.sub_count = sub_count;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getAgent_level() {
                return agent_level;
            }

            public void setAgent_level(String agent_level) {
                this.agent_level = agent_level;
            }

            public String getAgent_type() {
                return agent_type;
            }

            public void setAgent_type(String agent_type) {
                this.agent_type = agent_type;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public long getLogin_time() {
                return login_time;
            }

            public void setLogin_time(long login_time) {
                this.login_time = login_time;
            }

            public String getKuai_three_profit() {
                return kuai_three_profit;
            }

            public void setKuai_three_profit(String kuai_three_profit) {
                this.kuai_three_profit = kuai_three_profit;
            }

            public String getShishicai_profit() {
                return shishicai_profit;
            }

            public void setShishicai_profit(String shishicai_profit) {
                this.shishicai_profit = shishicai_profit;
            }

            public String getEleven_five_profit() {
                return eleven_five_profit;
            }

            public void setEleven_five_profit(String eleven_five_profit) {
                this.eleven_five_profit = eleven_five_profit;
            }

            public String getFucai_profit() {
                return fucai_profit;
            }

            public void setFucai_profit(String fucai_profit) {
                this.fucai_profit = fucai_profit;
            }

            public String getArray_profit() {
                return array_profit;
            }

            public void setArray_profit(String array_profit) {
                this.array_profit = array_profit;
            }

            public String getTwenty_eight_profit() {
                return twenty_eight_profit;
            }

            public void setTwenty_eight_profit(String twenty_eight_profit) {
                this.twenty_eight_profit = twenty_eight_profit;
            }

            public String getPk_ten_profit() {
                return pk_ten_profit;
            }

            public void setPk_ten_profit(String pk_ten_profit) {
                this.pk_ten_profit = pk_ten_profit;
            }

            public String getLhc_profit() {
                return lhc_profit;
            }

            public void setLhc_profit(String lhc_profit) {
                this.lhc_profit = lhc_profit;
            }

            public String getLow_frequency_profit() {
                return low_frequency_profit;
            }

            public void setLow_frequency_profit(String low_frequency_profit) {
                this.low_frequency_profit = low_frequency_profit;
            }

            public String getUser_level() {
                return user_level;
            }

            public void setUser_level(String user_level) {
                this.user_level = user_level;
            }

            public String getAgent_profit_mooney() {
                return agent_profit_mooney;
            }

            public void setAgent_profit_mooney(String agent_profit_mooney) {
                this.agent_profit_mooney = agent_profit_mooney;
            }

            public long getRegister_time() {
                return register_time;
            }

            public void setRegister_time(long register_time) {
                this.register_time = register_time;
            }
        }
    }
}

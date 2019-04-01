package com.sp.caitwo.bean;

import java.util.List;

public class PersonalAgentInfoBean {


    /**
     * data : {"agentType":1,"registerUrl":"http://47.100.234.151:5201/register.html","subAgentPeople":0,"totalShareProfit":0,"todayShareProfit":0,"yesterdayShareProfit":0,"agentLevel":2,"profitList":[{"id":9,"profit_name":"低频彩","profit_value":7,"type":9,"status":1},{"id":8,"profit_name":"六合彩","profit_value":7,"type":8,"status":1},{"id":7,"profit_name":"pk10","profit_value":7,"type":7,"status":1},{"id":6,"profit_name":"28游戏","profit_value":10,"type":6,"status":0},{"id":5,"profit_name":"排列3/5","profit_value":7,"type":5,"status":1},{"id":4,"profit_name":"福彩3D","profit_value":7,"type":4,"status":1},{"id":3,"profit_name":"11选5","profit_value":7,"type":3,"status":1},{"id":2,"profit_name":"时时彩","profit_value":7,"type":2,"status":1},{"id":1,"profit_name":"快三","profit_value":7,"type":1,"status":1}]}
     * timestamp : 2018-11-22 16:37:52
     * request_id : 1542875872151
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
         * agentType : 1
         * registerUrl : http://47.100.234.151:5201/register.html
         * subAgentPeople : 0
         * totalShareProfit : 0
         * todayShareProfit : 0
         * yesterdayShareProfit : 0
         * agentLevel : 2
         * profitList : [{"id":9,"profit_name":"低频彩","profit_value":7,"type":9,"status":1},{"id":8,"profit_name":"六合彩","profit_value":7,"type":8,"status":1},{"id":7,"profit_name":"pk10","profit_value":7,"type":7,"status":1},{"id":6,"profit_name":"28游戏","profit_value":10,"type":6,"status":0},{"id":5,"profit_name":"排列3/5","profit_value":7,"type":5,"status":1},{"id":4,"profit_name":"福彩3D","profit_value":7,"type":4,"status":1},{"id":3,"profit_name":"11选5","profit_value":7,"type":3,"status":1},{"id":2,"profit_name":"时时彩","profit_value":7,"type":2,"status":1},{"id":1,"profit_name":"快三","profit_value":7,"type":1,"status":1}]
         */

        private String agentType;
        private String registerUrl;
        private String subAgentPeople;
        private String totalShareProfit;
        private String todayShareProfit;
        private String yesterdayShareProfit;
        private String agentLevel;
        private List<ProfitListBean> profitList;

        public String getAgentType() {
            return agentType;
        }

        public void setAgentType(String agentType) {
            this.agentType = agentType;
        }

        public String getRegisterUrl() {
            return registerUrl;
        }

        public void setRegisterUrl(String registerUrl) {
            this.registerUrl = registerUrl;
        }

        public String getSubAgentPeople() {
            return subAgentPeople;
        }

        public void setSubAgentPeople(String subAgentPeople) {
            this.subAgentPeople = subAgentPeople;
        }

        public String getTotalShareProfit() {
            return totalShareProfit;
        }

        public void setTotalShareProfit(String totalShareProfit) {
            this.totalShareProfit = totalShareProfit;
        }

        public String getTodayShareProfit() {
            return todayShareProfit;
        }

        public void setTodayShareProfit(String todayShareProfit) {
            this.todayShareProfit = todayShareProfit;
        }

        public String getYesterdayShareProfit() {
            return yesterdayShareProfit;
        }

        public void setYesterdayShareProfit(String yesterdayShareProfit) {
            this.yesterdayShareProfit = yesterdayShareProfit;
        }

        public String getAgentLevel() {
            return agentLevel;
        }

        public void setAgentLevel(String agentLevel) {
            this.agentLevel = agentLevel;
        }

        public List<ProfitListBean> getProfitList() {
            return profitList;
        }

        public void setProfitList(List<ProfitListBean> profitList) {
            this.profitList = profitList;
        }

        public static class ProfitListBean {
            /**
             * id : 9
             * profit_name : 低频彩
             * profit_value : 7
             * type : 9
             * status : 1
             */

            private String id;
            private String profit_name;
            private String profit_value;
            private String type;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProfit_name() {
                return profit_name;
            }

            public void setProfit_name(String profit_name) {
                this.profit_name = profit_name;
            }

            public String getProfit_value() {
                return profit_value;
            }

            public void setProfit_value(String profit_value) {
                this.profit_value = profit_value;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}

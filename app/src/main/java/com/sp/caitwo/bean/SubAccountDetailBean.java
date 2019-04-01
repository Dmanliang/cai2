package com.sp.caitwo.bean;

import java.util.List;

public class SubAccountDetailBean {


    /**
     * data : {"total":1447,"list":[{"account":"lanabc159","point_desc":"新加坡283074750期开奖失败退款","point_remark":null,"point":10,"point_after":23115.49,"order_no":"201811150000051211","create_time":"2018-11-15 00:00:05"},{"account":"aaasss","point_desc":"天津时时彩20181009030期开奖失败退款","point_remark":null,"point":10,"point_after":1072457.9,"order_no":"201811150000056591","create_time":"2018-11-15 00:00:05"}]}
     * timestamp : 2018-11-16 14:47:39
     * request_id : 1542350859285
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
         * total : 1447
         * list : [{"account":"lanabc159","point_desc":"新加坡283074750期开奖失败退款","point_remark":null,"point":10,"point_after":23115.49,"order_no":"201811150000051211","create_time":"2018-11-15 00:00:05"},{"account":"aaasss","point_desc":"天津时时彩20181009030期开奖失败退款","point_remark":null,"point":10,"point_after":1072457.9,"order_no":"201811150000056591","create_time":"2018-11-15 00:00:05"}]
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
             * account : lanabc159
             * point_desc : 新加坡283074750期开奖失败退款
             * point_remark : null
             * point : 10
             * point_after : 23115.49
             * order_no : 201811150000051211
             * create_time : 2018-11-15 00:00:05
             */

            private String account;
            private String point_desc;
            private String point_remark;
            private String point;
            private double point_after;
            private String order_no;
            private String create_time;

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getPoint_desc() {
                return point_desc;
            }

            public void setPoint_desc(String point_desc) {
                this.point_desc = point_desc;
            }

            public String getPoint_remark() {
                return point_remark;
            }

            public void setPoint_remark(String point_remark) {
                this.point_remark = point_remark;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public double getPoint_after() {
                return point_after;
            }

            public void setPoint_after(double point_after) {
                this.point_after = point_after;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}

package com.sp.caitwo.bean;

import java.util.List;

public class SubRechargeRecordDetailBean {


    /**
     * data : {"total":187,"list":[{"account":"112222","order_no":"2018100719204429418441","recharge_fee":100000,"create_time":"2018-10-07 19:20:44","pay_type":6,"status":1,"result_fee":102000,"recharge_type":3,"pay_desc":null},{"account":"z111111","order_no":"2018100720054729452675","recharge_fee":100000,"create_time":"2018-10-07 20:05:47","pay_type":6,"status":1,"result_fee":102000,"recharge_type":3,"pay_desc":null}]}
     * timestamp : 2018-11-16 14:54:54
     * request_id : 1542351294237
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
         * total : 187
         * list : [{"account":"112222","order_no":"2018100719204429418441","recharge_fee":100000,"create_time":"2018-10-07 19:20:44","pay_type":6,"status":1,"result_fee":102000,"recharge_type":3,"pay_desc":null},{"account":"z111111","order_no":"2018100720054729452675","recharge_fee":100000,"create_time":"2018-10-07 20:05:47","pay_type":6,"status":1,"result_fee":102000,"recharge_type":3,"pay_desc":null}]
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
             * account : 112222
             * order_no : 2018100719204429418441
             * recharge_fee : 100000
             * create_time : 2018-10-07 19:20:44
             * pay_type : 6
             * status : 1
             * result_fee : 102000
             * recharge_type : 3
             * pay_desc : null
             */

            private String account;
            private String order_no;
            private String recharge_fee;
            private String create_time;
            private String pay_type;
            private String status;
            private String result_fee;
            private String recharge_type;
            private String pay_desc;

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getRecharge_fee() {
                return recharge_fee;
            }

            public void setRecharge_fee(String recharge_fee) {
                this.recharge_fee = recharge_fee;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getResult_fee() {
                return result_fee;
            }

            public void setResult_fee(String result_fee) {
                this.result_fee = result_fee;
            }

            public String getRecharge_type() {
                return recharge_type;
            }

            public void setRecharge_type(String recharge_type) {
                this.recharge_type = recharge_type;
            }

            public String getPay_desc() {
                return pay_desc;
            }

            public void setPay_desc(String pay_desc) {
                this.pay_desc = pay_desc;
            }
        }
    }
}

package com.sp.caitwo.bean;

import java.util.List;

public class SubWithdrawRecordBean {


    /**
     * data : {"total":10,"list":[{"fee":100,"account":"lanabc159","status":1,"create_time":"2018-10-23 16:11:25","order_no":"2018102316112529663682"},{"fee":100,"account":"gfccoder","status":1,"create_time":"2018-10-21 02:16:32","order_no":"2018102102163229537279"}]}
     * timestamp : 2018-11-16 15:18:31
     * request_id : 1542352709969
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
         * total : 10
         * list : [{"fee":100,"account":"lanabc159","status":1,"create_time":"2018-10-23 16:11:25","order_no":"2018102316112529663682"},{"fee":100,"account":"gfccoder","status":1,"create_time":"2018-10-21 02:16:32","order_no":"2018102102163229537279"}]
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
             * fee : 100
             * account : lanabc159
             * status : 1
             * create_time : 2018-10-23 16:11:25
             * order_no : 2018102316112529663682
             */

            private String fee;
            private String account;
            private String status;
            private String create_time;
            private String order_no;

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }
        }
    }
}

package com.sp.caitwo.bean;

public class NotReadMsgBean {


    /**
     * data : {"system_notice_count":1,"notice_count":4,"my_notice_count":3}
     * timestamp : 2018-08-13 11:35:16
     * request_id : 1534131316656
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
         * system_notice_count : 1
         * notice_count : 4
         * my_notice_count : 3
         */

        private int system_notice_count;
        private int notice_count;
        private int my_notice_count;

        public int getSystem_notice_count() {
            return system_notice_count;
        }

        public void setSystem_notice_count(int system_notice_count) {
            this.system_notice_count = system_notice_count;
        }

        public int getNotice_count() {
            return notice_count;
        }

        public void setNotice_count(int notice_count) {
            this.notice_count = notice_count;
        }

        public int getMy_notice_count() {
            return my_notice_count;
        }

        public void setMy_notice_count(int my_notice_count) {
            this.my_notice_count = my_notice_count;
        }
    }
}

package com.sp.caitwo.bean;

public class CustomServerAddrBean {


    /**
     * data : {"id":1000013,"other_key":"kefu_url","other_value":"https://f18.livechatvalue.com/chat/chatClient/chatbox.jsp?companyID=55229&configID=80293&jid=1334393335&s=1","beizhu":null}
     * timestamp : 2018-12-27 09:48:30
     * request_id : 1545875310057
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
         * id : 1000013
         * other_key : kefu_url
         * other_value : https://f18.livechatvalue.com/chat/chatClient/chatbox.jsp?companyID=55229&configID=80293&jid=1334393335&s=1
         * beizhu : null
         */

        private int id;
        private String other_key;
        private String other_value;
        private String beizhu;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOther_key() {
            return other_key;
        }

        public void setOther_key(String other_key) {
            this.other_key = other_key;
        }

        public String getOther_value() {
            return other_value;
        }

        public void setOther_value(String other_value) {
            this.other_value = other_value;
        }

        public String getBeizhu() {
            return beizhu;
        }

        public void setBeizhu(String beizhu) {
            this.beizhu = beizhu;
        }
    }
}

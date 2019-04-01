package com.sp.caitwo.bean;

import java.util.List;

public class SecurityQuestionBean {


    /**
     * data : [{"id":1,"question_desc":"你最喜欢什么颜色"},{"id":2,"question_desc":"你最喜欢吃什么水果"},{"id":3,"question_desc":"你的出生地址"},{"id":4,"question_desc":"你知道这是谁出的问题吗"},{"id":5,"question_desc":"您的小学班主任是谁"},{"id":6,"question_desc":"您的小学叫啥"},{"id":7,"question_desc":"您的初中班主任是谁"},{"id":8,"question_desc":"您的初中叫啥"},{"id":9,"question_desc":"您的高中班主任是谁"},{"id":10,"question_desc":"您的高中叫啥"},{"id":11,"question_desc":"您的高中叫啥"}]
     * timestamp : 2018-11-23 20:06:35
     * request_id : 1542974795088
     * result_code : 0
     * result_desc : 请求成功
     */

    private String timestamp;
    private String request_id;
    private String result_code;
    private String result_desc;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * question_desc : 你最喜欢什么颜色
         */

        private int id;
        private String question_desc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion_desc() {
            return question_desc;
        }

        public void setQuestion_desc(String question_desc) {
            this.question_desc = question_desc;
        }
    }
}

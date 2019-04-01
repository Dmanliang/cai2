package com.sp.caitwo.bean;

import java.util.List;

public class PersonalSecurityQuestionBean {


    /**
     * data : {"securityStatus":3,"questionList":[{"question_id":1,"question_desc":"你最喜欢什么颜色"},{"question_id":2,"question_desc":"你最喜欢吃什么水果"},{"question_id":3,"question_desc":"你的出生地址"}]}
     * timestamp : 2018-11-24 12:41:42
     * request_id : 1543034502223
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
         * securityStatus : 3
         * questionList : [{"question_id":1,"question_desc":"你最喜欢什么颜色"},{"question_id":2,"question_desc":"你最喜欢吃什么水果"},{"question_id":3,"question_desc":"你的出生地址"}]
         */

        private int securityStatus;
        private List<QuestionListBean> questionList;

        public int getSecurityStatus() {
            return securityStatus;
        }

        public void setSecurityStatus(int securityStatus) {
            this.securityStatus = securityStatus;
        }

        public List<QuestionListBean> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<QuestionListBean> questionList) {
            this.questionList = questionList;
        }

        public static class QuestionListBean {
            /**
             * question_id : 1
             * question_desc : 你最喜欢什么颜色
             */

            private int question_id;
            private String question_desc;

            public int getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public String getQuestion_desc() {
                return question_desc;
            }

            public void setQuestion_desc(String question_desc) {
                this.question_desc = question_desc;
            }
        }
    }
}

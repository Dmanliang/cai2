package com.sp.caitwo.bean;

import java.util.List;

public class MessageBean {


    /**
     * data : {"total":3,"list":[{"id":321,"title":"线下转账10.00元审核未通过通知","content":"188888888868账号线下转账10.00审核未通过。","create_time":"2018-10-31 15:01:47","notice_type":2,"user_id":2937,"status":2,"send_user":null},{"id":286,"title":"抽奖次数获取通知","content":"您昨日充值金额达到100000.0元，获得一次抽奖机会，当日有效","create_time":"2018-10-10 01:00:00","notice_type":2,"user_id":2937,"status":2,"send_user":null},{"id":279,"title":"后台充值100000.00元到账通知","content":"您后台充值的100000.00元已到账。请注意查收","create_time":"2018-10-09 09:24:16","notice_type":2,"user_id":2937,"status":2,"send_user":null}]}
     * timestamp : 2018-12-03 19:18:51
     * request_id : 1543835931809
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
         * total : 3
         * list : [{"id":321,"title":"线下转账10.00元审核未通过通知","content":"188888888868账号线下转账10.00审核未通过。","create_time":"2018-10-31 15:01:47","notice_type":2,"user_id":2937,"status":2,"send_user":null},{"id":286,"title":"抽奖次数获取通知","content":"您昨日充值金额达到100000.0元，获得一次抽奖机会，当日有效","create_time":"2018-10-10 01:00:00","notice_type":2,"user_id":2937,"status":2,"send_user":null},{"id":279,"title":"后台充值100000.00元到账通知","content":"您后台充值的100000.00元已到账。请注意查收","create_time":"2018-10-09 09:24:16","notice_type":2,"user_id":2937,"status":2,"send_user":null}]
         */

        private int total;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
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
             * id : 321
             * title : 线下转账10.00元审核未通过通知
             * content : 188888888868账号线下转账10.00审核未通过。
             * create_time : 2018-10-31 15:01:47
             * notice_type : 2
             * user_id : 2937
             * status : 2
             * send_user : null
             */

            private int id;
            private String title;
            private String content;
            private String create_time;
            private int notice_type;
            private int user_id;
            private int status;
            private String send_user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getNotice_type() {
                return notice_type;
            }

            public void setNotice_type(int notice_type) {
                this.notice_type = notice_type;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSend_user() {
                return send_user;
            }

            public void setSend_user(String send_user) {
                this.send_user = send_user;
            }
        }
    }
}

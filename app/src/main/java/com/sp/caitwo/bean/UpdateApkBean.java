package com.sp.caitwo.bean;

public class UpdateApkBean {


    /**
     * data : {"id":1,"version_no":"1.0","update_content":"发现新版本","version_url":"http://ilc168.com/download/500vip.apk","status":1}
     * timestamp : 2018-12-04 15:03:56
     * request_id : 1543907036286
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
         * id : 1
         * version_no : 1.0
         * update_content : 发现新版本
         * version_url : http://ilc168.com/download/500vip.apk
         * status : 1
         */

        private int id;
        private int version_no;
        private String update_content;
        private String version_url;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion_no() {
            return version_no;
        }

        public void setVersion_no(int version_no) {
            this.version_no = version_no;
        }

        public String getUpdate_content() {
            return update_content;
        }

        public void setUpdate_content(String update_content) {
            this.update_content = update_content;
        }

        public String getVersion_url() {
            return version_url;
        }

        public void setVersion_url(String version_url) {
            this.version_url = version_url;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}

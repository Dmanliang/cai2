package com.sp.caitwo.bean;

import java.util.List;

/**
 * Created by vinson on 2018/11/12.
 */

public class BannerBean {

    private String request_id;
    private int result_code;
    private String result_desc;
    private String timestamp;
    private int result_count;

    private List<Datas> data;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getResult_desc() {
        return result_desc;
    }

    public void setResult_desc(String result_desc) {
        this.result_desc = result_desc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getResult_count() {
        return result_count;
    }

    public void setResult_count(int result_count) {
        this.result_count = result_count;
    }

    public List<Datas> getData() {
        return data;
    }

    public void setData(List<Datas> data) {
        this.data = data;
    }

    public static class Datas{
        private int id;
        private String banner_name;
        private String banner_imgurl;
        private int is_go;   //是否跳转
        private int status;
        private int banner_place;
        private String url;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBanner_name() {
            return banner_name;
        }

        public void setBanner_name(String banner_name) {
            this.banner_name = banner_name;
        }

        public String getBanner_imgurl() {
            return banner_imgurl;
        }

        public void setBanner_imgurl(String banner_imgurl) {
            this.banner_imgurl = banner_imgurl;
        }

        public int getIs_go() {
            return is_go;
        }

        public void setIs_go(int is_go) {
            this.is_go = is_go;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getBanner_place() {
            return banner_place;
        }

        public void setBanner_place(int banner_place) {
            this.banner_place = banner_place;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}

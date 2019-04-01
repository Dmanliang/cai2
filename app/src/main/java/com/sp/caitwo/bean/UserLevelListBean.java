package com.sp.caitwo.bean;

import java.util.List;

public class UserLevelListBean {


    /**
     * data : [{"id":1,"level_name":"新手","recharge_fee":0,"promotion_bonus":0,"level_icon":"http://123.206.65.67:50011/upload/backstage/banner/20170224/upload_20170224164132_1487925692172.jpg"},{"id":2,"level_name":"青铜","recharge_fee":10,"promotion_bonus":1,"level_icon":"http://123.206.65.67:50011/upload/backstage/banner/20170227/upload_20170227140510_1488175510130.png"},{"id":3,"level_name":"白银","recharge_fee":200,"promotion_bonus":5,"level_icon":""},{"id":4,"level_name":"黄金","recharge_fee":1000,"promotion_bonus":10,"level_icon":""},{"id":5,"level_name":"铂金","recharge_fee":10000,"promotion_bonus":58,"level_icon":""},{"id":6,"level_name":"砖石","recharge_fee":50000,"promotion_bonus":318,"level_icon":""},{"id":7,"level_name":"星耀","recharge_fee":250000,"promotion_bonus":1688,"level_icon":null},{"id":8,"level_name":"王者","recharge_fee":1000000,"promotion_bonus":6888,"level_icon":null},{"id":9,"level_name":"彩神","recharge_fee":5000000,"promotion_bonus":38888,"level_icon":null}]
     * timestamp : 2018-12-02 14:56:22
     * request_id : 1543733782101
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
         * level_name : 新手
         * recharge_fee : 0
         * promotion_bonus : 0
         * level_icon : http://123.206.65.67:50011/upload/backstage/banner/20170224/upload_20170224164132_1487925692172.jpg
         */

        private String id;
        private String level_name;
        private String recharge_fee;
        private String promotion_bonus;
        private String level_icon;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getRecharge_fee() {
            return recharge_fee;
        }

        public void setRecharge_fee(String recharge_fee) {
            this.recharge_fee = recharge_fee;
        }

        public String getPromotion_bonus() {
            return promotion_bonus;
        }

        public void setPromotion_bonus(String promotion_bonus) {
            this.promotion_bonus = promotion_bonus;
        }

        public String getLevel_icon() {
            return level_icon;
        }

        public void setLevel_icon(String level_icon) {
            this.level_icon = level_icon;
        }
    }
}

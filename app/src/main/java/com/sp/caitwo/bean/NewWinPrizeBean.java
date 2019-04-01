package com.sp.caitwo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/13.
 */

public class NewWinPrizeBean implements Serializable {


    /**
     * data : [{"user_level":5,"sex":1,"user_id":3070,"head_img":"http://23.224.2.234:8099/res/upload/backstage/defaultPhoto/20181012/upload_20181012111235_1539313955490.jpg","account":"ze***8","nick_name":"10000511","point_desc":"北京赛车","total":9.9,"today_money":0,"play_game_list":[]},{"user_level":0,"sex":1,"user_id":1271,"head_img":"http://23.224.2.234:8099/res/upload/backstage/defaultPhoto/20181012/upload_20181012111338_1539314018892.jpg","account":"u_***1","nick_name":"我是呓人.卖","point_desc":"双色球","total":265,"today_money":156873,"play_game_list":[9,18,1,13,13,18]}]
     * timestamp : 2018-11-13 09:29:18
     * request_id : 1542072558272
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

    public static class DataBean  implements Serializable {
        /**
         * user_level : 5
         * sex : 1
         * user_id : 3070
         * head_img : http://23.224.2.234:8099/res/upload/backstage/defaultPhoto/20181012/upload_20181012111235_1539313955490.jpg
         * account : ze***8
         * nick_name : 10000511
         * point_desc : 北京赛车
         * total : 9.9
         * today_money : 0
         * play_game_list : []
         */

        private int user_level;
        private int sex;
        private int user_id;
        private String head_img;
        private String account;
        private String nick_name;
        private String point_desc;
        private double total;
        private double today_money;
        private List<Integer> play_game_list = new ArrayList<>();

        public int getUser_level() {
            return user_level;
        }

        public void setUser_level(int user_level) {
            this.user_level = user_level;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getPoint_desc() {
            return point_desc;
        }

        public void setPoint_desc(String point_desc) {
            this.point_desc = point_desc;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public double getToday_money() {
            return today_money;
        }

        public void setToday_money(double today_money) {
            this.today_money = today_money;
        }

        public List<Integer> getPlay_game_list() {
            return play_game_list;
        }

        public void setPlay_game_list(List<Integer> play_game_list) {
            this.play_game_list = play_game_list;
        }
    }
}

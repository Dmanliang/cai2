package com.sp.caitwo.bean;

import java.io.Serializable;
import java.util.List;

public class LongChoiceListInfo implements Serializable{

    /**
     * data : {"data":[{"id":5845,"user_id":3038,"room_id":109,"area_id":28,"choice_no":"20181022046","choice_result":"5,6,7,8,9","choice_name":"大(第一球)","bili":1.98,"point":10,"real_result":"17","result_type":"小,单","get_point":19.8,"create_time":1540197318000,"game_type":10,"is_zhong":1,"bili_id":2200,"bili_type":1,"all_fee":0,"get_result":"5+2+2+8+0=17","order_no":"2018102216351830389529","dragon_bet":"大,-,-,-,-"}],"count":1}
     * timestamp : 2018-10-23 17:16:23
     * request_id : 1540286183446
     * result_code : 0
     * result_desc : 请求成功
     */

    private DataBeanX data;
    private String timestamp;
    private String request_id;
    private String result_code;
    private String result_desc;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
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

    public static class DataBeanX implements Serializable{
        /**
         * data : [{"id":5845,"user_id":3038,"room_id":109,"area_id":28,"choice_no":"20181022046","choice_result":"5,6,7,8,9","choice_name":"大(第一球)","bili":1.98,"point":10,"real_result":"17","result_type":"小,单","get_point":19.8,"create_time":1540197318000,"game_type":10,"is_zhong":1,"bili_id":2200,"bili_type":1,"all_fee":0,"get_result":"5+2+2+8+0=17","order_no":"2018102216351830389529","dragon_bet":"大,-,-,-,-"}]
         * count : 1
         */

        private int count;
        private List<DataBean> data;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {
            /**
             * id : 5845
             * user_id : 3038
             * room_id : 109
             * area_id : 28
             * choice_no : 20181022046
             * choice_result : 5,6,7,8,9
             * choice_name : 大(第一球)
             * bili : 1.98
             * point : 10.0
             * real_result : 17
             * result_type : 小,单
             * get_point : 19.8
             * create_time : 1540197318000
             * game_type : 10
             * is_zhong : 1
             * bili_id : 2200
             * bili_type : 1
             * all_fee : 0.0
             * get_result : 5+2+2+8+0=17
             * order_no : 2018102216351830389529
             * dragon_bet : 大,-,-,-,-
             */

            private int id;
            private int user_id;
            private int room_id;
            private int area_id;
            private String choice_no;
            private String choice_result;
            private String choice_name;
            private double bili;
            private double point;
            private String real_result;
            private String result_type;
            private double get_point;
            private long create_time;
            private int game_type;
            private int is_zhong;
            private int bili_id;
            private int bili_type;
            private double all_fee;
            private String get_result;
            private String order_no;
            private String dragon_bet;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getRoom_id() {
                return room_id;
            }

            public void setRoom_id(int room_id) {
                this.room_id = room_id;
            }

            public int getArea_id() {
                return area_id;
            }

            public void setArea_id(int area_id) {
                this.area_id = area_id;
            }

            public String getChoice_no() {
                return choice_no;
            }

            public void setChoice_no(String choice_no) {
                this.choice_no = choice_no;
            }

            public String getChoice_result() {
                return choice_result;
            }

            public void setChoice_result(String choice_result) {
                this.choice_result = choice_result;
            }

            public String getChoice_name() {
                return choice_name;
            }

            public void setChoice_name(String choice_name) {
                this.choice_name = choice_name;
            }

            public double getBili() {
                return bili;
            }

            public void setBili(double bili) {
                this.bili = bili;
            }

            public double getPoint() {
                return point;
            }

            public void setPoint(double point) {
                this.point = point;
            }

            public String getReal_result() {
                return real_result;
            }

            public void setReal_result(String real_result) {
                this.real_result = real_result;
            }

            public String getResult_type() {
                return result_type;
            }

            public void setResult_type(String result_type) {
                this.result_type = result_type;
            }

            public double getGet_point() {
                return get_point;
            }

            public void setGet_point(double get_point) {
                this.get_point = get_point;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public int getGame_type() {
                return game_type;
            }

            public void setGame_type(int game_type) {
                this.game_type = game_type;
            }

            public int getIs_zhong() {
                return is_zhong;
            }

            public void setIs_zhong(int is_zhong) {
                this.is_zhong = is_zhong;
            }

            public int getBili_id() {
                return bili_id;
            }

            public void setBili_id(int bili_id) {
                this.bili_id = bili_id;
            }

            public int getBili_type() {
                return bili_type;
            }

            public void setBili_type(int bili_type) {
                this.bili_type = bili_type;
            }

            public double getAll_fee() {
                return all_fee;
            }

            public void setAll_fee(double all_fee) {
                this.all_fee = all_fee;
            }

            public String getGet_result() {
                return get_result;
            }

            public void setGet_result(String get_result) {
                this.get_result = get_result;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getDragon_bet() {
                return dragon_bet;
            }

            public void setDragon_bet(String dragon_bet) {
                this.dragon_bet = dragon_bet;
            }
        }
    }
}

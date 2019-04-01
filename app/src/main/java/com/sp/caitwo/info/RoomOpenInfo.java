package com.sp.caitwo.info;

import java.util.List;

public class RoomOpenInfo {

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
        private FirstResultBean first_result;
        private int seconds;
        private String game_num;
        private double all_max_point;
        private double per_min_point;
        private long serverTime;
        private double point;
        private double per_max_point;
        private int status;
        private List<OpenTimeBean> open_time;

        public FirstResultBean getFirst_result() {
            return first_result;
        }

        public void setFirst_result(FirstResultBean first_result) {
            this.first_result = first_result;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public String getGame_num() {
            return game_num;
        }

        public void setGame_num(String game_num) {
            this.game_num = game_num;
        }

        public double getAll_max_point() {
            return all_max_point;
        }

        public void setAll_max_point(double all_max_point) {
            this.all_max_point = all_max_point;
        }

        public double getPer_min_point() {
            return per_min_point;
        }

        public void setPer_min_point(double per_min_point) {
            this.per_min_point = per_min_point;
        }

        public long getServerTime() {
            return serverTime;
        }

        public void setServerTime(long serverTime) {
            this.serverTime = serverTime;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public double getPer_max_point() {
            return per_max_point;
        }

        public void setPer_max_point(double per_max_point) {
            this.per_max_point = per_max_point;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<OpenTimeBean> getOpen_time() {
            return open_time;
        }

        public void setOpen_time(List<OpenTimeBean> open_time) {
            this.open_time = open_time;
        }

        public static class FirstResultBean {
            private int id;
            private int user_id;
            private int game_type;
            private long open_time;
            private String game_num;
            private int game_result;
            private String game_result_desc;
            private String result_type;
            private int is_baozi;
            private int is_shunzi;
            private int page_no;
            private double xhibit_point;
            private double total_point;
            private int is_give;
            private double give_point;
            private String color;
            private String get_result;
            private String my_result;

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

            public int getGame_type() {
                return game_type;
            }

            public void setGame_type(int game_type) {
                this.game_type = game_type;
            }

            public long getOpen_time() {
                return open_time;
            }

            public void setOpen_time(long open_time) {
                this.open_time = open_time;
            }

            public String getGame_num() {
                return game_num;
            }

            public void setGame_num(String game_num) {
                this.game_num = game_num;
            }

            public int getGame_result() {
                return game_result;
            }

            public void setGame_result(int game_result) {
                this.game_result = game_result;
            }

            public String getGame_result_desc() {
                return game_result_desc;
            }

            public void setGame_result_desc(String game_result_desc) {
                this.game_result_desc = game_result_desc;
            }

            public String getResult_type() {
                return result_type;
            }

            public void setResult_type(String result_type) {
                this.result_type = result_type;
            }

            public int getIs_baozi() {
                return is_baozi;
            }

            public void setIs_baozi(int is_baozi) {
                this.is_baozi = is_baozi;
            }

            public int getIs_shunzi() {
                return is_shunzi;
            }

            public void setIs_shunzi(int is_shunzi) {
                this.is_shunzi = is_shunzi;
            }

            public int getPage_no() {
                return page_no;
            }

            public void setPage_no(int page_no) {
                this.page_no = page_no;
            }

            public double getXhibit_point() {
                return xhibit_point;
            }

            public void setXhibit_point(double xhibit_point) {
                this.xhibit_point = xhibit_point;
            }

            public double getTotal_point() {
                return total_point;
            }

            public void setTotal_point(double total_point) {
                this.total_point = total_point;
            }

            public int getIs_give() {
                return is_give;
            }

            public void setIs_give(int is_give) {
                this.is_give = is_give;
            }

            public double getGive_point() {
                return give_point;
            }

            public void setGive_point(double give_point) {
                this.give_point = give_point;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getGet_result() {
                return get_result;
            }

            public void setGet_result(String get_result) {
                this.get_result = get_result;
            }

            public String getMy_result() {
                return my_result;
            }

            public void setMy_result(String my_result) {
                this.my_result = my_result;
            }
        }

        public static class OpenTimeBean {

            private int id;
            private int user_id;
            private int game_type;
            private String open_time;
            private String game_num;
            private int game_result;
            private String game_result_desc;
            private String result_type;
            private int is_baozi;
            private int is_shunzi;
            private int page_no;
            private double xhibit_point;
            private double total_point;
            private int is_give;
            private double give_point;
            private String color;
            private String get_result;
            private String my_result;

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

            public int getGame_type() {
                return game_type;
            }

            public void setGame_type(int game_type) {
                this.game_type = game_type;
            }

            public String getOpen_time() {
                return open_time;
            }

            public void setOpen_time(String open_time) {
                this.open_time = open_time;
            }

            public String getGame_num() {
                return game_num;
            }

            public void setGame_num(String game_num) {
                this.game_num = game_num;
            }

            public int getGame_result() {
                return game_result;
            }

            public void setGame_result(int game_result) {
                this.game_result = game_result;
            }

            public String getGame_result_desc() {
                return game_result_desc;
            }

            public void setGame_result_desc(String game_result_desc) {
                this.game_result_desc = game_result_desc;
            }

            public String getResult_type() {
                return result_type;
            }

            public void setResult_type(String result_type) {
                this.result_type = result_type;
            }

            public int getIs_baozi() {
                return is_baozi;
            }

            public void setIs_baozi(int is_baozi) {
                this.is_baozi = is_baozi;
            }

            public int getIs_shunzi() {
                return is_shunzi;
            }

            public void setIs_shunzi(int is_shunzi) {
                this.is_shunzi = is_shunzi;
            }

            public int getPage_no() {
                return page_no;
            }

            public void setPage_no(int page_no) {
                this.page_no = page_no;
            }

            public double getXhibit_point() {
                return xhibit_point;
            }

            public void setXhibit_point(double xhibit_point) {
                this.xhibit_point = xhibit_point;
            }

            public double getTotal_point() {
                return total_point;
            }

            public void setTotal_point(double total_point) {
                this.total_point = total_point;
            }

            public int getIs_give() {
                return is_give;
            }

            public void setIs_give(int is_give) {
                this.is_give = is_give;
            }

            public double getGive_point() {
                return give_point;
            }

            public void setGive_point(double give_point) {
                this.give_point = give_point;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getGet_result() {
                return get_result;
            }

            public void setGet_result(String get_result) {
                this.get_result = get_result;
            }

            public String getMy_result() {
                return my_result;
            }

            public void setMy_result(String my_result) {
                this.my_result = my_result;
            }
        }
    }
}

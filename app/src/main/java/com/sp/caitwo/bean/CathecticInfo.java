package com.sp.caitwo.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/19.
 */

public class CathecticInfo {


   /**{"game_type":6,"game_num":20181126084,"position":7,"times":2,"type":"双","bili_list":[{"id":1165,"game_type":6,"bili_type":7,"bili_name":"单","bili":2,"result":"1,3,5,7,9","result_desc":"第7名号码:[1,3,5,7,9]","area_id":16,"min_point":1,"max_point":1000000},{"id":1166,"game_type":6,"bili_type":7,"bili_name":"双","bili":2,"result":"2,4,6,8,10","result_desc":"第7名号码:[2,4,6,8,10]","area_id":16,"min_point":1,"max_point":1000000}],"seconds":13,"status":1,"game_id":0,"game_name":"幸运飞艇","posiname":"第七"}],"serverTime":1543233811}
     * timestamp : 2018-11-26 20:03:31
     * request_id : 1543233811633
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

    public static class DataBeanX {


        private long serverTime;
        private List<DataBean> data;

        public long getServerTime() {
            return serverTime;
        }

        public void setServerTime(long serverTime) {
            this.serverTime = serverTime;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * game_type : 3
             * game_num : 126789
             * position : 0
             * times : 9
             * type : 大
             * bili_list : [{"id":253,"game_type":3,"bili_type":1,"bili_name":"大","bili":2,"result":"11,12,13,14,15,16,17,18","result_desc":"中奖和值:[11,12,13,14,15,16,17,18]","area_id":7,"min_point":1,"max_point":1000000},{"id":254,"game_type":3,"bili_type":1,"bili_name":"小","bili":2,"result":"3,4,5,6,7,8,9,10","result_desc":"中奖和值:[3,4,5,6,7,8,9,10]","area_id":7,"min_point":1,"max_point":1000000}]
             * seconds : 343
             * status : 1
             * game_id : 0
             * game_name : 北京快三
             * posiname : 和值
             */

            private int game_type;
            private long game_num;
            private int position;
            private int times;
            private String type;
            private int seconds;
            private int status;
            private int game_id;
            private String game_name;
            private String posiname;
            private List<BiliListBean> bili_list;
            private boolean isFocus1 = false;
            private boolean isFocus2 = false;

            public boolean isFocus1() {
                return isFocus1;
            }

            public void setFocus1(boolean focus1) {
                isFocus1 = focus1;
            }

            public boolean isFocus2() {
                return isFocus2;
            }

            public void setFocus2(boolean focus2) {
                isFocus2 = focus2;
            }

            public int getGame_type() {
                return game_type;
            }

            public void setGame_type(int game_type) {
                this.game_type = game_type;
            }

            public long getGame_num() {
                return game_num;
            }

            public void setGame_num(long game_num) {
                this.game_num = game_num;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public int getTimes() {
                return times;
            }

            public void setTimes(int times) {
                this.times = times;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getGame_id() {
                return game_id;
            }

            public void setGame_id(int game_id) {
                this.game_id = game_id;
            }

            public String getGame_name() {
                return game_name;
            }

            public void setGame_name(String game_name) {
                this.game_name = game_name;
            }

            public String getPosiname() {
                return posiname;
            }

            public void setPosiname(String posiname) {
                this.posiname = posiname;
            }

            public List<BiliListBean> getBili_list() {
                return bili_list;
            }

            public void setBili_list(List<BiliListBean> bili_list) {
                this.bili_list = bili_list;
            }

            public static class BiliListBean {
                /**
                 * id : 253
                 * game_type : 3
                 * bili_type : 1
                 * bili_name : 大
                 * bili : 2
                 * result : 11,12,13,14,15,16,17,18
                 * result_desc : 中奖和值:[11,12,13,14,15,16,17,18]
                 * area_id : 7
                 * min_point : 1
                 * max_point : 1000000
                 */

                private int id;
                private int game_type;
                private int bili_type;
                private String bili_name;
                private double bili;
                private String result;
                private String result_desc;
                private int area_id;
                private double min_point;
                private double max_point;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getGame_type() {
                    return game_type;
                }

                public void setGame_type(int game_type) {
                    this.game_type = game_type;
                }

                public int getBili_type() {
                    return bili_type;
                }

                public void setBili_type(int bili_type) {
                    this.bili_type = bili_type;
                }

                public String getBili_name() {
                    return bili_name;
                }

                public void setBili_name(String bili_name) {
                    this.bili_name = bili_name;
                }

                public double getBili() {
                    return bili;
                }

                public void setBili(double bili) {
                    this.bili = bili;
                }

                public String getResult() {
                    return result;
                }

                public void setResult(String result) {
                    this.result = result;
                }

                public String getResult_desc() {
                    return result_desc;
                }

                public void setResult_desc(String result_desc) {
                    this.result_desc = result_desc;
                }

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
                }

                public double getMin_point() {
                    return min_point;
                }

                public void setMin_point(int min_point) {
                    this.min_point = min_point;
                }

                public double getMax_point() {
                    return max_point;
                }

                public void setMax_point(int max_point) {
                    this.max_point = max_point;
                }
            }
        }
    }
}

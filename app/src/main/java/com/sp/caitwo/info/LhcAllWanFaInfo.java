package com.sp.caitwo.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LhcAllWanFaInfo implements Serializable {

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

    public static class DataBean implements Serializable{

        private String name;
        public int someNum;
        private List<ListsBean> lists = new ArrayList<>();
        private List<ListsBean.ListBean> list = new ArrayList<>();
        private String wanfa_dec;
        private double max_bili;
        private double min_bili;

        public double getMax_bili() {
            return max_bili;
        }

        public void setMax_bili(double max_bili) {
            this.max_bili = max_bili;
        }

        public double getMin_bili() {
            return min_bili;
        }

        public void setMin_bili(double min_bili) {
            this.min_bili = min_bili;
        }

        public String getWanfa_dec() {
            return wanfa_dec;
        }

        public void setWanfa_dec(String wanfa_dec) {
            this.wanfa_dec = wanfa_dec;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public List<ListsBean.ListBean> getList() {
            return list;
        }

        public void setList(List<ListsBean.ListBean> list) {
            this.list = list;
        }

        public int getSomeNum() {
            return someNum;
        }

        public void setSomeNum(int someNum) {
            this.someNum = someNum;
        }

        public static class ListsBean implements Serializable{

            private String name;
            public int someNum;
            private String wanfa_type;
            private double max_bili;
            private double min_bili;
            private String wanfa_dec;
            private List<ListBean> list = new ArrayList<>();

            public String getWanfa_dec() {
                return wanfa_dec;
            }

            public void setWanfa_dec(String wanfa_dec) {
                this.wanfa_dec = wanfa_dec;
            }

            public String getWanfa_type() {
                return wanfa_type;
            }

            public void setWanfa_type(String wanfa_type) {
                this.wanfa_type = wanfa_type;
            }

            public double getMax_bili() {
                return max_bili;
            }

            public void setMax_bili(double max_bili) {
                this.max_bili = max_bili;
            }

            public double getMin_bili() {
                return min_bili;
            }

            public void setMin_bili(double min_bili) {
                this.min_bili = min_bili;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public int getSomeNum() {
                return someNum;
            }

            public void setSomeNum(int someNum) {
                this.someNum = someNum;
            }

            public static class ListBean implements Serializable{

                private int id;
                private String wanfa_name;
                private double a_odds;
                private double b_odds;
                private double c_odds;
                private int is_disable;
                private String remarks;
                private double quota;
                private double odd;
                private String wanfa_parentname;
                private double min_money;
                private double max_money;
                private String shengxiao_nums;
                private boolean isFocus;
                public int someNum;

                public ListBean(int id,String wanfa_name,double a_odds,double b_odds,double c_odds,int is_disable,
                                String remarks,double quota,double odd,String wanfa_parentname,double min_money,double max_money,
                                String shengxiao_nums){
                    this.id = id;
                    this.wanfa_name = wanfa_name;
                    this.a_odds = a_odds;
                    this.b_odds = b_odds;
                    this.c_odds = c_odds;
                    this.is_disable = is_disable;
                    this.remarks = remarks;
                    this.quota =quota;
                    this.odd = odd;
                    this.wanfa_parentname = wanfa_parentname;
                    this.min_money = min_money;
                    this.max_money = max_money;
                    this.shengxiao_nums = shengxiao_nums;
                }

                public int getSomeNum() {
                    return someNum;
                }

                public void setSomeNum(int someNum) {
                    this.someNum = someNum;
                }

                public boolean isFocus() {
                    return isFocus;
                }

                public void setFocus(boolean focus) {
                    isFocus = focus;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getWanfa_name() {
                    return wanfa_name;
                }

                public void setWanfa_name(String wanfa_name) {
                    this.wanfa_name = wanfa_name;
                }

                public double getA_odds() {
                    return a_odds;
                }

                public void setA_odds(double a_odds) {
                    this.a_odds = a_odds;
                }

                public double getB_odds() {
                    return b_odds;
                }

                public void setB_odds(double b_odds) {
                    this.b_odds = b_odds;
                }

                public double getC_odds() {
                    return c_odds;
                }

                public void setC_odds(double c_odds) {
                    this.c_odds = c_odds;
                }

                public int getIs_disable() {
                    return is_disable;
                }

                public void setIs_disable(int is_disable) {
                    this.is_disable = is_disable;
                }

                public String getRemarks() {
                    return remarks;
                }

                public void setRemarks(String remarks) {
                    this.remarks = remarks;
                }

                public double getQuota() {
                    return quota;
                }

                public void setQuota(double quota) {
                    this.quota = quota;
                }

                public double getOdd() {
                    return odd;
                }

                public void setOdd(double odd) {
                    this.odd = odd;
                }

                public String getWanfa_parentname() {
                    return wanfa_parentname;
                }

                public void setWanfa_parentname(String wanfa_parentname) {
                    this.wanfa_parentname = wanfa_parentname;
                }

                public double getMin_money() {
                    return min_money;
                }

                public void setMin_money(double min_money) {
                    this.min_money = min_money;
                }

                public double getMax_money() {
                    return max_money;
                }

                public void setMax_money(double max_money) {
                    this.max_money = max_money;
                }

                public String getShengxiao_nums() {
                    return shengxiao_nums;
                }

                public void setShengxiao_nums(String shengxiao_nums) {
                    this.shengxiao_nums = shengxiao_nums;
                }

            }
        }
    }
}

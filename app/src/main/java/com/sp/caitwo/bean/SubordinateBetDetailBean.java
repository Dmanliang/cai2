package com.sp.caitwo.bean;

import java.io.Serializable;
import java.util.List;

public class SubordinateBetDetailBean implements Serializable {


    /**
     * data : {"total":4,"list":[{"user_id":3175,"account":"xu666","qihao":"20181129005","status":2,"money":4,"bonus":0,"create_time":"2018-11-29 09:21:04","base_wan_fa_name":"30","bet_numbers":"小#5","kaijiang_num":"1+6+6=13","game_type":30,"result_status":"未中奖","order_no":"PT18112909210431756093","bet_list":[{"wanfa_name":"和值","bet_content":"小","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"5","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]},{"user_id":3175,"account":"xu666","qihao":"20181129005","status":2,"money":8,"bonus":15.72,"create_time":"2018-11-29 09:20:40","base_wan_fa_name":"30","bet_numbers":"14#13#16#15","kaijiang_num":"1+6+6=13","game_type":30,"result_status":"未中奖","order_no":"PT18112909204031755136","bet_list":[{"wanfa_name":"和值","bet_content":"14","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"13","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":15.72,"status":1},{"wanfa_name":"和值","bet_content":"16","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"15","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]},{"user_id":3175,"account":"xu666","qihao":"126992","status":1,"money":8,"bonus":109.6,"create_time":"2018-11-29 09:20:20","base_wan_fa_name":"北京快三","bet_numbers":"17#15#14#16","kaijiang_num":"5+6+6=17","game_type":3,"result_status":"中奖","order_no":"PT18112909202031759412","bet_list":[{"wanfa_name":"和值","bet_content":"17","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":109.6,"status":1},{"wanfa_name":"和值","bet_content":"15","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"14","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"16","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]},{"user_id":3175,"account":"xu666","qihao":"20181129006","status":2,"money":8,"bonus":0,"create_time":"2018-11-29 09:20:02","base_wan_fa_name":"江苏快三","bet_numbers":"4#5#6#7","kaijiang_num":"1+3+5=9","game_type":7,"result_status":"未中奖","order_no":"PT18112909200231752011","bet_list":[{"wanfa_name":"和值","bet_content":"4","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"5","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"6","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"7","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]}]}
     * timestamp : 2018-11-29 09:55:38
     * request_id : 1543456538237
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

    public static class DataBean implements Serializable {
        /**
         * total : 4
         * list : [{"user_id":3175,"account":"xu666","qihao":"20181129005","status":2,"money":4,"bonus":0,"create_time":"2018-11-29 09:21:04","base_wan_fa_name":"30","bet_numbers":"小#5","kaijiang_num":"1+6+6=13","game_type":30,"result_status":"未中奖","order_no":"PT18112909210431756093","bet_list":[{"wanfa_name":"和值","bet_content":"小","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"5","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]},{"user_id":3175,"account":"xu666","qihao":"20181129005","status":2,"money":8,"bonus":15.72,"create_time":"2018-11-29 09:20:40","base_wan_fa_name":"30","bet_numbers":"14#13#16#15","kaijiang_num":"1+6+6=13","game_type":30,"result_status":"未中奖","order_no":"PT18112909204031755136","bet_list":[{"wanfa_name":"和值","bet_content":"14","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"13","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":15.72,"status":1},{"wanfa_name":"和值","bet_content":"16","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"15","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]},{"user_id":3175,"account":"xu666","qihao":"126992","status":1,"money":8,"bonus":109.6,"create_time":"2018-11-29 09:20:20","base_wan_fa_name":"北京快三","bet_numbers":"17#15#14#16","kaijiang_num":"5+6+6=17","game_type":3,"result_status":"中奖","order_no":"PT18112909202031759412","bet_list":[{"wanfa_name":"和值","bet_content":"17","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":109.6,"status":1},{"wanfa_name":"和值","bet_content":"15","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"14","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"16","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]},{"user_id":3175,"account":"xu666","qihao":"20181129006","status":2,"money":8,"bonus":0,"create_time":"2018-11-29 09:20:02","base_wan_fa_name":"江苏快三","bet_numbers":"4#5#6#7","kaijiang_num":"1+3+5=9","game_type":7,"result_status":"未中奖","order_no":"PT18112909200231752011","bet_list":[{"wanfa_name":"和值","bet_content":"4","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"5","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"6","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"7","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]}]
         */

        private String total;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * user_id : 3175
             * account : xu666
             * qihao : 20181129005
             * status : 2
             * money : 4
             * bonus : 0
             * create_time : 2018-11-29 09:21:04
             * base_wan_fa_name : 30
             * bet_numbers : 小#5
             * kaijiang_num : 1+6+6=13
             * game_type : 30
             * result_status : 未中奖
             * order_no : PT18112909210431756093
             * bet_list : [{"wanfa_name":"和值","bet_content":"小","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2},{"wanfa_name":"和值","bet_content":"5","bet_count":1,"multiple_count":1,"bet_money":2,"unit_type":1,"win_money":0,"status":2}]
             */

            private String user_id;
            private String account;
            private String qihao;
            private String status;
            private String money;
            private String bonus;
            private String create_time;
            private String base_wan_fa_name;
            private String bet_numbers;
            private String kaijiang_num;
            private String game_type;
            private String result_status;
            private String order_no;
            private List<BetListBean> bet_list;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getQihao() {
                return qihao;
            }

            public void setQihao(String qihao) {
                this.qihao = qihao;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getBonus() {
                return bonus;
            }

            public void setBonus(String bonus) {
                this.bonus = bonus;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getBase_wan_fa_name() {
                return base_wan_fa_name;
            }

            public void setBase_wan_fa_name(String base_wan_fa_name) {
                this.base_wan_fa_name = base_wan_fa_name;
            }

            public String getBet_numbers() {
                return bet_numbers;
            }

            public void setBet_numbers(String bet_numbers) {
                this.bet_numbers = bet_numbers;
            }

            public String getKaijiang_num() {
                return kaijiang_num;
            }

            public void setKaijiang_num(String kaijiang_num) {
                this.kaijiang_num = kaijiang_num;
            }

            public String getGame_type() {
                return game_type;
            }

            public void setGame_type(String game_type) {
                this.game_type = game_type;
            }

            public String getResult_status() {
                return result_status;
            }

            public void setResult_status(String result_status) {
                this.result_status = result_status;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public List<BetListBean> getBet_list() {
                return bet_list;
            }

            public void setBet_list(List<BetListBean> bet_list) {
                this.bet_list = bet_list;
            }

            public static class BetListBean implements Serializable {
                /**
                 * wanfa_name : 和值
                 * bet_content : 小
                 * bet_count : 1
                 * multiple_count : 1
                 * bet_money : 2
                 * unit_type : 1
                 * win_money : 0
                 * status : 2
                 */

                private String wanfa_name;
                private String bet_content;
                private String bet_count;
                private String multiple_count;
                private String bet_money;
                private String unit_type;
                private String win_money;
                private String status;

                public String getWanfa_name() {
                    return wanfa_name;
                }

                public void setWanfa_name(String wanfa_name) {
                    this.wanfa_name = wanfa_name;
                }

                public String getBet_content() {
                    return bet_content;
                }

                public void setBet_content(String bet_content) {
                    this.bet_content = bet_content;
                }

                public String getBet_count() {
                    return bet_count;
                }

                public void setBet_count(String bet_count) {
                    this.bet_count = bet_count;
                }

                public String getMultiple_count() {
                    return multiple_count;
                }

                public void setMultiple_count(String multiple_count) {
                    this.multiple_count = multiple_count;
                }

                public String getBet_money() {
                    return bet_money;
                }

                public void setBet_money(String bet_money) {
                    this.bet_money = bet_money;
                }

                public String getUnit_type() {
                    return unit_type;
                }

                public void setUnit_type(String unit_type) {
                    this.unit_type = unit_type;
                }

                public String getWin_money() {
                    return win_money;
                }

                public void setWin_money(String win_money) {
                    this.win_money = win_money;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}

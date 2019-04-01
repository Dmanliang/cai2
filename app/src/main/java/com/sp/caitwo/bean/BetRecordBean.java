package com.sp.caitwo.bean;

import java.io.Serializable;
import java.util.List;

public class BetRecordBean implements Serializable {

    /**
     * data : [{"account":"lanbc159","base_wan_fa_name":"安徽快三","bet_list":[{"bet_content":"顺子","bet_count":1,"bet_money":2,"multiple_count":1,"status":1,"unit_type":1,"wanfa_name":"顺豹玩法","win_money":15.14}],"bet_numbers":"顺子","bonus":15.14,"create_time":"2018-11-29 09:04:10","game_type":30,"kaijiang_num":"1+2+3=6","money":2,"order_no":"PT18112909041029673809","qihao":"20181129003","result_status":"中奖","status":1,"user_id":2967},{"account":"lanbc159","base_wan_fa_name":"安徽快三","bet_list":[{"bet_content":"4","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"5","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"6","bet_count":1,"bet_money":2,"multiple_count":1,"status":1,"unit_type":1,"wanfa_name":"和值","win_money":32.98},{"bet_content":"7","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0}],"bet_numbers":"4#5#6#7","bonus":32.98,"create_time":"2018-11-29 09:03:55","game_type":30,"kaijiang_num":"1+2+3=6","money":8,"order_no":"PT18112909035529679580","qihao":"20181129003","result_status":"未中奖","status":2,"user_id":2967},{"account":"lanbc159","base_wan_fa_name":"北京快三","bet_list":[{"bet_content":"4","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"5","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"6","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"7","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0}],"bet_numbers":"4#5#6#7","bonus":0,"create_time":"2018-11-29 09:03:39","game_type":3,"kaijiang_num":"2+3+6=11","money":8,"order_no":"PT18112909033929670763","qihao":"126990","result_status":"未中奖","status":2,"user_id":2967},{"account":"lanbc159","base_wan_fa_name":"江苏快三","bet_list":[{"bet_content":"17","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"16","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"15","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"14","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0}],"bet_numbers":"17#16#15#14","bonus":0,"create_time":"2018-11-29 09:03:19","game_type":7,"kaijiang_num":"1+2+3=6","money":8,"order_no":"PT18112909031929675856","qihao":"20181129004","result_status":"未中奖","status":2,"user_id":2967},{"account":"lanbc159","base_wan_fa_name":"江苏快三","bet_list":[{"bet_content":"小","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0},{"bet_content":"大双","bet_count":1,"bet_money":2,"multiple_count":1,"status":2,"unit_type":1,"wanfa_name":"和值","win_money":0}],"bet_numbers":"小#大双","bonus":0,"create_time":"2018-11-28 13:37:21","game_type":7,"kaijiang_num":"5+5+5=15","money":4,"order_no":"PT18112813372129678269","qihao":"20181128031","result_status":"未中奖","status":2,"user_id":2967}]
     * request_id : 1543562318731
     * result_code : 0
     * result_desc : 请求成功
     * timestamp : 2018-11-30 15:18:38
     */

    private String request_id;
    private String result_code;
    private String result_desc;
    private String timestamp;
    private List<DataBean> data;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * account : lanbc159
         * base_wan_fa_name : 安徽快三
         * bet_list : [{"bet_content":"顺子","bet_count":1,"bet_money":2,"multiple_count":1,"status":1,"unit_type":1,"wanfa_name":"顺豹玩法","win_money":15.14}]
         * bet_numbers : 顺子
         * bonus : 15.14
         * create_time : 2018-11-29 09:04:10
         * game_type : 30
         * kaijiang_num : 1+2+3=6
         * money : 2
         * order_no : PT18112909041029673809
         * qihao : 20181129003
         * result_status : 中奖
         * status : 1
         * user_id : 2967
         */

        private String account;
        private String base_wan_fa_name;
        private String bet_numbers;
        private double bonus;
        private String create_time;
        private String game_type;
        private String kaijiang_num;
        private String money;
        private String order_no;
        private String qihao;
        private String result_status;
        private String status;
        private String user_id;
        private List<BetListBean> bet_list;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
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

        public double getBonus() {
            return bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getGame_type() {
            return game_type;
        }

        public void setGame_type(String game_type) {
            this.game_type = game_type;
        }

        public String getKaijiang_num() {
            return kaijiang_num;
        }

        public void setKaijiang_num(String kaijiang_num) {
            this.kaijiang_num = kaijiang_num;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getQihao() {
            return qihao;
        }

        public void setQihao(String qihao) {
            this.qihao = qihao;
        }

        public String getResult_status() {
            return result_status;
        }

        public void setResult_status(String result_status) {
            this.result_status = result_status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<BetListBean> getBet_list() {
            return bet_list;
        }

        public void setBet_list(List<BetListBean> bet_list) {
            this.bet_list = bet_list;
        }

        public static class BetListBean implements Serializable{
            /**
             * bet_content : 顺子
             * bet_count : 1
             * bet_money : 2
             * multiple_count : 1
             * status : 1
             * unit_type : 1
             * wanfa_name : 顺豹玩法
             * win_money : 15.14
             */

            private String bet_content;
            private String bet_count;
            private String bet_money;
            private String multiple_count;
            private String status;
            private String unit_type;
            private String wanfa_name;
            private double win_money;

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

            public String getBet_money() {
                return bet_money;
            }

            public void setBet_money(String bet_money) {
                this.bet_money = bet_money;
            }

            public String getMultiple_count() {
                return multiple_count;
            }

            public void setMultiple_count(String multiple_count) {
                this.multiple_count = multiple_count;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUnit_type() {
                return unit_type;
            }

            public void setUnit_type(String unit_type) {
                this.unit_type = unit_type;
            }

            public String getWanfa_name() {
                return wanfa_name;
            }

            public void setWanfa_name(String wanfa_name) {
                this.wanfa_name = wanfa_name;
            }

            public double getWin_money() {
                return win_money;
            }

            public void setWin_money(double win_money) {
                this.win_money = win_money;
            }
        }
    }
}

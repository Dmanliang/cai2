package com.sp.caitwo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/19.
 */

public class CathecticRecordInfo implements Serializable {

    private String timestamp;
    private String request_id;
    private String result_code;
    private String result_desc;
    private List<DataBean> data = new ArrayList<>();

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

    public static class DataBean implements Serializable {

        private int user_id;
        private String account;
        private String qihao;
        private int status;//0待开奖 1中奖 2 未中奖3取消 4失败
        private double money;
        private double bonus;
        private String create_time;
        private String base_wan_fa_name;
        private String bet_numbers;
        private String kaijiang_num;
        private String result_status;
        private String order_no;
        private int game_type;
        private List<BetListBean> bet_list = new ArrayList<>();

        public int getGame_type() {
            return game_type;
        }

        public void setGame_type(int game_type) {
            this.game_type = game_type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
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

            private String wanfa_name;
            private String bet_content;
            private int bet_count;
            private int multiple_count;
            private double bet_money;
            private int unit_type;
            private double win_money;
            private int status;

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

            public int getBet_count() {
                return bet_count;
            }

            public void setBet_count(int bet_count) {
                this.bet_count = bet_count;
            }

            public int getMultiple_count() {
                return multiple_count;
            }

            public void setMultiple_count(int multiple_count) {
                this.multiple_count = multiple_count;
            }

            public double getBet_money() {
                return bet_money;
            }

            public void setBet_money(double bet_money) {
                this.bet_money = bet_money;
            }

            public int getUnit_type() {
                return unit_type;
            }

            public void setUnit_type(int unit_type) {
                this.unit_type = unit_type;
            }

            public double getWin_money() {
                return win_money;
            }

            public void setWin_money(double win_money) {
                this.win_money = win_money;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}

package com.sp.caitwo.bean;

import java.io.Serializable;
import java.util.List;

public class UserBankList implements Serializable {


    /**
     * data : [{"id":2,"user_id":2983,"account":"2983","real_name":"我的","bank_name":"广发银行","bank_no":"55556666","open_card_address":"北京--东城区--这里","default_card":null,"mobile":null,"create_time":1539743309000,"withdrawals_password":null},{"id":3,"user_id":2983,"account":"2983","real_name":"我的","bank_name":"广大银行","bank_no":"66666","open_card_address":"北京--东城区--hhg","default_card":null,"mobile":null,"create_time":1539852372000,"withdrawals_password":null}]
     * timestamp : 2018-11-19 17:48:33
     * request_id : 1542620913636
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

    public static class DataBean implements Serializable{
        /**
         * id : 2
         * user_id : 2983
         * account : 2983
         * real_name : 我的
         * bank_name : 广发银行
         * bank_no : 55556666
         * open_card_address : 北京--东城区--这里
         * default_card : null
         * mobile : null
         * create_time : 1539743309000
         * withdrawals_password : null
         */

        private int id;
        private int user_id;
        private String account;
        private String real_name;
        private String bank_name;
        private String bank_no;
        private String open_card_address;

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

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_no() {
            return bank_no;
        }

        public void setBank_no(String bank_no) {
            this.bank_no = bank_no;
        }

        public String getOpen_card_address() {
            return open_card_address;
        }

        public void setOpen_card_address(String open_card_address) {
            this.open_card_address = open_card_address;
        }
    }
}

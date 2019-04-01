package com.sp.caitwo.bean;

import java.util.List;

public class WithdrawInfoBean {


    /**
     * data : {"availableWithdraw":48.12,"notCompleteMoney":0,"bankList":[{"id":12,"user_id":2967,"account":"lanbc159","real_name":"局","bank_name":"工商银行","bank_no":"8888","open_card_address":"天津--天津--垃圾分类","default_card":1,"mobile":null,"create_time":1541144084000}],"withdrawalsCount":5,"userBalance":10018.12}
     * timestamp : 2018-11-30 11:13:17
     * request_id : 1543547597851
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

    public static class DataBean {
        /**
         * availableWithdraw : 48.12
         * notCompleteMoney : 0
         * bankList : [{"id":12,"user_id":2967,"account":"lanbc159","real_name":"局","bank_name":"工商银行","bank_no":"8888","open_card_address":"天津--天津--垃圾分类","default_card":1,"mobile":null,"create_time":1541144084000}]
         * withdrawalsCount : 5
         * userBalance : 10018.12
         */

        private double availableWithdraw;
        private String notCompleteMoney;
        private String withdrawalsCount;
        private double userBalance;
        private List<BankListBean> bankList;

        public double getAvailableWithdraw() {
            return availableWithdraw;
        }

        public void setAvailableWithdraw(double availableWithdraw) {
            this.availableWithdraw = availableWithdraw;
        }

        public String getNotCompleteMoney() {
            return notCompleteMoney;
        }

        public void setNotCompleteMoney(String notCompleteMoney) {
            this.notCompleteMoney = notCompleteMoney;
        }

        public String getWithdrawalsCount() {
            return withdrawalsCount;
        }

        public void setWithdrawalsCount(String withdrawalsCount) {
            this.withdrawalsCount = withdrawalsCount;
        }

        public double getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(double userBalance) {
            this.userBalance = userBalance;
        }

        public List<BankListBean> getBankList() {
            return bankList;
        }

        public void setBankList(List<BankListBean> bankList) {
            this.bankList = bankList;
        }

        public static class BankListBean {
            /**
             * id : 12
             * user_id : 2967
             * account : lanbc159
             * real_name : 局
             * bank_name : 工商银行
             * bank_no : 8888
             * open_card_address : 天津--天津--垃圾分类
             * default_card : 1
             * mobile : null
             * create_time : 1541144084000
             */

            private String id;
            private String user_id;
            private String account;
            private String real_name;
            private String bank_name;
            private String bank_no;
            private String open_card_address;
            private String default_card;
            private String mobile;
            private long create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

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

            public String getDefault_card() {
                return default_card;
            }

            public void setDefault_card(String default_card) {
                this.default_card = default_card;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }
        }
    }
}

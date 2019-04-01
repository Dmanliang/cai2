package com.sp.caitwo.bean;

import java.io.Serializable;
import java.util.List;

public class PayListBean implements Serializable {


    /**
     * data : [{"id":1,"name":"公司入款","remarks":"","type":0,"type_key":"1","recharge_min_limit":0,"recharge_max_limit":0,"recharge_way_list":[{"id":21,"account":"62184458366654432","real_name":"张顺","bank_name":"建设银行","open_card_address":"建设银行北京支行","photo":"","remark":"请每次转账后提供会员账号及支付凭证给24小时在线客服。","recharge_min_limit":0,"recharge_max_limit":0,"pay_limit":0,"sub_type":1,"user_remark":"","status":1},{"id":16,"account":"6218445836665456","real_name":"王明2","bank_name":"建设银行","open_card_address":"建设银行上海支行","photo":"","remark":"请每次转账后提供会员账号及支付凭证给24小时在线客服。","recharge_min_limit":11,"recharge_max_limit":0,"pay_limit":0,"sub_type":1,"user_remark":"","status":1}]},{"id":2,"name":"支付宝","remarks":"http://23.224.2.234:8083/pay.html","type":1,"type_key":"2","recharge_min_limit":10,"recharge_max_limit":3000,"recharge_way_list":[{"id":1,"pay_type":1,"pay_type_name":"万付宝","payee":null,"receive_account":null,"qrcode_img":null,"online_type":1,"remark":null,"user_remark":null,"recharge_min_limit":10,"recharge_max_limit":3000,"pay_limit":0,"mobile_pay_type":1,"computer_pay_type":0,"status":1}]},{"id":3,"name":"微信支付","remarks":"http://23.224.2.234:8083/pay.html","type":2,"type_key":"2","recharge_min_limit":20,"recharge_max_limit":2000,"recharge_way_list":[{"id":2,"pay_type":2,"pay_type_name":"万付宝","payee":null,"receive_account":null,"qrcode_img":null,"online_type":1,"remark":null,"user_remark":null,"recharge_min_limit":20,"recharge_max_limit":2000,"pay_limit":0,"mobile_pay_type":1,"computer_pay_type":0,"status":1}]},{"id":6,"name":"银联支付","remarks":"http://23.224.2.234:8083/pay.html","type":5,"type_key":"2","recharge_min_limit":10,"recharge_max_limit":3000,"recharge_way_list":[{"id":3,"pay_type":5,"pay_type_name":"万付宝","payee":null,"receive_account":null,"qrcode_img":null,"online_type":1,"remark":null,"user_remark":null,"recharge_min_limit":10,"recharge_max_limit":3000,"pay_limit":0,"mobile_pay_type":1,"computer_pay_type":0,"status":1}]},{"id":7,"name":"京东钱包","remarks":"http://23.224.2.234:8083/pay.html","type":6,"type_key":"2","recharge_min_limit":10,"recharge_max_limit":1000,"recharge_way_list":[{"id":4,"pay_type":6,"pay_type_name":"万付宝","payee":null,"receive_account":null,"qrcode_img":null,"online_type":1,"remark":null,"user_remark":null,"recharge_min_limit":10,"recharge_max_limit":1000,"pay_limit":0,"mobile_pay_type":1,"computer_pay_type":0,"status":1}]}]
     * timestamp : 2018-12-02 19:57:36
     * request_id : 1543751856046
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

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * name : 公司入款
         * remarks :
         * type : 0
         * type_key : 1
         * recharge_min_limit : 0
         * recharge_max_limit : 0
         * recharge_way_list : [{"id":21,"account":"62184458366654432","real_name":"张顺","bank_name":"建设银行","open_card_address":"建设银行北京支行","photo":"","remark":"请每次转账后提供会员账号及支付凭证给24小时在线客服。","recharge_min_limit":0,"recharge_max_limit":0,"pay_limit":0,"sub_type":1,"user_remark":"","status":1},{"id":16,"account":"6218445836665456","real_name":"王明2","bank_name":"建设银行","open_card_address":"建设银行上海支行","photo":"","remark":"请每次转账后提供会员账号及支付凭证给24小时在线客服。","recharge_min_limit":11,"recharge_max_limit":0,"pay_limit":0,"sub_type":1,"user_remark":"","status":1}]
         */

        private String id;
        private String name;
        private String remarks;
        private String type;
        private String type_key;
        private String recharge_min_limit;
        private String recharge_max_limit;
        private List<RechargeWayListBean> recharge_way_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_key() {
            return type_key;
        }

        public void setType_key(String type_key) {
            this.type_key = type_key;
        }

        public String getRecharge_min_limit() {
            return recharge_min_limit;
        }

        public void setRecharge_min_limit(String recharge_min_limit) {
            this.recharge_min_limit = recharge_min_limit;
        }

        public String getRecharge_max_limit() {
            return recharge_max_limit;
        }

        public void setRecharge_max_limit(String recharge_max_limit) {
            this.recharge_max_limit = recharge_max_limit;
        }

        public List<RechargeWayListBean> getRecharge_way_list() {
            return recharge_way_list;
        }

        public void setRecharge_way_list(List<RechargeWayListBean> recharge_way_list) {
            this.recharge_way_list = recharge_way_list;
        }

        public static class RechargeWayListBean implements Serializable {
            /**
             * id : 21
             * account : 62184458366654432
             * real_name : 张顺
             * bank_name : 建设银行
             * open_card_address : 建设银行北京支行
             * photo :
             * remark : 请每次转账后提供会员账号及支付凭证给24小时在线客服。
             * recharge_min_limit : 0
             * recharge_max_limit : 0
             * pay_limit : 0
             * sub_type : 1
             * user_remark :
             * status : 1
             */

            private String id;
            private String account;
            private String real_name;
            private String bank_name;
            private String open_card_address;
            private String photo;
            private String remark;
            private String recharge_min_limit;
            private String recharge_max_limit;
            private String pay_limit;
            private String sub_type;
            private String user_remark;
            private String status;
            private String computer_pay_type;
            private String mobile_pay_type;
            private String online_type;
            private String pay_type;
            private String pay_type_name;
            private String payee;
            private String receive_account;
            private String qrcode_img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getOpen_card_address() {
                return open_card_address;
            }

            public void setOpen_card_address(String open_card_address) {
                this.open_card_address = open_card_address;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getRecharge_min_limit() {
                return recharge_min_limit;
            }

            public void setRecharge_min_limit(String recharge_min_limit) {
                this.recharge_min_limit = recharge_min_limit;
            }

            public String getRecharge_max_limit() {
                return recharge_max_limit;
            }

            public void setRecharge_max_limit(String recharge_max_limit) {
                this.recharge_max_limit = recharge_max_limit;
            }

            public String getPay_limit() {
                return pay_limit;
            }

            public void setPay_limit(String pay_limit) {
                this.pay_limit = pay_limit;
            }

            public String getSub_type() {
                return sub_type;
            }

            public void setSub_type(String sub_type) {
                this.sub_type = sub_type;
            }

            public String getUser_remark() {
                return user_remark;
            }

            public void setUser_remark(String user_remark) {
                this.user_remark = user_remark;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getComputer_pay_type() {
                return computer_pay_type;
            }

            public void setComputer_pay_type(String computer_pay_type) {
                this.computer_pay_type = computer_pay_type;
            }

            public String getMobile_pay_type() {
                return mobile_pay_type;
            }

            public void setMobile_pay_type(String mobile_pay_type) {
                this.mobile_pay_type = mobile_pay_type;
            }

            public String getOnline_type() {
                return online_type;
            }

            public void setOnline_type(String online_type) {
                this.online_type = online_type;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getPay_type_name() {
                return pay_type_name;
            }

            public void setPay_type_name(String pay_type_name) {
                this.pay_type_name = pay_type_name;
            }

            public String getPayee() {
                return payee;
            }

            public void setPayee(String payee) {
                this.payee = payee;
            }

            public String getReceive_account() {
                return receive_account;
            }

            public void setReceive_account(String receive_account) {
                this.receive_account = receive_account;
            }

            public String getQrcode_img() {
                return qrcode_img;
            }

            public void setQrcode_img(String qrcode_img) {
                this.qrcode_img = qrcode_img;
            }
        }
    }
}

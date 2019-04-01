package com.sp.caitwo.bean;

import java.util.List;

public class InviteCodeListBean {


    /**
     * data : [{"id":4,"user_id":2967,"invite_code":"40177976","register_count":0,"kuai_three_profit":7,"shishicaip_profit":6,"eleven_five_profit":7,"fucai_profit":7,"array_profit":7,"twenty_eight_profit":10,"pk_ten_profit":7,"lhc_profit":7,"low_frequency_profit":7,"type":2,"status":1,"create_time":"2018-11-28 15:49:19","update_time":"2018-11-28 15:49:19"},{"id":3,"user_id":2967,"invite_code":"80153524","register_count":0,"kuai_three_profit":7,"shishicaip_profit":7,"eleven_five_profit":7,"fucai_profit":7,"array_profit":7,"twenty_eight_profit":10,"pk_ten_profit":7,"lhc_profit":7,"low_frequency_profit":7,"type":2,"status":1,"create_time":"2018-11-28 15:48:56","update_time":"2018-11-28 15:48:56"},{"id":2,"user_id":2967,"invite_code":"90576273","register_count":0,"kuai_three_profit":7,"shishicaip_profit":7,"eleven_five_profit":7,"fucai_profit":7,"array_profit":7,"twenty_eight_profit":10,"pk_ten_profit":7,"lhc_profit":7,"low_frequency_profit":7,"type":2,"status":1,"create_time":"2018-11-28 15:43:48","update_time":"2018-11-28 15:43:48"}]
     * timestamp : 2018-11-28 17:16:48
     * request_id : 1543396608442
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

    public static class DataBean {
        /**
         * id : 4
         * user_id : 2967
         * invite_code : 40177976
         * register_count : 0
         * kuai_three_profit : 7
         * shishicaip_profit : 6
         * eleven_five_profit : 7
         * fucai_profit : 7
         * array_profit : 7
         * twenty_eight_profit : 10
         * pk_ten_profit : 7
         * lhc_profit : 7
         * low_frequency_profit : 7
         * type : 2
         * status : 1
         * create_time : 2018-11-28 15:49:19
         * update_time : 2018-11-28 15:49:19
         */

        private String id;
        private String user_id;
        private String invite_code;
        private String register_count;
        private String kuai_three_profit;
        private String shishicaip_profit;
        private String eleven_five_profit;
        private String fucai_profit;
        private String array_profit;
        private String twenty_eight_profit;
        private String pk_ten_profit;
        private String lhc_profit;
        private String low_frequency_profit;
        private String type;
        private String status;
        private String create_time;
        private String update_time;

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

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getRegister_count() {
            return register_count;
        }

        public void setRegister_count(String register_count) {
            this.register_count = register_count;
        }

        public String getKuai_three_profit() {
            return kuai_three_profit;
        }

        public void setKuai_three_profit(String kuai_three_profit) {
            this.kuai_three_profit = kuai_three_profit;
        }

        public String getShishicaip_profit() {
            return shishicaip_profit;
        }

        public void setShishicaip_profit(String shishicaip_profit) {
            this.shishicaip_profit = shishicaip_profit;
        }

        public String getEleven_five_profit() {
            return eleven_five_profit;
        }

        public void setEleven_five_profit(String eleven_five_profit) {
            this.eleven_five_profit = eleven_five_profit;
        }

        public String getFucai_profit() {
            return fucai_profit;
        }

        public void setFucai_profit(String fucai_profit) {
            this.fucai_profit = fucai_profit;
        }

        public String getArray_profit() {
            return array_profit;
        }

        public void setArray_profit(String array_profit) {
            this.array_profit = array_profit;
        }

        public String getTwenty_eight_profit() {
            return twenty_eight_profit;
        }

        public void setTwenty_eight_profit(String twenty_eight_profit) {
            this.twenty_eight_profit = twenty_eight_profit;
        }

        public String getPk_ten_profit() {
            return pk_ten_profit;
        }

        public void setPk_ten_profit(String pk_ten_profit) {
            this.pk_ten_profit = pk_ten_profit;
        }

        public String getLhc_profit() {
            return lhc_profit;
        }

        public void setLhc_profit(String lhc_profit) {
            this.lhc_profit = lhc_profit;
        }

        public String getLow_frequency_profit() {
            return low_frequency_profit;
        }

        public void setLow_frequency_profit(String low_frequency_profit) {
            this.low_frequency_profit = low_frequency_profit;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}

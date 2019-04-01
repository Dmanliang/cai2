package com.sp.caitwo.info;

import java.util.List;

public class LhcWinsRecordInfo {

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

        private String qihao;
        private String number1;
        private String number2;
        private String number3;
        private String number4;
        private String number5;
        private String number6;
        private String number7;
        private String number1_sx;
        private String number2_sx;
        private String number3_sx;
        private String number4_sx;
        private String number5_sx;
        private String number6_sx;
        private String number7_sx;
        private long create_time;
        private int page_size;
        private int page_no;

        public String getQihao() {
            return qihao;
        }

        public void setQihao(String qihao) {
            this.qihao = qihao;
        }

        public String getNumber1() {
            return number1;
        }

        public void setNumber1(String number1) {
            this.number1 = number1;
        }

        public String getNumber2() {
            return number2;
        }

        public void setNumber2(String number2) {
            this.number2 = number2;
        }

        public String getNumber3() {
            return number3;
        }

        public void setNumber3(String number3) {
            this.number3 = number3;
        }

        public String getNumber4() {
            return number4;
        }

        public void setNumber4(String number4) {
            this.number4 = number4;
        }

        public String getNumber5() {
            return number5;
        }

        public void setNumber5(String number5) {
            this.number5 = number5;
        }

        public String getNumber6() {
            return number6;
        }

        public void setNumber6(String number6) {
            this.number6 = number6;
        }

        public String getNumber7() {
            return number7;
        }

        public void setNumber7(String number7) {
            this.number7 = number7;
        }

        public String getNumber1_sx() {
            return number1_sx;
        }

        public void setNumber1_sx(String number1_sx) {
            this.number1_sx = number1_sx;
        }

        public String getNumber2_sx() {
            return number2_sx;
        }

        public void setNumber2_sx(String number2_sx) {
            this.number2_sx = number2_sx;
        }

        public String getNumber3_sx() {
            return number3_sx;
        }

        public void setNumber3_sx(String number3_sx) {
            this.number3_sx = number3_sx;
        }

        public String getNumber4_sx() {
            return number4_sx;
        }

        public void setNumber4_sx(String number4_sx) {
            this.number4_sx = number4_sx;
        }

        public String getNumber5_sx() {
            return number5_sx;
        }

        public void setNumber5_sx(String number5_sx) {
            this.number5_sx = number5_sx;
        }

        public String getNumber6_sx() {
            return number6_sx;
        }

        public void setNumber6_sx(String number6_sx) {
            this.number6_sx = number6_sx;
        }

        public String getNumber7_sx() {
            return number7_sx;
        }

        public void setNumber7_sx(String number7_sx) {
            this.number7_sx = number7_sx;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public int getPage_no() {
            return page_no;
        }

        public void setPage_no(int page_no) {
            this.page_no = page_no;
        }
    }
}

package com.sp.caitwo.ui.info;

import java.util.List;

public class LotteryKJLogInfo {

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
        private String numbers;
        private long create_time;
        private int page_size;
        private int page_no;
        private String base_wan_fa_id;

        public String getQihao() {
            return qihao;
        }

        public void setQihao(String qihao) {
            this.qihao = qihao;
        }

        public String getNumbers() {
            return numbers;
        }

        public void setNumbers(String numbers) {
            this.numbers = numbers;
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

        public String getBase_wan_fa_id() {
            return base_wan_fa_id;
        }

        public void setBase_wan_fa_id(String base_wan_fa_id) {
            this.base_wan_fa_id = base_wan_fa_id;
        }
    }
}

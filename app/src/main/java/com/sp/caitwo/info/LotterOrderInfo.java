package com.sp.caitwo.info;

public class LotterOrderInfo {

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

        private int id;
        private String qihao;
        private int status;
        private long start_time;
        private long end_time;
        private long server_time;
        private WinInfoBean win_info;
        private long time;
        private int base_wan_fa_id;
        private int second;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }

        public WinInfoBean getWin_info() {
            return win_info;
        }

        public void setWin_info(WinInfoBean win_info) {
            this.win_info = win_info;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getBase_wan_fa_id() {
            return base_wan_fa_id;
        }

        public void setBase_wan_fa_id(int base_wan_fa_id) {
            this.base_wan_fa_id = base_wan_fa_id;
        }

        public long getServer_time() {
            return server_time;
        }

        public void setServer_time(long server_time) {
            this.server_time = server_time;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public static class WinInfoBean {
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
}

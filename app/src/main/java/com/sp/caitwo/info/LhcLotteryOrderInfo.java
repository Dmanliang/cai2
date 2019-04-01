package com.sp.caitwo.info;

public class LhcLotteryOrderInfo {


    /**
     * data : {"id":39,"qihao":"2018053","status":2,"start_time":0,"end_time":0,"win_info":{"qihao":2018052,"number1":"9","number2":"44","number3":"8","number4":"5","number5":"19","number6":"32","number7":"39","number1_sx":"虎","number2_sx":"兔","number3_sx":"兔","number4_sx":"马","number5_sx":"龙","number6_sx":"兔","number7_sx":"猴","create_time":1526391301,"page_size":0,"page_no":0},"time":1526563856}
     * timestamp : 2018-06-11 17:11:36
     * request_id : 1528708295328
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
         * id : 39
         * qihao : 2018053
         * status : 2
         * start_time : 0
         * end_time : 0
         * win_info : {"qihao":2018052,"number1":"9","number2":"44","number3":"8","number4":"5","number5":"19","number6":"32","number7":"39","number1_sx":"虎","number2_sx":"兔","number3_sx":"兔","number4_sx":"马","number5_sx":"龙","number6_sx":"兔","number7_sx":"猴","create_time":1526391301,"page_size":0,"page_no":0}
         * time : 1526563856
         */

        private int id;
        private String qihao;
        private int status;
        private String start_time;
        private String end_time;
        private WinInfoBean win_info;
        private long time;
        private long server_time;
        private int second;

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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
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

        public static class WinInfoBean {
            /**
             * qihao : 2018052
             * number1 : 9
             * number2 : 44
             * number3 : 8
             * number4 : 5
             * number5 : 19
             * number6 : 32
             * number7 : 39
             * number1_sx : 虎
             * number2_sx : 兔
             * number3_sx : 兔
             * number4_sx : 马
             * number5_sx : 龙
             * number6_sx : 兔
             * number7_sx : 猴
             * create_time : 1526391301
             * page_size : 0
             * page_no : 0
             */

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
}

package com.sp.caitwo.bean;

import java.io.Serializable;
import java.util.List;

public class TraceNumListBean implements Serializable {

    private DataBeanX data;
    private String timestamp;
    private String request_id;
    private String result_code;
    private String result_desc;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
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

    public static class DataBeanX implements Serializable{

        private int total;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {

            private String order_no;
            private int id;
            private String lottery_name;
            private String start_issue;
            private int already_pursued;
            private int total_issue;
            private double already_bet_money;
            private double total_bet_money;
            private double total_win_money;
            private String create_time;
            private int zjjt;
            private int status;
            private List<ChasingLogBetContentListBean> chasing_log_bet_content_list;
            private List<IssueListBean> issue_list;

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLottery_name() {
                return lottery_name;
            }

            public void setLottery_name(String lottery_name) {
                this.lottery_name = lottery_name;
            }

            public String getStart_issue() {
                return start_issue;
            }

            public void setStart_issue(String start_issue) {
                this.start_issue = start_issue;
            }

            public int getAlready_pursued() {
                return already_pursued;
            }

            public void setAlready_pursued(int already_pursued) {
                this.already_pursued = already_pursued;
            }

            public int getTotal_issue() {
                return total_issue;
            }

            public void setTotal_issue(int total_issue) {
                this.total_issue = total_issue;
            }

            public double getAlready_bet_money() {
                return already_bet_money;
            }

            public void setAlready_bet_money(double already_bet_money) {
                this.already_bet_money = already_bet_money;
            }

            public double getTotal_bet_money() {
                return total_bet_money;
            }

            public void setTotal_bet_money(double total_bet_money) {
                this.total_bet_money = total_bet_money;
            }

            public double getTotal_win_money() {
                return total_win_money;
            }

            public void setTotal_win_money(double total_win_money) {
                this.total_win_money = total_win_money;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getZjjt() {
                return zjjt;
            }

            public void setZjjt(int zjjt) {
                this.zjjt = zjjt;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<ChasingLogBetContentListBean> getChasing_log_bet_content_list() {
                return chasing_log_bet_content_list;
            }

            public void setChasing_log_bet_content_list(List<ChasingLogBetContentListBean> chasing_log_bet_content_list) {
                this.chasing_log_bet_content_list = chasing_log_bet_content_list;
            }

            public List<IssueListBean> getIssue_list() {
                return issue_list;
            }

            public void setIssue_list(List<IssueListBean> issue_list) {
                this.issue_list = issue_list;
            }

            public static class ChasingLogBetContentListBean implements Serializable {
                /**
                 * wanfa_name : 大小单双
                 * bet_content : 大(冠军)
                 * bet_count : 1
                 * multiple_count : 1
                 * bet_money : 10.0
                 * unit_type : 1
                 * win_money : 0.0
                 * status : 2
                 */

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

            public static class IssueListBean implements Serializable {

                private String order_no;
                private String issue;
                private int multiple;
                private double bet_money;
                private String open_number;
                private int status;
                private double win_money;
                private String create_time;
                private List<ChasingLogBetContentListBeanX> chasing_log_bet_content_list;

                public String getOrder_no() {
                    return order_no;
                }

                public void setOrder_no(String order_no) {
                    this.order_no = order_no;
                }

                public String getIssue() {
                    return issue;
                }

                public void setIssue(String issue) {
                    this.issue = issue;
                }

                public int getMultiple() {
                    return multiple;
                }

                public void setMultiple(int multiple) {
                    this.multiple = multiple;
                }

                public double getBet_money() {
                    return bet_money;
                }

                public void setBet_money(double bet_money) {
                    this.bet_money = bet_money;
                }

                public String getOpen_number() {
                    return open_number;
                }

                public void setOpen_number(String open_number) {
                    this.open_number = open_number;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public double getWin_money() {
                    return win_money;
                }

                public void setWin_money(double win_money) {
                    this.win_money = win_money;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public List<ChasingLogBetContentListBeanX> getChasing_log_bet_content_list() {
                    return chasing_log_bet_content_list;
                }

                public void setChasing_log_bet_content_list(List<ChasingLogBetContentListBeanX> chasing_log_bet_content_list) {
                    this.chasing_log_bet_content_list = chasing_log_bet_content_list;
                }

                public static class ChasingLogBetContentListBeanX implements Serializable {
                    /**
                     * wanfa_name : 大小单双
                     * bet_content : 大(冠军)
                     * bet_count : 1
                     * multiple_count : 1
                     * bet_money : 10.0
                     * unit_type : 1
                     * win_money : 0.0
                     * status : 2
                     */

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
    }
}

package com.sp.caitwo.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable {


    /**
     * data : {"access_token":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NDI4NjEyMjQsInVzZXJJZCI6IjI5ODMifQ.f7zf5JksT0OSbVsXYw9oVO19hhZrmmsS7raGdnGO5I6QPyoHDSRqVnod-BcYq6bgeshykVhyN7OacwfFlAOF8g","account":"aaaaaa","agent_level":2,"agent_type":1,"all_point":0,"array_profit":7,"chou_jiang_count":1,"cost":-22.4,"create_time":"2018-10-12 16:57:43","eleven_five_profit":7,"fans_number":0,"follow_number":0,"fucai_profit":7,"gift_number":0,"id":2983,"im_account":"10000370","invite_code":"55829491","is_bind_bank":1,"is_cservice":0,"is_first_login":1,"is_jiaren":0,"is_set_security_answer":0,"kuai_three_profit":7,"level":1,"lhc_profit":7,"live_number":0,"live_status":1,"login_address":"内网地址或者无法解析","login_time":"2018-11-21 12:33:31","low_frequency_profit":7,"nick_name":"wbbsb","personal_sign":"bshsshs","pk_ten_profit":7,"point":125.87,"real_name":"我的","registration_id":"1","security_level":4,"sex":1,"shishicaip_profit":7,"today_profit_and_loss":0,"todaybet_point":0,"todaywinning_point":0,"total_profit_and_loss":0,"twenty_eight_profit":10,"user_photo":"http://23.224.2.234:8099/res/upload/userPhoto/userPhoto_20181015105957_1539572397411.jpg","withdrawals_money":0,"withdrawals_password":"xxxx","yeb_blance":0,"yeb_is_open":1,"zfb_account":"66666666","zfb_name":"我的"}
     * request_id : 1542774824710
     * result_code : 0
     * result_desc : 请求成功
     * timestamp : 2018-11-21 12:33:44
     */

    private DataBean data;
    private String request_id;
    private String result_code;
    private String result_desc;
    private String timestamp;

    public DataBean getData() {
        if (data == null)
            return new DataBean();
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class DataBean implements Serializable{
        /**
         * access_token : Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NDI4NjEyMjQsInVzZXJJZCI6IjI5ODMifQ.f7zf5JksT0OSbVsXYw9oVO19hhZrmmsS7raGdnGO5I6QPyoHDSRqVnod-BcYq6bgeshykVhyN7OacwfFlAOF8g
         * account : aaaaaa
         * agent_level : 2
         * agent_type : 1
         * all_point : 0
         * array_profit : 7
         * chou_jiang_count : 1
         * cost : -22.4
         * create_time : 2018-10-12 16:57:43
         * eleven_five_profit : 7
         * fans_number : 0
         * follow_number : 0
         * fucai_profit : 7
         * gift_number : 0
         * id : 2983
         * im_account : 10000370
         * invite_code : 55829491
         * is_bind_bank : 1
         * is_cservice : 0
         * is_first_login : 1
         * is_jiaren : 0
         * is_set_security_answer : 0
         * kuai_three_profit : 7
         * level : 1
         * lhc_profit : 7
         * live_number : 0
         * live_status : 1
         * login_address : 内网地址或者无法解析
         * login_time : 2018-11-21 12:33:31
         * low_frequency_profit : 7
         * nick_name : wbbsb
         * personal_sign : bshsshs
         * pk_ten_profit : 7
         * point : 125.87
         * real_name : 我的
         * registration_id : 1
         * security_level : 4
         * sex : 1
         * shishicaip_profit : 7
         * today_profit_and_loss : 0
         * todaybet_point : 0
         * todaywinning_point : 0
         * total_profit_and_loss : 0
         * twenty_eight_profit : 10
         * user_photo : http://23.224.2.234:8099/res/upload/userPhoto/userPhoto_20181015105957_1539572397411.jpg
         * withdrawals_money : 0
         * withdrawals_password : xxxx
         * yeb_blance : 0
         * yeb_is_open : 1
         * zfb_account : 66666666
         * zfb_name : 我的
         */

        private String access_token;
        private String account;
        private String agent_level;
        private String agent_type;
        private double all_point;
        private String array_profit;
        private String chou_jiang_count;
        private double cost;
        private String create_time;
        private String eleven_five_profit;
        private String fans_number;
        private String follow_number;
        private String fucai_profit;
        private String gift_number;
        private String id;
        private String im_account;
        private String invite_code;
        private String is_bind_bank;
        private String is_cservice;
        private String is_first_login;
        private String is_jiaren;
        private String is_set_security_answer;
        private String kuai_three_profit;
        private String level;
        private String lhc_profit;
        private String live_number;
        private String live_status;
        private String login_address;
        private String login_time;
        private String low_frequency_profit;
        private String nick_name;
        private String personal_sign;
        private String pk_ten_profit;
        private double point;
        private String real_name;
        private String registration_id;
        private String security_level;
        private String sex;
        private String shishicaip_profit;
        private String today_profit_and_loss;
        private String todaybet_point;
        private String todaywinning_point;
        private String total_profit_and_loss;
        private String twenty_eight_profit;
        private String user_photo;
        private String withdrawals_money;
        private String withdrawals_password;
        private String yeb_blance;
        private String yeb_is_open;
        private String zfb_account;
        private String zfb_name;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAgent_level() {
            return agent_level;
        }

        public void setAgent_level(String agent_level) {
            this.agent_level = agent_level;
        }

        public String getAgent_type() {
            return agent_type;
        }

        public void setAgent_type(String agent_type) {
            this.agent_type = agent_type;
        }

        public double getAll_point() {
            return all_point;
        }

        public void setAll_point(double all_point) {
            this.all_point = all_point;
        }

        public String getArray_profit() {
            return array_profit;
        }

        public void setArray_profit(String array_profit) {
            this.array_profit = array_profit;
        }

        public String getChou_jiang_count() {
            return chou_jiang_count;
        }

        public void setChou_jiang_count(String chou_jiang_count) {
            this.chou_jiang_count = chou_jiang_count;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getEleven_five_profit() {
            return eleven_five_profit;
        }

        public void setEleven_five_profit(String eleven_five_profit) {
            this.eleven_five_profit = eleven_five_profit;
        }

        public String getFans_number() {
            return fans_number;
        }

        public void setFans_number(String fans_number) {
            this.fans_number = fans_number;
        }

        public String getFollow_number() {
            return follow_number;
        }

        public void setFollow_number(String follow_number) {
            this.follow_number = follow_number;
        }

        public String getFucai_profit() {
            return fucai_profit;
        }

        public void setFucai_profit(String fucai_profit) {
            this.fucai_profit = fucai_profit;
        }

        public String getGift_number() {
            return gift_number;
        }

        public void setGift_number(String gift_number) {
            this.gift_number = gift_number;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIm_account() {
            return im_account;
        }

        public void setIm_account(String im_account) {
            this.im_account = im_account;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getIs_bind_bank() {
            return is_bind_bank;
        }

        public void setIs_bind_bank(String is_bind_bank) {
            this.is_bind_bank = is_bind_bank;
        }

        public String getIs_cservice() {
            return is_cservice;
        }

        public void setIs_cservice(String is_cservice) {
            this.is_cservice = is_cservice;
        }

        public String getIs_first_login() {
            return is_first_login;
        }

        public void setIs_first_login(String is_first_login) {
            this.is_first_login = is_first_login;
        }

        public String getIs_jiaren() {
            return is_jiaren;
        }

        public void setIs_jiaren(String is_jiaren) {
            this.is_jiaren = is_jiaren;
        }

        public String getIs_set_security_answer() {
            return is_set_security_answer;
        }

        public void setIs_set_security_answer(String is_set_security_answer) {
            this.is_set_security_answer = is_set_security_answer;
        }

        public String getKuai_three_profit() {
            return kuai_three_profit;
        }

        public void setKuai_three_profit(String kuai_three_profit) {
            this.kuai_three_profit = kuai_three_profit;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLhc_profit() {
            return lhc_profit;
        }

        public void setLhc_profit(String lhc_profit) {
            this.lhc_profit = lhc_profit;
        }

        public String getLive_number() {
            return live_number;
        }

        public void setLive_number(String live_number) {
            this.live_number = live_number;
        }

        public String getLive_status() {
            return live_status;
        }

        public void setLive_status(String live_status) {
            this.live_status = live_status;
        }

        public String getLogin_address() {
            return login_address;
        }

        public void setLogin_address(String login_address) {
            this.login_address = login_address;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public String getLow_frequency_profit() {
            return low_frequency_profit;
        }

        public void setLow_frequency_profit(String low_frequency_profit) {
            this.low_frequency_profit = low_frequency_profit;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getPersonal_sign() {
            return personal_sign;
        }

        public void setPersonal_sign(String personal_sign) {
            this.personal_sign = personal_sign;
        }

        public String getPk_ten_profit() {
            return pk_ten_profit;
        }

        public void setPk_ten_profit(String pk_ten_profit) {
            this.pk_ten_profit = pk_ten_profit;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getRegistration_id() {
            return registration_id;
        }

        public void setRegistration_id(String registration_id) {
            this.registration_id = registration_id;
        }

        public String getSecurity_level() {
            return security_level;
        }

        public void setSecurity_level(String security_level) {
            this.security_level = security_level;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getShishicaip_profit() {
            return shishicaip_profit;
        }

        public void setShishicaip_profit(String shishicaip_profit) {
            this.shishicaip_profit = shishicaip_profit;
        }

        public String getToday_profit_and_loss() {
            return today_profit_and_loss;
        }

        public void setToday_profit_and_loss(String today_profit_and_loss) {
            this.today_profit_and_loss = today_profit_and_loss;
        }

        public String getTodaybet_point() {
            return todaybet_point;
        }

        public void setTodaybet_point(String todaybet_point) {
            this.todaybet_point = todaybet_point;
        }

        public String getTodaywinning_point() {
            return todaywinning_point;
        }

        public void setTodaywinning_point(String todaywinning_point) {
            this.todaywinning_point = todaywinning_point;
        }

        public String getTotal_profit_and_loss() {
            return total_profit_and_loss;
        }

        public void setTotal_profit_and_loss(String total_profit_and_loss) {
            this.total_profit_and_loss = total_profit_and_loss;
        }

        public String getTwenty_eight_profit() {
            return twenty_eight_profit;
        }

        public void setTwenty_eight_profit(String twenty_eight_profit) {
            this.twenty_eight_profit = twenty_eight_profit;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }

        public String getWithdrawals_money() {
            return withdrawals_money;
        }

        public void setWithdrawals_money(String withdrawals_money) {
            this.withdrawals_money = withdrawals_money;
        }

        public String getWithdrawals_password() {
            return withdrawals_password;
        }

        public void setWithdrawals_password(String withdrawals_password) {
            this.withdrawals_password = withdrawals_password;
        }

        public String getYeb_blance() {
            return yeb_blance;
        }

        public void setYeb_blance(String yeb_blance) {
            this.yeb_blance = yeb_blance;
        }

        public String getYeb_is_open() {
            return yeb_is_open;
        }

        public void setYeb_is_open(String yeb_is_open) {
            this.yeb_is_open = yeb_is_open;
        }

        public String getZfb_account() {
            return zfb_account;
        }

        public void setZfb_account(String zfb_account) {
            this.zfb_account = zfb_account;
        }

        public String getZfb_name() {
            return zfb_name;
        }

        public void setZfb_name(String zfb_name) {
            this.zfb_name = zfb_name;
        }
    }
}

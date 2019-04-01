package com.sp.caitwo.bean;

import java.io.Serializable;

public class UserDetailBean implements Serializable {


    /**
     * data : {"id":2967,"account":"lanbc159","user_photo":"http://23.224.2.234:8099/res/upload/backstage/defaultPhoto/20181012/upload_20181012111338_1539314018892.jpg","nick_name":"lihai","sex":2,"mobile":null,"point":9958.12,"balance":null,"create_time":"2018-10-09 09:52:26","registration_id":"1","im_account":"10000349","level":1,"login_time":"2018-11-30 19:36:24","personal_sign":null,"withdrawals_password":"xxxx","real_name":"局","all_point":0,"chou_jiang_count":1,"withdrawals_money":0,"today_profit_and_loss":0,"todaybet_point":0,"todaywinning_point":0,"total_profit_and_loss":17.91,"live_status":1,"gift_number":0,"live_number":0,"fans_number":0,"follow_number":0,"cost":-71,"is_cservice":0,"is_first_login":1,"notice":null,"yeb_blance":0,"yeb_is_open":1,"qq":null,"weixin":null,"zfb_name":"局","zfb_account":null,"is_jiaren":0,"access_token":null,"wanfa_sort":null,"background":null,"is_bind_bank":1,"withdrawals_number":5,"is_commission":1,"bet_protion":50,"bonus_money":7980.12,"agent_level":2,"invite_code":"55829491","agent_type":1,"kuai_three_profit":7,"shishicaip_profit":7,"eleven_five_profit":7,"fucai_profit":7,"array_profit":7,"twenty_eight_profit":10,"pk_ten_profit":7,"lhc_profit":7,"low_frequency_profit":7,"is_set_security_answer":0,"security_level":4,"login_address":null,"is_band_weixin":null}
     * timestamp : 2018-11-30 19:43:58
     * request_id : 1543578238401
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

    public static class DataBean implements Serializable{
        /**
         * id : 2967
         * account : lanbc159
         * user_photo : http://23.224.2.234:8099/res/upload/backstage/defaultPhoto/20181012/upload_20181012111338_1539314018892.jpg
         * nick_name : lihai
         * sex : 2
         * mobile : null
         * point : 9958.12
         * balance : null
         * create_time : 2018-10-09 09:52:26
         * registration_id : 1
         * im_account : 10000349
         * level : 1
         * login_time : 2018-11-30 19:36:24
         * personal_sign : null
         * withdrawals_password : xxxx
         * real_name : 局
         * all_point : 0
         * chou_jiang_count : 1
         * withdrawals_money : 0
         * today_profit_and_loss : 0
         * todaybet_point : 0
         * todaywinning_point : 0
         * total_profit_and_loss : 17.91
         * live_status : 1
         * gift_number : 0
         * live_number : 0
         * fans_number : 0
         * follow_number : 0
         * cost : -71
         * is_cservice : 0
         * is_first_login : 1
         * notice : null
         * yeb_blance : 0
         * yeb_is_open : 1
         * qq : null
         * weixin : null
         * zfb_name : 局
         * zfb_account : null
         * is_jiaren : 0
         * access_token : null
         * wanfa_sort : null
         * background : null
         * is_bind_bank : 1
         * withdrawals_number : 5
         * is_commission : 1
         * bet_protion : 50
         * bonus_money : 7980.12
         * agent_level : 2
         * invite_code : 55829491
         * agent_type : 1
         * kuai_three_profit : 7
         * shishicaip_profit : 7
         * eleven_five_profit : 7
         * fucai_profit : 7
         * array_profit : 7
         * twenty_eight_profit : 10
         * pk_ten_profit : 7
         * lhc_profit : 7
         * low_frequency_profit : 7
         * is_set_security_answer : 0
         * security_level : 4
         * login_address : null
         * is_band_weixin : null
         */

        private String id;
        private String account;
        private String user_photo;
        private String nick_name;
        private String sex;
        private String mobile;
        private double point;
        private String balance;
        private String create_time;
        private String registration_id;
        private String im_account;
        private String level;
        private String login_time;
        private String personal_sign;
        private String withdrawals_password;
        private String real_name;
        private String all_point;
        private String chou_jiang_count;
        private String withdrawals_money;
        private String today_profit_and_loss;
        private String todaybet_point;
        private String todaywinning_point;
        private double total_profit_and_loss;
        private String live_status;
        private String gift_number;
        private String live_number;
        private String fans_number;
        private String follow_number;
        private String cost;
        private String is_cservice;
        private String is_first_login;
        private String notice;
        private String yeb_blance;
        private String yeb_is_open;
        private String qq;
        private String weixin;
        private String zfb_name;
        private String zfb_account;
        private String is_jiaren;
        private String access_token;
        private String wanfa_sort;
        private String background;
        private String is_bind_bank;
        private String withdrawals_number;
        private String is_commission;
        private String bet_protion;
        private double bonus_money;
        private String agent_level;
        private String invite_code;
        private String agent_type;
        private String kuai_three_profit;
        private String shishicaip_profit;
        private String eleven_five_profit;
        private String fucai_profit;
        private String array_profit;
        private String twenty_eight_profit;
        private String pk_ten_profit;
        private String lhc_profit;
        private String low_frequency_profit;
        private String is_set_security_answer;
        private String security_level;
        private String login_address;
        private String is_band_weixin;

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

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getRegistration_id() {
            return registration_id;
        }

        public void setRegistration_id(String registration_id) {
            this.registration_id = registration_id;
        }

        public String getIm_account() {
            return im_account;
        }

        public void setIm_account(String im_account) {
            this.im_account = im_account;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public String getPersonal_sign() {
            return personal_sign;
        }

        public void setPersonal_sign(String personal_sign) {
            this.personal_sign = personal_sign;
        }

        public String getWithdrawals_password() {
            return withdrawals_password;
        }

        public void setWithdrawals_password(String withdrawals_password) {
            this.withdrawals_password = withdrawals_password;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getAll_point() {
            return all_point;
        }

        public void setAll_point(String all_point) {
            this.all_point = all_point;
        }

        public String getChou_jiang_count() {
            return chou_jiang_count;
        }

        public void setChou_jiang_count(String chou_jiang_count) {
            this.chou_jiang_count = chou_jiang_count;
        }

        public String getWithdrawals_money() {
            return withdrawals_money;
        }

        public void setWithdrawals_money(String withdrawals_money) {
            this.withdrawals_money = withdrawals_money;
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

        public double getTotal_profit_and_loss() {
            return total_profit_and_loss;
        }

        public void setTotal_profit_and_loss(double total_profit_and_loss) {
            this.total_profit_and_loss = total_profit_and_loss;
        }

        public String getLive_status() {
            return live_status;
        }

        public void setLive_status(String live_status) {
            this.live_status = live_status;
        }

        public String getGift_number() {
            return gift_number;
        }

        public void setGift_number(String gift_number) {
            this.gift_number = gift_number;
        }

        public String getLive_number() {
            return live_number;
        }

        public void setLive_number(String live_number) {
            this.live_number = live_number;
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

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
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

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
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

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getZfb_name() {
            return zfb_name;
        }

        public void setZfb_name(String zfb_name) {
            this.zfb_name = zfb_name;
        }

        public String getZfb_account() {
            return zfb_account;
        }

        public void setZfb_account(String zfb_account) {
            this.zfb_account = zfb_account;
        }

        public String getIs_jiaren() {
            return is_jiaren;
        }

        public void setIs_jiaren(String is_jiaren) {
            this.is_jiaren = is_jiaren;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getWanfa_sort() {
            return wanfa_sort;
        }

        public void setWanfa_sort(String wanfa_sort) {
            this.wanfa_sort = wanfa_sort;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getIs_bind_bank() {
            return is_bind_bank;
        }

        public void setIs_bind_bank(String is_bind_bank) {
            this.is_bind_bank = is_bind_bank;
        }

        public String getWithdrawals_number() {
            return withdrawals_number;
        }

        public void setWithdrawals_number(String withdrawals_number) {
            this.withdrawals_number = withdrawals_number;
        }

        public String getIs_commission() {
            return is_commission;
        }

        public void setIs_commission(String is_commission) {
            this.is_commission = is_commission;
        }

        public String getBet_protion() {
            return bet_protion;
        }

        public void setBet_protion(String bet_protion) {
            this.bet_protion = bet_protion;
        }

        public double getBonus_money() {
            return bonus_money;
        }

        public void setBonus_money(double bonus_money) {
            this.bonus_money = bonus_money;
        }

        public String getAgent_level() {
            return agent_level;
        }

        public void setAgent_level(String agent_level) {
            this.agent_level = agent_level;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getAgent_type() {
            return agent_type;
        }

        public void setAgent_type(String agent_type) {
            this.agent_type = agent_type;
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

        public String getIs_set_security_answer() {
            return is_set_security_answer;
        }

        public void setIs_set_security_answer(String is_set_security_answer) {
            this.is_set_security_answer = is_set_security_answer;
        }

        public String getSecurity_level() {
            return security_level;
        }

        public void setSecurity_level(String security_level) {
            this.security_level = security_level;
        }

        public String getLogin_address() {
            return login_address;
        }

        public void setLogin_address(String login_address) {
            this.login_address = login_address;
        }

        public String getIs_band_weixin() {
            return is_band_weixin;
        }

        public void setIs_band_weixin(String is_band_weixin) {
            this.is_band_weixin = is_band_weixin;
        }
    }
}

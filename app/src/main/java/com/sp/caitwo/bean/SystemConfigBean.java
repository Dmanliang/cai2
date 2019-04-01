package com.sp.caitwo.bean;

import java.util.List;

public class SystemConfigBean {


    /**
     * data : [{"name":"is_weixin_login","param_value":"1","config_desc":"微信登录"},{"name":"is_invite_code","param_value":"1","config_desc":"是否需要邀请码"},{"name":"register_phone","param_value":"2","config_desc":"注册手机号状态"},{"name":"register_invite_code","param_value":"1","config_desc":"注册邀请码状态"},{"name":"register_eamil","param_value":"3","config_desc":"注册邮箱状态"},{"name":"register_qq","param_value":"3","config_desc":"注册QQ状态"},{"name":"register_weixin","param_value":"3","config_desc":"注册微信状态"},{"name":"is_limit_xjfdc","param_value":"0","config_desc":"是否限制下级返点差"},{"name":"interact_room","param_value":"1","config_desc":"是否开启互动大厅"},{"name":"changlong","param_value":"1","config_desc":"是否开启长龙助手"},{"name":"is_speak","param_value":"1","config_desc":"是否开启发言状态"},{"name":"is_mute","param_value":"1","config_desc":"是否屏蔽消息"}]
     * timestamp : 2018-12-20 09:45:17
     * request_id : 1545270317500
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
         * name : is_weixin_login
         * param_value : 1
         * config_desc : 微信登录
         */

        private String name;
        private String param_value;
        private String config_desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParam_value() {
            return param_value;
        }

        public void setParam_value(String param_value) {
            this.param_value = param_value;
        }

        public String getConfig_desc() {
            return config_desc;
        }

        public void setConfig_desc(String config_desc) {
            this.config_desc = config_desc;
        }
    }
}

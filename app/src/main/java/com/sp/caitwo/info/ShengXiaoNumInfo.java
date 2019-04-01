package com.sp.caitwo.info;

public class ShengXiaoNumInfo {

    /**
     * data : {"狗":",1,13,25,37,49","鸡":",2,14,26,38","猴":",3,15,27,39","羊":",4,16,28,40","马":",5,17,29,41","蛇":",6,18,30,42","龙":",7,19,31,43","兔":",8,20,32,44","虎":",9,21,33,45","牛":",10,22,34,46","鼠":",11,23,35,47","猪":",12,24,36,48"}
     * timestamp : 2018-06-12 09:39:12
     * request_id : 1528767552451
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
        String data;
    }
}

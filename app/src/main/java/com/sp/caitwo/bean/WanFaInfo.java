package com.sp.caitwo.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 */

public class WanFaInfo {

    private String wanfa_type_name;
    private List<HomeWanFaBean.DataBean> wanFaList = new ArrayList<>();

    public String getWanfa_type_name() {
        return wanfa_type_name;
    }

    public void setWanfa_type_name(String wanfa_type_name) {
        this.wanfa_type_name = wanfa_type_name;
    }

    public List<HomeWanFaBean.DataBean> getWanFaList() {
        return wanFaList;
    }

    public void setWanFaList(List<HomeWanFaBean.DataBean> wanFaList) {
        this.wanFaList = wanFaList;
    }
}

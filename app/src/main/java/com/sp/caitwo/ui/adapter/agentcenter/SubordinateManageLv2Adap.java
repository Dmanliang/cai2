package com.sp.caitwo.ui.adapter.agentcenter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.SubordinateManageListBean;
import com.sp.caitwo.ui.holder.SubordinateReportFormsLv2Holder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TimeUtil;

import java.util.ArrayList;
import java.util.HashMap;

//复用代理报表中下级报表二级列表
public class SubordinateManageLv2Adap extends RVBaseAdap<SubordinateReportFormsLv2Holder> {

    private String[] label = {"累计返佣","当前余额","注册时间","最后登录","玩家类型"};
    private HashMap<String, String> dataMap;
    private ArrayList<String> labelList;
    private ArrayList<String> profitLossList;

    @Override
    public SubordinateReportFormsLv2Holder onCreateHolder(ViewGroup parent, int viewType) {
        return new SubordinateReportFormsLv2Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subordinate_report_forms_lv2,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(dataMap) ? 0 : dataMap.size();
    }

    @Override
    public void onBindHolder(SubordinateReportFormsLv2Holder holder, int position) {
        holder.tvLabel.setText(labelList.get(position));
        holder.tvProfitLoss.setText(profitLossList.get(position));
    }

    public void notifyDatasChang(SubordinateManageListBean.DataBean.ListBean listBean) {
        dataMap = new HashMap<>();//"累计返佣","当前余额","注册时间","最后登录","玩家类型"
        dataMap.put(label[0], String.valueOf(listBean.getAgent_profit_mooney()));
        dataMap.put(label[1], String.valueOf(listBean.getBalance()));
        dataMap.put(label[2], String.valueOf(TimeUtil.getTimeByTimeLen(listBean.getRegister_time(),"yyyy-MM-dd")));
        dataMap.put(label[3], TimeUtil.getTimeByTimeLen(Long.valueOf(listBean.getLogin_time()),"yyyy-MM-dd HH:mm"));
        dataMap.put(label[4], Integer.valueOf(listBean.getAgent_type()) == 1 ? listBean.getAgent_level() + "级代理" : "会员");
        labelList = new ArrayList<>(dataMap.keySet());
        profitLossList = new ArrayList<>(dataMap.values());
        super.notifyDatasChang();
    }
}

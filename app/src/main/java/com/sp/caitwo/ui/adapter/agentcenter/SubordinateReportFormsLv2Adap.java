package com.sp.caitwo.ui.adapter.agentcenter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.SubordinateReportFormsBean;
import com.sp.caitwo.ui.holder.SubordinateReportFormsLv2Holder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class SubordinateReportFormsLv2Adap extends RVBaseAdap<SubordinateReportFormsLv2Holder> {

    private String[] label = {"中奖金额","返点佣金","活动礼金","充值金额","提现金额"};
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
        if (profitLossList.get(position).startsWith("+"))
            holder.tvProfitLoss.setTextColor(Color.parseColor("#E54B55"));
        else if (profitLossList.get(position).startsWith("-"))
            holder.tvProfitLoss.setTextColor(Color.parseColor("#5BB960"));
        else
            holder.tvProfitLoss.setTextColor(Color.parseColor("#333333"));
    }

    public void notifyDatasChang(SubordinateReportFormsBean.DataBean.ListBean listBean) {
        dataMap = new HashMap<>();
        dataMap.put(label[0], String.valueOf(listBean.getWin_money()));
        dataMap.put(label[1], String.valueOf(listBean.getShare_profit_money()));
        dataMap.put(label[2], String.valueOf(listBean.getGift_money()));
        dataMap.put(label[3], String.valueOf(listBean.getRecharge_money()));
        dataMap.put(label[4], String.valueOf(listBean.getWithdraw_money()));
        labelList = new ArrayList<>(dataMap.keySet());
        profitLossList = new ArrayList<>(dataMap.values());
        super.notifyDatasChang();
    }
}

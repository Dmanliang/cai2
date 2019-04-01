package com.sp.caitwo.ui.adapter.agentcenter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.SubAccountDetailBean;
import com.sp.caitwo.bean.SubRechargeRecordDetailBean;
import com.sp.caitwo.bean.SubWithdrawRecordBean;
import com.sp.caitwo.ui.activity.agentcenter.SubordinateBusinessDetailActivity;
import com.sp.caitwo.ui.activity.UserInfo.UserBusinessRecordActivity;
import com.sp.caitwo.ui.holder.BetDetailHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.List;

/**
 * Created by vinson on 2018/11/8.
 * 复用注单详情的列表
 */

public class SubordinateBusinessDetailAdapter extends RVBaseAdap<BetDetailHolder> {

    private final Context context;
    private List<SubAccountDetailBean.DataBean.ListBean> accountDetailList;
    private List<SubWithdrawRecordBean.DataBean.ListBean> withdrawRecordList;
    private List<SubRechargeRecordDetailBean.DataBean.ListBean> rechargeRecordList;

    public SubordinateBusinessDetailAdapter(Context context) {
        this.context = context;
    }

    public void notifyDatasChang1(List<SubAccountDetailBean.DataBean.ListBean> list) {
        this.accountDetailList = list;
        this.withdrawRecordList = null;
        this.rechargeRecordList = null;
        super.notifyDatasChang();
    }

    public void notifyDatasChang2(List<SubWithdrawRecordBean.DataBean.ListBean> list) {
        this.withdrawRecordList = list;
        this.rechargeRecordList = null;
        this.accountDetailList = null;
        super.notifyDatasChang();
    }
    public void notifyDatasChang3(List<SubRechargeRecordDetailBean.DataBean.ListBean> list) {
        this.rechargeRecordList = list;
        this.accountDetailList = null;
        this.withdrawRecordList = null;
        super.notifyDatasChang();
    }

    @Override
    public BetDetailHolder onCreateHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bet_detail, parent, false);
        return new BetDetailHolder(inflate);
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(accountDetailList) ? BaseUtil.isEmpty(withdrawRecordList) ? BaseUtil.isEmpty(rechargeRecordList) ? 0 : rechargeRecordList.size() : withdrawRecordList.size() : accountDetailList.size();
    }

    @Override
    public void onBindHolder(BetDetailHolder holder, int position) {
        holder.tv_bet_money.setVisibility(View.GONE);
        String title = "";
        String time = "";
        String money = "";
        if (!BaseUtil.isEmpty(accountDetailList)){
            title = accountDetailList.get(position).getPoint_desc();
            time = accountDetailList.get(position).getCreate_time();
            money = accountDetailList.get(position).getPoint();
        }else if (!BaseUtil.isEmpty(withdrawRecordList)){
            if (BaseUtil.equals(withdrawRecordList.get(position).getStatus(),"0"))
                title = "未处理";
            else if (BaseUtil.equals(withdrawRecordList.get(position).getStatus(),"1"))
                title = "已处理";
            else if (BaseUtil.equals(withdrawRecordList.get(position).getStatus(),"2"))
                title = "处理失败";
            time = withdrawRecordList.get(position).getCreate_time();
            money = withdrawRecordList.get(position).getFee();
        }else if (!BaseUtil.isEmpty(rechargeRecordList)){
            if (BaseUtil.equals(rechargeRecordList.get(position).getStatus(),"0"))
                title = "待支付";
            else if (BaseUtil.equals(rechargeRecordList.get(position).getStatus(),"1"))
                title = "成功付款";
            else if (BaseUtil.equals(rechargeRecordList.get(position).getStatus(),"2"))
                title = "失败";
            time = rechargeRecordList.get(position).getCreate_time();
            money = rechargeRecordList.get(position).getRecharge_fee();
        }


        holder.tv_wanfa_name.setText(time);
        holder.tv_bet_content.setText(title);
        holder.tv_result.setText(money);
        if (money.startsWith("+")){
            holder.tv_result.setTextColor(Color.parseColor("#E54B55"));
        }else if (money.startsWith("-")){
            holder.tv_result.setTextColor(Color.parseColor("#5BB960"));
        }else {
            holder.tv_result.setTextColor(Color.parseColor("#333333"));
        }
    }

    @Override
    public void onPageLoad() {
        if (context instanceof SubordinateBusinessDetailActivity) {
            SubordinateBusinessDetailActivity activity = (SubordinateBusinessDetailActivity) context;
            activity.pageLoad();
        }else if (context instanceof UserBusinessRecordActivity){
            UserBusinessRecordActivity activity = (UserBusinessRecordActivity) context;
            activity.pageLoad();
        }
    }

    @Override
    public void onPullDownToRefresh() {
        if (context instanceof SubordinateBusinessDetailActivity) {
            SubordinateBusinessDetailActivity activity = (SubordinateBusinessDetailActivity) context;
            activity.setPage(1);
            activity.setData();
        }else if (context instanceof UserBusinessRecordActivity){
            UserBusinessRecordActivity activity = (UserBusinessRecordActivity) context;
            activity.setPage(1);
            activity.setData();
        }
    }
}

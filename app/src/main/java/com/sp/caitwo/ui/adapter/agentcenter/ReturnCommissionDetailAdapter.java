package com.sp.caitwo.ui.adapter.agentcenter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.ReturnCommissionDetailBean;
import com.sp.caitwo.ui.activity.agentcenter.ReturnCommissionDetailActivity;
import com.sp.caitwo.ui.holder.ReturnCommissionDetailHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.ArrayList;

public class ReturnCommissionDetailAdapter extends RVBaseAdap<ReturnCommissionDetailHolder> {

    private ArrayList<ReturnCommissionDetailBean.DataBean> dataList;
    private ReturnCommissionDetailActivity activity;

    public ReturnCommissionDetailAdapter(ReturnCommissionDetailActivity activity) {
        this.activity = activity;
    }

    @Override
    public ReturnCommissionDetailHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ReturnCommissionDetailHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_return_commission_detail,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(dataList) ? 0 : dataList.size();
    }

    @Override
    public void onBindHolder(ReturnCommissionDetailHolder holder, int position) {
        holder.tvTime.setText(dataList.get(position).getCreate_day());
        holder.tvMoney.setText(String.valueOf(dataList.get(position).getShare_profit_money()));
    }

    public void notifyDatasChang(ArrayList<ReturnCommissionDetailBean.DataBean> dataList) {
        this.dataList = dataList;
        super.notifyDatasChang();
    }

    @Override
    public void onPageLoad() {
        activity.pageLoad();
    }
}

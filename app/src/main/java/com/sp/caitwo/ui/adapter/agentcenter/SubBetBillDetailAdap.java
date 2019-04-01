package com.sp.caitwo.ui.adapter.agentcenter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.BetRecordBean;
import com.sp.caitwo.bean.CathecticRecordInfo;
import com.sp.caitwo.bean.SubordinateBetDetailBean;
import com.sp.caitwo.bean.TraceNumListBean;
import com.sp.caitwo.ui.holder.BetDetailHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.List;

import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by vinson on 2018/11/8.
 */

public class SubBetBillDetailAdap extends RVBaseAdap<BetDetailHolder> {

    private final Context context;
    private List<SubordinateBetDetailBean.DataBean.ListBean.BetListBean> betList;
    private List<BetRecordBean.DataBean.BetListBean> betRecordList;
    private List<TraceNumListBean.DataBeanX.DataBean.IssueListBean.ChasingLogBetContentListBeanX> traceNumList;

    public SubBetBillDetailAdap(Context context, List<SubordinateBetDetailBean.DataBean.ListBean.BetListBean> betList,
                                List<BetRecordBean.DataBean.BetListBean> betRecordList, List<TraceNumListBean.DataBeanX.DataBean.IssueListBean.ChasingLogBetContentListBeanX> traceNumList) {
        this.context = context;
        this.betList = betList;
        this.betRecordList = betRecordList;
        this.traceNumList = traceNumList;
    }

    @Override
    public BetDetailHolder onCreateHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bet_detail, parent, false);
        return new BetDetailHolder(inflate);
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(betList) ? BaseUtil.isEmpty(betRecordList) ? BaseUtil.isEmpty(traceNumList) ? 0 : traceNumList.size() : betRecordList.size() : betList.size();
    }

    @Override
    public void onBindHolder(BetDetailHolder holder, int position) {
        if (!BaseUtil.isEmpty(betList)) {
            holder.tv_wanfa_name.setText(betList.get(position).getWanfa_name());
            holder.tv_bet_content.setText(betList.get(position).getBet_content());
            holder.tv_bet_money.setText(String.format(context.getString(R.string.money_with_sign), betList.get(position).getBet_money()));
            if (BaseUtil.equals(betList.get(position).getStatus(), "0")) {
                holder.tv_result.setText("待开奖");
                holder.tv_result.setTextColor(Color.parseColor("#888888"));
            } else if (BaseUtil.equals(betList.get(position).getStatus(), "1")) {
                holder.tv_result.setText(String.format(context.getString(R.string.money_with_sign), betList.get(position).getWin_money()));
                holder.tv_result.setTextColor(Color.parseColor("#E54B55"));
            } else if (BaseUtil.equals(betList.get(position).getStatus(), "2")) {
                holder.tv_result.setText("未中奖");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            } else if (BaseUtil.equals(betList.get(position).getStatus(), "3")) {
                holder.tv_result.setText("已取消");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            } else {
                holder.tv_result.setText("失败");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            }
        }else if (!BaseUtil.isEmpty(betRecordList)){
            holder.tv_wanfa_name.setText(betRecordList.get(position).getWanfa_name());
            holder.tv_bet_content.setText(betRecordList.get(position).getBet_content());
            holder.tv_bet_money.setText(String.format(context.getString(R.string.money_with_sign), betRecordList.get(position).getBet_money()));
            if (BaseUtil.equals(betRecordList.get(position).getStatus(), "0")) {
                holder.tv_result.setText("待开奖");
                holder.tv_result.setTextColor(Color.parseColor("#888888"));
            } else if (BaseUtil.equals(betRecordList.get(position).getStatus(), "1")) {
                holder.tv_result.setText(String.format(context.getString(R.string.money_with_sign), String.valueOf(betRecordList.get(position).getWin_money())));
                holder.tv_result.setTextColor(Color.parseColor("#E54B55"));
            } else if (BaseUtil.equals(betRecordList.get(position).getStatus(), "2")) {
                holder.tv_result.setText("未中奖");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            } else if (BaseUtil.equals(betRecordList.get(position).getStatus(), "3")) {
                holder.tv_result.setText("已取消");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            } else {
                holder.tv_result.setText("失败");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            }
        }else if (!BaseUtil.isEmpty(traceNumList)){
            holder.tv_wanfa_name.setText(traceNumList.get(position).getWanfa_name());
            holder.tv_bet_content.setText(traceNumList.get(position).getBet_content());
            holder.tv_bet_money.setText(String.format(context.getString(R.string.money_with_sign), String.valueOf(traceNumList.get(position).getBet_money())));
            String status = String.valueOf(traceNumList.get(position).getStatus());
            if(BaseUtil.equals(status,"0")){
                holder.tv_result.setText("待开奖");
                holder.tv_result.setTextColor(Color.parseColor("#888888"));
            }else if(BaseUtil.equals(status,"1")){
                holder.tv_result.setText(String.format(context.getString(R.string.money_with_sign),String.valueOf(traceNumList.get(position).getWin_money())));
                holder.tv_result.setTextColor(Color.parseColor("#E54B55"));
            }else if(BaseUtil.equals(status,"2")){
                holder.tv_result.setText("未中奖");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            }else if(BaseUtil.equals(status,"3")){
                holder.tv_result.setText("已取消");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            }else if (BaseUtil.equals(status,"5")){
                holder.tv_result.setText("追号停止");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            }else {
                holder.tv_result.setText("失败");
                holder.tv_result.setTextColor(Color.parseColor("#333333"));
            }
        }
    }

}

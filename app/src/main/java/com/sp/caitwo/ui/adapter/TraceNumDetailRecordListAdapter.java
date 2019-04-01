package com.sp.caitwo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.TraceNumListBean;
import com.sp.caitwo.ui.activity.UserInfo.TraceNumDetailActivity;
import com.sp.caitwo.ui.activity.UserInfo.UserBetRecordActivity;
import com.sp.caitwo.ui.activity.agentcenter.SubBetBillDetailActivity;
import com.sp.caitwo.ui.holder.BetDetailHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.List;

/**
 * Created by vinson on 2018/11/8.
 * 复用下级投注明细
 */

public class TraceNumDetailRecordListAdapter extends RVBaseAdap<BetDetailHolder> {

    private final Context context;
    private final TraceNumListBean.DataBeanX.DataBean dataBean;
    private List<TraceNumListBean.DataBeanX.DataBean.IssueListBean> list;

    public TraceNumDetailRecordListAdapter(Context context,TraceNumListBean.DataBeanX.DataBean dataBean) {
        this.context = context;
        this.dataBean = dataBean;
        this.list = dataBean.getIssue_list();
    }

    @Override
    public BetDetailHolder onCreateHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bet_detail, parent, false);
        return new BetDetailHolder(inflate);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public void onBindHolder(BetDetailHolder holder, int position) {
        holder.tv_bet_content.setText("开奖号码:" + (BaseUtil.isEmpty(list.get(position).getOpen_number()) ? "" : list.get(position).getOpen_number()));
        holder.tv_wanfa_name.setText(list.get(position).getIssue());
        holder.tv_bet_money.setVisibility(View.GONE);
        String status = String.valueOf(list.get(position).getStatus());
        if(BaseUtil.equals(status,"0")){
            holder.tv_result.setText("待开奖");
            holder.tv_result.setTextColor(Color.parseColor("#888888"));
        }else if(BaseUtil.equals(status,"1")){
            holder.tv_result.setText(String.format(context.getString(R.string.money_with_sign),String.valueOf(list.get(position).getWin_money())));
            holder.tv_result.setTextColor(Color.parseColor("#E54B55"));
        }else if(BaseUtil.equals(status,"2")){
            holder.tv_result.setText("未中奖");
            holder.tv_result.setTextColor(Color.parseColor("#333333"));
        }else if(BaseUtil.equals(status,"3")){
            holder.tv_result.setText("已取消");
            holder.tv_result.setTextColor(Color.parseColor("#333333"));
        }else if (BaseUtil.equals(status,"5")){
            holder.tv_result.setText("追号停止");
            holder.tv_result.setTextColor(Color.parseColor("#888888"));
        }else {
            holder.tv_result.setText("失败");
            holder.tv_result.setTextColor(Color.parseColor("#333333"));
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                if (BaseUtil.isEmpty(list.get(pos).getOpen_number())){
                    ToastUtil.Toast(context,"追号已停止,不能查看详情");
                    return;
                }
                Intent intent = new Intent(context, SubBetBillDetailActivity.class);
                intent.putExtra("betDetail",dataBean);
                intent.putExtra("pos",pos);
                context.startActivity(intent);
            }
        });
    }
}

package com.sp.caitwo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.BetRecordBean;
import com.sp.caitwo.bean.TraceNumListBean;
import com.sp.caitwo.ui.activity.UserInfo.TraceNumDetailActivity;
import com.sp.caitwo.ui.activity.UserInfo.UserBetRecordActivity;
import com.sp.caitwo.ui.activity.agentcenter.SubBetBillDetailActivity;
import com.sp.caitwo.ui.fragment.UserInfo.UserTraceNumRecordFrag;
import com.sp.caitwo.ui.holder.BetDetailHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TimeUtil;

import java.text.DecimalFormat;
import java.util.List;

import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by vinson on 2018/11/8.
 * 复用下级投注明细
 */

public class TraceNumRecordAdapter extends RVBaseAdap<BetDetailHolder> {

    private final Context context;
    private UserTraceNumRecordFrag frag;
    private List<TraceNumListBean.DataBeanX.DataBean> list;

    public TraceNumRecordAdapter(UserTraceNumRecordFrag frag) {
        this.context = frag.getContext();
        this.frag = frag;
    }

    public void notifyDatasChang(List<TraceNumListBean.DataBeanX.DataBean> list) {
        this.list = list;
        super.notifyDatasChang();
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
        holder.tv_wanfa_name.setText(list.get(position).getCreate_time());
        holder.tv_bet_content.setText(list.get(position).getLottery_name());
        holder.tv_bet_money.setText(String.format(context.getString(R.string.money_with_sign),getTwoDecimal(list.get(position).getTotal_bet_money())));
        String status = String.valueOf(list.get(position).getStatus());
        if(BaseUtil.equals(status,"1")){
            holder.tv_result.setText("未开始");
            holder.tv_result.setTextColor(ContextCompat.getColor(context,R.color.gray2));
        }else if(BaseUtil.equals(status,"2")){
            holder.tv_result.setText("进行中");
            holder.tv_result.setTextColor(ContextCompat.getColor(context,R.color.gray2));
        }else if(BaseUtil.equals(status,"3")){
            double money = list.get(position).getTotal_win_money();
            if(money == 0){
                holder.tv_result.setText("未中奖");
                holder.tv_result.setTextColor(ContextCompat.getColor(context,R.color.text_black));
            }else {
                holder.tv_result.setText(String.format("+%s",new DecimalFormat("0.00").format(money)));
                holder.tv_result.setTextColor(ContextCompat.getColor(context,R.color.light_red));
            }

        }else {
            holder.tv_result.setText("失败");
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Intent intent = new Intent(context, TraceNumDetailActivity.class);
                intent.putExtra("traceNumDetail",list.get(pos));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onPageLoad() {
        frag.pageLoad();
    }

    @Override
    public void onPullDownToRefresh() {
        frag.setPage(1);
        frag.setData();
    }
}

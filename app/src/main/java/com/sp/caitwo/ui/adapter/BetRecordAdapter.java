package com.sp.caitwo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.BetRecordBean;
import com.sp.caitwo.ui.activity.agentcenter.SubBetBillDetailActivity;
import com.sp.caitwo.ui.activity.UserInfo.UserBetRecordActivity;
import com.sp.caitwo.ui.fragment.UserInfo.UserBetRecordFrag;
import com.sp.caitwo.ui.holder.BetDetailHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.List;

/**
 * Created by vinson on 2018/11/8.
 * 复用下级投注明细
 */

public class BetRecordAdapter extends RVBaseAdap<BetDetailHolder> {

    private final Context context;
    private UserBetRecordFrag frag;
    private List<BetRecordBean.DataBean> list;

    public BetRecordAdapter(UserBetRecordFrag frag) {
        this.context = frag.getContext();
        this.frag = frag;
    }

    public void notifyDatasChang(List<BetRecordBean.DataBean> list) {
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
        holder.tv_wanfa_name.setText(String.format(context.getString(R.string.how_issue),list.get(position).getQihao()));
        holder.tv_bet_content.setText(list.get(position).getBase_wan_fa_name());
        holder.tv_bet_money.setText(String.format(context.getString(R.string.money_with_sign),list.get(position).getMoney()));
        if(BaseUtil.equals(list.get(position).getStatus(),"0")){
            holder.tv_result.setText("待开奖");
            holder.tv_result.setTextColor(Color.parseColor("#888888"));
        }else if(BaseUtil.equals(list.get(position).getStatus(),"1")){
            holder.tv_result.setText(String.format(context.getString(R.string.money_with_sign),String.valueOf(list.get(position).getBonus())));
            holder.tv_result.setTextColor(Color.parseColor("#E54B55"));
        }else if(BaseUtil.equals(list.get(position).getStatus(),"2")){
            holder.tv_result.setText("未中奖");
            holder.tv_result.setTextColor(Color.parseColor("#333333"));
        }else if(BaseUtil.equals(list.get(position).getStatus(),"3")){
            holder.tv_result.setText("已取消");
            holder.tv_result.setTextColor(Color.parseColor("#333333"));
        }else{
            holder.tv_result.setText("失败");
            holder.tv_result.setTextColor(Color.parseColor("#333333"));
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Intent intent = new Intent(context, SubBetBillDetailActivity.class);
                intent.putExtra("betDetail",list.get(pos));
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

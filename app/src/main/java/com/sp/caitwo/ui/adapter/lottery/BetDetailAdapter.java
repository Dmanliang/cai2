package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.CathecticRecordInfo;
import com.sp.caitwo.ui.holder.BetDetailHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;

import java.util.ArrayList;
import java.util.List;

import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by vinson on 2018/11/8.
 */

public class BetDetailAdapter extends RVBaseAdap<BetDetailHolder> {

    private final Context context;
    private List<CathecticRecordInfo.DataBean.BetListBean> list;

    public BetDetailAdapter(Context context, List<CathecticRecordInfo.DataBean.BetListBean> list) {
        this.context = context;
        this.list = list;
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
    public void onBindHolder(BetDetailHolder betDetailHolder, int position) {
        betDetailHolder.tv_wanfa_name.setText(list.get(position).getWanfa_name());
        betDetailHolder.tv_bet_content.setText(list.get(position).getBet_content());
        betDetailHolder.tv_bet_money.setText(String.format(context.getString(R.string.money_with_sign),getTwoDecimal(list.get(position).getBet_money())+""));
        if(list.get(position).getStatus() == 0){
            betDetailHolder.tv_result.setText("待开奖");
        }else if(list.get(position).getStatus() == 1){
            betDetailHolder.tv_result.setText(String.format(context.getString(R.string.money_with_sign),getTwoDecimal(list.get(position).getWin_money())+""));
        }else if(list.get(position).getStatus() == 2){
            betDetailHolder.tv_result.setText("未中奖");
        }else if(list.get(position).getStatus() == 3){
            betDetailHolder.tv_result.setText("已取消");
        }else{
            betDetailHolder.tv_result.setText("失败");
        }

    }

}

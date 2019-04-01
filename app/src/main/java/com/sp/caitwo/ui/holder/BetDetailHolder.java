package com.sp.caitwo.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;

/**
 * Created by vinson on 2018/11/8.
 */

public class BetDetailHolder extends RecyclerView.ViewHolder {

    public TextView tv_bet_content,tv_bet_money,tv_wanfa_name,tv_result;

    public BetDetailHolder(View itemView) {
        super(itemView);
        tv_bet_content = itemView.findViewById(R.id.tv_bet_content);
        tv_bet_money = itemView.findViewById(R.id.tv_bet_money);
        tv_wanfa_name = itemView.findViewById(R.id.tv_wanfa_name);
        tv_result = itemView.findViewById(R.id.tv_result);
    }
}

package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.view.WarpLinearLayout;

public class LhcLotteryHistoryHolder extends RecyclerView.ViewHolder {

    public TextView tvIssueNum;
    public WarpLinearLayout lotteryNums;
    public WarpLinearLayout lotteryNames;
    public TextView tvLotteryTime;

    public LhcLotteryHistoryHolder(@NonNull View itemView) {
        super(itemView);
        tvIssueNum = itemView.findViewById(R.id.tv_issue_num);
        lotteryNums = itemView.findViewById(R.id.lottery_nums);
        lotteryNames = itemView.findViewById(R.id.lottery_names);
        tvLotteryTime = itemView.findViewById(R.id.tv_lottery_time);
    }
}

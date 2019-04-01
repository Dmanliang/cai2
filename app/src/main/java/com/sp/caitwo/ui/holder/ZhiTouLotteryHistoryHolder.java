package com.sp.caitwo.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.view.WarpLinearLayout;

/**
 * Created by vinson on 2018/11/7.
 */

public class ZhiTouLotteryHistoryHolder extends RecyclerView.ViewHolder {

    public final TextView tvIssueNum;
    public final WarpLinearLayout lotteryNums;
    public final LinearLayout lotteryNums2;
    public final TextView tvLotteryTime;

    public ZhiTouLotteryHistoryHolder(View itemView) {
        super(itemView);
        tvIssueNum = itemView.findViewById(R.id.tv_issue_num);
        lotteryNums = itemView.findViewById(R.id.lottery_nums);
        tvLotteryTime = itemView.findViewById(R.id.tv_lottery_time);
        lotteryNums2 = itemView.findViewById(R.id.lottery_nums2);
    }
}

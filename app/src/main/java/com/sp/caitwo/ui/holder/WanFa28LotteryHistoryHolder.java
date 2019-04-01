package com.sp.caitwo.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;

/**
 * Created by vinson on 2018/11/7.
 */

public class WanFa28LotteryHistoryHolder extends RecyclerView.ViewHolder {

    public final TextView tvIssueNum;
    public final TextView tvNumSize;
    public final TextView tvForm;
    public final LinearLayout lotteryHistoryNums;

    public WanFa28LotteryHistoryHolder(View itemView) {
        super(itemView);
        tvIssueNum = itemView.findViewById(R.id.tv_issue_num);
        tvNumSize = itemView.findViewById(R.id.tv_num_size);
        tvForm = itemView.findViewById(R.id.tv_form);
        lotteryHistoryNums = itemView.findViewById(R.id.lottery_history_nums);
    }
}

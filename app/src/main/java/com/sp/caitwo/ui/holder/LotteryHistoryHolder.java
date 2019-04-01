package com.sp.caitwo.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;

/**
 * Created by vinson on 2018/11/7.
 */

public class LotteryHistoryHolder extends RecyclerView.ViewHolder {

    public final TextView tvIssueNum;
    public final ImageView ivLotteryDicePos1;
    public final ImageView ivLotteryDicePos2;
    public final ImageView ivLotteryDicePos3;
    public final TextView tvSumValue;
    public final TextView tvNumSize;
    public final TextView tvForm;

    public LotteryHistoryHolder(View itemView) {
        super(itemView);
        tvIssueNum = itemView.findViewById(R.id.tv_issue_num);
        ivLotteryDicePos1 = itemView.findViewById(R.id.iv_lottery_dice_pos1);
        ivLotteryDicePos2 = itemView.findViewById(R.id.iv_lottery_dice_pos2);
        ivLotteryDicePos3 = itemView.findViewById(R.id.iv_lottery_dice_pos3);
        tvSumValue = itemView.findViewById(R.id.tv_sum_value);
        tvNumSize = itemView.findViewById(R.id.tv_num_size);
        tvForm = itemView.findViewById(R.id.tv_form);
    }
}

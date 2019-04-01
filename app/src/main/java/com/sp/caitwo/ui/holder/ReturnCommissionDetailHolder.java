package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;

public class ReturnCommissionDetailHolder extends RecyclerView.ViewHolder {

    public final TextView tvTime;
    public final TextView tvMoney;

    public ReturnCommissionDetailHolder(@NonNull View itemView) {
        super(itemView);
        tvTime = itemView.findViewById(R.id.tv_time);
        tvMoney = itemView.findViewById(R.id.tv_money);
    }
}

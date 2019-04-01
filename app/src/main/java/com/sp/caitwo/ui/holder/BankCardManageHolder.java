package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;

public class BankCardManageHolder extends RecyclerView.ViewHolder {

    public final ImageView ivBankLogo;
    public final TextView tvBankType;
    public final TextView tvTailNum;
    public final TextView tvChange;

    public BankCardManageHolder(@NonNull View itemView) {
        super(itemView);
        ivBankLogo = itemView.findViewById(R.id.iv_bank_logo);
        tvBankType = itemView.findViewById(R.id.tv_bank_name);
        tvTailNum = itemView.findViewById(R.id.tv_tail_num);
        tvChange = itemView.findViewById(R.id.tv_change);
    }
}

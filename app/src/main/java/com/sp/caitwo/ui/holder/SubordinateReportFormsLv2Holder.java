package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;

public class SubordinateReportFormsLv2Holder extends RecyclerView.ViewHolder {

    public final TextView tvLabel;
    public final TextView tvProfitLoss;

    public SubordinateReportFormsLv2Holder(@NonNull View itemView) {
        super(itemView);
        tvLabel = itemView.findViewById(R.id.tv_label);
        tvProfitLoss = itemView.findViewById(R.id.tv_profit_loss);
    }
}

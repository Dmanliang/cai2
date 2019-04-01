package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;

public class SubordinateReportFormsLv1Holder extends RecyclerView.ViewHolder {

    public CheckBox cbArrow;
    public final TextView tvAccount;
    public final TextView tvLevel;
    public final TextView tvBet;
    public final TextView tvProfitLoss;
    public final RecyclerView rvLevel2;
    public final TextView tvHisTeamReportForms;
    public LinearLayout llyLv2List;

    public SubordinateReportFormsLv1Holder(@NonNull View itemView) {
        super(itemView);
        cbArrow = itemView.findViewById(R.id.cb_arrow);
        tvAccount = itemView.findViewById(R.id.tv_account);
        tvLevel = itemView.findViewById(R.id.tv_level);
        tvBet = itemView.findViewById(R.id.tv_bet);
        tvProfitLoss = itemView.findViewById(R.id.tv_profit_loss);
        tvHisTeamReportForms = itemView.findViewById(R.id.tv_his_team_report_forms);
        rvLevel2 = itemView.findViewById(R.id.rv_level2);
        llyLv2List = itemView.findViewById(R.id.lly_lv2list);
    }
}

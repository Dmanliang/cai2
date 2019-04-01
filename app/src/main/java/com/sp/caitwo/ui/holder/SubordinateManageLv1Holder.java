package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;

public class SubordinateManageLv1Holder extends RecyclerView.ViewHolder {

    public CheckBox cbArrow;
    public final TextView tvAccount;
    public final TextView tvLevel;
    public final TextView tvSubordinateNum;
    public LinearLayout llyLv2List;
    public final RecyclerView rvLevel2;
    public final TextView tvLookReturnPoint;
    public final TextView tvLookSubordinate;

    public SubordinateManageLv1Holder(@NonNull View itemView) {
        super(itemView);
        cbArrow = itemView.findViewById(R.id.cb_arrow);
        tvAccount = itemView.findViewById(R.id.tv_account);
        tvLevel = itemView.findViewById(R.id.tv_level);
        tvSubordinateNum = itemView.findViewById(R.id.tv_subordinate_num);
        llyLv2List = itemView.findViewById(R.id.lly_lv2list);
        rvLevel2 = itemView.findViewById(R.id.rv_level2);
        tvLookReturnPoint = itemView.findViewById(R.id.tv_look_return_point);
        tvLookSubordinate = itemView.findViewById(R.id.tv_look_subordinate);
    }
}

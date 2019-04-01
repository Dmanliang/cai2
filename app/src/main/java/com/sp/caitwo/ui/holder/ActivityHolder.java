package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;

public class ActivityHolder extends RecyclerView.ViewHolder {

    public final ImageView ivBannerImg;
    public final TextView tvTitle;
    public final TextView tvSubTitle;

    public ActivityHolder(@NonNull View itemView) {
        super(itemView);
        ivBannerImg = itemView.findViewById(R.id.iv_banner_img);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvSubTitle = itemView.findViewById(R.id.tv_sub_title);
    }
}

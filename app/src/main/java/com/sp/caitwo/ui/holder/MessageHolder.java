package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;

public class MessageHolder extends RecyclerView.ViewHolder {

    public final TextView tvTitle;
    public final TextView tvTime;
    public final ImageView ivRedPoint;

    public MessageHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_title);
        tvTime = itemView.findViewById(R.id.tv_time);
        ivRedPoint = itemView.findViewById(R.id.iv_red_point);

    }
}

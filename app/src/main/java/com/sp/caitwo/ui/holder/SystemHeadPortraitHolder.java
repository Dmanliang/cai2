package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sp.caitwo.R;

public class SystemHeadPortraitHolder extends RecyclerView.ViewHolder {

    public final ImageView image;

    public SystemHeadPortraitHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.iv_head_portrait);
    }
}

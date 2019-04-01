package com.sp.caitwo.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sp.caitwo.R;
import com.sp.xwjlibrary.widget.DoubleTextView;

/**
 * Created by Administrator on 2018/11/6.
 */

public class Kuai3HzHolder extends RecyclerView.ViewHolder {
    public final DoubleTextView dtvKuai3Hz;


    public Kuai3HzHolder(View itemView) {
        super(itemView);
        dtvKuai3Hz = itemView.findViewById(R.id.dtv_jiangsu_kuai3);
    }
}

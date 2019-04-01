package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.DailyBonusBean;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 */

public class DailyBonusAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<DailyBonusBean.DataBean>  wanfalist;
    public DailyBonusAdapter(Context context,List<DailyBonusBean.DataBean>  wanfalist){
        this.context = context;
        this.wanfalist = wanfalist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_daily_bonus,null,false);
        Holder viewholder = new Holder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof Holder){
            TextView tvVipLevel = ((Holder) holder).tv_num1;
            TextView scale100 = ((Holder) holder).tv_num2;
            TextView scale100000 = ((Holder) holder).tv_num3;
            TextView scale500000 = ((Holder) holder).tv_num4;
            if (BaseUtil.isEmpty(tvVipLevel.getText()))
                tvVipLevel.setText(String.format(context.getString(R.string.vip_level_int),wanfalist.get(position * 3).getUser_level()));
            if (BaseUtil.isEmpty(scale100.getText()))
                scale100.setText(String.format("%s%%", wanfalist.get(position * 3).getBonus_rate()));
            if (BaseUtil.isEmpty(scale500000.getText()))
                scale100000.setText(String.format("%s%%", wanfalist.get(position * 3 + 1).getBonus_rate()));
            if (BaseUtil.isEmpty(scale500000.getText()))
                scale500000.setText(String.format("%s%%", wanfalist.get(position * 3 + 2).getBonus_rate()));
        }
    }

    @Override
    public int getItemCount() {
        return BaseUtil.isEmpty(wanfalist) ? 0 : wanfalist.size() / 3;
    }

    public class  Holder extends RecyclerView.ViewHolder{

        public TextView tv_num1,tv_num2,tv_num3,tv_num4;

        public Holder(View itemView) {
            super(itemView);
            tv_num1 = itemView.findViewById(R.id.tv_num1);
            tv_num2 = itemView.findViewById(R.id.tv_num2);
            tv_num3 = itemView.findViewById(R.id.tv_num3);
            tv_num4 = itemView.findViewById(R.id.tv_num4);

        }
    }


}

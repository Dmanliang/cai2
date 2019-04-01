package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.ui.holder.Kuai3HzHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.widget.DoubleTextView;

import java.util.ArrayList;
import java.util.List;

import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by vinson on 2018/11/5.
 */

public class BetKuai3Adapter extends RVBaseAdap<RecyclerView.ViewHolder> {

    private Context context;
    private List<BiliListInfo.ListBeans> list;
    private Kuai3ItemClickListener kuai3ClickListener;

    public BetKuai3Adapter(Context context, List<BiliListInfo.ListBeans> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jiangsu_kuai3_hz,null,false);
        Kuai3HzHolder viewHolder = new Kuai3HzHolder(view);
        return viewHolder;
    }

    @Override
    public int getCount() {
        return list.size() != 0 ? list.get(0).getList().size() : 0;
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if(viewHolder instanceof Kuai3HzHolder){
            DoubleTextView dtvKuai3Hz = ((Kuai3HzHolder) viewHolder).dtvKuai3Hz;
            dtvKuai3Hz.setOneText(list.get(0).getList().get(position).getBili_name());
            dtvKuai3Hz.setTwoText("赔"+list.get(0).getList().get(position).getBili());
            dtvKuai3Hz.setTag(position);
            dtvKuai3Hz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kuai3ClickListener.ItemClickListener(v,position);
                }
            });
            if (list.get(0).getList().get(position).isFocus()) {
                dtvKuai3Hz.setBackgroundResource(R.drawable.shape_lottery_rect_ball_red);
                dtvKuai3Hz.setOneColor(context.getResources().getColor(R.color.text_white));
                dtvKuai3Hz.setTwoColor(context.getResources().getColor(R.color.text_white));
            } else {
                dtvKuai3Hz.setBackgroundResource(R.drawable.shape_lottery_rect_ball);
                dtvKuai3Hz.setOneColor(context.getResources().getColor(R.color.text_red));
                dtvKuai3Hz.setTwoColor(context.getResources().getColor(R.color.gray2));
            }
        }
    }


    public void setKuai3ClickListener(Kuai3ItemClickListener kuai3ClickListener){
        this.kuai3ClickListener = kuai3ClickListener;
    }

    public interface Kuai3ItemClickListener{
        void ItemClickListener(View view,int position);
    }
}

package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.info.LhcAllWanFaInfo;

import java.util.List;

public class LhcLotteryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<LhcAllWanFaInfo.DataBean.ListsBean> list;
    private int titleindex,tabindex;
    private OnItemClickListener mOnItemClickListener;
    private LhcLotteryItemAdapter lotteryItemAdapter;

    public LhcLotteryAdapter(Context context,List<LhcAllWanFaInfo.DataBean.ListsBean> list, int titleindex, int tabindex){
        this.context = context;
        this.list = list;
        this.titleindex = titleindex;
        this.tabindex = tabindex;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lottery, viewGroup, false);
        return new LhcLotteryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof  LhcLotteryViewHolder){
            ((LhcLotteryViewHolder) viewHolder).lottery_bit.setVisibility(View.GONE);
            ((LhcLotteryViewHolder) viewHolder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
            lotteryItemAdapter = new LhcLotteryItemAdapter(context,list.get(position).getList(), position);
            ((LhcLotteryViewHolder) viewHolder).lottery_ball_list.setAdapter(lotteryItemAdapter);
            ((LhcLotteryViewHolder) viewHolder).rgSpeedChoice.setVisibility(View.GONE);
            lotteryItemAdapter.setOnItemImageViewClickListener(new LhcLotteryItemAdapter.OnItemImageViewClickListener() {
                @Override
                public void OnItemImageViewClick(View view, int positionPare, int position) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.OnItemImageViewClick(view,positionPare,position);
                    }
                }
            });
            lotteryItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LhcLotteryViewHolder extends RecyclerView.ViewHolder{

        TextView lottery_bit;
        RecyclerView lottery_ball_list;
        private final RadioGroup rgSpeedChoice;

        public LhcLotteryViewHolder(View itemView) {
            super(itemView);
            lottery_bit = itemView.findViewById(R.id.lottery_bit);
            lottery_ball_list = itemView.findViewById(R.id.lottery_ball_list);
            rgSpeedChoice = itemView.findViewById(R.id.rg_speed_choice);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void OnItemImageViewClick(View view, int positionPare, int position);
    }
}

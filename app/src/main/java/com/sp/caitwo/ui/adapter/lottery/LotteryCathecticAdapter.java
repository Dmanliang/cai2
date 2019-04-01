package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.info.BetInfo;
import com.sp.caitwo.ui.info.LotteryCathecticInfo;

import java.util.List;

public class LotteryCathecticAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<BetInfo> list;
    private RemoveOnClickListener removeOnClickListener;

    public LotteryCathecticAdapter(Context context, List<BetInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lottery_cathectic, viewGroup, false);
        CathecticViewHolder viewHolder = new CathecticViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if(viewHolder instanceof CathecticViewHolder){
            ((CathecticViewHolder) viewHolder).lotteryBetstr.setText(list.get(i).getBet_dec());
            ((CathecticViewHolder) viewHolder).lotteryTypecontent.setText(list.get(i).getWanfa_name()+"  " +list.get(i).getBet_num()+"注 " +list.get(i).getBet_money());
            ((CathecticViewHolder) viewHolder).lotteryReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeOnClickListener.removeOnClick(view,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CathecticViewHolder extends RecyclerView.ViewHolder {

        public TextView lotteryBetstr, lotteryTypecontent;
        public RelativeLayout lotteryReduce;

        public CathecticViewHolder(@NonNull View itemView) {
            super(itemView);
            lotteryBetstr = itemView.findViewById(R.id.lottery_betstr);
            lotteryTypecontent = itemView.findViewById(R.id.lottery_typecontent);
            lotteryReduce = itemView.findViewById(R.id.lottery_reduce);

        }
    }

    public void setRemoveOnClickListener(RemoveOnClickListener removeOnClickListener){
        this.removeOnClickListener = removeOnClickListener;
    }

    public interface RemoveOnClickListener{
        void removeOnClick(View view, int position);
    }
}

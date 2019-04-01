package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.base.SeBoCons;
import com.sp.caitwo.info.LhcAllWanFaInfo;
import com.sp.xwjlibrary.widget.DoubleTextView;

import java.util.List;

import static com.sp.util.Util.getTwoDecimal;

public class LhcLotteryItemAdapter extends RecyclerView.Adapter {

    private List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> list;
    private Context context;
    private int positionPare;
    private OnItemImageViewClickListener mOnItemImageViewClickListener;


    public LhcLotteryItemAdapter(Context context,List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> list,int positionPare){
        this.context = context;
        this.list = list;
        this.positionPare = positionPare;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lottery_ball_rect,viewGroup,false);
        LhcLotteryRectItemViewHolder lhcLotteryRectItemViewHolder = new LhcLotteryRectItemViewHolder(view);
        return lhcLotteryRectItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int position) {
        if(viewHolder instanceof LhcLotteryRectItemViewHolder){
            if(list.get(position).isFocus()){
                ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setOneColor(context.getResources().getColor(R.color.text_white));
                ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setTwoColor(context.getResources().getColor(R.color.text_white));
                if(SeBoCons.getInstant().getRedball().contains(list.get(position).getWanfa_name()) || list.get(position).getWanfa_name().contains("红")){
                    ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball_red);
                }else if(SeBoCons.getInstant().getBlueball().contains(list.get(position).getWanfa_name())|| list.get(position).getWanfa_name().contains("蓝")){
                    ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball_blue);
                }else if(SeBoCons.getInstant().getGreenball().contains(list.get(position).getWanfa_name())|| list.get(position).getWanfa_name().contains("绿")){
                    ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball_green);
                }else{
                    ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball_red);
                }
            }else{
                ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball);
                ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setTwoColor(context.getResources().getColor(R.color.gray2));
                if(SeBoCons.getInstant().getRedball().contains(list.get(position).getWanfa_name()) || list.get(position).getWanfa_name().contains("红")){
                    ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setOneColor(context.getResources().getColor(R.color.lottery_red));
                }else if(SeBoCons.getInstant().getBlueball().contains(list.get(position).getWanfa_name())|| list.get(position).getWanfa_name().contains("蓝")){
                    ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setOneColor(context.getResources().getColor(R.color.lottery_blue));
                }else if(SeBoCons.getInstant().getGreenball().contains(list.get(position).getWanfa_name())|| list.get(position).getWanfa_name().contains("绿")){
                    ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setOneColor(context.getResources().getColor(R.color.lottery_green));
                }else{
                    ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setOneColor(context.getResources().getColor(R.color.lottery_red));
                }
            }

            ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setOneText(list.get(position).getWanfa_name());
            ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setTwoText("赔" + list.get(position).getOdd());
            ((LhcLotteryRectItemViewHolder) viewHolder).item_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemImageViewClickListener.OnItemImageViewClick(view,positionPare,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LhcLotteryRectItemViewHolder extends RecyclerView.ViewHolder {
        DoubleTextView item_text;

        public LhcLotteryRectItemViewHolder(View itemView) {
            super(itemView);
            item_text = itemView.findViewById(R.id.tv_num_rect);
        }
    }


    public void setOnItemImageViewClickListener(OnItemImageViewClickListener mOnItemImageViewClickListener) {
        this.mOnItemImageViewClickListener = mOnItemImageViewClickListener;
    }

    public interface OnItemImageViewClickListener {
        void OnItemImageViewClick(View view, int positionPare, int position);
    }
}

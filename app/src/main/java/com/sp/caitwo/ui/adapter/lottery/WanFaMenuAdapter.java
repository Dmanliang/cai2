package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.HomeWanFaBean;

import java.util.List;

public class WanFaMenuAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<HomeWanFaBean.DataBean> list;
    private OnItemClickListener onItemClickListener;

    public WanFaMenuAdapter(Context context,List<HomeWanFaBean.DataBean> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wanfa_menu,viewGroup,false);
        WanfaMenuViewHolder wanfaMenuViewHolder = new WanfaMenuViewHolder(view);
        return wanfaMenuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        if(viewHolder instanceof WanfaMenuViewHolder){
            ((WanfaMenuViewHolder) viewHolder).tv_wanfa.setText(list.get(i).getName());
            ((WanfaMenuViewHolder) viewHolder).tv_wanfa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(view,i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public class WanfaMenuViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_wanfa;

        public WanfaMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_wanfa = itemView.findViewById(R.id.tv_wanfa);
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
    }
}

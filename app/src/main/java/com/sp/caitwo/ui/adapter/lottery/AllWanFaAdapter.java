package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.WanFaInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/11/2.
 */

public class AllWanFaAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<WanFaInfo> wanfalist;
    private AllWanfaItemClickListener allWanfaItemClickListener;
    private WanFaAdapter adapter;

    public AllWanFaAdapter(Context context,List<WanFaInfo> wanfalist){
        this.context = context;
        this.wanfalist = wanfalist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_wanfa,null,false);
        AllWanFaHolder viewholder = new AllWanFaHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof AllWanFaHolder){
            ((AllWanFaHolder) holder).wanfa_name.setText(wanfalist.get(position).getWanfa_type_name());
            if(wanfalist.get(position).getWanFaList().size() != 0){
                ((AllWanFaHolder) holder).wanfa_recycler.setLayoutManager(new GridLayoutManager(context,3));
                adapter = new WanFaAdapter(context,wanfalist.get(position).getWanFaList(),position);
                ((AllWanFaHolder) holder).wanfa_recycler.setAdapter(adapter);
                adapter.setOnItemImageViewClickListener(new WanFaAdapter.OnItemImageViewClickListener() {
                    @Override
                    public void OnItemImageViewClick(View view, int positionPare, int position) {
                        if(allWanfaItemClickListener != null){
                            allWanfaItemClickListener.AllWanfaClick(view,positionPare,position);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return wanfalist.size();
    }

    public void setWanfaItemListener(AllWanfaItemClickListener allWanfaItemClickListener){
        this.allWanfaItemClickListener = allWanfaItemClickListener;
    }

    public class AllWanFaHolder extends RecyclerView.ViewHolder{

        public TextView wanfa_name;
        public RecyclerView wanfa_recycler;

        public AllWanFaHolder(View itemView) {
            super(itemView);
            wanfa_name = (TextView)itemView.findViewById(R.id.wanfa_name);
            wanfa_recycler = (RecyclerView)itemView.findViewById(R.id.wanfa_recycler);
        }
    }

    public interface AllWanfaItemClickListener{
        void AllWanfaClick(View view, int positionPare, int position);
    }
}

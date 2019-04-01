package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.HomeWanFaBean;

import java.util.List;

import static com.sp.caitwo.base.LotteryCons.iconRes;
import static com.sp.caitwo.base.LotteryCons.setImageIcn;

/**
 * Created by yong on 2018/1/2.
 */
public class WanFaAdapter extends RecyclerView.Adapter {


    private List<HomeWanFaBean.DataBean> data;
    private OnItemImageViewClickListener mOnItemImageViewClickListener;
    private int positionPare;
    private Context context;

    public WanFaAdapter(Context context, List<HomeWanFaBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    public WanFaAdapter(Context context, List<HomeWanFaBean.DataBean> data, int positionPare) {
        this.context = context;
        this.data = data;
        this.positionPare = positionPare;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wanfa, null, false);
        WanFaViewHolder viewHolder = new WanFaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof WanFaViewHolder) {
            setImageIcn(((WanFaViewHolder) holder).image,data.get(position).getGame_type());
            ((WanFaViewHolder) holder).name.setText(data.get(position).getName());
            ((WanFaViewHolder) holder).remarks.setText(data.get(position).getRemarks());
            ((WanFaViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemImageViewClickListener != null) {
                        mOnItemImageViewClickListener.OnItemImageViewClick(v, positionPare, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class WanFaViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name, remarks;

        public WanFaViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            remarks = itemView.findViewById(R.id.remarks);
        }
    }

    public void setOnItemImageViewClickListener(OnItemImageViewClickListener mOnItemImageViewClickListener) {
        this.mOnItemImageViewClickListener = mOnItemImageViewClickListener;
    }

    public interface OnItemImageViewClickListener {
        void OnItemImageViewClick(View view, int positionPare, int position);
    }
}

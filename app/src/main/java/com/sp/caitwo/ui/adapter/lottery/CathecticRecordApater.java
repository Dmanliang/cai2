package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.CathecticRecordInfo;

import java.util.List;

import static com.sp.util.Util.getTwoDecimal;


/**
 * Created by Administrator on 2018/10/23.
 */

public class CathecticRecordApater extends RecyclerView.Adapter {

    private Context context;
    private List<CathecticRecordInfo.DataBean> list;
    private CheckOnClickListener checkOnClickListener;

    protected final int TYPE_HEADER = 0;
    protected final int TYPE_FOOTER = 1;
    protected final int TYPE_NORMAL = 2;
    private View mHeaderView;
    private View mFooterView;


    public CathecticRecordApater(Context context, List<CathecticRecordInfo.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        //第一个item应该加载Header
        if (position == 0){
            return TYPE_HEADER;
        }
        //最后一个,应该加载Footer
        if(position == getItemCount() - 1){
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    //添加HeaderView和FooterView
    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public void addFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return new RecyclerView.ViewHolder(mHeaderView) {};
        }
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new RecyclerView.ViewHolder(mFooterView) {};
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_cathectic_record, null, false);
        CathecticRecordViewHolder viewHolder = new CathecticRecordViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CathecticRecordViewHolder) {
            if (position == getItemCount() - 1){
                return;
            }
            ((CathecticRecordViewHolder) holder).cathecticrecord_name.setText(list.get(position).getBase_wan_fa_name());
            ((CathecticRecordViewHolder) holder).cathecticrecord_issue.setText(list.get(position).getQihao() + "期");
            ((CathecticRecordViewHolder) holder).cathecticrecord_money.setText(getTwoDecimal(list.get(position).getMoney()) + "");
            if (list.get(position).getStatus() == 1) {//0待开奖 1中奖 2 未中奖3取消 4失败
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setTextColor(context.getResources().getColor(R.color.red_gesture));
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setText("+" + getTwoDecimal(list.get(position).getBonus()) + "");
            } else if (list.get(position).getStatus() == 0) {
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setTextColor(context.getResources().getColor(R.color.text_gray3));
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setText("待开奖");
            } else if (list.get(position).getStatus() == 2) {
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setTextColor(context.getResources().getColor(R.color.text_black));
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setText("未中奖");
            } else if (list.get(position).getStatus() == 3) {
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setTextColor(context.getResources().getColor(R.color.text_black));
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setText("已取消");
            }else if (list.get(position).getStatus() == 4) {
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setTextColor(context.getResources().getColor(R.color.text_black));
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setText("开奖失败");
            }else{
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setTextColor(context.getResources().getColor(R.color.red_gesture));
                ((CathecticRecordViewHolder) holder).cathecticrecord_getmoney.setText("+" + getTwoDecimal(list.get(position).getBonus()) + "");
            }
            ((CathecticRecordViewHolder) holder).cathecticrecord_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkOnClickListener.setCheckItemClick(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() + 1  : 0;
    }

    public void setClickListener(CheckOnClickListener checkOnClickListener) {
        this.checkOnClickListener = checkOnClickListener;
    }

    public class CathecticRecordViewHolder extends RecyclerView.ViewHolder {

        public TextView cathecticrecord_name, cathecticrecord_issue, cathecticrecord_money, cathecticrecord_getmoney, cathecticrecord_check;

        public CathecticRecordViewHolder(View itemView) {
            super(itemView);
            cathecticrecord_name = itemView.findViewById(R.id.cathecticrecord_name);
            cathecticrecord_issue = itemView.findViewById(R.id.cathecticrecord_issue);
            cathecticrecord_money = itemView.findViewById(R.id.cathecticrecord_money);
            cathecticrecord_getmoney = itemView.findViewById(R.id.cathecticrecord_getmoney);
            cathecticrecord_check = itemView.findViewById(R.id.cathecticrecord_check);
        }
    }

    public interface CheckOnClickListener {
        void setCheckItemClick(RecyclerView.ViewHolder viewholder, int position);
    }
}

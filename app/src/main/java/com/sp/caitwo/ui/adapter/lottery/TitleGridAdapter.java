package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.info.LotteryTitle;

import java.util.List;

/**
 * Created by dell on 2017/8/31.
 */

public class TitleGridAdapter extends BaseAdapter {

    private Context context;
    private List<LotteryTitle> list;
    public GridTitleViewHolder viewholder;
    private LayoutInflater mInflater;
    public OnItemClickListener onItemClickListener;

    public TitleGridAdapter(Context context, List<LotteryTitle> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewholder = null;
        if (convertView == null) {
            viewholder = new GridTitleViewHolder();
            convertView = mInflater.inflate(R.layout.item_lottery_choice, parent, false);
            viewholder.lottery_text = convertView.findViewById(R.id.item_choice_title);
            viewholder.lottery_choice_layout = convertView.findViewById(R.id.lottery_choice_layout);
            viewholder.lottery_text.setTag(position);
            convertView.setTag(viewholder);
        } else {
            viewholder = (GridTitleViewHolder) convertView.getTag();
        }
        if(!list.get(position).getLottery_title().isEmpty()){
            viewholder.lottery_text.setText(list.get(position).getLottery_title());
        }else{
            viewholder.lottery_text.setVisibility(View.GONE);
        }
        viewholder.lottery_choice_layout.setBackgroundColor(context.getResources().getColor(R.color.text_white));
        if(list.get(position).isFocus()){
            viewholder.lottery_text.setChecked(true);
            viewholder.lottery_text.setTextColor(context.getResources().getColor(R.color.text_white));
        }else{
            viewholder.lottery_text.setChecked(false);
            viewholder.lottery_text.setTextColor(context.getResources().getColor(R.color.text_black));
        }
        viewholder.lottery_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.OnClick(viewholder,position);
                }
            }
        });
        return convertView;
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public class GridTitleViewHolder {
        public CheckBox lottery_text;
        public RelativeLayout lottery_choice_layout;
    }

    public interface OnItemClickListener{
        void OnClick(GridTitleViewHolder viewHolder, int position);
    }
}

package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.info.LotteryTabInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */

public class TabGridAdapter extends BaseAdapter {

    private List<LotteryTabInfo> list;
    private Context context;
    private GridTabViewHolder viewholder;
    private LayoutInflater mInflater;
    private TabOnItemClickListener tabOnItemClickListener;

    public TabGridAdapter(Context context, List<LotteryTabInfo> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewholder = null;
        if (convertView == null) {
            viewholder = new GridTabViewHolder();
            convertView = mInflater.inflate(R.layout.item_lottery_choice, parent, false);
            viewholder.lottery_text = (CheckBox) convertView.findViewById(R.id.item_choice_title);
            viewholder.lottery_choice_layout = (RelativeLayout) convertView.findViewById(R.id.lottery_choice_layout);
            viewholder.lottery_text.setTag(position);
            convertView.setTag(viewholder);
        } else {
            viewholder = (GridTabViewHolder) convertView.getTag();
        }
        if(!list.get(position).getTitle().isEmpty()){
            viewholder.lottery_text.setText(list.get(position).getTitle());
        }else{
            viewholder.lottery_text.setVisibility(View.GONE);
        }
        viewholder.lottery_choice_layout.setBackgroundColor(context.getResources().getColor(R.color.c4));
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
                if(tabOnItemClickListener != null){
                    tabOnItemClickListener.onItemClick(viewholder,position);
                }
            }
        });
        return convertView;
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

    public class GridTabViewHolder {
        public CheckBox lottery_text;
        public RelativeLayout lottery_choice_layout;
    }

    public void setOnItemClickListener(TabOnItemClickListener tabOnItemClickListener){
        this.tabOnItemClickListener = tabOnItemClickListener;
    }

    public interface TabOnItemClickListener{
        void onItemClick(GridTabViewHolder viewHolder, int position);
    }

}

package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.info.LhcWinsRecordInfo;
import com.sp.caitwo.ui.holder.LhcLotteryHistoryHolder;
import com.sp.caitwo.ui.view.WarpLinearLayout;
import com.sp.util.DateUtil;
import com.sp.util.ViewUtil;
import java.util.Arrays;
import java.util.List;

import static com.sp.caitwo.ui.fragment.lottery.LhcFragment.blueball;
import static com.sp.caitwo.ui.fragment.lottery.LhcFragment.greenball;
import static com.sp.caitwo.ui.fragment.lottery.LhcFragment.redball;
import static com.sp.xwjlibrary.util.TimeUtil.getTimeByTimeLen;

public class LhcHistoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<LhcWinsRecordInfo.DataBean> historyList;
    private TextView[] tvs_nums = new TextView[8];
    private TextView[] tvs_name = new TextView[8];


    public LhcHistoryAdapter(Context context,List<LhcWinsRecordInfo.DataBean> historyList){
        this.context = context;
        this.historyList = historyList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LhcLotteryHistoryHolder(View.inflate(context, R.layout.item_lhc_lottery_history, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof LhcLotteryHistoryHolder){
            ((LhcLotteryHistoryHolder) viewHolder).tvIssueNum.setText(historyList.get(i).getQihao());
            ((LhcLotteryHistoryHolder) viewHolder).tvLotteryTime.setText(getTimeByTimeLen(historyList.get(i).getCreate_time()*1000,"yyyy年MM月dd日 HH:mm"));
            setNumText(((LhcLotteryHistoryHolder) viewHolder).lotteryNums,((LhcLotteryHistoryHolder) viewHolder).lotteryNames,i);
        }
    }

    public void setNumText(WarpLinearLayout lotteryNums,WarpLinearLayout lotteryNames,int position) {
        lotteryNums.removeAllViews();
        lotteryNames.removeAllViews();
        tvs_nums[0] = setTextView(historyList.get(position).getNumber1(), 0);
        tvs_nums[1] = setTextView(historyList.get(position).getNumber2(), 0);
        tvs_nums[2] = setTextView(historyList.get(position).getNumber3(), 0);
        tvs_nums[3] = setTextView(historyList.get(position).getNumber4(), 0);
        tvs_nums[4] = setTextView(historyList.get(position).getNumber5(), 0);
        tvs_nums[5] = setTextView(historyList.get(position).getNumber6(), 0);
        tvs_nums[7] = setTextView(historyList.get(position).getNumber7(), 0);
        tvs_name[0] = setTextView(historyList.get(position).getNumber1_sx(), 1);
        tvs_name[1] = setTextView(historyList.get(position).getNumber2_sx(), 1);
        tvs_name[2] = setTextView(historyList.get(position).getNumber3_sx(), 1);
        tvs_name[3] = setTextView(historyList.get(position).getNumber4_sx(), 1);
        tvs_name[4] = setTextView(historyList.get(position).getNumber5_sx(), 1);
        tvs_name[5] = setTextView(historyList.get(position).getNumber6_sx(), 1);
        tvs_name[7] = setTextView(historyList.get(position).getNumber7_sx(), 1);
        tvs_nums[6] = setTextView("+", 1);
        tvs_name[6] = setTextView("+", 1);
        for(int i=0;i<tvs_name.length;i++){
            lotteryNums.addView(tvs_nums[i]);
            lotteryNames.addView(tvs_name[i]);
        }
    }

    public TextView setTextView(String text, int type) {
        int width = ViewUtil.Dip2Px(context, 16);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        lp.setMargins(5,0,5,0);
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        if (type == 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            textView.setTextColor(Color.WHITE);
            if (Arrays.asList(redball).contains(text)) {
                textView.setBackground(context.getResources().getDrawable(R.drawable.shape_lottery_ball_red_red)); //红色球设置背景
            } else if (Arrays.asList(blueball).contains(text)) {
                textView.setBackground(context.getResources().getDrawable(R.drawable.shape_lottery_ball_blue)); //蓝色球设置背景
            } else if (Arrays.asList(greenball).contains(text)) {
                textView.setBackground(context.getResources().getDrawable(R.drawable.shape_lottery_ball_green)); //绿色球设置背景
            }
        } else {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textView.setTextColor(ContextCompat.getColor(context, R.color.color_333));
        }
        textView.setText(text);
        textView.setLayoutParams(lp);
        return textView;
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}

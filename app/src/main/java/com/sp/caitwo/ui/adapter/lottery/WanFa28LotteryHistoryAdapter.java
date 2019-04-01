package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
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
import com.sp.caitwo.base.SeBoCons;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.ui.holder.LotteryHistoryHolder;
import com.sp.caitwo.ui.holder.WanFa28LotteryHistoryHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vinson on 2018/11/7.
 */

public class WanFa28LotteryHistoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RoomOpenInfo.DataBean.OpenTimeBean> list;
    private int[] sizeForm = new int[]{R.drawable.shape_big_double_yellow, R.drawable.shape_small_single_blue};

    public WanFa28LotteryHistoryAdapter(Context context, List<RoomOpenInfo.DataBean.OpenTimeBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WanFa28LotteryHistoryHolder(View.inflate(context, R.layout.item_wanfa28_lottery_history, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof WanFa28LotteryHistoryHolder) {
            ((WanFa28LotteryHistoryHolder) viewHolder).tvIssueNum.setText(list.get(position).getGame_num());
            setTextNums(((WanFa28LotteryHistoryHolder) viewHolder).lotteryHistoryNums, list.get(position).getGet_result());
            String[] typeArray = null;
            typeArray = list.get(position).getResult_type().split(",");
            if (typeArray[0].equals("大")) {
                ((WanFa28LotteryHistoryHolder) viewHolder).tvNumSize.setBackgroundResource(sizeForm[0]);
            } else {
                ((WanFa28LotteryHistoryHolder) viewHolder).tvNumSize.setBackgroundResource(sizeForm[1]);
            }
            if (typeArray[1].equals("双")) {
                ((WanFa28LotteryHistoryHolder) viewHolder).tvForm.setBackgroundResource(sizeForm[0]);
            } else {
                ((WanFa28LotteryHistoryHolder) viewHolder).tvForm.setBackgroundResource(sizeForm[1]);
            }
            ((WanFa28LotteryHistoryHolder) viewHolder).tvNumSize.setText(typeArray[0]);
            ((WanFa28LotteryHistoryHolder) viewHolder).tvForm.setText(typeArray[1]);
        }
    }

    public void setTextNums(LinearLayout lotteryNums, String get_result) {
        lotteryNums.removeAllViews();
        String[] numArray = null;
        get_result = get_result.replaceAll("\\+", ",");
        get_result = get_result.replaceAll("\\=", ",");
        numArray = get_result.split(",");
        for (int i = 0; i < numArray.length; i++) {
            TextView tvs = new TextView(context);
            TextView tvs1 = new TextView(context);
            tvs.setText(numArray[i]);
            Integer num = Integer.valueOf(numArray[i]);
            if (i == numArray.length - 1) {
                if (0 == num || 13 == num || 14 == num || 27 == num) {
                    tvs.setBackgroundResource(R.drawable.icon_ball_gray);
                } else if (0 == num%3) {
                    tvs.setBackgroundResource(R.drawable.icon_ball_red);
                }  else if (1 == num%3) {
                    tvs.setBackgroundResource(R.drawable.icon_ball_green);
                }else{
                    tvs.setBackgroundResource(R.drawable.icon_ball_blue);
                }
            } else {
                tvs.setBackgroundResource(R.drawable.icon_ball_red);
            }
            tvs.setGravity(Gravity.CENTER);
            tvs.setTextColor(ContextCompat.getColor(context, R.color.white));
            tvs.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            lotteryNums.addView(tvs);
            tvs1.setTextColor(ContextCompat.getColor(context, R.color.color_333));
            tvs1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            if (i != numArray.length - 2 && i != numArray.length - 1) {
                tvs1.setText("+");
                lotteryNums.addView(tvs1);
            } else if (i == numArray.length - 2) {
                tvs1.setText("=");
                lotteryNums.addView(tvs1);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

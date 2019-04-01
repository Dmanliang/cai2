package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.ui.holder.ZhiTouLotteryHistoryHolder;
import com.sp.caitwo.ui.view.WarpLinearLayout;
import com.sp.util.DateUtil;

import java.util.ArrayList;

public class LotteryHistoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<RoomOpenInfo.DataBean.OpenTimeBean> list;
    private int game_type;


    public LotteryHistoryAdapter(Context context, ArrayList<RoomOpenInfo.DataBean.OpenTimeBean> list, int game_type) {
        this.context = context;
        this.list = list;
        this.game_type = game_type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ZhiTouLotteryHistoryHolder(View.inflate(context, R.layout.item_ffc_lottery_history, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ZhiTouLotteryHistoryHolder) {
            ((ZhiTouLotteryHistoryHolder) viewHolder).tvIssueNum.setText(list.get(position).getGame_num());
            ((ZhiTouLotteryHistoryHolder) viewHolder).tvLotteryTime.setText(DateUtil.getTime(list.get(position).getOpen_time()));
            setNumText(((ZhiTouLotteryHistoryHolder) viewHolder).lotteryNums, ((ZhiTouLotteryHistoryHolder) viewHolder).lotteryNums2, list.get(position).getGet_result());
        }
    }

    public void setNumText(WarpLinearLayout lotteryNums, LinearLayout lotteryNums2, String numbers) {
        lotteryNums.removeAllViews();
        lotteryNums2.removeAllViews();
        String[] numArray = null;
        numbers = numbers.replaceAll("\\+", ",");
        numbers = numbers.replaceAll("\\=", ",");
        numArray = numbers.split(",");
        int len = numArray.length;
        if (game_type == LotteryCons.CQSSC || game_type == LotteryCons.TJSSC) {
            len = len - 1;
        }
        for (int i = 0; i < len; i++) {
            TextView tvs = new TextView(context);
            tvs.setBackgroundResource(R.drawable.icon_ball_red);
            tvs.setGravity(Gravity.CENTER);
            tvs.setTextColor(ContextCompat.getColor(context, R.color.white));
            if (game_type == LotteryCons.CQSSC || game_type == LotteryCons.TJSSC) {
                tvs.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            } else {
                tvs.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            }
            tvs.setText(numArray[i]);
            if (game_type == LotteryCons.XYPK10 || game_type == LotteryCons.BJPK10) {
                lotteryNums.setVisibility(View.VISIBLE);
                lotteryNums2.setVisibility(View.GONE);
                lotteryNums.addView(tvs);
            } else {
                lotteryNums.setVisibility(View.GONE);
                lotteryNums2.setVisibility(View.VISIBLE);
                lotteryNums2.addView(tvs);
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

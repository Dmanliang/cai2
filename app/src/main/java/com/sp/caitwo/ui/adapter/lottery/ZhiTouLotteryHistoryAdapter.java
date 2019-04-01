package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.ui.holder.ZhiTouLotteryHistoryHolder;
import com.sp.caitwo.ui.info.LotteryKJLogInfo;
import com.sp.caitwo.ui.view.WarpLinearLayout;
import com.sp.util.DateUtil;
import com.sp.xwjlibrary.adapter.RVBaseAdap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sp.xwjlibrary.util.TimeUtil.getTimeByTimeLen;

/**
 * Created by vinson on 2018/11/7.
 */

public class ZhiTouLotteryHistoryAdapter extends RVBaseAdap<ZhiTouLotteryHistoryHolder> {

    private Context context;
    private List<LotteryKJLogInfo.DataBean> list;
    private int game_type;

    public ZhiTouLotteryHistoryAdapter(Context context, List<LotteryKJLogInfo.DataBean> list, int game_type) {
        this.context = context;
        this.list = list;
        this.game_type = game_type;
    }

    @Override
    public ZhiTouLotteryHistoryHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ZhiTouLotteryHistoryHolder(View.inflate(context, R.layout.item_ffc_lottery_history, null));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void onBindHolder(ZhiTouLotteryHistoryHolder lotteryHistoryHolder, int position) {
        lotteryHistoryHolder.tvIssueNum.setText(list.get(position).getQihao());
        lotteryHistoryHolder.tvLotteryTime.setText(getTimeByTimeLen(list.get(position).getCreate_time() * 1000, "yyyy年MM月dd日 HH:mm:ss"));
        setNumText(lotteryHistoryHolder.lotteryNums, lotteryHistoryHolder.lotteryNums2, list.get(position).getNumbers());
    }

    public void setNumText(WarpLinearLayout lotteryNums, LinearLayout lotteryNums2, String numbers) {
        lotteryNums.setVisibility(View.GONE);
        lotteryNums2.setVisibility(View.VISIBLE);
        lotteryNums2.removeAllViews();
        String[] numArray = null;
        numArray = numbers.split(",");
        for (int i = 0; i < numArray.length; i++) {
            TextView tvs = new TextView(context);
            tvs.setBackgroundResource(R.drawable.icon_ball_red);
            tvs.setGravity(Gravity.CENTER);
            tvs.setTextColor(ContextCompat.getColor(context, R.color.white));
            tvs.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            tvs.setText(numArray[i]);
            lotteryNums2.addView(tvs);
        }
    }
}

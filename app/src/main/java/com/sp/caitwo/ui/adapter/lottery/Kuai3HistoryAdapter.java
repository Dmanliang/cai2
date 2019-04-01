package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.ui.holder.LotteryHistoryHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vinson on 2018/11/7.
 */

public class Kuai3HistoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RoomOpenInfo.DataBean.OpenTimeBean> historyList;
    private int[] dices = new int[]{R.drawable.the_dice_1,R.drawable.the_dice_2,
            R.drawable.the_dice_3,R.drawable.the_dice_4,
            R.drawable.the_dice_5,R.drawable.the_dice_6};
    private int[] sizeForm = new int[]{R.drawable.shape_big_double_yellow,R.drawable.shape_small_single_blue};

    public Kuai3HistoryAdapter(Context context, List<RoomOpenInfo.DataBean.OpenTimeBean> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lottery_history,viewGroup,false);
        return new LotteryHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof LotteryHistoryHolder){
            ((LotteryHistoryHolder)viewHolder).tvIssueNum.setText(historyList.get(i).getGame_num());
            String[] numArray = null;
            String[] typeArray = null;
            String get_result = historyList.get(i).getGet_result();
            get_result = get_result.replaceAll("\\+", ",");
            get_result = get_result.replaceAll("\\=", ",");
            numArray = get_result.split(",");
            typeArray =  historyList.get(i).getResult_type().split(",");
            ((LotteryHistoryHolder)viewHolder).ivLotteryDicePos1.setImageResource(dices[Integer.parseInt(numArray[0]) - 1]);
            ((LotteryHistoryHolder)viewHolder).ivLotteryDicePos2.setImageResource(dices[Integer.parseInt(numArray[1]) - 1]);
            ((LotteryHistoryHolder)viewHolder).ivLotteryDicePos3.setImageResource(dices[Integer.parseInt(numArray[2]) - 1]);
            ((LotteryHistoryHolder)viewHolder).tvSumValue.setText(numArray[3]);
            if(typeArray[0].equals("大")){
                ((LotteryHistoryHolder)viewHolder).tvNumSize.setBackgroundResource(sizeForm[0]);
            }else{
                ((LotteryHistoryHolder)viewHolder).tvNumSize.setBackgroundResource(sizeForm[1]);
            }
            if(typeArray[1].equals("双")){
                ((LotteryHistoryHolder)viewHolder).tvForm.setBackgroundResource(sizeForm[0]);
            }else{
                ((LotteryHistoryHolder)viewHolder).tvForm.setBackgroundResource(sizeForm[1]);
            }
            ((LotteryHistoryHolder)viewHolder).tvNumSize.setText(typeArray[0]);
            ((LotteryHistoryHolder)viewHolder).tvForm.setText(typeArray[1]);
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}

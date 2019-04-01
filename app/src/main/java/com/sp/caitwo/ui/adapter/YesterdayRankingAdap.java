package com.sp.caitwo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.NewWinPrizeBean;
import com.sp.caitwo.ui.activity.UserInfo.PlayerInfoActivity;
import com.sp.caitwo.ui.fragment.home.YesterdayRankingFrag;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class YesterdayRankingAdap extends RVBaseAdap<YesterdayRankingAdap.Holder> {

    private List<NewWinPrizeBean.DataBean> dataList;
    private Context context;
    private YesterdayRankingFrag frag;

    public void notifyDatasChang(List<NewWinPrizeBean.DataBean> dataList) {
        this.dataList = dataList;
        super.notifyDatasChang();
    }

    public YesterdayRankingAdap(YesterdayRankingFrag frag) {
        this.context = frag.getContext();
        this.frag = frag;
    }

    @Override
    public Holder onCreateHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yesterday_ranking,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(dataList) ? 0 : dataList.size();
    }

    @Override
    public void onBindHolder(Holder holder, final int position) {
        holder.tvUserName.setText(dataList.get(position).getAccount());
        holder.tvPrizeWanfa.setText(dataList.get(position).getPoint_desc());
        holder.tvWinMoney.setText(String.format(context.getString(R.string.money_with_sign),String.valueOf(dataList.get(position).getTotal())));
        ImageOptions build = new ImageOptions.Builder()
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setFadeIn(true)
                .setUseMemCache(true)
                .setConfig(Bitmap.Config.RGB_565)
                .build();
        x.image().bind(holder.ivHeadPortrait,dataList.get(position).getHead_img(),build);
        holder.tvRanking.setText(String.valueOf(position + 4));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerInfoActivity.class);
                intent.putExtra("playerInfo",dataList.get(position));
                context.startActivity(intent);
            }
        });
    }

    class Holder extends RecyclerView.ViewHolder{

        private final ImageView ivHeadPortrait;
        private final TextView tvUserName;
        private final TextView tvPrizeWanfa;
        private final TextView tvWinMoney;
        private final TextView tvRanking;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivHeadPortrait = itemView.findViewById(R.id.iv_head_portrait);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvPrizeWanfa = itemView.findViewById(R.id.tv_prize_wanfa);
            tvWinMoney = itemView.findViewById(R.id.tv_win_money);
            tvRanking = itemView.findViewById(R.id.tv_ranking);
        }
    }

    @Override
    public void onPullDownToRefresh() {
        frag.getData();
    }
}

package com.sp.caitwo.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sp.caitwo.R;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.bean.GameIconSelecter;
import com.sp.util.ProjectUtil;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.HolderUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerInfoLikeLotteryAdap extends RVBaseAdap<PlayerInfoLikeLotteryAdap.Holder> {

    /*private int iconRes[] = {
            R.drawable.icon_k3_gray,R.drawable.icon_pc28_gray,R.drawable.icon_ssc_gray,
            R.drawable.icon_2fc_gray,R.drawable.icon_ffc_gray,R.drawable.icon_pk10_gray,
            R.drawable.icon_11x5_gray,R.drawable.icon_lhc_gray,R.drawable.icon_pl3_gray,
            R.drawable.icon_pl5_gray,R.drawable.icon_fc3d_gray,
    };*/

    private GameIconSelecter gameIconSelecter[] = {
            new GameIconSelecter("快三",R.drawable.icon_k3_gray,R.drawable.icon_k3),
            new GameIconSelecter("28",R.drawable.icon_pc28_gray,R.drawable.icon_pc28),
            new GameIconSelecter("时时彩",R.drawable.icon_ssc_gray,R.drawable.icon_ssc),
            new GameIconSelecter("1.5分彩",R.drawable.icon_2fc_gray,R.drawable.icon_2fc),
            new GameIconSelecter("分分彩",R.drawable.icon_ffc_gray,R.drawable.icon_ffc),
            new GameIconSelecter("pk10",R.drawable.icon_pk10_gray,R.drawable.icon_pk10),
            new GameIconSelecter("11选5",R.drawable.icon_11x5_gray,R.drawable.icon_11x5),
            new GameIconSelecter("六合彩",R.drawable.icon_lhc_gray,R.drawable.icon_lhc),
            new GameIconSelecter("排列3",R.drawable.icon_pl3_gray,R.drawable.icon_pl3),
            new GameIconSelecter("排列5",R.drawable.icon_pl5_gray,R.drawable.icon_pl5),
            new GameIconSelecter("福彩3D",R.drawable.icon_fc3d_gray,R.drawable.icon_fc3d)
    };

    private List<Integer> wanfaIdList;

    public PlayerInfoLikeLotteryAdap(List<Integer> wanfaIdList) {
        this.wanfaIdList = new ArrayList<>();
        for (int j = 0; j < gameIconSelecter.length; j++) {
            GameIconSelecter iconSelecter = gameIconSelecter[j];
            for (int i = 0; i < wanfaIdList.size(); i++) {
                Integer gameType = wanfaIdList.get(i);
                if (ProjectUtil.getGameForShort(gameType).contains(iconSelecter.getGameForShort())) {
                    if (!this.wanfaIdList.contains(iconSelecter.getPlayIcon()))
                        this.wanfaIdList.add(iconSelecter.getPlayIcon());
                    break;
                }
            }
        }

        for (int j = 0; j < gameIconSelecter.length; j++) {
            GameIconSelecter iconSelecter = gameIconSelecter[j];
            if (!this.wanfaIdList.contains(iconSelecter.getPlayIcon())) {
                this.wanfaIdList.add(iconSelecter.getNotPlayIcon());
            }
        }
    }

    @Override
    public Holder onCreateHolder(ViewGroup parent, int viewType) {
        return new Holder(new ImageView(parent.getContext()));
    }

    @Override
    public int getCount() {
        return wanfaIdList.size();
    }

    @Override
    public void onBindHolder(Holder holder, int position) {
        holder.ivWanfa.setImageResource(wanfaIdList.get(position));
    }

    class Holder extends RecyclerView.ViewHolder{

        private final ImageView ivWanfa;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivWanfa = (ImageView) itemView;
            ivWanfa.setAdjustViewBounds(true);
            HolderUtil.setMargin(ivWanfa,10,10,10,10);
        }
    }

}

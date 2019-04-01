package com.sp.caitwo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.sp.caitwo.R;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.LhcAllWanFaInfo;

import java.util.List;

public class BaseLotteryFragment extends Fragment {

    public OnMenuItemClickListener onMenuItemClickListener;
    public BadgeView badgeView;
    public TextView lotteryOk;
    public TextView lotteryTotally,lotteryMachineSelect;
    public int mPosition;
    public int game_type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void initBaseUI(View inflate) {
        lotteryTotally = inflate.findViewById(R.id.lottery_totally);
        lotteryMachineSelect = inflate.findViewById(R.id.lottery_machine_select);
        lotteryOk = inflate.findViewById(R.id.lottery_ok);
    }

    public void setBadgeView(TextView lotteryOk) {
        badgeView = new BadgeView(this.getContext());
        badgeView.setTargetView(lotteryOk);
        badgeView.setBackground(this.getResources().getDrawable(R.drawable.shape_circle_white));
        badgeView.setTextColor(this.getResources().getColor(R.color.light_red));
    }

    public interface OnMenuItemClickListener {

        void onAddBetNum(List<BiliListInfo.ListBeans> data, int mPosition, int unit_type, BadgeView badgeView, RecyclerView.Adapter adapter, String mGameNum, int bet_count);

        void onConfirmBet(String mGameNum, BadgeView badgeView,List<BiliListInfo.ListBeans> ballHm, List<LhcAllWanFaInfo.DataBean.ListsBean> lhcballHm);

        void onAddLhcBetNum(List<LhcAllWanFaInfo.DataBean.ListsBean> list, int mPosition, BadgeView badgeView, RecyclerView.Adapter adapter, String mGameNum, int bet_count,int point);

    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    public void setGameType(int game_type){
        this.game_type = game_type;
    }
}

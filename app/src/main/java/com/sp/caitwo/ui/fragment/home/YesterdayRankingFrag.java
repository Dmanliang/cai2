package com.sp.caitwo.ui.fragment.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.NewWinPrizeBean;
import com.sp.caitwo.ui.activity.UserInfo.PlayerInfoActivity;
import com.sp.caitwo.ui.adapter.YesterdayRankingAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class YesterdayRankingFrag extends Fragment implements View.OnClickListener {

    private View inflate;
    private ImageView ivChampion;
    private ImageView ivRunnerUp;
    private ImageView ivThirdPlace;
    private TextView tvAccount1;
    private TextView tvAccount2;
    private TextView tvAccount3;
    private TextView tvWinMoney1;
    private TextView tvWinMoney2;
    private TextView tvWinMoney3;
    private YesterdayRankingAdap mYesterdayRankingAdap;
    private List<NewWinPrizeBean.DataBean> jsonList;
    private SwipeRefreshLayout srlBetDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_yesterday_ranking, null);

        jsonList = new ArrayList<>();

        initUI();

        getData();

        return inflate;
    }

    public void getData() {
        InterfaceTask.getInstance().getYesterDayWinRank(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                NewWinPrizeBean json = (NewWinPrizeBean) obj;
                if (!BaseUtil.isEmpty(json.getData())){
                    List<NewWinPrizeBean.DataBean> data = json.getData();
                    jsonList.clear();
                    jsonList.addAll(data);
                    ImageOptions build = new ImageOptions.Builder()
                            .setCircular(true)
                            .setImageScaleType(ImageView.ScaleType.FIT_XY)
                            .setFadeIn(true)
                            .setUseMemCache(true)
                            .setConfig(Bitmap.Config.RGB_565)
                            .build();
                    x.image().bind(ivChampion,data.get(0).getHead_img(),build);
                    x.image().bind(ivRunnerUp,data.get(1).getHead_img(),build);
                    x.image().bind(ivThirdPlace,data.get(2).getHead_img(),build);
                    tvAccount1.setText(data.get(0).getAccount());
                    tvAccount2.setText(data.get(1).getAccount());
                    tvAccount3.setText(data.get(2).getAccount());
                    tvWinMoney1.setText(String.format(getString(R.string.money_with_sign),String.valueOf(data.get(0).getTotal())));
                    tvWinMoney2.setText(String.format(getString(R.string.money_with_sign),String.valueOf(data.get(1).getTotal())));
                    tvWinMoney3.setText(String.format(getString(R.string.money_with_sign),String.valueOf(data.get(2).getTotal())));

                    List<NewWinPrizeBean.DataBean> dataList = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        if (i >= 3){
                            dataList.add(data.get(i));
                        }
                    }
                    mYesterdayRankingAdap.notifyDatasChang(dataList);
                }
            }
        });
    }

    private void initUI() {
        ivChampion = inflate.findViewById(R.id.iv_champion);
        ivRunnerUp = inflate.findViewById(R.id.iv_runner_up);
        ivThirdPlace = inflate.findViewById(R.id.iv_third_place);
        tvAccount1 = inflate.findViewById(R.id.tv_account1);
        tvAccount2 = inflate.findViewById(R.id.tv_account2);
        tvAccount3 = inflate.findViewById(R.id.tv_account3);
        tvWinMoney1 = inflate.findViewById(R.id.tv_win_money1);
        tvWinMoney2 = inflate.findViewById(R.id.tv_win_money2);
        tvWinMoney3 = inflate.findViewById(R.id.tv_win_money3);
        srlBetDetail = inflate.findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        RecyclerView rvList = inflate.findViewById(R.id.rv_list);

        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mYesterdayRankingAdap = new YesterdayRankingAdap(this);
        rvList.setAdapter(mYesterdayRankingAdap);
        mYesterdayRankingAdap.registerPullDownToRefresh(srlBetDetail);
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.divide_line_horizontal_px1);
        decor.setDrawable(drawable);
        rvList.addItemDecoration(decor);

        ivChampion.setOnClickListener(this);
        ivRunnerUp.setOnClickListener(this);
        ivThirdPlace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (BaseUtil.isEmpty(jsonList)) return;
        Intent intent = new Intent(getContext(), PlayerInfoActivity.class);
        switch(v.getId()){
            case R.id.iv_champion:
                intent.putExtra("playerInfo",jsonList.get(0));
                startActivity(intent);
                break;
            case R.id.iv_runner_up:
                intent.putExtra("playerInfo",jsonList.get(1));
                startActivity(intent);
                break;
            case R.id.iv_third_place:
                intent.putExtra("playerInfo",jsonList.get(2));
                startActivity(intent);
                break;
        }
    }
}

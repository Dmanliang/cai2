package com.sp.caitwo.ui.activity.UserInfo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.bean.NewWinPrizeBean;
import com.sp.caitwo.ui.adapter.PlayerInfoLikeLotteryAdap;
import com.sp.xwjlibrary.util.StatusBarUtil;

import org.xutils.image.ImageOptions;
import org.xutils.x;

public class PlayerInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        StatusBarUtil.setStatusBarTint(this, Color.parseColor("#F65D67"));

        NewWinPrizeBean.DataBean dataBean = (NewWinPrizeBean.DataBean) getIntent().getSerializableExtra("playerInfo");

        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.player_info));
        ImageView ivHeadPortrait = findViewById(R.id.iv_head_portrait);
        TextView tvAccount = findViewById(R.id.tv_account);
        RadioButton rbSex = findViewById(R.id.rb_sex);
        TextView tvTodayPrize = findViewById(R.id.tv_today_prize);
        TextView tvHonor = findViewById(R.id.tv_honor);
        TextView tvLevel = findViewById(R.id.tv_level);
        RecyclerView rvWanfa = findViewById(R.id.rv_wanfa);

        ImageOptions build = new ImageOptions.Builder()
                .setCircular(true)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setFadeIn(true)
                .setUseMemCache(true)
                .setConfig(Bitmap.Config.RGB_565)
                .build();
        x.image().bind(ivHeadPortrait,dataBean.getHead_img(),build);
        tvAccount.setText(dataBean.getAccount());
        rbSex.setChecked(dataBean.getSex() == 1);
        tvTodayPrize.setText(String.format(getString(R.string.money_with_sign),String.valueOf(dataBean.getToday_money())));
        tvLevel.setText(String.format(getString(R.string.vip_level_num),String.valueOf(dataBean.getUser_level())));
        tvHonor.setText(LotteryCons.getLevelDj(dataBean.getUser_level()));

        findViewById(R.id.iv_top_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        PlayerInfoLikeLotteryAdap playerInfoLikeLotteryAdap = new PlayerInfoLikeLotteryAdap(dataBean.getPlay_game_list());
        rvWanfa.setAdapter(playerInfoLikeLotteryAdap);
        rvWanfa.setLayoutManager(new GridLayoutManager(this,5));
    }
}

package com.sp.caitwo.ui.activity.UserInfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.ui.info.ProfitLossInfo;

import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by Administrator on 2018/8/30.
 */

public class ProfitLossActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView profitLossMoney, withdraw, touzhu, getLottery, activityMoney, recharge, rebackMoney, getRedPaper, sendRedPaper, backRedPaper;
    private ImageView ivTopBack;
    private TextView tvTopTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_loss);
        initView();
        requestProfitLossInfo();
    }

    public void initView() {
        profitLossMoney = findViewById(R.id.profit_loss_money);
        withdraw = findViewById(R.id.withdraw);
        touzhu = findViewById(R.id.touzhu);
        getLottery = findViewById(R.id.get_lottery);
        activityMoney = findViewById(R.id.activity_money);
        recharge = findViewById(R.id.recharge);
        rebackMoney = findViewById(R.id.reback_money);
        getRedPaper = findViewById(R.id.getRedPaper);
        sendRedPaper = findViewById(R.id.sendRedPaper);
        backRedPaper = findViewById(R.id.backRedPaper);
        tvTopTitle = findViewById(R.id.tv_top_title);
        ivTopBack = findViewById(R.id.iv_top_back);
        tvTopTitle.setText("今日盈亏");
        ivTopBack.setOnClickListener(this);
    }

    public void requestProfitLossInfo() {
        InterfaceTask.getInstance().getTodayProfitLoss(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                ProfitLossInfo profitLossInfo = (ProfitLossInfo) obj;
                profitLossMoney.setText(getTwoDecimal(profitLossInfo.getData().getProfit_loss()));
                touzhu.setText(getTwoDecimal(profitLossInfo.getData().getBet_point()));
                getLottery.setText(getTwoDecimal(profitLossInfo.getData().getGet_point()));
                activityMoney.setText(getTwoDecimal(profitLossInfo.getData().getActivity_gift_point()));
                recharge.setText(getTwoDecimal(profitLossInfo.getData().getRecharge_point()));
                withdraw.setText(getTwoDecimal(profitLossInfo.getData().getWithdrawals_logs_point()));
                rebackMoney.setText(getTwoDecimal(profitLossInfo.getData().getAgent_profit_point()));
                getRedPaper.setText(getTwoDecimal(profitLossInfo.getData().getGet_red_pack_money()));
                sendRedPaper.setText(getTwoDecimal(profitLossInfo.getData().getSend_red_pack_money()));
                backRedPaper.setText(getTwoDecimal(profitLossInfo.getData().getReturn_red_pack_money()));
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                finish();
                break;
        }
    }
}

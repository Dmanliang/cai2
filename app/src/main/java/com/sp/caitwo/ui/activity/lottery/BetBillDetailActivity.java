package com.sp.caitwo.ui.activity.lottery;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.CathecticRecordInfo;
import com.sp.caitwo.ui.activity.agentcenter.SubBetBillDetailActivity;
import com.sp.caitwo.ui.adapter.lottery.BetDetailAdapter;
import com.sp.caitwo.ui.adapter.lottery.BetDetailItemDecoration;
import com.sp.xwjlibrary.dialog.SimpleDialog;
import com.sp.xwjlibrary.myinterface.OnDialogButtonListener;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

import static com.sp.caitwo.base.LotteryCons.iconRes;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.UPDATE_INFO;
import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by vinson on 2018/11/8.
 */

public class BetBillDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvBetDetail;
    private List<CathecticRecordInfo.DataBean.BetListBean> list = new ArrayList<>();
    private CathecticRecordInfo.DataBean listBean;
    private TextView topTitle;
    private ImageView wanFaLogo;
    private TextView wanFaName, issueNum, betMoney, betTime, deliveryPrize, lotteryNum, tvLotteryTicket, lotteryTicketCopy;
    private int game_type;
    private String orderNum;
    private TextView tvCancelBetOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_bill);
        getData();
        initUI();
    }

    public void getData(){
        listBean = (CathecticRecordInfo.DataBean)getIntent().getSerializableExtra("detail_list");
        game_type = getIntent().getIntExtra("game_type",0);
        orderNum = getIntent().getStringExtra("orderNum");
        list.addAll(listBean.getBet_list());
    }

    private void initUI() {
        topTitle = findViewById(R.id.tv_top_title);
        topTitle.setText(getString(R.string.bet_bill_detail));
        rvBetDetail = findViewById(R.id.rv_bet_detail);
        wanFaLogo = findViewById(R.id.iv_wanfa_logo);
        wanFaName = findViewById(R.id.tv_wanfa_name);
        issueNum = findViewById(R.id.tv_issue_num);
        betMoney = findViewById(R.id.tv_bet_money);
        betTime = findViewById(R.id.tv_bet_time);
        tvCancelBetOrder = findViewById(R.id.icon_right);
        tvCancelBetOrder.setText(getString(R.string.cancel_order));
        deliveryPrize = findViewById(R.id.tv_delivery_prize);
        lotteryNum = findViewById(R.id.tv_lottery_num);
        lotteryTicketCopy = findViewById(R.id.lottery_ticket_copy);
        tvLotteryTicket = findViewById(R.id.tv_lottery_ticket);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        tvCancelBetOrder.setOnClickListener(this);
        lotteryTicketCopy.setOnClickListener(this);
        rvBetDetail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        BetDetailItemDecoration decor = new BetDetailItemDecoration(this, LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(this,R.drawable.divide_line_horizontal_px1));
        rvBetDetail.addItemDecoration(decor);
        BetDetailAdapter betDetailAdapter = new BetDetailAdapter(this,list);
        rvBetDetail.setAdapter(betDetailAdapter);
        setData();
    }

    public void setData(){
        wanFaLogo.setImageDrawable(this.getResources().getDrawable(iconRes[game_type]));
        tvLotteryTicket.setText(orderNum);
        wanFaName.setText(listBean.getBase_wan_fa_name());
        issueNum.setText(String.format(getString(R.string.some_issue),listBean.getQihao()));
        betMoney.setText(String.format(getString(R.string.money_with_sign),getTwoDecimal(listBean.getMoney())+""));
        betTime.setText(listBean.getCreate_time());
        deliveryPrize.setText(getTwoDecimal(listBean.getBonus())+"元");
        lotteryNum.setText(TextUtils.isEmpty(listBean.getKaijiang_num())?"开奖中":listBean.getKaijiang_num());
        if (list.get(0).getStatus() != 0){
            tvCancelBetOrder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_top_back:
                setResult(10001);
                finish();
                break;
            case R.id.icon_right:
                SimpleDialog simpleDialog = new SimpleDialog(this)
                        .setTitle("温馨提示")
                        .setContent("是否撤销订单\n" + orderNum)
                        .setStyle(SimpleDialog.STYLE_RIGHT)
                        .setText("取消","确认");
                simpleDialog.setOnDialogButtonListener(new OnDialogButtonListener() {
                    @Override
                    public void onLeftBtn() {

                    }

                    @Override
                    public void onRightBtn() {
                        UserCancelBetOrder();                    }
                });
                simpleDialog.show();
                break;
            case R.id.lottery_ticket_copy:
                CopyTicket(orderNum);
                break;
        }
    }

    public void UserCancelBetOrder(){
        InterfaceTask.getInstance().userCancelBetOrder(this, String.valueOf(game_type), orderNum, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                ToastUtil.Toast(BetBillDetailActivity.this, "撤销成功");
                BetBillDetailActivity.this.setResult(10001);
                BetBillDetailActivity.this.finish();
            }
        });
    }

    public void CopyTicket(String content){
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(content);
        ToastUtil.Toast(this,"单号复制成功!");
    }
}

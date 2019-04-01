package com.sp.caitwo.ui.activity.agentcenter;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.BetRecordBean;
import com.sp.caitwo.bean.SubordinateBetDetailBean;
import com.sp.caitwo.bean.TraceNumListBean;
import com.sp.caitwo.ui.activity.lottery.BetBillDetailActivity;
import com.sp.caitwo.ui.adapter.agentcenter.SubBetBillDetailAdap;
import com.sp.caitwo.ui.adapter.lottery.BetDetailItemDecoration;
import com.sp.util.ProjectUtil;
import com.sp.xwjlibrary.dialog.SimpleDialog;
import com.sp.xwjlibrary.myinterface.OnDialogButtonListener;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vinson on 2018/11/8.
 */

public class SubBetBillDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvBetDetail;
    private TextView tvTitle;
    private ImageView ivWanFaLogo;
    private TextView tvWanFaName, tvIssueNum, tvBetMoney, tvBetTime, tvDeliveryPrize, tvBetOrder,tvLotteryNum;
    private SubordinateBetDetailBean.DataBean.ListBean betDetail;
    private List<SubordinateBetDetailBean.DataBean.ListBean.BetListBean> betList;
    private BetRecordBean.DataBean betRecordDetail;
    private List<BetRecordBean.DataBean.BetListBean> betRecordList;
    private TraceNumListBean.DataBeanX.DataBean traceNumDetail;
    private TraceNumListBean.DataBeanX.DataBean.IssueListBean traceNumRecordDetail;
    private List<TraceNumListBean.DataBeanX.DataBean.IssueListBean.ChasingLogBetContentListBeanX> traceNumList;
    private TextView tvCancelBetOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_bill);

        Serializable serializable = getIntent().getSerializableExtra("betDetail");
        if (serializable instanceof SubordinateBetDetailBean.DataBean.ListBean) {
            this.betDetail = (SubordinateBetDetailBean.DataBean.ListBean) serializable;
            betList = this.betDetail.getBet_list();
        }else if (serializable instanceof BetRecordBean.DataBean){
            betRecordDetail = (BetRecordBean.DataBean) serializable;
            betRecordList = betRecordDetail.getBet_list();
        }else if (serializable instanceof TraceNumListBean.DataBeanX.DataBean){
            int pos = getIntent().getIntExtra("pos", 0);
            traceNumDetail = (TraceNumListBean.DataBeanX.DataBean) serializable;
            traceNumRecordDetail = traceNumDetail.getIssue_list().get(pos);
            traceNumList = traceNumDetail.getIssue_list().get(pos).getChasing_log_bet_content_list();
        }
        initUI();
    }

    private void initUI() {
        tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.bet_bill_detail));
        rvBetDetail = findViewById(R.id.rv_bet_detail);
        ivWanFaLogo = findViewById(R.id.iv_wanfa_logo);
        tvWanFaName = findViewById(R.id.tv_wanfa_name);
        tvIssueNum = findViewById(R.id.tv_issue_num);
        tvBetMoney = findViewById(R.id.tv_bet_money);
        tvBetTime = findViewById(R.id.tv_bet_time);
        tvCancelBetOrder = findViewById(R.id.icon_right);
        tvCancelBetOrder.setText(getString(R.string.cancel_order));
        tvDeliveryPrize = findViewById(R.id.tv_delivery_prize);
        tvLotteryNum = findViewById(R.id.tv_lottery_num);
        tvBetOrder = findViewById(R.id.tv_lottery_ticket);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.lottery_ticket_copy).setOnClickListener(this);
        tvCancelBetOrder.setOnClickListener(this);
        rvBetDetail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        BetDetailItemDecoration decor = new BetDetailItemDecoration(this, LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(this,R.drawable.divide_line_horizontal_px1));
        rvBetDetail.addItemDecoration(decor);
        SubBetBillDetailAdap betDetailAdapter = new SubBetBillDetailAdap(this,betList,betRecordList,traceNumList);
        rvBetDetail.setAdapter(betDetailAdapter);
        setData();
    }

    private void setData(){
        if (!BaseUtil.isEmpty(betDetail)) {
            ivWanFaLogo.setImageResource(ProjectUtil.getGameIcon(betDetail.getBase_wan_fa_name()));
            tvWanFaName.setText(betDetail.getBase_wan_fa_name());
            tvIssueNum.setText(String.format(getString(R.string.some_issue), betDetail.getQihao()));
            tvBetMoney.setText(String.format(getString(R.string.money_with_sign), betDetail.getMoney()));
            tvBetTime.setText(betDetail.getCreate_time());
            tvDeliveryPrize.setText(betDetail.getBonus() + "元");
            tvLotteryNum.setText(BaseUtil.isEmpty(betDetail.getKaijiang_num()) ? "开奖中" : betDetail.getKaijiang_num());
            tvBetOrder.setText(betDetail.getOrder_no());
            if (!BaseUtil.equals(betList.get(0).getStatus(),"0")){
                tvCancelBetOrder.setVisibility(View.GONE);
            }
        }else if (!BaseUtil.isEmpty(betRecordDetail)){
            ivWanFaLogo.setImageResource(ProjectUtil.getGameIcon(betRecordDetail.getBase_wan_fa_name()));
            tvWanFaName.setText(betRecordDetail.getBase_wan_fa_name());
            tvIssueNum.setText(String.format(getString(R.string.some_issue), betRecordDetail.getQihao()));
            tvBetMoney.setText(String.format(getString(R.string.money_with_sign), betRecordDetail.getMoney()));
            tvBetTime.setText(betRecordDetail.getCreate_time());
            tvDeliveryPrize.setText(betRecordDetail.getBonus() + "元");
            tvLotteryNum.setText(BaseUtil.isEmpty(betRecordDetail.getKaijiang_num()) ? "开奖中" : betRecordDetail.getKaijiang_num());
            tvBetOrder.setText(betRecordDetail.getOrder_no());
            if (!BaseUtil.equals(betRecordList.get(0).getStatus(),"0")){
                tvCancelBetOrder.setVisibility(View.GONE);
            }
        }else if (!BaseUtil.isEmpty(traceNumDetail)){
            ivWanFaLogo.setImageResource(ProjectUtil.getGameIcon(traceNumDetail.getLottery_name()));
            tvWanFaName.setText(traceNumDetail.getLottery_name());
            tvIssueNum.setText(String.format(getString(R.string.some_issue), traceNumRecordDetail.getIssue()));
            tvBetMoney.setText(String.format(getString(R.string.money_with_sign), String.valueOf(traceNumRecordDetail.getBet_money())));
            tvBetTime.setText(traceNumDetail.getCreate_time());
            tvDeliveryPrize.setText(traceNumRecordDetail.getWin_money() + "元");
            tvLotteryNum.setText(BaseUtil.isEmpty(traceNumRecordDetail.getOpen_number()) ? "开奖中" : traceNumRecordDetail.getOpen_number());
            tvBetOrder.setText(traceNumRecordDetail.getOrder_no());
            tvCancelBetOrder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.lottery_ticket_copy:
                ProjectUtil.copy(this,tvBetOrder.getText().toString());
                break;
            case R.id.icon_right:
                String gameType = "";
                String orderNo = "";
                if (!BaseUtil.isEmpty(betDetail)) {
                    gameType = betDetail.getGame_type();
                    orderNo = betDetail.getOrder_no();
                }else if (!BaseUtil.isEmpty(betRecordDetail)){
                    gameType = betRecordDetail.getGame_type();
                    orderNo = betRecordDetail.getOrder_no();
                }
                SimpleDialog simpleDialog = new SimpleDialog(this)
                        .setTitle("温馨提示")
                        .setContent("是否撤销订单\n" + orderNo)
                        .setStyle(SimpleDialog.STYLE_RIGHT)
                        .setText("取消","确认");
                final String gameTypeFinal = gameType;
                final String orderNoFinal = orderNo;
                simpleDialog.setOnDialogButtonListener(new OnDialogButtonListener() {
                    @Override
                    public void onLeftBtn() {

                    }

                    @Override
                    public void onRightBtn() {
                        InterfaceTask.getInstance().userCancelBetOrder(SubBetBillDetailActivity.this, gameTypeFinal, orderNoFinal, new InterfaceTask.OnInterfaceTask() {
                            @Override
                            public void onError() {
                                super.onError();
                            }

                            @Override
                            public void onSuccess(Object obj) {
                                super.onSuccess(obj);
                                ToastUtil.Toast(SubBetBillDetailActivity.this, "撤销成功");
                                SubBetBillDetailActivity.this.setResult(10001);
                                SubBetBillDetailActivity.this.finish();
                            }
                        });
                    }
                });
                simpleDialog.show();
                break;
        }
    }
}

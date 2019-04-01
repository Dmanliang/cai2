package com.sp.caitwo.ui.activity.UserInfo;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.TraceNumListBean;
import com.sp.caitwo.ui.adapter.TraceNumDetailRecordListAdapter;
import com.sp.util.ProjectUtil;

import java.util.ArrayList;

public class TraceNumDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TraceNumListBean.DataBeanX.DataBean traceNumDetail;
    private TraceNumDetailRecordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_num_detail);

        traceNumDetail = (TraceNumListBean.DataBeanX.DataBean) getIntent().getSerializableExtra("traceNumDetail");

        initUI();

    }

    private void initUI() {
        ImageView ivWanfaLogo = findViewById(R.id.iv_wanfa_logo);
        TextView tvWanfaName = findViewById(R.id.tv_wanfa_name);
        TextView tvIssueNum = findViewById(R.id.tv_issue_num);
        TextView tvBetMoney = findViewById(R.id.tv_bet_money);
        TextView tvBetTime = findViewById(R.id.tv_bet_time);
        TextView tvTraceNumOrder = findViewById(R.id.tv_trace_num_order);
        TextView tvTraceNumCase = findViewById(R.id.tv_trace_num_case);
        TextView tvTraceNumProgress = findViewById(R.id.tv_trace_num_progress);
        TextView tvTraceNumMoney = findViewById(R.id.tv_trace_num_money);
        TextView tvDeliveryPrize = findViewById(R.id.tv_delivery_prize);
        TextView tvPrizeImmediateStop = findViewById(R.id.tv_prize_immediate_stop);
        TextView tvTitle = findViewById(R.id.tv_top_title);
        RecyclerView rvBetDetail = findViewById(R.id.rv_bet_detail);
        final NestedScrollView sv = findViewById(R.id.nsv_trace_num_detail);

        findViewById(R.id.tv_copy_order_num).setOnClickListener(this);
        findViewById(R.id.iv_top_back).setOnClickListener(this);

        tvTitle.setText(getString(R.string.trace_num_detail));
        tvWanfaName.setText(traceNumDetail.getLottery_name());
        tvIssueNum.setText(String.format(getString(R.string.some_issue_trace_num),traceNumDetail.getStart_issue()));
        tvBetMoney.setText(String.format(getString(R.string.money_with_sign), String.valueOf(traceNumDetail.getAlready_bet_money())));
        tvBetTime.setText(traceNumDetail.getCreate_time());
        tvTraceNumOrder.setText(traceNumDetail.getOrder_no());
        ArrayList<String> traceNumCases = new ArrayList<>();
        for (TraceNumListBean.DataBeanX.DataBean.ChasingLogBetContentListBean bean : traceNumDetail.getChasing_log_bet_content_list()) {
            String traceNumCase = String.format("%s_%s",bean.getWanfa_name(), bean.getBet_content());
            if (!traceNumCases.contains(traceNumCase)){
                traceNumCases.add(traceNumCase);
            }
        }
        tvTraceNumCase.setText(traceNumCases.toString().replace("[","").replace("]",""));
        tvTraceNumProgress.setText(String.format(getString(R.string.trace_num_progress),traceNumDetail.getAlready_pursued(),traceNumDetail.getTotal_issue()));
        tvTraceNumMoney.setText(String.format(getString(R.string.money_with_sign), String.valueOf(traceNumDetail.getTotal_bet_money())));
        tvDeliveryPrize.setText(String.format(getString(R.string.money_with_sign), String.valueOf(traceNumDetail.getTotal_win_money())));
        tvPrizeImmediateStop.setText(traceNumDetail.getZjjt() == 1 ? getString(R.string.yes) : getString(R.string.no));
        ivWanfaLogo.setImageResource(ProjectUtil.getGameIcon(traceNumDetail.getLottery_name()));
        mAdapter = new TraceNumDetailRecordListAdapter(this,traceNumDetail);
        rvBetDetail.setLayoutManager(new LinearLayoutManager(this));
        rvBetDetail.setAdapter(mAdapter);
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.tv_copy_order_num:
                ProjectUtil.copy(this,traceNumDetail.getOrder_no());
                break;
        }
    }
}

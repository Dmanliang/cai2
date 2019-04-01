package com.sp.caitwo.ui.activity.agentcenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.PersonalAgentInfoBean;
import com.sp.caitwo.ui.activity.sysm.WebLoadActivity;
import com.sp.xwjlibrary.widget.DoubleTextView;

public class AgentCenterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMySubordinatePerson;
    private TextView tvAddUpReturnCommission;
    private DoubleTextView dtvYesterdayReturnCommission;
    private DoubleTextView dtvTodayReturnCommission;
    private TextView tvTitle;
    private String registerUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_center);

        initUI();

        getData();
    }

    private void getData() {
        InterfaceTask.getInstance().getPersonalAgentInfo(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                PersonalAgentInfoBean info = (PersonalAgentInfoBean) obj;
                tvMySubordinatePerson.setText(info.getData().getSubAgentPeople());
                tvAddUpReturnCommission.setText(String.format(getString(R.string.add_up_return_commission),info.getData().getTotalShareProfit()));
                dtvYesterdayReturnCommission.setTwoText(info.getData().getYesterdayShareProfit());
                dtvTodayReturnCommission.setTwoText(info.getData().getTodayShareProfit());
                registerUrl = info.getData().getRegisterUrl();
            }
        });
    }

    private void initUI() {
        tvTitle = findViewById(R.id.tv_top_title);
        tvMySubordinatePerson = findViewById(R.id.tv_my_subordinate_person);
        tvAddUpReturnCommission = findViewById(R.id.tv_add_up_return_commission);
        dtvYesterdayReturnCommission = findViewById(R.id.dtv_yesterday_return_commission);
        dtvTodayReturnCommission = findViewById(R.id.dtv_today_return_commission);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.tv_return_commission_detail).setOnClickListener(this);
        findViewById(R.id.lly_agent_introduction).setOnClickListener(this);
        findViewById(R.id.lly_subordinate_opener).setOnClickListener(this);
        findViewById(R.id.lly_agent_report_forms).setOnClickListener(this);
        findViewById(R.id.lly_subordinate_manage).setOnClickListener(this);
        findViewById(R.id.lly_subordinate_bet_detail).setOnClickListener(this);
        findViewById(R.id.lly_subordinate_business_detail).setOnClickListener(this);

        tvTitle.setText(getString(R.string.agent_center));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.tv_return_commission_detail:
                startActivity(new Intent(this,ReturnCommissionDetailActivity.class));
                break;
            case R.id.lly_agent_introduction:
                Intent intent = new Intent(this, AgentExplainActivity.class);
                startActivity(intent);
                break;
            case R.id.lly_subordinate_opener:
                Intent intent1 = new Intent(this, SubordinateOpenerActivity.class);
                intent1.putExtra("registerUrl",registerUrl);
                startActivity(intent1);
                break;
            case R.id.lly_agent_report_forms:
                startActivity(new Intent(this,AgentReportFormsActivity.class));
                break;
            case R.id.lly_subordinate_manage:
                startActivity(new Intent(this,SubordinateManageActivity.class));
                break;
            case R.id.lly_subordinate_bet_detail:
                startActivity(new Intent(this,SubordinateBetDetailActivity.class));
                break;
            case R.id.lly_subordinate_business_detail:
                startActivity(new Intent(this,SubordinateBusinessDetailActivity.class));
                break;
        }
    }
}

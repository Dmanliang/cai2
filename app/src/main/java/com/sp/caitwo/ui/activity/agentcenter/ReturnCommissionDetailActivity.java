package com.sp.caitwo.ui.activity.agentcenter;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.ReturnCommissionDetailBean;
import com.sp.caitwo.ui.adapter.agentcenter.ReturnCommissionDetailAdapter;
import com.sp.xwjlibrary.dialog.DateDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TimeUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class ReturnCommissionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvIncomeDetail;
    private TextView tvTimeStart;
    private TextView tvTimeEnd;
    private TextView tvDateSectionIncome;
    private DateDialog mDateDialog;
    private View dateView;
    private String startTime;
    private String endTime;
    private int page = 1;
    private ArrayList<ReturnCommissionDetailBean.DataBean> dataList;
    private ReturnCommissionDetailAdapter adapter;
    private double dateSectionIncome = 0.00;
    private SwipeRefreshLayout srlBetDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_commission_detail);

        initUI();

        setAdapter();

        dataList = new ArrayList<>();
        endTime = TimeUtil.getCurrTime("yyyy-MM-dd");
        startTime = TimeUtil.setDate(endTime,"yyyy-MM-dd","yyyy-MM-dd",0,0,0,true);
        tvTimeStart.setText(startTime);
        tvTimeEnd.setText(endTime);

        initDialog();

        setData();
    }

    private void setData() {
        InterfaceTask.getInstance().getReturnCommissionDetail(page, startTime, endTime, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                ReturnCommissionDetailBean json = (ReturnCommissionDetailBean) obj;
                if (page == 1){
                    dateSectionIncome = 0.00;
                    dataList.clear();
                }
                if (srlBetDetail.isRefreshing()){
                    srlBetDetail.setRefreshing(false);
                }
                if (!BaseUtil.isEmpty(json.getData())){
                    for (ReturnCommissionDetailBean.DataBean dataBean : json.getData()) {
                        dateSectionIncome += dataBean.getShare_profit_money();
                    }
                    dataList.addAll(json.getData());
                }else {
                    //数据全部加载完毕
                    ToastUtil.Toast(getBaseContext(),"数据全部加载完毕");
                }
                adapter.notifyDatasChang(dataList);
                tvDateSectionIncome.setText(String.valueOf(dateSectionIncome));
            }
        });
    }

    public void pageLoad(){
        page ++;
        setData();
    }

    private void initDialog() {
        int year = TimeUtil.extractDate(startTime, "yyyy-MM-dd", Calendar.YEAR);
        int month = TimeUtil.extractDate(startTime, "yyyy-MM-dd", Calendar.MONTH);
        int day = TimeUtil.extractDate(startTime, "yyyy-MM-dd", Calendar.DAY_OF_MONTH);
        mDateDialog = new DateDialog(this, "yyyy-MM-dd",year,month,day, new DateDialog.OnDateSetListener() {
            @Override
            public void onDateSet(String date) {
                switch(dateView.getId()){
                    case R.id.lly_time_start:
                        if (TimeUtil.compare2Time("yyyy-MM-dd",date,endTime) > 0){
                            ToastUtil.Toast(getBaseContext(),"开始时间不能大于结束时间");
                            return;
                        }
                        startTime = date;
                        tvTimeStart.setText(date);
                        break;
                    case R.id.lly_time_end:
                        if (TimeUtil.compare2Time("yyyy-MM-dd",date,startTime) < 0){
                            ToastUtil.Toast(getBaseContext(),"结束时间不能小于开始时间");
                            return;
                        }
                        endTime = date;
                        tvTimeEnd.setText(date);
                        break;
                }
                page = 1;
                setData();
            }
        });
        mDateDialog.setMinAndMax(0,System.currentTimeMillis());
    }

    private void setAdapter() {
        adapter = new ReturnCommissionDetailAdapter(this);
        rvIncomeDetail.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(this,R.drawable.divide_line_horizontal_px1));
        rvIncomeDetail.addItemDecoration(decor);
        rvIncomeDetail.setAdapter(adapter);
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        rvIncomeDetail = findViewById(R.id.rv_income_detail);
        tvTimeStart = findViewById(R.id.tv_time_start);
        tvTimeEnd = findViewById(R.id.tv_time_end);
        tvDateSectionIncome = findViewById(R.id.tv_date_section_income);
        findViewById(R.id.lly_time_start).setOnClickListener(this);
        findViewById(R.id.lly_time_end).setOnClickListener(this);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        srlBetDetail = findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        srlBetDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                setData();
            }
        });

        tvTitle.setText(getString(R.string.return_commission_detail));
    }

    @Override
    public void onClick(View v) {
        dateView = v;
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.lly_time_start:
                mDateDialog.setTitle(getString(R.string.please_sel_start_time));
                mDateDialog.show();
                break;
            case R.id.lly_time_end:
                mDateDialog.setTitle(getString(R.string.please_sel_end_time));
                mDateDialog.show();
                break;
        }
    }
}

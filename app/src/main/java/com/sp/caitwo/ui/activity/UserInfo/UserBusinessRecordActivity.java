package com.sp.caitwo.ui.activity.UserInfo;

import android.animation.ObjectAnimator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.SubAccountDetailBean;
import com.sp.caitwo.bean.SubRechargeRecordDetailBean;
import com.sp.caitwo.bean.SubWithdrawRecordBean;
import com.sp.caitwo.ui.adapter.agentcenter.SubordinateBusinessDetailAdapter;
import com.sp.xwjlibrary.dialog.LoadDialog;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.util.TimeUtil;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;
import com.sp.xwjlibrary.widget.IconView;

import java.util.ArrayList;

public class UserBusinessRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private View indicator;
    private RadioGroup rgFilter;
    private SubordinateBusinessDetailAdapter mAdapter;
    private String currentDate;
    private String accountStartDate;
    private String withdrawStartDate;
    private String rechargeStartDate;
    private String accountEndDate;
    private String withdrawEndDate;
    private String rechargeEndDate;
    private String accountStateType = "0";
    private String withdrawStateType = "0";
    private String rechargeStateType = "0";
    private String type = "1";
    private int page = 1;
    private ArrayList<SubAccountDetailBean.DataBean.ListBean> accountDetailList;
    private ArrayList<SubWithdrawRecordBean.DataBean.ListBean> withdrawRecordList;
    private ArrayList<SubRechargeRecordDetailBean.DataBean.ListBean> rechargeRecordList;
    private PopupDialog accountDetailDialog;
    private PopupDialog withdrawDialog;
    private PopupDialog rechargeDialog;
    private RadioGroup rgAccountDetailTime;
    private RadioGroup rgWithdrawTime;
    private RadioGroup rgWithdrawState;
    private RadioGroup rgRechargeTime;
    private RadioGroup rgRechargeState;
    private RadioGroup rgSort11;
    private RadioGroup rgSort12;
    private RadioGroup rgSort13;
    private RadioGroup rgSort14;
    private SwipeRefreshLayout srlBetDetail;
    public LoadDialog mLoadDialog;

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_record);

        accountDetailList = new ArrayList<>();
        withdrawRecordList = new ArrayList<>();
        rechargeRecordList = new ArrayList<>();

        initUI();

        accountStartDate = accountEndDate = withdrawStartDate = withdrawEndDate =
                rechargeStartDate = rechargeEndDate = currentDate = TimeUtil.getCurrTime("yyyy-MM-dd");

        setTitleFilter();

        setData();

        initDialog();

    }

    private void initDialog() {
        mLoadDialog = new LoadDialog(this,"Loading", Constants.drawableResids);
        mLoadDialog.show();

        View withdrawRecordView = View.inflate(this, R.layout.subordinate_business_detail_filter2, null);
        rgWithdrawTime = withdrawRecordView.findViewById(R.id.rg_time);
        rgWithdrawState = withdrawRecordView.findViewById(R.id.rg_withdraw_state);
        rgWithdrawTime.setOnCheckedChangeListener(new RadioGroupListener(withdrawRecordView));
        rgWithdrawState.setOnCheckedChangeListener(new RadioGroupListener(withdrawRecordView));
        withdrawRecordView.findViewById(R.id.btn_sure).setOnClickListener(this);
        withdrawDialog = new PopupDialog(this, withdrawRecordView);

        View rechargeRecordView = View.inflate(this, R.layout.subordinate_business_detail_filter3, null);
        rgRechargeTime = rechargeRecordView.findViewById(R.id.rg_time);
        rgRechargeState = rechargeRecordView.findViewById(R.id.rg_recharge_state);
        rgRechargeTime.setOnCheckedChangeListener(new RadioGroupListener(rechargeRecordView));
        rgRechargeState.setOnCheckedChangeListener(new RadioGroupListener(rechargeRecordView));
        rechargeRecordView.findViewById(R.id.btn_sure).setOnClickListener(this);
        rechargeDialog = new PopupDialog(this, rechargeRecordView);

        View accountDetailView = View.inflate(this, R.layout.subordinate_business_detail_filter, null);
        rgAccountDetailTime = accountDetailView.findViewById(R.id.rg_time);
        rgSort11 = accountDetailView.findViewById(R.id.rg_sort11);
        rgSort12 = accountDetailView.findViewById(R.id.rg_sort12);
        rgSort13 = accountDetailView.findViewById(R.id.rg_sort13);
        rgSort14 = accountDetailView.findViewById(R.id.rg_sort14);
        rgAccountDetailTime.setOnCheckedChangeListener(new RadioGroupListener(accountDetailView));
        rgSort11.setOnCheckedChangeListener(new RadioGroupListener(accountDetailView));
        rgSort12.setOnCheckedChangeListener(new RadioGroupListener(accountDetailView));
        rgSort13.setOnCheckedChangeListener(new RadioGroupListener(accountDetailView));
        rgSort14.setOnCheckedChangeListener(new RadioGroupListener(accountDetailView));
        accountDetailView.findViewById(R.id.btn_sure).setOnClickListener(this);
        accountDetailDialog = new PopupDialog(this, accountDetailView);
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener{
        private View menu2View;

        public RadioGroupListener(View menu2View) {
            this.menu2View = menu2View;
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group != null && checkedId != -1) {
                if (rgAccountDetailTime == group) {
                    setSubMenu(checkedId);
                } else if (rgSort11 == group) {
                    rgSort12.clearCheck();
                    rgSort13.clearCheck();
                    rgSort14.clearCheck();
                    accountStateType = (String) menu2View.findViewById(checkedId).getTag();
                } else if (rgSort12 == group) {
                    rgSort11.clearCheck();
                    rgSort13.clearCheck();
                    rgSort14.clearCheck();
                    accountStateType = (String) menu2View.findViewById(checkedId).getTag();
                } else if (rgSort13 == group) {
                    rgSort11.clearCheck();
                    rgSort12.clearCheck();
                    rgSort14.clearCheck();
                    accountStateType = (String) menu2View.findViewById(checkedId).getTag();
                } else if (rgSort14 == group) {
                    rgSort11.clearCheck();
                    rgSort12.clearCheck();
                    rgSort13.clearCheck();
                    accountStateType = (String) menu2View.findViewById(checkedId).getTag();
                }else if (rgWithdrawTime == group){
                    setSubMenu(checkedId);
                }else if (rgWithdrawState == group){
                    withdrawStateType = (String) rgWithdrawState.findViewById(checkedId).getTag();
                }else if (rgRechargeTime == group){
                    setSubMenu(checkedId);
                }else if (rgRechargeState == group){
                    rechargeStateType = (String) rgRechargeState.findViewById(checkedId).getTag();
                }
                group.check(checkedId);
            }
        }

        private void setSubMenu(int checkedId) {
            switch(checkedId){
                case R.id.rb_today:
                    if (BaseUtil.equals(type,"1")){
                        accountStartDate = accountEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    }else if (BaseUtil.equals(type,"3")){
                        withdrawStartDate = withdrawEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    }else if (BaseUtil.equals(type,"2")){
                        rechargeStartDate = rechargeEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    }
                    break;
                case R.id.rb_yesterday:
                    if (BaseUtil.equals(type,"1")){
                        accountStartDate = accountEndDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-1,false);
                    }else if (BaseUtil.equals(type,"3")){
                        withdrawStartDate = withdrawEndDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-1,false);
                    }else if (BaseUtil.equals(type,"2")){
                        rechargeStartDate = rechargeEndDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-1,false);
                    }
                    break;
                case R.id.rb_seven_days:
                    if (BaseUtil.equals(type,"1")){
                        accountEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                        accountStartDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-7,false);
                    }else if (BaseUtil.equals(type,"3")){
                        withdrawEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                        withdrawStartDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-7,false);
                    }else if (BaseUtil.equals(type,"2")){
                        rechargeEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                        rechargeStartDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-7,false);
                    }
                    break;
            }
        }
    }

    public void pageLoad(){
        page ++;
        mLoadDialog.show();
        setData();
    }

    public void setData() {
        String stateType = "0",subStartDate = currentDate,subEndDate = currentDate;
        if (BaseUtil.equals(type,"1")){
            stateType = accountStateType;
            subStartDate = accountStartDate;
            subEndDate = accountEndDate;
        }else if (BaseUtil.equals(type,"3")){
            stateType = withdrawStateType;
            subStartDate = withdrawStartDate;
            subEndDate = withdrawEndDate;
        }else if (BaseUtil.equals(type,"2")){
            stateType = rechargeStateType;
            subStartDate = rechargeStartDate;
            subEndDate = rechargeEndDate;
        }
        InterfaceTask.getInstance().getUserBusinessRecord(page,type, stateType, subStartDate, subEndDate,
                new InterfaceTask.OnInterfaceTask() {
                    @Override
                    public void onSuccess(Object obj) {
                        if (srlBetDetail.isRefreshing()){
                            srlBetDetail.setRefreshing(false);
                        }
                        if (mLoadDialog != null && mLoadDialog.isShowing()){
                            mLoadDialog.cancel();
                        }
                        if (BaseUtil.equals(type,"1")){
                            SubAccountDetailBean json = (SubAccountDetailBean) obj;
                            if (page == 1){
                                accountDetailList.clear();
                            }
                            if (BaseUtil.isEmpty(json.getData()) || BaseUtil.isEmpty(json.getData().getList())){
                                ToastUtil.Toast(getBaseContext(),getString(R.string.all_data_load_finish));
                            }else {
                                accountDetailList.addAll(json.getData().getList());
                            }
                            mAdapter.notifyDatasChang1(accountDetailList);
                        }else if (BaseUtil.equals(type,"3")){
                            SubWithdrawRecordBean json = (SubWithdrawRecordBean) obj;
                            if (page == 1){
                                withdrawRecordList.clear();
                            }
                            if (BaseUtil.isEmpty(json.getData()) || BaseUtil.isEmpty(json.getData().getList())){
                                ToastUtil.Toast(getBaseContext(),getString(R.string.all_data_load_finish));
                            }else {
                                withdrawRecordList.addAll(json.getData().getList());
                            }
                            mAdapter.notifyDatasChang2(withdrawRecordList);
                        }else if (BaseUtil.equals(type,"2")){
                            SubRechargeRecordDetailBean json = (SubRechargeRecordDetailBean) obj;
                            if (page == 1){
                                rechargeRecordList.clear();
                            }
                            if (BaseUtil.isEmpty(json.getData()) || BaseUtil.isEmpty(json.getData().getList())){
                                ToastUtil.Toast(getBaseContext(),getString(R.string.all_data_load_finish));
                            }else {
                                rechargeRecordList.addAll(json.getData().getList());
                            }
                            mAdapter.notifyDatasChang3(rechargeRecordList);
                        }

                    }
                });
    }

    private void initUI() {
        indicator = findViewById(R.id.indicator);
        rgFilter = findViewById(R.id.rg_filter);
        TextView tvTitle = findViewById(R.id.tv_top_title);
        IconView tvFilterMenu = findViewById(R.id.icon_right);
        srlBetDetail = findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        RecyclerView rvList = findViewById(R.id.rv_list);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        tvTitle.setText(getString(R.string.user_business_record));
        tvFilterMenu.setText(getString(R.string.filter));
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        tvFilterMenu.setOnClickListener(this);

        mAdapter = new SubordinateBusinessDetailAdapter(this);
        mAdapter.registerPageLoad(rvList);
        rvList.setAdapter(mAdapter);
        mAdapter.registerPullDownToRefresh(srlBetDetail);
    }

    private void setTitleFilter() {
        rgFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setIndicatorLen(checkedId);
                String[] types = {"1","3","2"};
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId) {
                        type = types[i];
                        break;
                    }
                }
                page = 1;
                mLoadDialog.show();
                setData();
            }
        });
        rgFilter.post(new Runnable() {
            @Override
            public void run() {
                setIndicatorLen(rgFilter.getCheckedRadioButtonId());
            }
        });
    }

    private void setIndicatorLen(int checkedId) {
        if (checkedId != -1) {
            RadioButton checkView = findViewById(checkedId);
            int textWidth = (int) (TextViewUtil.getTextWidth(checkView) + 0.5);
            int indicatorShort = checkView.getWidth() - textWidth;
            ViewGroup.LayoutParams lp = indicator.getLayoutParams();
            lp.width = checkView.getWidth() - indicatorShort;
            indicator.setLayoutParams(lp);
            ObjectAnimator.ofFloat(indicator,"translationX",((Integer.valueOf(checkView.getTag().toString())) * checkView.getWidth()) + indicatorShort / 2).start();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.icon_right:
                if (BaseUtil.equals(type,"1")){
                    accountDetailDialog.show(v);
                }else if (BaseUtil.equals(type,"3")){
                    withdrawDialog.show(v);
                }else if (BaseUtil.equals(type,"2")){
                    rechargeDialog.show(v);
                }
                break;
            case R.id.btn_sure:
                page = 1;
                mLoadDialog.show();
                setData();
                if (accountDetailDialog.isShowing())
                    accountDetailDialog.dismiss();
                if (withdrawDialog.isShowing())
                    withdrawDialog.dismiss();
                if (rechargeDialog.isShowing())
                    rechargeDialog.dismiss();
                break;
        }
    }
}

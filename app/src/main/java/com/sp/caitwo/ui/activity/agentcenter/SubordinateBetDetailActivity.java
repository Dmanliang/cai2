package com.sp.caitwo.ui.activity.agentcenter;

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

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.bean.SubordinateBetDetailBean;
import com.sp.caitwo.ui.adapter.agentcenter.SubordinateBetDetailAdapter;
import com.sp.xwjlibrary.dialog.LoadDialog;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.util.TimeUtil;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;
import com.sp.xwjlibrary.widget.IconView;

import java.util.ArrayList;
import java.util.List;

public class SubordinateBetDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private View indicator;
    private RadioGroup rgFilter;
    private SubordinateBetDetailAdapter mAdapter;
    private String currentDate;
    private String subStartDate;
    private String subEndDate;
    private String stateType = "0";
    private String gameType = "0";
    private String account = "";
    private int page = 1;
    private ArrayList<SubordinateBetDetailBean.DataBean.ListBean> dataList;
    private IconEditClearView iecvSearchKey;
    private PopupDialog menu2ViewDialog;
    private RadioGroup rgSubTime;
    private RadioGroup[] rgItemChild;
    private SwipeRefreshLayout srlBetDetail;
    private LoadDialog mLoadDialog;

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subordinate_bet_detail);
        mLoadDialog = new LoadDialog(this,"Loading", Constants.drawableResids);
        dataList = new ArrayList<>();

        initUI();

        subStartDate = subEndDate = currentDate = TimeUtil.getCurrTime("yyyy-MM-dd");

        setTitleFilter();

        setData();

        initDialog();
    }

    private void initDialog() {
        View menu2View = View.inflate(this, R.layout.subordinate_bet_detail_filter, null);
        rgSubTime = menu2View.findViewById(R.id.rg_time);
        RadioGroup rgWanfaFilter = menu2View.findViewById(R.id.rg_wanfa_filter);
        List<HomeWanFaBean.DataBean> wanFaDatas = new ArrayList<>(App.homeWanFaBean.getData());
        HomeWanFaBean.DataBean dataBean = new HomeWanFaBean.DataBean();
        dataBean.setGame_type(0);
        dataBean.setName(getString(R.string.all));
        wanFaDatas.add(0, dataBean);
        int row = (wanFaDatas.size() + 4) / 4;
        rgItemChild = new RadioGroup[row];
        for (int i = 0; i < row; i++) {
            RadioGroup rg = (RadioGroup) View.inflate(this, R.layout.item_wanfa_filter, null);
            int column = rg.getChildCount();
            for (int j = 0; j < column; j++) {
                RadioButton child = (RadioButton) rg.getChildAt(j);
                int columnStartIndex = i * 4 + j;
                if (columnStartIndex < wanFaDatas.size()){
                    child.setText(wanFaDatas.get(columnStartIndex).getName());
                    child.setTag(String.valueOf(wanFaDatas.get(columnStartIndex).getGame_type()));
                    if (columnStartIndex == 0){
                        rg.check(rg.getChildAt(0).getId());
                    }
                    rg.setOnCheckedChangeListener(new RadioGroupListener());
                }else {
                    child.setVisibility(View.INVISIBLE);
                }
            }
            rgItemChild[i] = rg;
            RadioGroup.LayoutParams rl = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,RadioGroup.LayoutParams.WRAP_CONTENT,1);
            rgWanfaFilter.addView(rg,rl);
        }
        rgSubTime.setOnCheckedChangeListener(new RadioGroupListener());
        menu2View.findViewById(R.id.btn_sure).setOnClickListener(this);
        menu2ViewDialog = new PopupDialog(this, menu2View);
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group != null && checkedId != -1) {
                if (rgSubTime == group) {
                    setSubMenu(checkedId);
                } else{
                    for (RadioGroup radioGroup : rgItemChild) {
                        if (radioGroup == group) {
                            gameType = (String) radioGroup.findViewById(checkedId).getTag();
                        }else {
                            radioGroup.clearCheck();
                        }
                    }
                }
                group.check(checkedId);
            }
        }

        private void setSubMenu(int checkedId) {
            switch(checkedId){
                case R.id.rb_today:
                    subStartDate = subEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    break;
                case R.id.rb_yesterday:
                    subStartDate = subEndDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-1,false);
                    break;
                case R.id.rb_seven_days:
                    subEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    subStartDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-7,false);
                    break;
            }
        }
    }

    public void pageLoad(){
        page ++;
        setData();
    }

    public void setData() {
        if (!srlBetDetail.isRefreshing()){
            mLoadDialog.show();
        }
        InterfaceTask.getInstance().getSubordinateBetDetail(page, stateType, gameType, subStartDate, subEndDate,account,
                new InterfaceTask.OnInterfaceTask() {
                    @Override
                    public void onSuccess(Object obj) {
                        SubordinateBetDetailBean json = (SubordinateBetDetailBean) obj;
                        if (page == 1){
                            dataList.clear();
                        }
                        if (mLoadDialog!= null){
                            mLoadDialog.cancel();
                        }
                        if (BaseUtil.isEmpty(json.getData()) || BaseUtil.isEmpty(json.getData().getList())){
                            ToastUtil.Toast(getBaseContext(),getString(R.string.all_data_load_finish));
                        }else {
                            dataList.addAll(json.getData().getList());
                        }
                        mAdapter.notifyDatasChang(dataList);
                    }
                });
    }

    private void initUI() {
        indicator = findViewById(R.id.indicator);
        rgFilter = findViewById(R.id.rg_filter);
        iecvSearchKey = findViewById(R.id.iecv_search_key);
        TextView tvTitle = findViewById(R.id.tv_top_title);
        IconView tvFilterMenu = findViewById(R.id.icon_right);
        srlBetDetail = findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        RecyclerView rvList = findViewById(R.id.rv_list);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        tvTitle.setText(getString(R.string.subordinate_bet_detail));
        tvFilterMenu.setText(getString(R.string.filter));
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.tv_search).setOnClickListener(this);
        tvFilterMenu.setOnClickListener(this);

        mAdapter = new SubordinateBetDetailAdapter(this);
        mAdapter.registerPageLoad(rvList);
        rvList.setAdapter(mAdapter);
        mAdapter.registerPullDownToRefresh(srlBetDetail);
    }

    private void setTitleFilter() {
        rgFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setIndicatorLen(checkedId);
                String[] stateTypes = {"0","2","3","1"};
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId) {
                        stateType = stateTypes[i];
                        break;
                    }
                }
                page = 1;
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
                menu2ViewDialog.show(v);
                break;
            case R.id.btn_sure:
                page = 1;
                setData();
                menu2ViewDialog.dismiss();
                break;
            case R.id.tv_search:
                page = 1;
                account = iecvSearchKey.getInputInfo();
                setData();
                break;
        }
    }
}

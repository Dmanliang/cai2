package com.sp.caitwo.ui.activity.agentcenter;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.SubordinateManageListBean;
import com.sp.caitwo.ui.adapter.agentcenter.SubordinateManageLv1Adap;
import com.sp.xwjlibrary.dialog.LoadDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import java.util.ArrayList;

public class SubordinateManageActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvSearchKey;
    private RadioGroup rgFilter;
    private TextView tvWhoSubordinate;
    private TextView tvTitle;
    private int page = 1;
    private ArrayList<SubordinateManageListBean.DataBean.ListBean> dataList;
    private SubordinateManageLv1Adap manageLv1Adap;
    private String subUserId = "";
    private String account = "";
    private String subordinateAccount = "";
    private String sortType = "1";
    private SwipeRefreshLayout srlBetDetail;
    private LoadDialog mLoadDialog;

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subordinate_manage);
        mLoadDialog = new LoadDialog(this,"Loading", Constants.drawableResids);

        dataList = new ArrayList<>();

        initUI();

        setData();
    }

    public void setData() {
        if (!srlBetDetail.isRefreshing()) mLoadDialog.show();
        InterfaceTask.getInstance().getSubordinateList(this,page, subUserId, account,sortType, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                SubordinateManageListBean json = (SubordinateManageListBean) obj;
                if (page == 1){
                    dataList.clear();
                }
                if (mLoadDialog != null && mLoadDialog.isShowing()){
                    mLoadDialog.cancel();
                }
                if (BaseUtil.isEmpty(json.getData()) || BaseUtil.isEmpty(json.getData().getList())){
                    ToastUtil.Toast(getBaseContext(),getString(R.string.all_data_load_finish));
                }else {
                    dataList.addAll(json.getData().getList());
                }
                if (BaseUtil.isEmpty(account) && BaseUtil.isEmpty(subUserId)){
                    tvTitle.setText(getString(R.string.subordinate_manage));
                    tvWhoSubordinate.setText(String.format(getString(R.string.who_subordinate),"我"));
                }else {
                    tvWhoSubordinate.setText(String.format(getString(R.string.who_subordinate), subordinateAccount));
                    tvTitle.setText(String.format(getString(R.string.who_subordinate), subordinateAccount));
                }
                manageLv1Adap.notifyDatasChang(dataList);
            }
        });
    }

    public void pageLoad(){
        page ++;
        setData();
    }

    public void lookSubordinate(String userIdAndAccount){
        String[] split = userIdAndAccount.split(",");
        this.subUserId = split[0];
        this.account = "";
        subordinateAccount = split[1];
        page = 1;
        setData();
    }

    private void initUI() {
        iecvSearchKey = findViewById(R.id.iecv_search_key);
        rgFilter = findViewById(R.id.rg_filter);
        tvWhoSubordinate = findViewById(R.id.tv_who_subordinate);
        tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.subordinate_manage));
        tvWhoSubordinate.setText(String.format(getString(R.string.who_subordinate),"我"));
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.tv_search).setOnClickListener(this);

        srlBetDetail = findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        RecyclerView rvLevel1 = findViewById(R.id.rv_level1);
        rvLevel1.setLayoutManager(new LinearLayoutManager(this));
        manageLv1Adap = new SubordinateManageLv1Adap(this);
        manageLv1Adap.registerPageLoad(rvLevel1);
        rvLevel1.setAdapter(manageLv1Adap);
        manageLv1Adap.registerPullDownToRefresh(srlBetDetail);

        rgFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sortType = (String) group.findViewById(checkedId).getTag();
                setData();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.tv_search:
                page = 1;
                if (BaseUtil.isEmpty(iecvSearchKey.getInputInfo())){
                    account = "";
                    subUserId = "";
                    subordinateAccount = "";
                }else {
                    account = iecvSearchKey.getInputInfo();
                    subordinateAccount = iecvSearchKey.getInputInfo();
                }
                setData();
                break;
        }
    }
}

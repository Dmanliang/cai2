package com.sp.caitwo.ui.fragment.agentcenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.SubordinateReportFormsBean;
import com.sp.caitwo.ui.activity.agentcenter.AgentReportFormsActivity;
import com.sp.caitwo.ui.adapter.agentcenter.SubordinateReportFormsLv1Adap;
import com.sp.xwjlibrary.dialog.LoadDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SubordinateReportFormsFrag extends Fragment {

    private int page = 1;
    private SubordinateReportFormsLv1Adap lv1Adap;
    private List<SubordinateReportFormsBean.DataBean.ListBean> dataList;
    private View inflate;
    private SwipeRefreshLayout srlBetDetail;
    private String startDate;
    private String endDate;
    private String account = "";
    private String sort = "betMoney";
    private String sortType = "asc";
    private LoadDialog mLoadDialog;
    private boolean isFirstLoadData = true;

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_subordinate_report_forms, null);
        dataList = new ArrayList<>();
        mLoadDialog = new LoadDialog(getContext(),"Loading", Constants.drawableResids);

        initUI();

        return inflate;
    }

    private void initUI() {
        srlBetDetail = inflate.findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        RecyclerView rvLevel1 = inflate.findViewById(R.id.rv_level1);
        rvLevel1.setLayoutManager(new LinearLayoutManager(getContext()));
        lv1Adap = new SubordinateReportFormsLv1Adap(this);
        lv1Adap.registerPageLoad(rvLevel1);
        rvLevel1.setAdapter(lv1Adap);
        lv1Adap.registerPullDownToRefresh(srlBetDetail);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && isFirstLoadData){
            isFirstLoadData = false;
            setData();
        }
    }

    public void setData() {
        if (mLoadDialog != null && !srlBetDetail.isRefreshing() && !isHidden()){
            mLoadDialog.show();
        }
        InterfaceTask.getInstance().getSubordinateReportForms(page, startDate, endDate, account,sort,sortType,
                new InterfaceTask.OnInterfaceTask() {
                    @Override
                    public void onSuccess(Object obj) {
                        if (page == 1){
                            dataList.clear();
                        }
                        if (mLoadDialog != null && mLoadDialog.isShowing()){
                            mLoadDialog.cancel();
                        }
                        if (srlBetDetail.isRefreshing()){
                            srlBetDetail.setRefreshing(false);
                        }
                        SubordinateReportFormsBean json = (SubordinateReportFormsBean) obj;
                        if (!BaseUtil.isEmpty(json.getData()) && !BaseUtil.isEmpty(json.getData().getList())){
                            dataList.addAll(json.getData().getList());
                        }else {
                            //数据全部加载完毕
                            ToastUtil.Toast(getContext(),"数据全部加载完毕");
                        }
                        lv1Adap.notifyDatasChang(dataList);
                    }
                });
    }

    public void pageLoad(){
        page ++;
        setData();
    }

}

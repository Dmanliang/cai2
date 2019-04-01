package com.sp.caitwo.ui.fragment.UserInfo;

import android.animation.ObjectAnimator;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.BetRecordBean;
import com.sp.caitwo.bean.TraceNumListBean;
import com.sp.caitwo.ui.activity.UserInfo.UserBetRecordActivity;
import com.sp.caitwo.ui.adapter.BetRecordAdapter;
import com.sp.caitwo.ui.adapter.TraceNumRecordAdapter;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.util.TimeUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;

public class UserTraceNumRecordFrag extends Fragment {

    private View inflate;
    private View indicator;
    private RadioGroup rgFilter;
    private TraceNumRecordAdapter mAdapter;
    private String stateType = "0";
    private int page = 1;
    private String subStartDate;
    private String subEndDate;
    private String gameType = "0";
    private ArrayList<TraceNumListBean.DataBeanX.DataBean> dataList;
    private SwipeRefreshLayout srlBetDetail;
    private UserBetRecordActivity mActivity;

    public void setSubStartDate(String subStartDate) {
        this.subStartDate = subStartDate;
    }

    public void setSubEndDate(String subEndDate) {
        this.subEndDate = subEndDate;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.user_trace_num_record_frag, null);
        mActivity = (UserBetRecordActivity) getActivity();
        dataList = new ArrayList<>();
        subStartDate = subEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");

        initUI();
        setTitleFilter();

        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void initUI() {
        indicator = inflate.findViewById(R.id.indicator);
        rgFilter = inflate.findViewById(R.id.rg_filter);
        srlBetDetail = inflate.findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        RecyclerView rvList = inflate.findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new TraceNumRecordAdapter(this);
        mAdapter.registerPageLoad(rvList);
        mAdapter.registerPullDownToRefresh(srlBetDetail);
        rvList.setAdapter(mAdapter);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            rgFilter.post(new Runnable() {
                @Override
                public void run() {
                    setIndicatorLen(rgFilter.getCheckedRadioButtonId());
                }
            });
        }
    }

    private void setTitleFilter() {
        rgFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setIndicatorLen(checkedId);
                String[] stateTypes = {"0","1","2","3"};
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId) {
                        stateType = stateTypes[i];
                        break;
                    }
                }
                page = 1;
                mActivity.mLoadDialog.show();
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

    public void pageLoad(){
        page ++;
        mActivity.mLoadDialog.show();
        setData();
    }

    public void setData() {
        InterfaceTask.getInstance().getUserTraceNumList(page, stateType, gameType, subStartDate, subEndDate,
                new InterfaceTask.OnInterfaceTask() {
                    @Override
                    public void onSuccess(Object obj) {
                        if (srlBetDetail.isRefreshing()){
                            srlBetDetail.setRefreshing(false);
                        }
                        if (mActivity.mLoadDialog != null && mActivity.mLoadDialog.isShowing()){
                            mActivity.mLoadDialog.cancel();
                        }
                        TraceNumListBean json = (TraceNumListBean) obj;
                        if (page == 1){
                            dataList.clear();
                        }
                        if (BaseUtil.isEmpty(json.getData()) || BaseUtil.isEmpty(json.getData().getData())){
                            ToastUtil.Toast(getContext(),getString(R.string.all_data_load_finish));
                        }else {
                            dataList.addAll(json.getData().getData());
                        }
                        mAdapter.notifyDatasChang(dataList);
                    }
                });
    }

    private void setIndicatorLen(int checkedId) {
        if (checkedId != -1) {
            RadioButton checkView = inflate.findViewById(checkedId);
            int textWidth = (int) (TextViewUtil.getTextWidth(checkView) + 0.5);
            int indicatorShort = checkView.getWidth() - textWidth;
            ViewGroup.LayoutParams lp = indicator.getLayoutParams();
            lp.width = checkView.getWidth() - indicatorShort;
            indicator.setLayoutParams(lp);
            ObjectAnimator.ofFloat(indicator,"translationX",((Integer.valueOf(checkView.getTag().toString())) * checkView.getWidth()) + indicatorShort / 2).start();
        }
    }

}

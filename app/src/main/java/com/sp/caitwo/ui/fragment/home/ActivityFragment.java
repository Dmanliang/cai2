package com.sp.caitwo.ui.fragment.home;

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
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.ActivityCenterBean;
import com.sp.caitwo.ui.adapter.ActivityAdapter;
import com.jcodecraeer.xrecyclerview.ItemDecoration.SpaceItemDecoration;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 2018/11/5.
 */

public class ActivityFragment extends Fragment {

    private View inflate;
    private ArrayList<ActivityCenterBean.DataBean> dataList;
    private ActivityAdapter activityAdapter;
    private SwipeRefreshLayout srlBetDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.frag_activitiy, null, false);
            initUI();

            dataList = new ArrayList<>();
            setData();
        }
        return inflate;
    }

    public void setData() {
        InterfaceTask.getInstance().getActivityInfo(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                ActivityCenterBean json = (ActivityCenterBean) obj;
                dataList.clear();
                if (!BaseUtil.isEmpty(json.getData())) {
                    for (ActivityCenterBean.DataBean dataBean : json.getData()) {
                        if (dataBean.getType() == 4 || dataBean.getType() == 5 || dataBean.getType() == 6) {
                            dataList.add(dataBean);
                        }
                    }
                }
                if (!BaseUtil.isEmpty(dataList)) {
                    Collections.sort(dataList, new Comparator<ActivityCenterBean.DataBean>() {
                        @Override
                        public int compare(ActivityCenterBean.DataBean o1, ActivityCenterBean.DataBean o2) {
                            return o1.getId() - o2.getId();
                        }
                    });
                }
                activityAdapter.notifyDatasChang(dataList);
            }
        });
    }

    private void initUI() {
        TextView tvTitle = inflate.findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.activity_center));
        RecyclerView rvActivity = inflate.findViewById(R.id.rv_activity);
        srlBetDetail = inflate.findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        activityAdapter = new ActivityAdapter(this);
        rvActivity.setLayoutManager(new LinearLayoutManager(getContext()));
        rvActivity.setAdapter(activityAdapter);
        activityAdapter.registerPullDownToRefresh(srlBetDetail);
        rvActivity.addItemDecoration(new SpaceItemDecoration(20));
    }


}

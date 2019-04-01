package com.sp.caitwo.ui.fragment.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.NewWinPrizeBean;
import com.sp.caitwo.ui.adapter.NewWinPrizeAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.Timer;
import java.util.TimerTask;

public class NewWinPrizeFrag extends Fragment {

    private NewWinPrizeAdap mNewWinPrizeAdap;
    private SwipeRefreshLayout srlNewWinPrize;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BaseUtil.isEmpty(srlNewWinPrize) && !BaseUtil.isEmpty(getContext())) {
            srlNewWinPrize = new SwipeRefreshLayout(getContext());
            srlNewWinPrize.setColorSchemeResources(R.color.light_red);
            RecyclerView rvList = new RecyclerView(getContext());
            rvList.setLayoutManager(new LinearLayoutManager(getContext()));
            mNewWinPrizeAdap = new NewWinPrizeAdap(this);
            rvList.setAdapter(mNewWinPrizeAdap);
            srlNewWinPrize.addView(rvList);
            mNewWinPrizeAdap.registerPullDownToRefresh(srlNewWinPrize);
            DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.divide_line_horizontal_px1);
            decor.setDrawable(drawable);
            rvList.addItemDecoration(decor);

            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    getData();
                }
            };
            timer.schedule(timerTask, 0, 5000);
        }
        return srlNewWinPrize;
    }

    public void getData() {
        InterfaceTask.getInstance().getNewWinPrize(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                NewWinPrizeBean json = (NewWinPrizeBean) obj;
                if (!BaseUtil.isEmpty(json.getData())){
                    mNewWinPrizeAdap.notifyDatasChang(json.getData());
                }
            }
        });
    }

}

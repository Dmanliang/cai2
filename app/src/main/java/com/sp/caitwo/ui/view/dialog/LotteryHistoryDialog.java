package com.sp.caitwo.ui.view.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sp.caitwo.R;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.ui.adapter.lottery.Kuai3HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class LotteryHistoryDialog extends CommonDialog {

    private Context context;
    private XRecyclerView xrvLotteryHistory;
    private Kuai3HistoryAdapter kuai3HistoryAdapter;
    private List<RoomOpenInfo.DataBean.OpenTimeBean> historyList = new ArrayList<>();


    public LotteryHistoryDialog(Context context) {
        super(context, R.layout.kuai3_lottery_history, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        this.context = context;
    }
    @Override
    public void initDlgView() {
        xrvLotteryHistory = getView(R.id.xrv_lottery_history);
        xrvLotteryHistory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        kuai3HistoryAdapter = new Kuai3HistoryAdapter(context, historyList);
        xrvLotteryHistory.setAdapter(kuai3HistoryAdapter);
        xrvLotteryHistory.setPullRefreshEnabled(false);
        xrvLotteryHistory.setLoadingMoreEnabled(false);
    }

    public List<RoomOpenInfo.DataBean.OpenTimeBean> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<RoomOpenInfo.DataBean.OpenTimeBean> historyList) {
        this.historyList = historyList;
    }
}

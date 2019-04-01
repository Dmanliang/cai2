package com.sp.caitwo.ui.fragment.message;

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
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.MessageBean;
import com.sp.caitwo.ui.adapter.MessageAdap;
import com.sp.xwjlibrary.dialog.LoadDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;

public class MessageFrag extends Fragment {

    private View inflate;
    private MessageAdap messageAdap;
    private ArrayList<MessageBean.DataBean.ListBean> dataList;
    private int page = 1;
    private SwipeRefreshLayout srlBetDetail;
    private LoadDialog mLoadDialog;

    public void setPage(int page) {
        this.page = page;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_message, null);
        mLoadDialog = new LoadDialog(getContext(),"Loading", Constants.drawableResids);
        initUI();

        dataList = new ArrayList<>();

        getData();

        return inflate;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!isHidden()){
            getData();
        }
    }

    public void getData() {
        if (!srlBetDetail.isRefreshing()){
            mLoadDialog.show();
        }
        InterfaceTask.getInstance().getCommonMsg(page, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                MessageBean json = (MessageBean) obj;
                if (page == 1){
                    dataList.clear();
                }
                if (mLoadDialog != null){
                    mLoadDialog.cancel();
                }
                if (srlBetDetail.isRefreshing()){
                    srlBetDetail.setRefreshing(false);
                }
                if (BaseUtil.isEmpty(json.getData()) || BaseUtil.isEmpty(json.getData().getList())){
                    ToastUtil.Toast(getContext(),getString(R.string.all_data_load_finish));
                }else {
                    dataList.addAll(json.getData().getList());
                }
                messageAdap.notifyDatasChang(dataList);
            }
        });
    }

    public void pageLoad(){
        page ++;
        getData();
    }

    private void initUI() {
        srlBetDetail = inflate.findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);
        RecyclerView rvMsgList = inflate.findViewById(R.id.rv_message_list);
        rvMsgList.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.divide_line_horizontal_px1));
        rvMsgList.addItemDecoration(decor);
        messageAdap = new MessageAdap(getActivity(), MessageAdap.TYPE_MSG);
        messageAdap.registerPageLoad(rvMsgList);
        rvMsgList.setAdapter(messageAdap);
        messageAdap.registerPullDownToRefresh(srlBetDetail);
    }
}

package com.sp.caitwo.ui.fragment.agentcenter;

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
import android.widget.RadioGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.InviteCodeListBean;
import com.sp.caitwo.ui.adapter.agentcenter.InviteCodeAdap;
import com.sp.caitwo.ui.adapter.agentcenter.InviteCodeItemDecoration;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.List;

public class InviteCodeFrag extends Fragment {

    private View inflate;
    private RecyclerView rvList;
    private InviteCodeAdap inviteCodeAdap;
    private String registerUrl;
    private SwipeRefreshLayout srlBetDetail;
    private String type = "1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_invite_code, null);
        srlBetDetail = inflate.findViewById(R.id.srl_bet_detail);
        srlBetDetail.setColorSchemeResources(R.color.light_red);

        registerUrl = getArguments().getString("registerUrl");

        setList();

        setTypeListener();

        return inflate;
    }

    private void setTypeListener() {
        RadioGroup rgOpenerType = inflate.findViewById(R.id.rg_opener_type);
        rgOpenerType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_agent_type:
                        type = "1";
                        getList();
                        break;
                    case R.id.rb_player_type:
                        type = "2";
                        getList();
                        break;
                }
            }
        });
    }

    private void setList() {
        rvList = inflate.findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        InviteCodeItemDecoration decor = new InviteCodeItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.invite_code_list_divide));
        rvList.addItemDecoration(decor);
        inviteCodeAdap = new InviteCodeAdap(this,registerUrl);
        rvList.setAdapter(inviteCodeAdap);
        inviteCodeAdap.registerPullDownToRefresh(srlBetDetail);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            getList();
        }
    }

    public void getList() {
        InterfaceTask.getInstance().getInviteCodeList(type, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                InviteCodeListBean json = (InviteCodeListBean) obj;
                if (!BaseUtil.isEmpty(json.getData())){
                    List<InviteCodeListBean.DataBean> data = json.getData();
                    inviteCodeAdap.notifyDatasChang(data);
                }else {
                    inviteCodeAdap.notifyDatasChang(null);
                }
            }
        });
    }
}

package com.sp.caitwo.ui.adapter.agentcenter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.InviteCodeListBean;
import com.sp.caitwo.ui.fragment.agentcenter.InviteCodeFrag;
import com.sp.caitwo.ui.holder.InviteCodeHolder;
import com.sp.util.ProjectUtil;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.dialog.ViewDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.List;

public class InviteCodeAdap extends RVBaseAdap<InviteCodeHolder> {
    private List<InviteCodeListBean.DataBean> data;
    private FragmentActivity activity;
    private InviteCodeFrag frag;
    private String registerUrl;
    private ViewDialog viewDialog;
    private TextView tvKuai3;
    private TextView tvSsc;
    private TextView tv11x5;
    private TextView tvFc3d;
    private TextView tvPl35;
    private TextView tv28;
    private TextView tvPk10;
    private TextView tvLhc;
    private TextView tvDpc;

    public InviteCodeAdap(InviteCodeFrag frag, String registerUrl) {
        this.activity = frag.getActivity();
        this.frag = frag;
        this.registerUrl = registerUrl;
        initDialog();
    }

    private void initDialog() {
        View inflate = View.inflate(activity, R.layout.dialog_look_return_point, null);
        tvKuai3 = inflate.findViewById(R.id.tv_kuai3);
        tvSsc = inflate.findViewById(R.id.tv_ssc);
        tv11x5 = inflate.findViewById(R.id.tv_11x5);
        tvFc3d = inflate.findViewById(R.id.tv_fc3d);
        tvPl35 = inflate.findViewById(R.id.tv_pl35);
        tv28 = inflate.findViewById(R.id.tv_28);
        tvPk10 = inflate.findViewById(R.id.tv_pk10);
        tvLhc = inflate.findViewById(R.id.tv_lhc);
        tvDpc = inflate.findViewById(R.id.tv_dpc);
        inflate.findViewById(R.id.tv_i_know).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDialog.dismiss();
            }
        });
        viewDialog = new ViewDialog(activity, inflate);
        viewDialog.setBothSideSpace(300);
    }

    @Override
    public InviteCodeHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new InviteCodeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invite_code,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(data) ? 0 : data.size();
    }

    @Override
    public void onBindHolder(InviteCodeHolder holder, int position) {
        holder.tvInviteCode.setText(String.format(activity.getString(R.string.invite_code_num),data.get(position).getInvite_code()));
        holder.tvGenerateTime.setText(data.get(position).getCreate_time());
        holder.tvRegisterNum.setText(String.format(activity.getString(R.string.register_num),data.get(position).getRegister_count()));
        holder.tvDelete.setTag(position);
        holder.tvCopyInviteCode.setTag(position);
        holder.tvCopyRegisterLink.setTag(position);
        holder.tvLookReturnPoint.setTag(position);
        holder.tvDelete.setOnClickListener(new ViewClick());
        holder.tvCopyInviteCode.setOnClickListener(new ViewClick());
        holder.tvCopyRegisterLink.setOnClickListener(new ViewClick());
        holder.tvLookReturnPoint.setOnClickListener(new ViewClick());
    }

    public void notifyDatasChang(List<InviteCodeListBean.DataBean> data) {
        this.data = data;
        super.notifyDatasChang();
    }

    private class ViewClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final int pos = (int) v.getTag();
            switch(v.getId()){
                case R.id.tv_delete:
                    InterfaceTask.getInstance().delInviteCode(activity, data.get(pos).getId(),
                            new InterfaceTask.OnInterfaceTask() {
                                @Override
                                public void onSuccess(Object obj) {
                                    data.remove(pos);
                                    notifyDatasChang(data);
                                }
                            });
                    break;
                case R.id.tv_copy_invite_code:
                    ProjectUtil.copy(activity,data.get(pos).getInvite_code());
                    break;
                case R.id.tv_copy_register_link:
                    ProjectUtil.copy(activity,registerUrl + "?id=" + data.get(pos).getInvite_code());
                    break;
                case R.id.tv_look_return_point:
                    tvKuai3.setText(data.get(pos).getKuai_three_profit());
                    tvSsc.setText(data.get(pos).getShishicaip_profit());
                    tv11x5.setText(data.get(pos).getEleven_five_profit());
                    tvFc3d.setText(data.get(pos).getFucai_profit());
                    tvPl35.setText(data.get(pos).getArray_profit());
                    tv28.setText(data.get(pos).getTwenty_eight_profit());
                    tvPk10.setText(data.get(pos).getPk_ten_profit());
                    tvLhc.setText(data.get(pos).getLhc_profit());
                    tvDpc.setText(data.get(pos).getLow_frequency_profit());
                    viewDialog.show();
                    break;
            }
        }
    }

    @Override
    public void onPullDownToRefresh() {
        frag.getList();
    }
}

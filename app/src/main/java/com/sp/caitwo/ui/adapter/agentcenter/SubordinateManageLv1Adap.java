package com.sp.caitwo.ui.adapter.agentcenter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.SubordinateManageListBean;
import com.sp.caitwo.ui.activity.agentcenter.SubordinateManageActivity;
import com.sp.caitwo.ui.adapter.lottery.AgentReportItemDecoration;
import com.sp.caitwo.ui.holder.SubordinateManageLv1Holder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.dialog.ViewDialog;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class SubordinateManageLv1Adap extends RVBaseAdap<SubordinateManageLv1Holder> {

    private SubordinateManageActivity activity;
    private HashMap<Integer,SubordinateManageLv2Adap> lv2AdapMap = new HashMap<>();
    private ArrayList<SubordinateManageListBean.DataBean.ListBean> dataList;
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

    public SubordinateManageLv1Adap(SubordinateManageActivity activity) {
        this.activity = activity;
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
    public SubordinateManageLv1Holder onCreateHolder(ViewGroup parent, int viewType) {
        return new SubordinateManageLv1Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subordinate_manage_lv1,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(dataList) ? 0 : dataList.size();
    }

    @Override
    public void onBindHolder(final SubordinateManageLv1Holder holder, int position) {
        SubordinateManageListBean.DataBean.ListBean listBean = dataList.get(position);
        holder.tvAccount.setText(listBean.getAccount());
        holder.tvLevel.setText(String.format(activity.getString(R.string.vip_level_num),listBean.getUser_level()));
        holder.tvSubordinateNum.setText(String.format(activity.getString(R.string.subordinate_num), listBean.getSub_count()));

        if (!lv2AdapMap.containsKey(position)){
            SubordinateManageLv2Adap adapter = new SubordinateManageLv2Adap();
            lv2AdapMap.put(position,adapter);
        }
        holder.rvLevel2.setLayoutManager(new LinearLayoutManager(activity));
        AgentReportItemDecoration decor = new AgentReportItemDecoration(activity, LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(activity, R.drawable.divide_line_horizontal_px1));
        holder.rvLevel2.addItemDecoration(decor);
        holder.rvLevel2.setAdapter(lv2AdapMap.get(position));
        lv2AdapMap.get(position).notifyDatasChang(listBean);

        holder.itemView.setTag(holder.llyLv2List.getId(),holder.llyLv2List);
        holder.itemView.setTag(holder.cbArrow.getId(),holder.cbArrow);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.llyLv2List = (LinearLayout) v.getTag(holder.llyLv2List.getId());
                holder.cbArrow = (CheckBox) v.getTag(holder.cbArrow.getId());
                if (holder.cbArrow.isChecked()){
                    holder.cbArrow.setChecked(false);
                    holder.llyLv2List.setVisibility(View.GONE);
                }else {
                    holder.cbArrow.setChecked(true);
                    holder.llyLv2List.setVisibility(View.VISIBLE);
                }
            }
        });
        holder.tvLookReturnPoint.setTag(listBean);
        holder.tvLookReturnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubordinateManageListBean.DataBean.ListBean listBean = (SubordinateManageListBean.DataBean.ListBean) v.getTag();
                tvKuai3.setText(listBean.getKuai_three_profit());
                tvSsc.setText(listBean.getShishicai_profit());
                tv11x5.setText(listBean.getEleven_five_profit());
                tvFc3d.setText(listBean.getFucai_profit());
                tvPl35.setText(listBean.getArray_profit());
                tv28.setText(listBean.getTwenty_eight_profit());
                tvPk10.setText(listBean.getPk_ten_profit());
                tvLhc.setText(listBean.getLhc_profit());
                tvDpc.setText(listBean.getLow_frequency_profit());
                viewDialog.show();
            }
        });
        holder.tvLookSubordinate.setTag(listBean.getUser_id() + "," + listBean.getAccount());
        holder.tvLookSubordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIdAndAccount = (String) v.getTag();
                activity.lookSubordinate(userIdAndAccount);
            }
        });
    }

    @Override
    public void onPageLoad() {
        activity.pageLoad();
    }

    public void notifyDatasChang(ArrayList<SubordinateManageListBean.DataBean.ListBean> dataList) {
        this.dataList = dataList;
        super.notifyDatasChang();
    }

    @Override
    public void onPullDownToRefresh() {
        activity.setPage(1);
        activity.setData();
    }
}

package com.sp.caitwo.ui.adapter.agentcenter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.SubordinateReportFormsBean;
import com.sp.caitwo.ui.activity.agentcenter.AgentReportFormsActivity;
import com.sp.caitwo.ui.adapter.lottery.AgentReportItemDecoration;
import com.sp.caitwo.ui.fragment.agentcenter.SubordinateReportFormsFrag;
import com.sp.caitwo.ui.holder.SubordinateReportFormsLv1Holder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.HashMap;
import java.util.List;

public class SubordinateReportFormsLv1Adap extends RVBaseAdap<SubordinateReportFormsLv1Holder> {

    private HashMap<Integer,SubordinateReportFormsLv2Adap> lv2AdapMap = new HashMap<>();
    private SubordinateReportFormsFrag frag;
    private List<SubordinateReportFormsBean.DataBean.ListBean> list;

    public SubordinateReportFormsLv1Adap(SubordinateReportFormsFrag frag) {
        this.frag = frag;
    }

    @Override
    public SubordinateReportFormsLv1Holder onCreateHolder(ViewGroup parent, int viewType) {
        return new SubordinateReportFormsLv1Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subordinate_report_forms_lv1,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public void onBindHolder(final SubordinateReportFormsLv1Holder holder, int position) {
        if (!lv2AdapMap.containsKey(position)){
            SubordinateReportFormsLv2Adap adapter = new SubordinateReportFormsLv2Adap();
            lv2AdapMap.put(position,adapter);
        }
        holder.rvLevel2.setLayoutManager(new LinearLayoutManager(frag.getContext()));
        AgentReportItemDecoration decor = new AgentReportItemDecoration(frag.getContext(), LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(frag.getContext(), R.drawable.divide_line_horizontal_px1));
        holder.rvLevel2.addItemDecoration(decor);
        holder.rvLevel2.setAdapter(lv2AdapMap.get(position));

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
        holder.tvAccount.setText(list.get(position).getAccount());
        holder.tvLevel.setText(String.valueOf(list.get(position).getUser_level()));
        holder.tvBet.setText(String.valueOf(list.get(position).getBet_money()));
        holder.tvProfitLoss.setText(String.valueOf(list.get(position).getWin_lost_money()));
        if (String.valueOf(list.get(position).getWin_lost_money()).startsWith("+"))
            holder.tvProfitLoss.setTextColor(Color.parseColor("#E54B55"));
        else if (String.valueOf(list.get(position).getWin_lost_money()).startsWith("-"))
            holder.tvProfitLoss.setTextColor(Color.parseColor("#5BB960"));
        else
            holder.tvProfitLoss.setTextColor(Color.parseColor("#333333"));


        lv2AdapMap.get(position).notifyDatasChang(list.get(position));
        holder.tvHisTeamReportForms.setTag(list.get(position).getAccount());
        holder.tvHisTeamReportForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgentReportFormsActivity activity = (AgentReportFormsActivity) frag.getActivity();
                activity.hisReportData(v.getTag().toString());
            }
        });
    }

    @Override
    public void onPageLoad() {
        super.onPageLoad();
        frag.pageLoad();
    }

    public void notifyDatasChang(List<SubordinateReportFormsBean.DataBean.ListBean> list) {
        this.list = list;
        super.notifyDatasChang();
    }

    @Override
    public void onPullDownToRefresh() {
        frag.setPage(1);
        frag.setData();
    }
}

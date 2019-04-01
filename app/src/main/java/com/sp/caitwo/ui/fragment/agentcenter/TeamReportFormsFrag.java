package com.sp.caitwo.ui.fragment.agentcenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.TeamReportFormsBean;
import com.sp.caitwo.ui.activity.agentcenter.AgentReportFormsActivity;
import com.sp.xwjlibrary.dialog.LoadDialog;
import com.sp.xwjlibrary.dialog.ViewDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.widget.DoubleTextView;

public class TeamReportFormsFrag extends Fragment implements View.OnClickListener {

    private View inflate;
    private TextView tvMySubordinatePerson;
    private TextView tvWhoTeam;
    private DoubleTextView dtvBetMoney;
    private DoubleTextView dtvPrizeMoney;
    private DoubleTextView dtvActivityCashGift;
    private DoubleTextView dtvTeamReturnCommission;
    private DoubleTextView dtvTeamProfitLoss;
    private DoubleTextView dtvReturnPointCommission;
    private DoubleTextView dtvRechargeMoney;
    private DoubleTextView dtvWithdrawalMoney;
    private DoubleTextView dtvTeamSurplusMoney;
    private DoubleTextView dtvFirstRechargePeople;
    private DoubleTextView dtvRegisterPeople;
    private DoubleTextView dtvBetPeople;
    private AgentReportFormsActivity activity;
    private ViewDialog mNounExplainDialog;
    private String startDate;
    private String endDate;
    private String account = "";
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_team_report_forms, null);
        activity = (AgentReportFormsActivity) getActivity();
        mLoadDialog = new LoadDialog(getContext(),"Loading", Constants.drawableResids);

        initUI();

        View view = LayoutInflater.from(activity).inflate( R.layout.dialog_noun_explain,null,false);
        view.findViewById(R.id.tv_i_know).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNounExplainDialog.dismiss();
            }
        });
        mNounExplainDialog = new ViewDialog(activity, view);
        return this.inflate;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && isFirstLoadData){
            isFirstLoadData = false;
            setData();
        }
    }

    public void setData() {
        if (mLoadDialog != null) mLoadDialog.show();
        InterfaceTask.getInstance().getTeamReportForms(startDate, endDate, account, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                if (mLoadDialog != null && mLoadDialog.isShowing()){
                    mLoadDialog.cancel();
                }
                TeamReportFormsBean info = (TeamReportFormsBean) obj;
                tvMySubordinatePerson.setText(String.valueOf(info.getData().getSub_agent_people()));
                dtvBetMoney.setOneText(String.valueOf(info.getData().getBet_money()));
                dtvPrizeMoney.setOneText(String.valueOf(info.getData().getWin_money()));
                dtvActivityCashGift.setOneText(String.valueOf(info.getData().getGift_money()));
                dtvTeamReturnCommission.setOneText(String.valueOf(info.getData().getTeam_profit_money()));
                dtvTeamProfitLoss.setOneText(String.valueOf(info.getData().getWin_lost_money()));
                dtvReturnPointCommission.setOneText(String.valueOf(info.getData().getShare_profit_money()));
                dtvRechargeMoney.setOneText(String.valueOf(info.getData().getRecharge_money()));
                dtvWithdrawalMoney.setOneText(String.valueOf(info.getData().getWithdrawals_money()));
                dtvTeamSurplusMoney.setOneText(String.valueOf(info.getData().getTeam_balance()));
                dtvFirstRechargePeople.setOneText(String.valueOf(info.getData().getFirst_recharge_people()));
                dtvRegisterPeople.setOneText(String.valueOf(info.getData().getRegister_people()));
                dtvBetPeople.setOneText(String.valueOf(info.getData().getBet_people()));
                if (!BaseUtil.isEmpty(account))
                    tvWhoTeam.setText(String.format(getString(R.string.who_team_report_forms),account));
                else
                    tvWhoTeam.setText(String.format(getString(R.string.who_team_report_forms),"我"));
            }
        });
    }

    private void initUI() {
        tvMySubordinatePerson = inflate.findViewById(R.id.tv_my_subordinate_person);
        tvWhoTeam = inflate.findViewById(R.id.tv_who_team);
        dtvBetMoney = inflate.findViewById(R.id.dtv_bet_money);
        dtvPrizeMoney = inflate.findViewById(R.id.dtv_prize_money);
        dtvActivityCashGift = inflate.findViewById(R.id.dtv_activity_cash_gift);
        dtvTeamReturnCommission = inflate.findViewById(R.id.dtv_team_return_commission);
        dtvTeamProfitLoss = inflate.findViewById(R.id.dtv_team_profit_loss);
        dtvReturnPointCommission = inflate.findViewById(R.id.dtv_return_point_commission);
        dtvRechargeMoney = inflate.findViewById(R.id.dtv_recharge_money);
        dtvWithdrawalMoney = inflate.findViewById(R.id.dtv_withdrawal_money);
        dtvTeamSurplusMoney = inflate.findViewById(R.id.dtv_team_surplus_money);
        dtvFirstRechargePeople = inflate.findViewById(R.id.dtv_first_recharge_people);
        dtvRegisterPeople = inflate.findViewById(R.id.dtv_register_people);
        dtvBetPeople = inflate.findViewById(R.id.dtv_bet_people);
        tvWhoTeam.setText(String.format(getString(R.string.who_team_report_forms),"我"));

        inflate.findViewById(R.id.tv_noun_explain).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_noun_explain:
                mNounExplainDialog.show();
                break;
        }
    }
}

package com.sp.caitwo.ui.activity.transferaccounts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.WithdrawInfoBean;
import com.sp.caitwo.ui.adapter.transferaccounts.WithdrawBankAdap;
import com.sp.caitwo.ui.view.dialog.WheelDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import java.util.ArrayList;
import java.util.List;

import kankan.wheel.widget.WheelView;

public class WithdrawActivity extends AppCompatActivity implements View.OnClickListener {

    private WheelDialog wheelDialog;
    private WheelView wheelQuestion;
    private ArrayList<WithdrawInfoBean.DataBean.BankListBean> bankCardList;
    private TextView tvBankName;
    private TextView tvTailNum;
    private ImageView ivBankLogo;
    private IconEditClearView iecvAccountBalance;
    private IconEditClearView iecvCanWithdrawMoney;
    private IconEditClearView iecvSurplusPlayCode;
    private IconEditClearView iecvWithdrawMoney;
    private IconEditClearView iecvSecurityPsw;
    private double availableWithdraw;
    private String bankId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        initUI();

        bankCardList = new ArrayList<>();

        getData();

        initWheel();
    }

    private void initUI() {
        ivBankLogo = findViewById(R.id.iv_bank_logo);
        tvBankName = findViewById(R.id.tv_bank_name);
        tvTailNum = findViewById(R.id.tv_tail_num);
        iecvAccountBalance = findViewById(R.id.iecv_account_balance);
        iecvCanWithdrawMoney = findViewById(R.id.iecv_can_withdraw_money);
        iecvSurplusPlayCode = findViewById(R.id.iecv_surplus_play_code);
        iecvWithdrawMoney = findViewById(R.id.iecv_withdraw_money);
        iecvSecurityPsw = findViewById(R.id.iecv_security_psw);
        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.withdrawal));

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.lly_sel_bank).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
    }

    private void getData() {
        InterfaceTask.getInstance().withdrawInfo(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                WithdrawInfoBean json = (WithdrawInfoBean) obj;
                if (!BaseUtil.isEmpty(json.getData())){
                    iecvAccountBalance.setInputInfo(String.valueOf(json.getData().getUserBalance()));
                    availableWithdraw = json.getData().getAvailableWithdraw();
                    iecvCanWithdrawMoney.setInputInfo(String.valueOf(availableWithdraw));
                    iecvSurplusPlayCode.setInputInfo(String.valueOf(json.getData().getNotCompleteMoney()));
                    List<WithdrawInfoBean.DataBean.BankListBean> bankList = json.getData().getBankList();
                    ivBankLogo.setImageResource(Constants.bankMap.get(bankList.get(0).getBank_name()));
                    tvBankName.setText(bankList.get(0).getBank_name());
                    tvTailNum.setText(String.format(getString(R.string.tail_num_4),bankList.get(0).getBank_no()));
                    bankId = bankList.get(0).getId();
                    bankCardList.clear();
                    bankCardList.addAll(bankList);
                    wheelQuestion.invalidateWheel(true);
                }
            }
        });
    }

    private void initWheel() {
        View inflate = View.inflate(this,R.layout.dialog_security_question, null);
        wheelQuestion = inflate.findViewById(R.id.wheel_question);
        inflate.findViewById(R.id.tv_wheel_cancle).setOnClickListener(this);
        inflate.findViewById(R.id.tv_wheel_sure).setOnClickListener(this);
        wheelQuestion.setWheelBackground(R.color.white);
        wheelQuestion.setWheelForeground(R.drawable.top_bottom_grayside_whitebg);
        wheelQuestion.setViewAdapter(new WithdrawBankAdap(bankCardList));
        wheelQuestion.setDrawShadows(false);
        wheelQuestion.setVisibleItems(7);
        wheelDialog = new WheelDialog(this, inflate);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.lly_sel_bank:
                wheelDialog.show();
                break;
            case R.id.tv_wheel_cancle:
                wheelDialog.dismiss();
                break;
            case R.id.tv_wheel_sure:
                WithdrawInfoBean.DataBean.BankListBean bean = bankCardList.get(wheelQuestion.getCurrentItem());
                tvBankName.setText(bean.getBank_name());
                String tailName = String.format(getString(R.string.tail_num_4), bean.getBank_no());
                tvTailNum.setText(tailName);
                ivBankLogo.setImageResource(Constants.bankMap.get(bean.getBank_name()));
                bankId = bean.getId();
                wheelDialog.dismiss();
                break;
            case R.id.btn_sure:
                if (!BaseUtil.isEmpty(iecvWithdrawMoney.getInputInfo())) {
                    Double withdrawMoney = Double.valueOf(iecvWithdrawMoney.getInputInfo());
                    if (withdrawMoney >= 10 && withdrawMoney <= availableWithdraw && withdrawMoney <= 5000000) {
                        InterfaceTask.getInstance().withdraw(this, bankId, String.valueOf(withdrawMoney), iecvSecurityPsw.getInputInfo());
                    } else {
                        ToastUtil.Toast(this, "提现金额有误");
                    }
                }else {
                    ToastUtil.Toast(this, "请输入提现金额");
                }
                break;
        }
    }
}

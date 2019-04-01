package com.sp.caitwo.ui.activity.securitycenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sp.caitwo.bean.UserBankList;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.info.RegionInfo;
import com.sp.caitwo.ui.adapter.securitycenter.WheelQuestionAdap;
import com.sp.caitwo.ui.view.dialog.WheelDialog;
import com.sp.util.SetTextViewSameWidthUtil;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import java.io.Serializable;
import java.util.ArrayList;

import kankan.wheel.widget.WheelView;

public class AddBankCardActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvOpenerName;
    private IconEditClearView iecvSelBank;
    private IconEditClearView iecvOpenerProvince;
    private IconEditClearView iecvOpenerCity;
    private IconEditClearView iecvBranchBankName;
    private IconEditClearView iecvBankCardNum;
    private IconEditClearView iecvConfirmCardNum;
    private IconEditClearView iecvSecurityPsw;
    private WheelDialog wheelDialog;
    private View clickView;
    private ArrayList<String> list;
    private WheelView wheelQuestion;
    private int provinceId = -1;
    private UserBankList.DataBean bankCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);

        initUI();

        initWheel();

        App.readCityDb(this);

    }

    private void initWheel() {
        list = new ArrayList<>();
        View inflate = View.inflate(this,R.layout.dialog_security_question, null);
        wheelQuestion = inflate.findViewById(R.id.wheel_question);
        inflate.findViewById(R.id.tv_wheel_cancle).setOnClickListener(this);
        inflate.findViewById(R.id.tv_wheel_sure).setOnClickListener(this);
        wheelQuestion.setWheelBackground(R.color.white);
        wheelQuestion.setWheelForeground(R.drawable.top_bottom_grayside_whitebg);
        wheelQuestion.setViewAdapter(new WheelQuestionAdap(list));
        wheelQuestion.setDrawShadows(false);
        wheelQuestion.setVisibleItems(7);
        wheelDialog = new WheelDialog(this, inflate);
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        iecvOpenerName = findViewById(R.id.iecv_opener_name);
        iecvSelBank = findViewById(R.id.iecv_sel_bank);
        iecvOpenerProvince = findViewById(R.id.iecv_opener_province);
        iecvOpenerCity = findViewById(R.id.iecv_opener_city);
        iecvBranchBankName = findViewById(R.id.iecv_branch_bank_name);
        iecvBankCardNum = findViewById(R.id.iecv_bank_card_num);
        iecvConfirmCardNum = findViewById(R.id.iecv_confirm_card_num);
        iecvSecurityPsw = findViewById(R.id.iecv_security_psw);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        iecvSelBank.getInputView().setOnClickListener(this);
        iecvOpenerProvince.getInputView().setOnClickListener(this);
        iecvOpenerCity.getInputView().setOnClickListener(this);

        ViewGroup rootLayout = HolderUtil.getRootLayout(this);
        SetTextViewSameWidthUtil.setIecv(rootLayout);
        String openerName = getIntent().getStringExtra("openerName");
        bankCard = (UserBankList.DataBean) getIntent().getSerializableExtra("bankCard");
        if (BaseUtil.isEmpty(bankCard)) {
            tvTitle.setText(getString(R.string.bind_bank_card));
        }else {
            tvTitle.setText(getString(R.string.change_bank_card));
            String[] openerAddr = bankCard.getOpen_card_address().split("--");
            iecvSelBank.setInputInfo(bankCard.getBank_name());
            iecvOpenerProvince.setInputInfo(openerAddr[0]);
            iecvOpenerCity.setInputInfo(openerAddr[1]);
            iecvBranchBankName.setInputInfo(openerAddr[2]);
            iecvBankCardNum.setInputInfo(bankCard.getBank_no());
            iecvConfirmCardNum.setInputInfo(bankCard.getBank_no());
            openerName = bankCard.getReal_name();
        }
        if (!BaseUtil.isEmpty(openerName) || !BaseUtil.isEmpty(bankCard)){
            iecvOpenerName.setInputInfo(openerName);
            iecvOpenerName.setEnabled(false);
            iecvOpenerName.getClearView().setText("");
            HolderUtil.disableSubControls(iecvOpenerName);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == iecvSelBank.getInputView()){
            list.clear();
            list.addAll(Constants.bankName);
            clickView = v;
            wheelQuestion.invalidateWheel(true);
            wheelQuestion.setCurrentItem(0);
            wheelDialog.show();
        }else if (v == iecvOpenerProvince.getInputView()){
            list.clear();
            for (RegionInfo regionInfo : App.provinceList) {
                list.add(regionInfo.getName());
            }
            clickView = v;
            wheelQuestion.invalidateWheel(true);
            wheelQuestion.setCurrentItem(0);
            wheelDialog.show();
        }else if (v == iecvOpenerCity.getInputView()){
            if (provinceId == -1) {
                ToastUtil.Toast(this,getString(R.string.please_before_sel_opener_province));
                return;
            }
            clickView = v;
            list.clear();
            for (RegionInfo info : App.cityList) {
                if (provinceId == info.getParentId()) {
                    list.add(info.getName());
                }
            }
            wheelQuestion.setCurrentItem(0);
            wheelQuestion.invalidateWheel(true);
            wheelDialog.show();
        }else{
            switch(v.getId()){
                case R.id.iv_top_back:
                    finish();
                    break;
                case R.id.btn_sure:
                    addBindBankCard();
                    break;
                case R.id.tv_wheel_cancle:
                    wheelDialog.dismiss();
                    break;
                case R.id.tv_wheel_sure:
                    if (clickView == iecvSelBank.getInputView()){
                        iecvSelBank.setInputInfo(list.get(wheelQuestion.getCurrentItem()));
                        wheelDialog.dismiss();
                    }else if (clickView == iecvOpenerProvince.getInputView()){
                        provinceId = App.provinceList.get(wheelQuestion.getCurrentItem()).getId();
                        iecvOpenerProvince.setInputInfo(list.get(wheelQuestion.getCurrentItem()));
                        for (int i = 0; i < App.cityList.size(); i++) {
                            if (provinceId == App.cityList.get(i).getParentId()) {
                                iecvOpenerCity.setInputInfo(App.cityList.get(i).getName());
                                break;
                            }
                        }
                        wheelDialog.dismiss();
                    }else if (clickView == iecvOpenerCity.getInputView()){
                        iecvOpenerCity.setInputInfo(list.get(wheelQuestion.getCurrentItem()));
                        wheelDialog.dismiss();
                    }
                    break;
            }
        }
    }

    private void addBindBankCard() {
        if (BaseUtil.isEmpty(iecvOpenerName.getInputInfo())){
            iecvOpenerName.setErrorInfo(getString(R.string.input_opener_name));
        }
        if (BaseUtil.isEmpty(iecvSelBank.getInputInfo())){
            iecvSelBank.setErrorInfo(getString(R.string.please_sel_bank));
        }
        if (BaseUtil.isEmpty(iecvOpenerProvince.getInputInfo())){
            iecvOpenerProvince.setErrorInfo(getString(R.string.please_select_opener_province));
        }
        if (BaseUtil.isEmpty(iecvOpenerCity.getInputInfo())){
            iecvOpenerCity.setErrorInfo(getString(R.string.please_select_opener_city));
        }
        if (BaseUtil.isEmpty(iecvBranchBankName.getInputInfo())){
            iecvBranchBankName.setErrorInfo(getString(R.string.input_branch_bank_name));
        }
        if (BaseUtil.isEmpty(iecvBankCardNum.getInputInfo())){
            iecvBankCardNum.setErrorInfo(getString(R.string.input_bank_card_num));
        }
        if (BaseUtil.isEmpty(iecvConfirmCardNum.getInputInfo())){
            iecvConfirmCardNum.setErrorInfo(getString(R.string.input_again_card_num));
        }else if (!BaseUtil.isEmpty(iecvBankCardNum.getInputInfo())){
            if (!BaseUtil.equals(iecvBankCardNum.getInputInfo(),iecvConfirmCardNum.getInputInfo())){
                iecvConfirmCardNum.setErrorInfo(getString(R.string.double_bank_num_differ));
            }
        }
        if (BaseUtil.isEmpty(iecvSecurityPsw.getInputInfo())){
            iecvSecurityPsw.setErrorInfo(getString(R.string.input_security_psw));
        }

        if (!iecvOpenerName.isHasError() && !iecvSelBank.isHasError() && !iecvOpenerProvince.isHasError() &&
                !iecvOpenerCity.isHasError() && !iecvBranchBankName.isHasError() && !iecvBankCardNum.isHasError() &&
                !iecvConfirmCardNum.isHasError() && !iecvSecurityPsw.isHasError()){
            if (BaseUtil.isEmpty(bankCard)) {
                InterfaceTask.getInstance().addUserBank(this,iecvOpenerName.getInputInfo(),iecvSelBank.getInputInfo(),
                        iecvOpenerProvince.getInputInfo() + "--" + iecvOpenerCity.getInputInfo() + "--" +
                        iecvBranchBankName.getInputInfo(), iecvBankCardNum.getInputInfo(), iecvSecurityPsw.getInputInfo());
            }else {
                InterfaceTask.getInstance().changeUserBank(this,String.valueOf(bankCard.getId()),iecvOpenerName.getInputInfo(),iecvSelBank.getInputInfo(),
                        iecvOpenerProvince.getInputInfo() + "--" + iecvOpenerCity.getInputInfo() + "--" +
                                iecvBranchBankName.getInputInfo(), iecvBankCardNum.getInputInfo(), iecvSecurityPsw.getInputInfo());

            }
        }

    }
}

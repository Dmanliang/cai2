package com.sp.caitwo.ui.activity.securitycenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.PersonalSecurityQuestionBean;
import com.sp.caitwo.ui.activity.sysm.CustomServiceActivity;
import com.sp.util.SetTextViewSameWidthUtil;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

public class CheckSecurityPswActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvOldSecurityPsw;
    private IconEditClearView iecvAccount;
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_security_psw);

        initUI();

        SetTextViewSameWidthUtil.setIecv(HolderUtil.getRootLayout(this));
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        iecvOldSecurityPsw = findViewById(R.id.iecv_old_security_psw);
        iecvAccount = findViewById(R.id.iecv_account);
        findViewById(R.id.tv_forget_security_psw).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        findViewById(R.id.iv_top_back).setOnClickListener(this);

        tvTitle.setText(getString(R.string.check_security_psw));
        account = getIntent().getStringExtra("account");
        iecvAccount.setInputInfo(account);
        if (!BaseUtil.isEmpty(account)){
            iecvAccount.setEnabled(false);
            iecvAccount.getClearView().setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.tv_forget_security_psw:
                InterfaceTask.getInstance().getPersonalSecurityQuestion(this, account, new InterfaceTask.OnInterfaceTask() {
                    @Override
                    public void onSuccess(Object obj) {
                        PersonalSecurityQuestionBean info = (PersonalSecurityQuestionBean) obj;
                        int securityStatus = info.getData().getSecurityStatus();
                        Intent intent = new Intent();
                        if (securityStatus == 0 || securityStatus == 1){
                            intent.setClass(getBaseContext(),CustomServiceActivity.class);
                        }else {
                            intent.setClass(getBaseContext(), CheckSecurityQuestionActivity.class);
                            intent.putExtra("account",account);
                            intent.putExtra("Class","forgetPsw");
                        }
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            case R.id.btn_sure:
                String oldSecurityPsw = iecvOldSecurityPsw.getInputInfo();
                if (BaseUtil.isEmpty(oldSecurityPsw)){
                    iecvOldSecurityPsw.setErrorInfo(getString(R.string.input_old_psw));
                }
                if (BaseUtil.isEmpty(account)){
                    iecvAccount.setErrorInfo(getString(R.string.input_account_hint));
                }

                if (!iecvOldSecurityPsw.isHasError() && !iecvAccount.isHasError()){
                    InterfaceTask.getInstance().checkSecurityPsw(this,account,oldSecurityPsw);
                }
                break;
        }
    }
}

package com.sp.caitwo.ui.activity.securitycenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.util.SetTextViewSameWidthUtil;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

public class ChangePswActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvOldPsw;
    private IconEditClearView iecvLoginPsw;
    private IconEditClearView iecvConfirmPsw;
    private String securityCode;
    private String securityPsw;
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_psw);

        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.change_login_psw));
        iecvOldPsw = findViewById(R.id.iecv_old_psw);
        iecvLoginPsw = findViewById(R.id.iecv_login_psw);
        iecvConfirmPsw = findViewById(R.id.iecv_confirm_psw);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);

        SetTextViewSameWidthUtil.setIecv(HolderUtil.getRootLayout(this));

        securityCode = getIntent().getStringExtra("securityCode");
        securityPsw = getIntent().getStringExtra("securityPsw");
        account = getIntent().getStringExtra("account");
        if (!BaseUtil.isEmpty(securityCode) || !BaseUtil.isEmpty(securityPsw)){
            iecvOldPsw.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.btn_sure:
                if (!BaseUtil.isEmpty(iecvLoginPsw.getInputInfo())) {
                    int length = iecvLoginPsw.getInputInfo().length();
                    if (length < 6 || length > 16){
                        iecvLoginPsw.setErrorInfo(getString(R.string.set_psw_hint));
                    }
                }
                //安全码(先验证安全密码)找回密码
                if (!BaseUtil.isEmpty(securityCode)) {
                    if (BaseUtil.isEmpty(iecvLoginPsw.getInputInfo())) {
                        iecvLoginPsw.setErrorInfo(getString(R.string.input_login_psw));
                    } else if (!BaseUtil.equals(iecvLoginPsw.getInputInfo(), iecvConfirmPsw.getInputInfo())) {
                        iecvConfirmPsw.setErrorInfo(getString(R.string.double_psw_not_same));
                    }
                    if (!iecvLoginPsw.isHasError() && !iecvConfirmPsw.isHasError())
                        InterfaceTask.getInstance().getBackPsw(this, securityCode, iecvLoginPsw.getInputInfo(), account, "", "1");
                    //安全密码找回密码
                } else if (!BaseUtil.isEmpty(securityPsw)) {
                    if (BaseUtil.isEmpty(iecvLoginPsw.getInputInfo())) {
                        iecvLoginPsw.setErrorInfo(getString(R.string.input_login_psw));
                    } else if (!BaseUtil.equals(iecvLoginPsw.getInputInfo(), iecvConfirmPsw.getInputInfo())) {
                        iecvConfirmPsw.setErrorInfo(getString(R.string.double_psw_not_same));
                    }
                    if (!iecvLoginPsw.isHasError() && !iecvConfirmPsw.isHasError())
                        InterfaceTask.getInstance().getBackPsw(this, "", iecvLoginPsw.getInputInfo(), account, securityPsw, "0");
                } else {
                    if (BaseUtil.isEmpty(iecvOldPsw.getInputInfo())) {
                        iecvOldPsw.setErrorInfo(getString(R.string.input_old_psw));
                    }
                    if (BaseUtil.isEmpty(iecvLoginPsw.getInputInfo())) {
                        iecvLoginPsw.setErrorInfo(getString(R.string.input_login_psw));
                    } else if (!BaseUtil.equals(iecvLoginPsw.getInputInfo(), iecvConfirmPsw.getInputInfo())) {
                        iecvConfirmPsw.setErrorInfo(getString(R.string.double_psw_not_same));
                    }
                    if (!iecvOldPsw.isHasError() && !iecvLoginPsw.isHasError() && !iecvConfirmPsw.isHasError()) {
                        InterfaceTask.getInstance().changeLoginPsw(this, iecvLoginPsw.getInputInfo(), iecvOldPsw.getInputInfo());
                    }
                }
                break;
        }
    }
}

package com.sp.caitwo.ui.activity.securitycenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.xwjlibrary.widget.IconEditClearView;

public class SetSecurityPswActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvSecurityPsw;
    private IconEditClearView iecvConfirmPsw;
    private String securityCode;
    private String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_security_psw);

        initUI();

        securityCode = getIntent().getStringExtra("securityCode");
        account = getIntent().getStringExtra("account");

    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        iecvSecurityPsw = findViewById(R.id.iecv_security_psw);
        iecvConfirmPsw = findViewById(R.id.iecv_confirm_psw);

        tvTitle.setText(getString(R.string.set_saft_psw));
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.btn_sure:
                if (BaseUtil.isEmpty(iecvSecurityPsw.getInputInfo()) || iecvSecurityPsw.getInputInfo().length() != 6){
                    iecvSecurityPsw.setErrorInfo(getString(R.string.input_security_psw));
                }
                if (BaseUtil.isEmpty(iecvConfirmPsw.getInputInfo())){
                    iecvConfirmPsw.setErrorInfo(getString(R.string.input_again_saft_psw));
                }else if (!BaseUtil.isEmpty(iecvSecurityPsw.getInputInfo())){
                    if (!BaseUtil.equals(iecvSecurityPsw.getInputInfo(),iecvConfirmPsw.getInputInfo())) {
                        iecvConfirmPsw.setErrorInfo(getString(R.string.double_psw_not_same));
                    }
                }
                if (!iecvSecurityPsw.isHasError() && !iecvConfirmPsw.isHasError()){
                    if (BaseUtil.isEmpty(securityCode)) {
                        InterfaceTask.getInstance().setSecurityPsw(this,iecvSecurityPsw.getInputInfo());
                    }else {
                        InterfaceTask.getInstance().getBackSecurityPsw(this,securityCode,account,iecvSecurityPsw.getInputInfo());
                    }
                }
                break;
        }
    }
}

package com.sp.caitwo.ui.activity.sysm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sp.caitwo.App;
import com.sp.caitwo.bean.SystemConfigBean;
import com.sp.util.ProjectUtil;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;
import com.sp.xwjlibrary.widget.IconView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvInviteCode;
    private IconEditClearView iecvAccount;
    private IconEditClearView iecvSetPsw;
    private IconEditClearView iecvConfirm;
    private boolean isEnableInviteCode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initUI();

        InterfaceTask.getInstance().getSystemConfig(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                SystemConfigBean json = (SystemConfigBean) obj;
                isEnableInviteCode = ProjectUtil.functionIsNeed(json,"is_invite_code");
            }
        });
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        IconView iconCustomService = findViewById(R.id.icon_right);
        iecvInviteCode = findViewById(R.id.iecv_invite_code);
        iecvAccount = findViewById(R.id.iecv_account);
        iecvSetPsw = findViewById(R.id.iecv_set_psw);
        iecvConfirm = findViewById(R.id.iecv_confirm_psw);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
        iconCustomService.setOnClickListener(this);

        tvTitle.setText(getString(R.string.user_register));
        iconCustomService.setText(getString(R.string.custom_service));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        TextViewUtil.setSameWidth(new TextView[]{iecvInviteCode.getIconView(), iecvAccount.getIconView(), iecvSetPsw.getIconView(), iecvConfirm.getIconView()},layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.icon_right:
                if (!BaseUtil.isEmpty(App.customServerAddr)) {
                    Intent intent = new Intent(this, WebLoadActivity.class);
                    intent.putExtra(WebLoadActivity.URL, App.customServerAddr);
                    intent.putExtra(WebLoadActivity.TITLE, getString(R.string.custom_service));
                    startActivity(intent);
                }
                break;
            case R.id.btn_register:
                registerTask();
                break;
        }
    }

    private void registerTask() {
        String account = iecvAccount.getInputInfo();
        String psw = iecvSetPsw.getInputInfo();
        String confirmPsw = iecvConfirm.getInputInfo();
        String inviteCode = iecvInviteCode.getInputInfo();
        if (BaseUtil.isEmpty(account))
            iecvAccount.setErrorInfo(getString(R.string.input_account_hint));
        else {
//            if (!Character.isLetter(account.charAt(0)))
//                iecvAccount.setErrorInfo(getString(R.string.need_letter_start));
//            else
            if (!(account.length() >= 4 && account.length() <= 16))
                iecvAccount.setErrorInfo(getString(R.string.account_hint));
        }
        if (BaseUtil.isEmpty(psw))
            iecvSetPsw.setErrorInfo(getString(R.string.input_psw_hint));
        else {
            if (!(psw.length() >= 6 && psw.length() <= 16))
                iecvSetPsw.setErrorInfo(getString(R.string.set_psw_hint));
            else if (!BaseUtil.equals(psw, confirmPsw))
                iecvConfirm.setErrorInfo(getString(R.string.double_psw_not_same));
        }
        if (isEnableInviteCode) {
            if (BaseUtil.isEmpty(inviteCode)){
                iecvInviteCode.setErrorInfo(getString(R.string.input_invite_code));
            }else if (!(inviteCode.length() >= 6 && inviteCode.length() <= 8))
                iecvInviteCode.setErrorInfo(getString(R.string.invite_code_hint));
        }

        if (!iecvAccount.isHasError() && !iecvSetPsw.isHasError()
                && !iecvConfirm.isHasError() && !iecvInviteCode.isHasError())
            InterfaceTask.getInstance().register(this, account, psw, inviteCode);
    }
}

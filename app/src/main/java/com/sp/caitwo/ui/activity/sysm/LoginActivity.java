package com.sp.caitwo.ui.activity.sysm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sp.caitwo.App;
import com.sp.caitwo.MainActivity;
import com.sp.caitwo.ui.fragment.home.MineFragment;
import com.sp.xwjlibrary.util.AppUtil;
import com.sp.xwjlibrary.util.BaseUtil;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.ui.activity.securitycenter.GetBackPswActivity;
import com.sp.xwjlibrary.widget.IconEditClearView;
import com.sp.xwjlibrary.widget.IconView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvAccount;
    private IconEditClearView iecvPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        IconView iconCustomService = findViewById(R.id.icon_right);
        iecvAccount = findViewById(R.id.iecv_account);
        iecvPsw = findViewById(R.id.iecv_psw);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        iconCustomService.setOnClickListener(this);
        findViewById(R.id.tv_get_back_psw).setOnClickListener(this);
        findViewById(R.id.btn_immediately_login).setOnClickListener(this);
        findViewById(R.id.btn_free_register).setOnClickListener(this);

        tvTitle.setText(getString(R.string.login));
        iconCustomService.setText(getString(R.string.custom_service));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_top_back:
                setResult(Activity.RESULT_CANCELED);
                AppUtil.backAct(this,MainActivity.class);
                break;
            case R.id.icon_right:
                if (!BaseUtil.isEmpty(App.customServerAddr)) {
                    Intent intent = new Intent(this, WebLoadActivity.class);
                    intent.putExtra(WebLoadActivity.URL, App.customServerAddr);
                    intent.putExtra(WebLoadActivity.TITLE, getString(R.string.custom_service));
                    startActivity(intent);
                }
                break;
            case R.id.tv_get_back_psw:
                startActivity(new Intent(this,GetBackPswActivity.class));
                break;
            case R.id.btn_immediately_login:
                String account = iecvAccount.getInputInfo();
                String psw = iecvPsw.getInputInfo();
                if (BaseUtil.isEmpty(account))
                    iecvAccount.setErrorInfo(getString(R.string.input_account_hint));
                if (BaseUtil.isEmpty(psw))
                    iecvPsw.setErrorInfo(getString(R.string.input_psw_hint));
                if (!iecvAccount.isHasError() && !iecvPsw.isHasError())
                    InterfaceTask.getInstance().login(this, account, psw);
                break;
            case R.id.btn_free_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            setResult(Activity.RESULT_CANCELED);
            AppUtil.backAct(this,MainActivity.class);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

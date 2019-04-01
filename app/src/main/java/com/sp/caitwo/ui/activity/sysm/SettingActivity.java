package com.sp.caitwo.ui.activity.sysm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sp.xwjlibrary.util.AppUtil;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.ui.activity.securitycenter.BankCardManageActivity;
import com.sp.caitwo.ui.activity.securitycenter.SecurityCenterActivity;
import com.sp.caitwo.ui.activity.securitycenter.SetSecurityPswActivity;
import com.sp.xwjlibrary.util.ACache;
import com.sp.xwjlibrary.util.PhoneUtil;
import com.sp.xwjlibrary.util.ToastUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.lly_security_center).setOnClickListener(this);
        findViewById(R.id.lly_bank_card_manage).setOnClickListener(this);
        findViewById(R.id.lly_clear_cache).setOnClickListener(this);
        findViewById(R.id.lly_help_guide).setOnClickListener(this);
        findViewById(R.id.lly_about).setOnClickListener(this);
        findViewById(R.id.btn_quit_login).setOnClickListener(this);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvCacheSize = findViewById(R.id.tv_cache_size);
        tvTitle.setText(getString(R.string.setting));
        try {
            tvCacheSize.setText(PhoneUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.lly_security_center:
                startActivity(new Intent(this,SecurityCenterActivity.class));
                break;
            case R.id.lly_bank_card_manage:
                if (BaseUtil.isEmpty(App.userInfoBean.getData().getWithdrawals_password())){
                    startActivity(new Intent(this,SetSecurityPswActivity.class));
                }else {
                    startActivity(new Intent(this,BankCardManageActivity.class));
                }
                break;
            case R.id.lly_clear_cache:
                PhoneUtil.clearAllCache(this);
                tvCacheSize.setText("");
                ToastUtil.Toast(this,"清理成功");
                break;
            case R.id.lly_help_guide:
                startActivity(new Intent(this,HelpGuideActivity.class));
                break;
            case R.id.lly_about:
                startActivity(new Intent(this,AboutActivity.class));
                break;
            case R.id.btn_quit_login:
                App.userInfoBean.getData().setAccess_token("");
                ACache.get(this).put("UserInfo", App.userInfoBean);
                AppUtil.logoutApp(this,LoginActivity.class);
                break;
        }
    }
}

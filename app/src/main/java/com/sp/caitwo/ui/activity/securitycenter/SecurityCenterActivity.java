package com.sp.caitwo.ui.activity.securitycenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.xwjlibrary.widget.IconView;

public class SecurityCenterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_center);

        initUI();
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        TextView tvLastLoginTime = findViewById(R.id.tv_last_login_time);
        TextView tvLastLoginLocal = findViewById(R.id.tv_last_login_local);
        tvTitle.setText(getString(R.string.security_center));
        tvLastLoginTime.setText(App.userInfoBean.getData().getLogin_time());
        tvLastLoginLocal.setText(App.userInfoBean.getData().getLogin_address());

        IconView iconStarPos1 = findViewById(R.id.icon_star_pos1);
        IconView iconStarPos2 = findViewById(R.id.icon_star_pos2);
        IconView iconStarPos3 = findViewById(R.id.icon_star_pos3);
        IconView iconStarPos4 = findViewById(R.id.icon_star_pos4);
        IconView iconStarPos5 = findViewById(R.id.icon_star_pos5);
        IconView[] iconViews = new IconView[]{iconStarPos1,iconStarPos2,iconStarPos3,iconStarPos4,iconStarPos5};
        Integer securityLevel = Integer.valueOf(App.userInfoBean.getData().getSecurity_level());
        for (Integer i = 0; i < securityLevel; i++) {
            iconViews[i].setTextColor(Color.parseColor("#FFD556"));
        }

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.lly_change_login_psw).setOnClickListener(this);
        findViewById(R.id.tv_change_psw).setOnClickListener(this);
        findViewById(R.id.lly_set_security_psw).setOnClickListener(this);
        findViewById(R.id.lly_set_security_question).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.lly_change_login_psw:
            case R.id.tv_change_psw:
                startActivity(new Intent(this,ChangePswActivity.class));
                break;
            case R.id.lly_set_security_psw:
                if (BaseUtil.isEmpty(App.userInfoBean.getData().getWithdrawals_password())) {
                    startActivity(new Intent(this,SetSecurityPswActivity.class));
                }else {
                    Intent intent = new Intent(this, CheckSecurityPswActivity.class);
                    intent.putExtra("Class","set");
                    intent.putExtra("account",App.userInfoBean.getData().getAccount());
                    startActivity(intent);
                }
                break;
            case R.id.lly_set_security_question:
                if (BaseUtil.equals(App.userInfoBean.getData().getIs_set_security_answer(),"0")) {
                    startActivity(new Intent(this,SetSecurityQuestionActivity.class));
                }else {
                    Intent intent = new Intent(this, CheckSecurityQuestionActivity.class);
                    intent.putExtra("Class","set");
                    intent.putExtra("account",App.userInfoBean.getData().getAccount());
                    startActivity(intent);
                }
                break;
        }
    }
}

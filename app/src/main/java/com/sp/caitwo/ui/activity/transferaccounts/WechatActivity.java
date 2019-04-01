package com.sp.caitwo.ui.activity.transferaccounts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.xwjlibrary.widget.IconEditClearView;

public class WechatActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvRechargeMoney;
    private IconEditClearView iecvRemarkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);

        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.wechat_pay));
        iecvRechargeMoney = findViewById(R.id.iecv_recharge_money);
        iecvRemarkInfo = findViewById(R.id.iecv_remark_info);

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

                break;
        }
    }
}

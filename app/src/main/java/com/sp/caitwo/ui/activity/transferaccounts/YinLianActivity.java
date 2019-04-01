package com.sp.caitwo.ui.activity.transferaccounts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.xwjlibrary.widget.IconEditClearView;

public class YinLianActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSelWay;
    private IconEditClearView iecvRechargeMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_lian);

        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.yinlian_pay));
        tvSelWay = findViewById(R.id.tv_sel_way);
        iecvRechargeMoney = findViewById(R.id.iecv_recharge_money);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.lly_pay_way).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.lly_pay_way:

                break;
            case R.id.btn_sure:

                break;
        }
    }
}

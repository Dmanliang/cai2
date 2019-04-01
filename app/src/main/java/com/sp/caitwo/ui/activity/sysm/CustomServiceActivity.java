package com.sp.caitwo.ui.activity.sysm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.xwjlibrary.util.BaseUtil;

public class CustomServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_service);

        findViewById(R.id.iv_top_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_contact_custom_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!BaseUtil.isEmpty(App.customServerAddr)) {
                    Intent intent = new Intent(getBaseContext(), WebLoadActivity.class);
                    intent.putExtra(WebLoadActivity.URL, App.customServerAddr);
                    intent.putExtra(WebLoadActivity.TITLE, getString(R.string.custom_service));
                    startActivity(intent);
                }
            }
        });
        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.custom_service));
    }
}

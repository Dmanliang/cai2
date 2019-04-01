package com.sp.caitwo.ui.activity.agentcenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;

public class AgentExplainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_explain);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.agent_introduction));

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}

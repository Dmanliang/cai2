package com.sp.caitwo.ui.activity.sysm;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.MainActivity;
import com.sp.caitwo.R;

public class LauncherActivity extends AppCompatActivity {

    private ImageView ivLauncherPage;
    private TextView tvSkip;
    private int time = 5;
    private boolean isAlreadIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        tvSkip = findViewById(R.id.tv_launcher_skip);
        ivLauncherPage = findViewById(R.id.iv_launcher_page);

        ObjectAnimator.ofFloat(ivLauncherPage,"alpha",0,1).setDuration(3000).start();

        //跳过
        tvSkip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isAlreadIntent = true;
                startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                finish();
            }
        });
        tvSkip.postDelayed(new Runnable(){
            @Override
            public void run() {
                if(isAlreadIntent){
                    return;
                }
                if(time == 0){
                    startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                    finish();
                    return;
                }
                tvSkip.setText(time + "s" + "\n跳过");
                time --;
                tvSkip.postDelayed(this, 1000);
            }
        }, 0);

    }
}

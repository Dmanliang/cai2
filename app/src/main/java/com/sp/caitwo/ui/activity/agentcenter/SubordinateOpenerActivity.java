package com.sp.caitwo.ui.activity.agentcenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.fragment.agentcenter.InviteCodeFrag;
import com.sp.caitwo.ui.fragment.agentcenter.SubordinateOpenerFrag;

public class SubordinateOpenerActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgTitle;
    private SubordinateOpenerFrag subordinateOpenerFrag;
    private InviteCodeFrag inviteCodeFrag;
    private FragmentManager fm;
    private String registerUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subordinate_opener);
        registerUrl = getIntent().getStringExtra("registerUrl");
        initUI();
        addFragment();
        rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getChildAt(0).getId() == checkedId){
                    fm.beginTransaction().show(subordinateOpenerFrag).hide(inviteCodeFrag).commit();
                }else {
                    fm.beginTransaction().show(inviteCodeFrag).hide(subordinateOpenerFrag).commit();
                }
            }
        });
    }

    private void addFragment() {
        subordinateOpenerFrag = new SubordinateOpenerFrag();
        inviteCodeFrag = new InviteCodeFrag();
        Bundle bundle = new Bundle();
        bundle.putString("registerUrl",registerUrl);
        inviteCodeFrag.setArguments(bundle);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fly_container,subordinateOpenerFrag).addToBackStack(null).commit();
        fm.beginTransaction().add(R.id.fly_container,inviteCodeFrag).addToBackStack(null).commit();
        fm.beginTransaction().show(subordinateOpenerFrag).hide(inviteCodeFrag).commit();
    }

    private void initUI() {
        rgTitle = findViewById(R.id.rg_title);
        RadioButton rbSubordinateOpener = (RadioButton) rgTitle.getChildAt(0);
        RadioButton rbInvite = (RadioButton) rgTitle.getChildAt(1);
        rbSubordinateOpener.setText(getString(R.string.subordinate_opener));
        rbInvite.setText(getString(R.string.invite_code));
        findViewById(R.id.iv_top_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return false;
    }
}

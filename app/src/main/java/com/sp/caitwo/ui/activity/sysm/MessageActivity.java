package com.sp.caitwo.ui.activity.sysm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.fragment.message.MessageFrag;
import com.sp.caitwo.ui.fragment.message.NoticeFrag;

import java.util.List;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup rgTitle;
    private FragmentManager fm;
    private MessageFrag messageFrag;
    private NoticeFrag noticeFrag;
    private boolean isFirstIntent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //复用代理报表
        setContentView(R.layout.activity_agent_report_froms);

        initUI();

        addFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFirstIntent) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragments) {
                fragment.onHiddenChanged(fragment.isHidden());
            }
        }else {
            isFirstIntent = false;
        }
    }

    private void addFragment() {
        messageFrag = new MessageFrag();
        noticeFrag = new NoticeFrag();
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fly_container,messageFrag).addToBackStack(null).commit();
        fm.beginTransaction().add(R.id.fly_container,noticeFrag).addToBackStack(null).commit();
        fm.beginTransaction().show(messageFrag).hide(noticeFrag).commit();
    }

    private void initUI() {
        rgTitle = findViewById(R.id.rg_title);
        RadioButton rbMessage = (RadioButton) rgTitle.getChildAt(0);
        RadioButton rbNotice = (RadioButton) rgTitle.getChildAt(1);
        rbMessage.setText(getString(R.string.message));
        rbNotice.setText(getString(R.string.notice));

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.lly_subordinate_bet_search).setVisibility(View.GONE);
        rgTitle.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.getChildAt(0).getId() == checkedId){
            fm.beginTransaction().show(messageFrag).hide(noticeFrag).commit();
        }else {
            fm.beginTransaction().show(noticeFrag).hide(messageFrag).commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

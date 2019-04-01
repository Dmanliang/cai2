package com.sp.caitwo.ui.activity.UserInfo;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.fragment.UserInfo.LevelHonorFrag;
import com.sp.caitwo.ui.fragment.UserInfo.PersonInfoFrag;
import com.sp.xwjlibrary.util.BaseUtil;

public class PersonDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private RadioGroup rgTitle;
    private PersonInfoFrag personInfoFrag;
    private LevelHonorFrag levelHonorFrag;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //复用下级开户
        setContentView(R.layout.activity_subordinate_opener);

        initUI();

        addFragment();

        rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getChildAt(0).getId() == checkedId){
                    fm.beginTransaction().show(personInfoFrag).hide(levelHonorFrag).commit();
                }else {
                    fm.beginTransaction().show(levelHonorFrag).hide(personInfoFrag).commit();
                }
            }
        });

        String fragment = getIntent().getStringExtra("fragment");
        if (!BaseUtil.isEmpty(fragment)) {
            rgTitle.check(rgTitle.getChildAt(1).getId());
        }
    }

    private void addFragment() {
        personInfoFrag = new PersonInfoFrag();
        levelHonorFrag = new LevelHonorFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("userDetail",getIntent().getSerializableExtra("userDetail"));
        personInfoFrag.setArguments(bundle);
        levelHonorFrag.setArguments(bundle);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fly_container, personInfoFrag).addToBackStack(null).commit();
        fm.beginTransaction().add(R.id.fly_container, levelHonorFrag).addToBackStack(null).commit();
        fm.beginTransaction().show(personInfoFrag).hide(levelHonorFrag).commit();
    }

    private void initUI() {
        rgTitle = findViewById(R.id.rg_title);
        RadioButton rbRersonInfo = (RadioButton) rgTitle.getChildAt(0);
        RadioButton rbLevelHonor = (RadioButton) rgTitle.getChildAt(1);
        rbRersonInfo.setText(getString(R.string.person_info));
        rbLevelHonor.setText(getString(R.string.level_honor));

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

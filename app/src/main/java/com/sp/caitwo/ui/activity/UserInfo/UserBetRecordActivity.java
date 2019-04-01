package com.sp.caitwo.ui.activity.UserInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.ui.fragment.UserInfo.UserBetRecordFrag;
import com.sp.caitwo.ui.fragment.UserInfo.UserTraceNumRecordFrag;
import com.sp.xwjlibrary.dialog.LoadDialog;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class UserBetRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgTitle;
    private FragmentManager fm;
    private UserBetRecordFrag betRecordFrag;
    private UserTraceNumRecordFrag trackNumRecordFrag;
    private PopupDialog menu2ViewDialog;
    private RadioGroup rgSubTime;
    private String currentDate;
    private String subStartDate;
    private String subEndDate;
    private String gameType = "0";
    private RadioGroup[] rgItemChild;
    public LoadDialog mLoadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //复用代理报表
        setContentView(R.layout.activity_agent_report_froms);
        subStartDate = subEndDate = currentDate = TimeUtil.getCurrTime("yyyy-MM-dd");
        initUI();
        addFragment();
        initDialog();
        rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getChildAt(0).getId() == checkedId) {
                    fm.beginTransaction().show(betRecordFrag).hide(trackNumRecordFrag).commit();
                } else {
                    fm.beginTransaction().show(trackNumRecordFrag).hide(betRecordFrag).commit();
                }
            }
        });
    }

    private void initDialog() {
        mLoadDialog = new LoadDialog(this,"Loading", Constants.drawableResids);
        mLoadDialog.show();

        View menu2View = View.inflate(this, R.layout.subordinate_bet_detail_filter, null);
        rgSubTime = menu2View.findViewById(R.id.rg_time);
        RadioGroup rgWanfaFilter = menu2View.findViewById(R.id.rg_wanfa_filter);
        List<HomeWanFaBean.DataBean> wanFaDatas = new ArrayList<>(App.homeWanFaBean.getData());
        HomeWanFaBean.DataBean dataBean = new HomeWanFaBean.DataBean();
        dataBean.setGame_type(0);
        dataBean.setName(getString(R.string.all));
        wanFaDatas.add(0, dataBean);
        int row = (wanFaDatas.size() + 4) / 4;
        rgItemChild = new RadioGroup[row];
        for (int i = 0; i < row; i++) {
            RadioGroup rg = (RadioGroup) View.inflate(this, R.layout.item_wanfa_filter, null);
            int column = rg.getChildCount();
            for (int j = 0; j < column; j++) {
                RadioButton child = (RadioButton) rg.getChildAt(j);
                int columnStartIndex = i * 4 + j;
                if (columnStartIndex < wanFaDatas.size()) {
                    child.setText(wanFaDatas.get(columnStartIndex).getName());
                    child.setTag(String.valueOf(wanFaDatas.get(columnStartIndex).getGame_type()));
                    if (columnStartIndex == 0) {
                        rg.check(rg.getChildAt(0).getId());
                    }
                    rg.setOnCheckedChangeListener(new RadioGroupListener());
                } else {
                    child.setVisibility(View.INVISIBLE);
                }
            }
            rgItemChild[i] = rg;
            RadioGroup.LayoutParams rl = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, 1);
            rgWanfaFilter.addView(rg, rl);
        }

        rgSubTime.setOnCheckedChangeListener(new RadioGroupListener());
        menu2View.findViewById(R.id.btn_sure).setOnClickListener(this);
        menu2ViewDialog = new PopupDialog(this, menu2View);
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group != null && checkedId != -1) {
                if (rgSubTime == group) {
                    setSubMenu(checkedId);
                } else {
                    for (RadioGroup radioGroup : rgItemChild) {
                        if (radioGroup == group) {
                            gameType = (String) radioGroup.findViewById(checkedId).getTag();
                        } else {
                            radioGroup.clearCheck();
                        }
                    }
                }
                group.check(checkedId);
            }
        }

        private void setSubMenu(int checkedId) {
            switch (checkedId) {
                case R.id.rb_today:
                    subStartDate = subEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    break;
                case R.id.rb_yesterday:
                    subStartDate = subEndDate = TimeUtil.setDate(currentDate, "yyyy-MM-dd", "yyyy-MM-dd", 0, 0, -1, false);
                    break;
                case R.id.rb_seven_days:
                    subEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    subStartDate = TimeUtil.setDate(currentDate, "yyyy-MM-dd", "yyyy-MM-dd", 0, 0, -7, false);
                    break;
            }
        }
    }

    private void addFragment() {
        betRecordFrag = new UserBetRecordFrag();
        trackNumRecordFrag = new UserTraceNumRecordFrag();
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fly_container, betRecordFrag).addToBackStack(null).commit();
        fm.beginTransaction().add(R.id.fly_container, trackNumRecordFrag).addToBackStack(null).commit();
        fm.beginTransaction().show(betRecordFrag).hide(trackNumRecordFrag).commit();
    }

    private void initUI() {
        rgTitle = findViewById(R.id.rg_title);
        RadioButton rbBet = (RadioButton) rgTitle.getChildAt(0);
        RadioButton rbTrackNum = (RadioButton) rgTitle.getChildAt(1);
        TextView tvFilterMenu = findViewById(R.id.tv_top_right);
        rbBet.setText(getString(R.string.bet));
        rbTrackNum.setText(getString(R.string.track_num));
        tvFilterMenu.setText(getString(R.string.filter));

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.lly_subordinate_bet_search).setVisibility(View.GONE);
        tvFilterMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.tv_top_right:
                menu2ViewDialog.show(v);
                break;
            case R.id.btn_sure:
                mLoadDialog.show();
                betRecordFrag.setGameType(gameType);
                betRecordFrag.setSubStartDate(subStartDate);
                betRecordFrag.setSubEndDate(subEndDate);
                betRecordFrag.setPage(1);
                betRecordFrag.setData();
                trackNumRecordFrag.setGameType(gameType);
                trackNumRecordFrag.setSubStartDate(subStartDate);
                trackNumRecordFrag.setSubEndDate(subEndDate);
                trackNumRecordFrag.setPage(1);
                trackNumRecordFrag.setData();
                menu2ViewDialog.dismiss();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }
}

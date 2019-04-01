package com.sp.caitwo.ui.activity.agentcenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.fragment.agentcenter.SubordinateReportFormsFrag;
import com.sp.caitwo.ui.fragment.agentcenter.TeamReportFormsFrag;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.myinterface.OnIconEditClearListener;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TimeUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

public class AgentReportFormsActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgTeamMenu;
    private RadioGroup rgTitle;
    private TeamReportFormsFrag teamReportFormsFrag;
    private SubordinateReportFormsFrag subordinateReportFormsFrag;
    private FragmentManager fm;
    private TextView tvFilter;
    private PopupDialog menuViewDialog;
    private PopupDialog menu2ViewDialog;
    private String currentDate;
    private String teamStartDate;
    private String teamEndDate;
    private String subStartDate;
    private String subEndDate;
    private String teamReportFormsFilter;
    private RadioGroup rgSubTime;
    private RadioGroup rgSort11;
    private RadioGroup rgSort12;
    private RadioGroup rgSortType;
    private String sort = "betMoney";
    private String sortType = "asc";
    private IconEditClearView iecvSearchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_report_froms);

        initUI();

        addFragment();

        setMenuDialog();

        initData();
    }

    private void initData() {
        teamReportFormsFilter = getString(R.string.today);
        teamStartDate = teamEndDate = subStartDate = subEndDate = currentDate = TimeUtil.getCurrTime("yyyy-MM-dd");
        teamReportFormsFrag.setStartDate(teamStartDate);
        teamReportFormsFrag.setEndDate(teamEndDate);
        teamReportFormsFrag.setData();
        subordinateReportFormsFrag.setStartDate(subStartDate);
        subordinateReportFormsFrag.setEndDate(subEndDate);
        subordinateReportFormsFrag.setSort(sort);
        subordinateReportFormsFrag.setSortType(sortType);
    }

    /**
     * 下级报表中--他的团队报表
     */
    public void hisReportData(String account){
        rgTitle.check(rgTitle.getChildAt(0).getId());
        teamReportFormsFrag.setAccount(account);
        subordinateReportFormsFrag.setAccount(account);
        iecvSearchKey.setInputInfo(account);
        teamReportFormsFrag.setData();
        subordinateReportFormsFrag.setPage(1);
        subordinateReportFormsFrag.setData();
    }

    private void setMenuDialog() {
        View menuView = View.inflate(this, R.layout.menu_agent_report_froms, null);
        rgTeamMenu = menuView.findViewById(R.id.rg_menu);
        menuViewDialog = new PopupDialog(this, menuView);
        rgTeamMenu.setOnCheckedChangeListener(new RadioGroupListener(menuView));
        View menu2View = View.inflate(this, R.layout.menu_agent_report_froms_filter, null);
        rgSubTime = menu2View.findViewById(R.id.rg_time);
        rgSort11 = menu2View.findViewById(R.id.rg_sort11);
        rgSort12 = menu2View.findViewById(R.id.rg_sort12);
        rgSortType = menu2View.findViewById(R.id.rg_sortType);
        rgSubTime.setOnCheckedChangeListener(new RadioGroupListener(menu2View));
        rgSort11.setOnCheckedChangeListener(new RadioGroupListener(menu2View));
        rgSort12.setOnCheckedChangeListener(new RadioGroupListener(menu2View));
        rgSortType.setOnCheckedChangeListener(new RadioGroupListener(menu2View));
        menu2View.findViewById(R.id.btn_sure).setOnClickListener(this);
        menu2ViewDialog = new PopupDialog(this, menu2View);
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener{
        private View menu2View;

        public RadioGroupListener(View menu2View) {
            this.menu2View = menu2View;
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group != null && checkedId != -1) {
                if (rgTeamMenu == group) {
                    setTeamMenu(checkedId);
                } else if (rgSubTime == group) {
                    setSubMenu(checkedId);
                } else if (rgSort11 == group) {
                    rgSort12.clearCheck();
                    sort = (String) menu2View.findViewById(checkedId).getTag();
                } else if (rgSort12 == group) {
                    rgSort11.clearCheck();
                    sort = (String) menu2View.findViewById(checkedId).getTag();
                } else if (rgSortType == group) {
                    sortType = (String) menu2View.findViewById(checkedId).getTag();
                }
                group.check(checkedId);
            }
        }

        private void setSubMenu(int checkedId) {
            switch(checkedId){
                case R.id.rb_today:
                    subStartDate = subEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    break;
                case R.id.rb_yesterday:
                    subStartDate = subEndDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-1,false);
                    break;
                case R.id.rb_seven_days:
                    subEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    subStartDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-7,false);
                    break;
            }
        }

        private void setTeamMenu(int checkedId) {
            switch(checkedId){
                case R.id.rb_today:
                    tvFilter.setText(getString(R.string.today));
                    teamStartDate = teamEndDate = TimeUtil.getCurrTime("yyyy-MM-dd");
                    break;
                case R.id.rb_yesterday:
                    tvFilter.setText(getString(R.string.yesterday));
                    teamStartDate = teamEndDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,-1,false);
                    break;
                case R.id.rb_current_month:
                    tvFilter.setText(getString(R.string.current_month));
                    teamStartDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,0,true);
                    teamEndDate = currentDate;
                    break;
                case R.id.rb_last_month:
                    teamStartDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,-1,0,true);
                    teamEndDate = TimeUtil.setDate(currentDate,"yyyy-MM-dd","yyyy-MM-dd",0,0,0,false);
                    tvFilter.setText(getString(R.string.last_month));
                    break;
            }
            teamReportFormsFrag.setStartDate(teamStartDate);
            teamReportFormsFrag.setEndDate(teamEndDate);
            teamReportFormsFrag.setData();
            teamReportFormsFilter = tvFilter.getText().toString();
            menuViewDialog.dismiss();
        }
    }

    private void addFragment() {
        teamReportFormsFrag = new TeamReportFormsFrag();
        subordinateReportFormsFrag = new SubordinateReportFormsFrag();
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fly_container, teamReportFormsFrag).addToBackStack(null).commit();
        fm.beginTransaction().add(R.id.fly_container, subordinateReportFormsFrag).addToBackStack(null).commit();
        fm.beginTransaction().show(teamReportFormsFrag).hide(subordinateReportFormsFrag).commit();
        rgTitle.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(android.widget.RadioGroup group, int checkedId) {
                if (group.getChildAt(0).getId() == checkedId){
                    fm.beginTransaction().show(teamReportFormsFrag).hide(subordinateReportFormsFrag).commit();
                    tvFilter.setText(teamReportFormsFilter);
                }else {
                    fm.beginTransaction().show(subordinateReportFormsFrag).hide(teamReportFormsFrag).commit();
                    tvFilter.setText(getString(R.string.filter));
                }
            }
        });
    }

    private void initUI() {
        rgTitle = findViewById(R.id.rg_title);
        RadioButton rbTeamReportForms = (RadioButton) rgTitle.getChildAt(0);
        RadioButton rbSubordinateReportForms = (RadioButton) rgTitle.getChildAt(1);
        rbTeamReportForms.setText(getString(R.string.team_report_forms));
        rbSubordinateReportForms.setText(getString(R.string.subordinate_report_forms));
        tvFilter = findViewById(R.id.tv_top_right);
        tvFilter.setText(getString(R.string.today));

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        tvFilter.setOnClickListener(this);

        iecvSearchKey = findViewById(R.id.iecv_search_key);
        findViewById(R.id.tv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamReportFormsFrag.setAccount(iecvSearchKey.getInputInfo());
                subordinateReportFormsFrag.setAccount(iecvSearchKey.getInputInfo());
                teamReportFormsFrag.setData();
                subordinateReportFormsFrag.setPage(1);
                subordinateReportFormsFrag.setData();
            }
        });
        iecvSearchKey.setOnIconEditClearListener(new OnIconEditClearListener() {
            @Override
            public void OnTextChanged(CharSequence s, int start, int before, int count) {
                if (BaseUtil.isEmpty(s.toString().trim())){
                    teamReportFormsFrag.setAccount("");
                    subordinateReportFormsFrag.setAccount("");
                    teamReportFormsFrag.setData();
                    subordinateReportFormsFrag.setPage(1);
                    subordinateReportFormsFrag.setData();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.tv_top_right:
                if (rgTitle.getCheckedRadioButtonId() == rgTitle.getChildAt(0).getId())
                    menuViewDialog.show(v);
                else
                    menu2ViewDialog.show(v);
                break;
            case R.id.btn_sure:
                subordinateReportFormsFrag.setPage(1);
                subordinateReportFormsFrag.setStartDate(subStartDate);
                subordinateReportFormsFrag.setEndDate(subEndDate);
                subordinateReportFormsFrag.setSort(sort);
                subordinateReportFormsFrag.setSortType(sortType);
                subordinateReportFormsFrag.setData();
                menu2ViewDialog.dismiss();
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

package com.sp.caitwo.ui.activity.lottery;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.ui.adapter.LongDragonFragmentAdpter;
import com.sp.caitwo.ui.fragment.home.CathecticFragment;
import com.sp.caitwo.ui.fragment.lottery.CathecticRecordFragment;
import com.sp.xwjlibrary.widget.IconView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;


public class LongDragonActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tab_layout;
    private ViewPager longdragon_viewpager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private View longdragonview;
    private PopupWindow longdragonWindow;
    private RelativeLayout ll_zdl;
    private boolean isShowMoney = false;
    private double allPoint;
    private ImageView icon_remainmoney;
    private TextView lottery_remainmoney;
    public static final String UPDATE_INFO = "update_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_long_dragon);
        EventBus.getDefault().register(this);
        initUI();
        loadUserInfo();
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        IconView iconCustomService = findViewById(R.id.icon_right);
        Drawable rightDrawable = getResources().getDrawable(R.drawable.sp_btn_help_1);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        iconCustomService.setCompoundDrawables(null, null, rightDrawable, null);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        iconCustomService.setOnClickListener(this);
        tab_layout = findViewById(R.id.tab_layout);
        longdragon_viewpager = findViewById(R.id.longdragon_viewpager);
        tvTitle.setText(getString(R.string.longfrageon));
        initFragmet();

    }

    public void initFragmet() {
        fragmentList.add(new CathecticFragment());
        fragmentList.add(new CathecticRecordFragment());
        list_title.add("我要投注");
        list_title.add("最近投注");
        longdragon_viewpager.setAdapter(new LongDragonFragmentAdpter(getSupportFragmentManager(), LongDragonActivity.this, fragmentList, list_title));
        tab_layout.setupWithViewPager(longdragon_viewpager);
        icon_remainmoney = findViewById(R.id.icon_remainmoney);
        lottery_remainmoney = findViewById(R.id.lottery_remainmoney);
        findViewById(R.id.lly_balance).setOnClickListener(this);
        setLotteryExplain();
        tab_layout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    getCathecticRecord();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void getCathecticRecord() {
        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof CathecticRecordFragment) {
                ((CathecticRecordFragment) fragment).getGame_type(0);
            }
        }
    }


    public void loadUserInfo() {
        InterfaceTask.getInstance().createUserInfo(LongDragonActivity.this, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                allPoint = App.userInfoBean.getData().getPoint();
                if (isShowMoney) {
                    lottery_remainmoney.setText("余额:" + allPoint);
                }
            }
        });
    }

    public void setLotteryExplain() {
        int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        longdragonview = this.getLayoutInflater().inflate(R.layout.layout_longdragon_explain, null);
        longdragonview.setSystemUiVisibility(flag);
        longdragonWindow = new PopupWindow(longdragonview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        longdragonWindow.setTouchable(true);
        longdragonWindow.setOutsideTouchable(true);
        longdragonWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        longdragonWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        longdragonWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80000000")));
        longdragonWindow.update();
        if (longdragonWindow.isShowing()) {
            longdragonWindow.dismiss();
        } else {
            longdragonWindow.setFocusable(true);
            longdragonWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    longdragonWindow.setFocusable(false);
                    return false;
                }
            });
        }
        ll_zdl = longdragonview.findViewById(R.id.ll_zdl);
        ll_zdl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.ll_zdl:
                longdragonWindow.dismiss();
                break;
            case R.id.icon_right:
                longdragonWindow.showAtLocation(longdragonview, Gravity.CENTER, 0, 0);
                break;
            case R.id.lly_balance:
                showMoney();
                break;

        }
    }

    public void showMoney() {
        if (isShowMoney) {
            isShowMoney = false;
            icon_remainmoney.setVisibility(View.VISIBLE);
            lottery_remainmoney.setText("余额:");
        } else {
            isShowMoney = true;
            icon_remainmoney.setVisibility(View.GONE);
            lottery_remainmoney.setText("余额:" + allPoint);
            loadUserInfo();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void Event(String messageEvent) {
        if (messageEvent.equals(UPDATE_INFO)) {
            loadUserInfo();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

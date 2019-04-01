package com.sp.caitwo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.MessageBean;
import com.sp.caitwo.bean.UserInfoBean;
import com.sp.caitwo.ui.fragment.home.ActivityFragment;
import com.sp.caitwo.ui.fragment.home.FindFragment;
import com.sp.caitwo.ui.fragment.home.HomeFragment;
import com.sp.caitwo.ui.fragment.home.MineFragment;
import com.sp.xwjlibrary.dialog.ViewDialog;
import com.sp.xwjlibrary.util.ACache;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.DoubleClickExit;
import com.sp.xwjlibrary.util.WebViewUtil;
import com.sp.xwjlibrary.widget.TabHostView;

public class MainActivity extends AppCompatActivity {

    private TabHostView thvMain;
    private TextView tvNoticeTitle;
    private WebView wvNoticeContent;
    private ViewDialog viewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initUserInfo(this);
        initBankImgRes();
        //更新版本
        InterfaceTask.getInstance().updateApk(MainActivity.this);
        //获取客服地址
        InterfaceTask.getInstance().getCustomServerAddr();

        notice();
    }

    private void notice() {
        View view = View.inflate(this,R.layout.dialog_homepage_notice, null);
        tvNoticeTitle = view.findViewById(R.id.tv_notice_title);
        wvNoticeContent = view.findViewById(R.id.wv_notice_content);
        viewDialog = new ViewDialog(this, view);
        viewDialog.setBgTransparent();
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        viewDialog.setBothSideSpace(outMetrics.widthPixels / 4);
        view.findViewById(R.id.tv_i_know).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDialog.dismiss();
            }
        });
        InterfaceTask.getInstance().getNoticeMsg(1, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                MessageBean messageBean = (MessageBean) obj;
                if (!BaseUtil.isEmpty(messageBean.getData()) && !BaseUtil.isEmpty(messageBean.getData().getList())){
                    for (int i = 0; i < messageBean.getData().getList().size(); i++) {
                        MessageBean.DataBean.ListBean listBean = messageBean.getData().getList().get(i);
                        if (listBean.getNotice_type() == 2) {
                            tvNoticeTitle.setText(messageBean.getData().getList().get(i).getTitle());
                            WebViewUtil.loadHtmlText(wvNoticeContent,messageBean.getData().getList().get(i).getContent());
                            viewDialog.show();
                        }
                    }
                }
            }
        });
    }

    private void initUserInfo(Context context) {
        UserInfoBean userInfo = (UserInfoBean) ACache.get(context).getAsObject("UserInfo");
        if (userInfo != null) {
            App.userInfoBean = userInfo;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ACache.get(this).put("UserInfo", App.userInfoBean);
    }

    private void initUI() {
        thvMain = findViewById(R.id.thv_main);
        thvMain.addTab(this,getString(R.string.icon_homepage),"",getString(R.string.homepage),HomeFragment.class);
        thvMain.addTab(this,getString(R.string.icon_activity),"",getString(R.string.activity),ActivityFragment.class);
        thvMain.addTab(this,getString(R.string.icon_find),"",getString(R.string.find), FindFragment.class);
        thvMain.addTab(this,getString(R.string.icon_mine),"",getString(R.string.mine),MineFragment.class);
    }

    private void initBankImgRes() {
        String[] name = {"工商银行","建设银行","农业银行","民生银行","招商银行","交通银行","中国银行","邮政银行",
                "中信银行","兴业银行","华夏银行","浦发银行","广发银行","平安银行","光大银行","农村信用社","其他"};
        int[] id = new int[]{R.drawable.gongshangyinhang,R.drawable.jiansheyinhang,R.drawable.nongyeyinhang,
                R.drawable.minshengyinhang,R.drawable.zhaoshangyinhang,R.drawable.jiaotongyinhang,
                R.drawable.zhongguoyinhang,R.drawable.youchuyinhang,R.drawable.zhongxinyinhang,
                R.drawable.xingye,R.drawable.huaxiayinhang,R.drawable.pufayinhang,R.drawable.guangfayinhang,
                R.drawable.pinganyinhang,R.drawable.guangdayinhang,R.drawable.nongcunxinyongshe,R.drawable.yinlian
        };
        for (int i = 0; i < name.length; i++) {
            Constants.bankMap.put(name[i],id[i]);
            Constants.bankName.add(name[i]);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return DoubleClickExit.isExit(this,keyCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_CANCELED){
            thvMain.setTab(0);
        }
    }
}

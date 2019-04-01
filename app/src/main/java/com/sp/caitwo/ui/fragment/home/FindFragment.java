package com.sp.caitwo.ui.fragment.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.ui.activity.sysm.WebLoadActivity;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.HolderUtil;

public class FindFragment extends Fragment {

    private View inflate;
    private FragmentManager fm;
    private NewWinPrizeFrag newWinPrizeFrag;
    private YesterdayRankingFrag yesterdayRankingFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BaseUtil.isEmpty(inflate)) {
            //复用下级开户
            inflate = inflater.inflate(R.layout.activity_subordinate_opener, null, false);
            initUI();
            addFragment();
        }
        return inflate;
    }

    private void addFragment() {
        fm = getChildFragmentManager();
        newWinPrizeFrag = new NewWinPrizeFrag();
        yesterdayRankingFrag = new YesterdayRankingFrag();
        fm.beginTransaction().add(R.id.fly_container, newWinPrizeFrag).addToBackStack(null).commit();
        fm.beginTransaction().add(R.id.fly_container, yesterdayRankingFrag).addToBackStack(null).commit();
        fm.beginTransaction().show(newWinPrizeFrag).hide(yesterdayRankingFrag).commit();
    }

    private void initUI() {
        inflate.findViewById(R.id.iv_top_back).setVisibility(View.GONE);
        TextView tvCustomService = inflate.findViewById(R.id.tv_top_right);
        tvCustomService.setBackgroundResource(R.drawable.icon_service);
        HolderUtil.setHolderZoom(tvCustomService,0.2f);
        FrameLayout parent = (FrameLayout) tvCustomService.getParent();
        parent.setBackgroundResource(R.color.colorPrimary);
        RadioGroup rgTitle = inflate.findViewById(R.id.rg_title);
        ((RadioButton) rgTitle.getChildAt(0)).setText("最新中奖");
        ((RadioButton) rgTitle.getChildAt(1)).setText("昨日排行");
        rgTitle.getChildAt(0).setBackgroundResource(R.drawable.red2bg2whitebg_left_r2);
        rgTitle.getChildAt(1).setBackgroundResource(R.drawable.red2bg2whitebg_right_r2);
        HolderUtil.setMargin(tvCustomService,0,0,20,0);
        rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getChildAt(0).getId() == checkedId){
                    fm.beginTransaction().show(newWinPrizeFrag).hide(yesterdayRankingFrag).commit();
                }else {
                    fm.beginTransaction().show(yesterdayRankingFrag).hide(newWinPrizeFrag).commit();
                }
            }
        });
        tvCustomService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!BaseUtil.isEmpty(App.customServerAddr)) {
                    Intent intent = new Intent(getContext(), WebLoadActivity.class);
                    intent.putExtra(WebLoadActivity.URL, App.customServerAddr);
                    intent.putExtra(WebLoadActivity.TITLE, getString(R.string.custom_service));
                    startActivity(intent);
                }
            }
        });
    }
}

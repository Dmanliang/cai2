package com.sp.caitwo.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.NotReadMsgBean;
import com.sp.caitwo.bean.UserBankList;
import com.sp.caitwo.bean.UserDetailBean;
import com.sp.caitwo.ui.activity.UserInfo.PersonDetailActivity;
import com.sp.caitwo.ui.activity.UserInfo.UserBetRecordActivity;
import com.sp.caitwo.ui.activity.UserInfo.ProfitLossActivity;
import com.sp.caitwo.ui.activity.UserInfo.UserBusinessRecordActivity;
import com.sp.caitwo.ui.activity.sysm.MessageActivity;
import com.sp.caitwo.ui.activity.sysm.WebLoadActivity;
import com.sp.caitwo.ui.activity.transferaccounts.RechargeWayActivity;
import com.sp.caitwo.ui.activity.transferaccounts.WithdrawActivity;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.ui.activity.agentcenter.AgentCenterActivity;
import com.sp.caitwo.ui.activity.sysm.SettingActivity;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.IconView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 *
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private TextView tvTopTitle;
    private TextView tvVipLevel;
    private TextView tvUserName;
    private View inflate;
    private ImageView ivHead;
    private ImageView ivRedPoint;
    private IconView iconBalance;
    private UserDetailBean userDetail;
    private boolean isHasBindBank;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(inflate == null){
            inflate = inflater.inflate(R.layout.frag_mine, null, false);
            init();
        }

        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
        getUserDetail();
    }

    private void getUserDetail(){
        InterfaceTask.getInstance().getUserDetail(getActivity(),new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                userDetail = (UserDetailBean) obj;
                setData();
                getNotReadMsg();
            }
        });

    }

    private void getNotReadMsg(){
        InterfaceTask.getInstance().getNotReadMsg(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                NotReadMsgBean json = (NotReadMsgBean) obj;
                if (BaseUtil.isEmpty(json.getData()) || json.getData().getMy_notice_count() == 0){
                    ivRedPoint.setVisibility(View.GONE);
                }else {
                    ivRedPoint.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setData() {
        InterfaceTask.getInstance().getUserBankList(getActivity(),new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                UserBankList json = (UserBankList) obj;
                List<UserBankList.DataBean> data = json.getData();
                isHasBindBank = data.size() > 0;
            }
        });

        ImageOptions imageOptions= new ImageOptions.Builder()
                .setCircular(true)
                .setAutoRotate(true)
                .setFadeIn(true)
                .build(); //淡入效果
        x.image().bind(ivHead, userDetail.getData().getUser_photo(),imageOptions);
        tvVipLevel.setText(String.format(getString(R.string.vip_level), userDetail.getData().getLevel()));
        tvUserName.setText(userDetail.getData().getAccount());
        iconBalance.setText(getString(R.string.icon_close_eyes));
    }

    private void init() {
        tvTopTitle = inflate.findViewById(R.id.tv_top_title);
        inflate.findViewById(R.id.lly_bet_record).setOnClickListener(this);
        inflate.findViewById(R.id.lly_business_record).setOnClickListener(this);
        inflate.findViewById(R.id.lly_today_profit_loss).setOnClickListener(this);
        inflate.findViewById(R.id.gift_prize_exchange).setOnClickListener(this);
        inflate.findViewById(R.id.lly_agent_center).setOnClickListener(this);
        inflate.findViewById(R.id.lly_setting).setOnClickListener(this);
        inflate.findViewById(R.id.lly_recharge).setOnClickListener(this);
        inflate.findViewById(R.id.lly_withdraw).setOnClickListener(this);
        inflate.findViewById(R.id.lly_customer_service).setOnClickListener(this);
        inflate.findViewById(R.id.lly_notify).setOnClickListener(this);
        inflate.findViewById(R.id.lly_balance).setOnClickListener(this);
        inflate.findViewById(R.id.iv_person_detail).setOnClickListener(this);
        tvTopTitle.setText("我的");

        ivHead = inflate.findViewById(R.id.iv_head);
        ivRedPoint = inflate.findViewById(R.id.iv_red_point);
        iconBalance = inflate.findViewById(R.id.icon_balance);
        tvVipLevel = inflate.findViewById(R.id.tv_vip_level);
        tvUserName = inflate.findViewById(R.id.tv_nick_name);
        tvVipLevel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lly_agent_center://代理中心
                if (BaseUtil.equals(App.userInfoBean.getData().getAgent_type(),"1"))
                    startActivity(new Intent(getActivity(),AgentCenterActivity.class));
                else
                    ToastUtil.Toast(getActivity(),"您不是代理类型");
                break;
            case R.id.iv_person_detail://个人资料
                Intent intent = new Intent(MineFragment.this.getContext(), PersonDetailActivity.class);
                intent.putExtra("userDetail",userDetail);
                startActivity(intent);
                break;
            case R.id.tv_vip_level://等级头衔
                Intent intent1 = new Intent(MineFragment.this.getContext(), PersonDetailActivity.class);
                intent1.putExtra("userDetail",userDetail);
                intent1.putExtra("fragment","vipLevel");
                startActivity(intent1);
                break;
            case R.id.lly_today_profit_loss://今日盈亏
                startActivity(new Intent(MineFragment.this.getContext(),ProfitLossActivity.class));
                break;
            case R.id.lly_setting://设置
                startActivity(new Intent(getActivity(),SettingActivity.class));
                break;
            case R.id.lly_bet_record://投注记录
                startActivity(new Intent(getActivity(),UserBetRecordActivity.class));
                break;
            case R.id.lly_business_record://交易记录
                startActivity(new Intent(getActivity(),UserBusinessRecordActivity.class));
                break;
            case R.id.gift_prize_exchange://礼金兑换
                break;
            case R.id.lly_recharge://充值
                startActivity(new Intent(getActivity(), RechargeWayActivity.class));
                break;
            case R.id.lly_withdraw://提现
                if (isHasBindBank)
                    startActivity(new Intent(getActivity(),WithdrawActivity.class));
                else
                    ToastUtil.Toast(getContext(),"请先绑定银行卡");
                break;
            case R.id.lly_customer_service://客服
                if (!BaseUtil.isEmpty(App.customServerAddr)) {
                    Intent intent2 = new Intent(getContext(), WebLoadActivity.class);
                    intent2.putExtra(WebLoadActivity.URL, App.customServerAddr);
                    intent2.putExtra(WebLoadActivity.TITLE, getString(R.string.custom_service));
                    startActivity(intent2);
                }
                break;
            case R.id.lly_notify://消息
                startActivity(new Intent(getActivity(),MessageActivity.class));
                break;
            case R.id.lly_balance://余额
                if (!BaseUtil.isEmpty(userDetail) && BaseUtil.equals(iconBalance.getText().toString(),getString(R.string.icon_close_eyes))){
                    iconBalance.setText(String.valueOf(userDetail.getData().getPoint()));
                }else {
                    iconBalance.setText(getString(R.string.icon_close_eyes));
                }
                break;
        }
    }
}

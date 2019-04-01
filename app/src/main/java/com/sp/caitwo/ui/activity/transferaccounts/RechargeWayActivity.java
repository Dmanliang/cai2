package com.sp.caitwo.ui.activity.transferaccounts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.PayListBean;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.HolderUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class RechargeWayActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PAY_TYPE_COMPANY_INCOME = "0";
    public static final String PAY_TYPE_ZHIFUBAO = "1";
    public static final String PAY_TYPE_WECHAT = "2";
    public static final String PAY_TYPE_E_BANK = "3";
    public static final String PAY_TYPE_QQ_PAY = "4";
    public static final String PAY_TYPE_YINLIAN = "5";
    public static final String PAY_TYPE_JINGDONG = "6";
    public static HashMap<String, Integer> iconMap;
    private LinearLayout llyList;
    private PayListBean payListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initResource();
        initUI();
        InterfaceTask.getInstance().getPayList(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                PayListBean json = (PayListBean) obj;
                payListBean = json;
                for (PayListBean.DataBean dataBean : json.getData()) {
                    LinearLayout item = (LinearLayout) View.inflate(getBaseContext(), R.layout.item_recharge_activity, null);
                    ArrayList<View> subControls = HolderUtil.getSubControls(item);
                    for (int i = 0; i < subControls.size(); i++) {
                        View child = subControls.get(i);
                        if (BaseUtil.equals(((String) child.getTag()), "icon") && child instanceof ImageView) {
                            Integer resId = iconMap.get(dataBean.getType());
                            ((ImageView) child).setImageResource(BaseUtil.isEmpty(resId) ? R.drawable.yinlian : resId);
                        }
                        if (BaseUtil.equals(((String) child.getTag()), "payName") && child instanceof TextView) {
                            ((TextView) child).setText(dataBean.getName());
                        }
                        if (BaseUtil.equals(((String) child.getTag()), "recommend") && child instanceof TextView && BaseUtil.equals(dataBean.getName(),"公司入款")) {
                            child.setVisibility(View.VISIBLE);
                        }
                        if (BaseUtil.equals(((String) child.getTag()), "limitMoney") && child instanceof TextView) {
                            ((TextView) child).setText(String.format(getString(R.string.limit_money), dataBean.getRecharge_min_limit(), dataBean.getRecharge_max_limit()));
                        }
                    }
                    item.setTag(dataBean.getType());
                    item.setOnClickListener(RechargeWayActivity.this);
                    llyList.addView(item);
                }
            }
        });
    }

    private void initResource() {
        iconMap = new HashMap<>();
        iconMap.put(PAY_TYPE_COMPANY_INCOME, R.drawable.yinlian);//公司入款
        iconMap.put(PAY_TYPE_ZHIFUBAO, R.drawable.icon_zhifubao);//支付宝
        iconMap.put(PAY_TYPE_WECHAT, R.drawable.icon_wechat);//微信
        iconMap.put(PAY_TYPE_E_BANK, R.drawable.yinlian);//网银
        iconMap.put(PAY_TYPE_QQ_PAY, R.drawable.icon_qq_pay);//QQ钱包
        iconMap.put(PAY_TYPE_YINLIAN, R.drawable.yinlian);//银联
        iconMap.put(PAY_TYPE_JINGDONG, R.drawable.icon_jingdong);//京东
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.select_recharge_way));
        llyList = findViewById(R.id.lly_list);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_top_back:
                finish();
                break;
            default:
                if (BaseUtil.isEmpty(payListBean)) return;
                Intent intent = new Intent();
                String payType = (String) v.getTag();
                for (PayListBean.DataBean dataBean : payListBean.getData()) {
                    if (BaseUtil.equals(dataBean.getType(), payType)) {
                        intent.putExtra("payWayBean", dataBean);
                        break;
                    }
                }
                if (BaseUtil.equals(payType, PAY_TYPE_COMPANY_INCOME)) {
                    intent.setClass(this, CompanyIncomeActivity.class);
                    startActivity(intent);
                } else if (BaseUtil.equals(payType, PAY_TYPE_ZHIFUBAO)) {
                    intent.setClass(this, RechargePayActivity.class);
                    startActivity(intent);
                } else if (BaseUtil.equals(payType, PAY_TYPE_WECHAT)) {
                    intent.setClass(this, RechargePayActivity.class);
                    startActivity(intent);
                } else if (BaseUtil.equals(payType, PAY_TYPE_E_BANK)) {
                    intent.setClass(this, RechargePayActivity.class);
                    startActivity(intent);
                } else if (BaseUtil.equals(payType, PAY_TYPE_QQ_PAY)) {
                    intent.setClass(this, RechargePayActivity.class);
                    startActivity(intent);
                } else if (BaseUtil.equals(payType, PAY_TYPE_YINLIAN)) {
                    intent.setClass(this, RechargePayActivity.class);
                    startActivity(intent);
                } else if (BaseUtil.equals(payType, PAY_TYPE_JINGDONG)) {
                    intent.setClass(this, RechargePayActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}

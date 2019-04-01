package com.sp.caitwo.ui.activity.transferaccounts;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.PayListBean;
import com.sp.caitwo.ui.adapter.securitycenter.WheelQuestionAdap;
import com.sp.caitwo.ui.view.dialog.WheelDialog;
import com.sp.util.ProjectUtil;
import com.sp.xwjlibrary.dialog.ViewDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.FileUtil;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import org.xutils.XCallback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kankan.wheel.widget.WheelView;

public class RechargePayActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSelWay;
    private IconEditClearView iecvRechargeMoney;
    private IconEditClearView iecvRemarkInfo;

    private ArrayList<String> payWayNameList;
    private WheelDialog wheelDialog;
    private WheelView wheelView;
    private String minLimitMoney = "";
    private String maxLimitMoney = "";
    private List<PayListBean.DataBean.RechargeWayListBean> rechargeWayList;
    private IconEditClearView iecvReceivablesPerson;
    private IconEditClearView iecvReceivablesAccount;
    private IconEditClearView iecvPayAccount;
    private View llyOfflinePay;
    private ImageView ivPayLogo;
    private PayListBean.DataBean payWayBean;
    private TextView tvTitle;
    private ViewDialog viewDialog;
    private View llyLookOver;
    private ArrayList<Boolean> selPassageList;
    private RecyclerView rvRechargePassage;
    private PassageAdap passageAdap;

    private void qrcodePay(){
        View qrcodeView = View.inflate(this, R.layout.dialog_qrcode, null);
        qrcodeView.findViewById(R.id.icon_close).setOnClickListener(new OnClickListener());
        qrcodeView.findViewById(R.id.lly_save_2_picture).setOnClickListener(new OnClickListener());
        TextView tvRechargeMoney = qrcodeView.findViewById(R.id.tv_recharge_money);
        ImageView ivQrcode = qrcodeView.findViewById(R.id.iv_qrcode);
        ((LinearLayout) tvRechargeMoney.getParent()).setVisibility(View.GONE);
        x.image().bind(ivQrcode,rechargeWayList.get(wheelView.getCurrentItem()).getQrcode_img());
        viewDialog = new ViewDialog(this, qrcodeView);
        viewDialog.setCancelable(false);
        viewDialog.setBgTransparent();
    }

    class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.icon_close:
                    viewDialog.dismiss();
                    break;
                case R.id.lly_save_2_picture:
                    String imgUrl = rechargeWayList.get(wheelView.getCurrentItem()).getQrcode_img();
                    RequestParams params = new RequestParams(imgUrl);
                    try {
                        params.setSaveFilePath(FileUtil.saveToAppName(getBaseContext(),"image",imgUrl));
                    } catch (FileUtil.SdcardDisableException e) {
                        e.printStackTrace();
                    }
                    x.http().get(params, new XCallback<File>() {
                        @Override
                        public void onSuccess(File result) {
                            ToastUtil.Toast(getBaseContext(),"下载地址:" + result);
                        }
                    });
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_pay);
        payWayNameList = new ArrayList<>();
        selPassageList = new ArrayList<>();

        initUI();
        initWheel();
        setPassageData();

        payWayBean = (PayListBean.DataBean) getIntent().getSerializableExtra("payWayBean");
        if (!BaseUtil.isEmpty(payWayBean)) {
            rechargeWayList = payWayBean.getRecharge_way_list();
            for (PayListBean.DataBean.RechargeWayListBean bean : payWayBean.getRecharge_way_list()) {
                if (!BaseUtil.isEmpty((bean.getPay_type_name())))
                    payWayNameList.add(bean.getPay_type_name());
                //初始化通道选择状态
                selPassageList.add(false);
            }
            selPassageList.set(0,true);
            passageAdap.notifyDataSetChanged();
            payActivityShow(rechargeWayList.get(0));

            if (!BaseUtil.isEmpty(rechargeWayList.get(wheelView.getCurrentItem()).getQrcode_img())){
                qrcodePay();
            }else {
                llyLookOver.setVisibility(View.GONE);
            }
        }
    }

    private void setPassageData() {
        passageAdap = new PassageAdap();
        rvRechargePassage.setLayoutManager(new LinearLayoutManager(this,LinearLayout.VERTICAL,false));
        rvRechargePassage.setAdapter(passageAdap);
        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(this, R.drawable.divide_line_horizontal_px1));
        rvRechargePassage.addItemDecoration(decor);
    }

    private void payActivityShow(PayListBean.DataBean.RechargeWayListBean bean) {
        tvSelWay.setText(bean.getPay_type_name());
        Integer resId = RechargeWayActivity.iconMap.get(payWayBean.getType());
        ivPayLogo.setImageResource(BaseUtil.isEmpty(resId) ? R.drawable.yinlian : resId);
        tvTitle.setText(payWayBean.getName());
        if (BaseUtil.equals(bean.getOnline_type(),"2")){//线下支付
            llyOfflinePay.setVisibility(View.VISIBLE);
            llyLookOver.setVisibility(View.VISIBLE);
            minLimitMoney = bean.getRecharge_min_limit();
            maxLimitMoney = bean.getRecharge_max_limit();
            iecvRemarkInfo.setHint(bean.getRemark());
            iecvReceivablesPerson.setInputInfo(bean.getPayee());
            iecvReceivablesAccount.setInputInfo(bean.getReceive_account());
        }else if (BaseUtil.equals(bean.getOnline_type(),"1")){//线上支付
            llyOfflinePay.setVisibility(View.GONE);
            llyLookOver.setVisibility(View.GONE);
            minLimitMoney = bean.getRecharge_min_limit();
            maxLimitMoney = bean.getRecharge_max_limit();
            iecvRemarkInfo.setHint(bean.getRemark());
        }
    }

    private void initWheel() {
        View inflate = View.inflate(this,R.layout.dialog_security_question, null);
        wheelView = inflate.findViewById(R.id.wheel_question);
        inflate.findViewById(R.id.tv_wheel_cancle).setOnClickListener(this);
        inflate.findViewById(R.id.tv_wheel_sure).setOnClickListener(this);
        wheelView.setWheelBackground(R.color.white);
        wheelView.setWheelForeground(R.drawable.top_bottom_grayside_whitebg);
        wheelView.setViewAdapter(new WheelQuestionAdap(payWayNameList));
        wheelView.setDrawShadows(false);
        wheelView.setVisibleItems(7);
        wheelDialog = new WheelDialog(this, inflate);
    }

    private void initUI() {
        tvTitle = findViewById(R.id.tv_top_title);
        ivPayLogo = findViewById(R.id.iv_pay_logo);
        tvSelWay = findViewById(R.id.tv_sel_way);
        iecvRechargeMoney = findViewById(R.id.iecv_recharge_money);
        iecvRemarkInfo = findViewById(R.id.iecv_remark_info);
        iecvReceivablesPerson = findViewById(R.id.iecv_receivables_person);
        iecvReceivablesAccount = findViewById(R.id.iecv_receivables_account);
        iecvPayAccount = findViewById(R.id.iecv_pay_account);
        llyOfflinePay = findViewById(R.id.lly_offline_pay);
        llyLookOver = findViewById(R.id.lly_qrcode);
        rvRechargePassage = findViewById(R.id.rv_recharge_passage);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.lly_pay_way).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        findViewById(R.id.tv_copy_receivables_person).setOnClickListener(this);
        findViewById(R.id.tv_copy_receivables_account).setOnClickListener(this);
        findViewById(R.id.tv_look_over_qrcode).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.tv_look_over_qrcode:
                viewDialog.show();
                break;
            case R.id.tv_copy_receivables_person:
                ProjectUtil.copy(this,iecvReceivablesPerson.getInputInfo());
                break;
            case R.id.tv_copy_receivables_account:
                ProjectUtil.copy(this,iecvReceivablesAccount.getInputInfo());
                break;
            case R.id.lly_pay_way:
                if (!BaseUtil.isEmpty(wheelDialog))
                    wheelDialog.show();
                break;
            case R.id.tv_wheel_cancle:
                if (!BaseUtil.isEmpty(wheelDialog))
                    wheelDialog.dismiss();
                break;
            case R.id.tv_wheel_sure:
                if (!BaseUtil.isEmpty(wheelDialog)) {
                    payActivityShow(rechargeWayList.get(wheelView.getCurrentItem()));
                    wheelDialog.dismiss();
                }
                break;
            case R.id.btn_sure:
                confirmPay(rechargeWayList.get(wheelView.getCurrentItem()).getOnline_type());
                break;
        }
    }

    private void confirmPay(String type){
        int position = Integer.valueOf(type);
        String title = payWayBean.getName();
        double min = Double.parseDouble(minLimitMoney);
        double max = Double.parseDouble(maxLimitMoney);
        if (!BaseUtil.isEmpty(iecvRechargeMoney.getInputInfo())){
            double recharge = Double.parseDouble(iecvRechargeMoney.getInputInfo());
            if (recharge < min || recharge > max){
                iecvRechargeMoney.setErrorInfo(getString(R.string.recharge_num_error));
            }
        }else {
            iecvRechargeMoney.setErrorInfo(getString(R.string.input_recharge_money));
        }
        if (BaseUtil.isEmpty(iecvPayAccount.getInputInfo())){
            iecvPayAccount.setErrorInfo(getString(R.string.input_pay_account));
        }
        if (position == 2){
            if (!iecvRechargeMoney.isHasError() && !iecvPayAccount.isHasError()){
                InterfaceTask.getInstance().offlinePay(this,iecvRechargeMoney.getInputInfo(),"2",rechargeWayList.get(wheelView.getCurrentItem()).getId(),iecvPayAccount.getInputInfo());
            }
        }else if (position == 1){
            if (!iecvRechargeMoney.isHasError()){
                InterfaceTask.getInstance().onlinePay(this,title,iecvRechargeMoney.getInputInfo(),rechargeWayList.get(wheelView.getCurrentItem()).getId());
            }
        }
    }

    class Holder extends RecyclerView.ViewHolder{

        private final TextView tvPassageName;
        private final TextView tvPassageDetail;
        private final RadioButton rbPassage;

        public Holder(View itemView) {
            super(itemView);
            tvPassageName = itemView.findViewById(R.id.tv_passage_name);
            tvPassageDetail = itemView.findViewById(R.id.tv_passage_detail);
            rbPassage = itemView.findViewById(R.id.rb_passage);
        }
    }

    class PassageAdap extends RecyclerView.Adapter<Holder>{
        @Override
        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_passage, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(Holder holder, int i) {
            holder.tvPassageName.setText(rechargeWayList.get(i).getPay_type_name());
            holder.tvPassageDetail.setText(rechargeWayList.get(i).getRemark());
            holder.rbPassage.setChecked(selPassageList.get(i));
            holder.itemView.setTag(i);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = ((int) view.getTag());
                    for (int i1 = 0; i1 < selPassageList.size(); i1++) {
                        if (pos == i1){
                            selPassageList.set(i1,true);
                        }else {
                            selPassageList.set(i1,false);
                        }
                    }
                    payActivityShow(rechargeWayList.get(pos));
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return rechargeWayList.size();
        }
    }

}

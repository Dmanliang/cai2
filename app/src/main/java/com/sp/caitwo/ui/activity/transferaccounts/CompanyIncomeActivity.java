package com.sp.caitwo.ui.activity.transferaccounts;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.PayListBean;
import com.sp.caitwo.ui.adapter.securitycenter.WheelQuestionAdap;
import com.sp.caitwo.ui.view.dialog.WheelDialog;
import com.sp.util.ProjectUtil;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import java.util.ArrayList;
import java.util.List;

import kankan.wheel.widget.WheelView;

public class CompanyIncomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<TextView> labelList = new ArrayList<>();
    private ArrayList<String> bankNameList;
    private WheelDialog wheelDialog;
    private WheelView wheelBank;
    private TextView tvSelBank;
    private IconEditClearView iecvReceivablesPerson;
    private IconEditClearView iecvReceivablesAccount;
    private IconEditClearView iecvOpenerSubbranch;
    private IconEditClearView iecvRechargeMoney;
    private IconEditClearView iecvPayPerson;
    private IconEditClearView iecvRemarkInfo;
    private List<PayListBean.DataBean.RechargeWayListBean> rechargeWayList;
    private ImageView ivBankLogo;
    private String bankId = "";
    private ArrayList<Boolean> selPassageList;
    private RecyclerView rvRechargePassage;
    private PassageAdap passageAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_income);
        bankNameList = new ArrayList<>();
        selPassageList = new ArrayList<>();

        initUI();

        initWheel();

        setPassageData();

        PayListBean.DataBean payWayBean = (PayListBean.DataBean) getIntent().getSerializableExtra("payWayBean");
        if (!BaseUtil.isEmpty(payWayBean)){
            rechargeWayList = payWayBean.getRecharge_way_list();
            for (PayListBean.DataBean.RechargeWayListBean bean : rechargeWayList) {
                if (!BaseUtil.isEmpty((bean.getBank_name())))
                    bankNameList.add(bean.getBank_name());
                //初始化通道选择状态
                selPassageList.add(false);
            }
            selPassageList.set(0,true);
            passageAdap.notifyDataSetChanged();
            setBankInfo(0);
        }

    }

    private void setPassageData() {
        passageAdap = new PassageAdap();
        rvRechargePassage.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        rvRechargePassage.setAdapter(passageAdap);
        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(this, R.drawable.divide_line_horizontal_px1));
        rvRechargePassage.addItemDecoration(decor);
    }

    private void setBankInfo(int position) {
        tvSelBank.setText(rechargeWayList.get(position).getBank_name());
        iecvReceivablesPerson.setInputInfo(rechargeWayList.get(position).getReal_name());
        iecvReceivablesAccount.setInputInfo(rechargeWayList.get(position).getAccount());
        iecvOpenerSubbranch.setInputInfo(rechargeWayList.get(position).getOpen_card_address());
        iecvRemarkInfo.setHint(rechargeWayList.get(position).getRemark());
        Integer resId = Constants.bankMap.get(rechargeWayList.get(position).getBank_name());
        ivBankLogo.setImageResource(BaseUtil.isEmpty(resId) ? R.drawable.yinlian : resId);
        bankId = rechargeWayList.get(position).getId();
    }

    private void initWheel() {
        View inflate = View.inflate(this,R.layout.dialog_security_question, null);
        wheelBank = inflate.findViewById(R.id.wheel_question);
        inflate.findViewById(R.id.tv_wheel_cancle).setOnClickListener(this);
        inflate.findViewById(R.id.tv_wheel_sure).setOnClickListener(this);
        wheelBank.setWheelBackground(R.color.white);
        wheelBank.setWheelForeground(R.drawable.top_bottom_grayside_whitebg);
        wheelBank.setViewAdapter(new WheelQuestionAdap(bankNameList));
        wheelBank.setDrawShadows(false);
        wheelBank.setVisibleItems(7);
        wheelDialog = new WheelDialog(this, inflate);
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.company_income));
        tvSelBank = findViewById(R.id.tv_sel_bank);
        iecvReceivablesPerson = findViewById(R.id.iecv_receivables_person);
        iecvReceivablesAccount = findViewById(R.id.iecv_receivables_account);
        iecvOpenerSubbranch = findViewById(R.id.iecv_opener_subbranch);
        iecvRechargeMoney = findViewById(R.id.iecv_recharge_money);
        iecvPayPerson = findViewById(R.id.iecv_pay_person);
        iecvRemarkInfo = findViewById(R.id.iecv_remark_info);
        ivBankLogo = findViewById(R.id.iv_bank_logo);
        rvRechargePassage = findViewById(R.id.rv_recharge_passage);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        findViewById(R.id.lly_sel_bank).setOnClickListener(this);
        findViewById(R.id.tv_copy_receivables_person).setOnClickListener(this);
        findViewById(R.id.tv_copy_receivables_account).setOnClickListener(this);
        findViewById(R.id.tv_copy_opener_subbranch).setOnClickListener(this);

        setLabelSame(HolderUtil.getRootLayout(this));
    }

    private void setLabelSame(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof IconEditClearView){
                TextView iconView = ((IconEditClearView) v).getIconView();
                if (!BaseUtil.isEmpty(iconView.getText().toString()))
                    labelList.add(iconView);
            }else if(v instanceof ViewGroup){
                setLabelSame((ViewGroup) v);
            }
        }
        TextViewUtil.setSameWidth(labelList.toArray(new TextView[0]),null);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.lly_sel_bank:
                wheelDialog.show();
                break;
            case R.id.tv_wheel_cancle:
                wheelDialog.dismiss();
                break;
            case R.id.tv_wheel_sure:
                int currentItem = wheelBank.getCurrentItem();
                setBankInfo(currentItem);
                wheelDialog.dismiss();
                break;
            case R.id.tv_copy_receivables_person:
            case R.id.tv_copy_receivables_account:
            case R.id.tv_copy_opener_subbranch:
                if (BaseUtil.equals(((String) v.getTag()),"person")){
                    ProjectUtil.copy(this,iecvReceivablesPerson.getInputInfo());
                }else if (BaseUtil.equals(((String) v.getTag()),"account")){
                    ProjectUtil.copy(this,iecvReceivablesAccount.getInputInfo());
                }else if (BaseUtil.equals(((String) v.getTag()),"subbranch")){
                    ProjectUtil.copy(this,iecvOpenerSubbranch.getInputInfo());
                }
                break;
            case R.id.btn_sure:
                InterfaceTask.getInstance().offlinePay(this,iecvRechargeMoney.getInputInfo(),"1",bankId,iecvPayPerson.getInputInfo());
                break;
        }
    }

    class Holder extends RecyclerView.ViewHolder{

        private final TextView tvPassageName;
        private final TextView tvPassageDetail;
        private final RadioButton rbPassage;
        private final ImageView ivBankLogo;

        public Holder(View itemView) {
            super(itemView);
            tvPassageName = itemView.findViewById(R.id.tv_passage_name);
            tvPassageDetail = itemView.findViewById(R.id.tv_passage_detail);
            rbPassage = itemView.findViewById(R.id.rb_passage);
            ivBankLogo = itemView.findViewById(R.id.iv_bank_logo);
        }
    }

    class PassageAdap extends RecyclerView.Adapter<Holder>{
        @Override
        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_passage, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(Holder holder, int i) {
            holder.tvPassageName.setText(rechargeWayList.get(i).getBank_name());
            holder.tvPassageDetail.setText(rechargeWayList.get(i).getRemark());
            holder.rbPassage.setChecked(selPassageList.get(i));
            Integer resId = Constants.bankMap.get(rechargeWayList.get(i).getBank_name());
            holder.ivBankLogo.setImageResource(BaseUtil.isEmpty(resId) ? R.drawable.yinlian : resId);
            holder.ivBankLogo.setVisibility(View.VISIBLE);
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
                    setBankInfo(pos);
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

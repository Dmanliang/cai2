package com.sp.caitwo.ui.activity.securitycenter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.UserBankList;
import com.sp.caitwo.ui.adapter.securitycenter.BankCardManageAdap;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class BankCardManageActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<UserBankList.DataBean> bankCardList;
    private BankCardManageAdap bankCardManageAdap;
    private TextView tvNoBindBankHint;
    private RecyclerView rvBankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_manage);

        initUI();

        bankCardList = new ArrayList<>();
        rvBankList.setLayoutManager(new LinearLayoutManager(this));
        bankCardManageAdap = new BankCardManageAdap(this,bankCardList);
        rvBankList.setAdapter(bankCardManageAdap);

        getData();
    }

    private void getData() {
        InterfaceTask.getInstance().getUserBankList(this,new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                UserBankList json = (UserBankList) obj;
                List<UserBankList.DataBean> data = json.getData();
                bankCardList.clear();
                bankCardList.addAll(data);
                bankCardManageAdap.notifyDatasChang();
                if (bankCardList.size() != 0){
                    tvNoBindBankHint.setVisibility(View.GONE);
                }else {
                    tvNoBindBankHint.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initUI() {
        rvBankList = findViewById(R.id.rv_bank_list);
        tvNoBindBankHint = findViewById(R.id.tv_no_bind_bank_hint);
        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.bank_card_manage));
        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.btn_add_bank_card).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.btn_add_bank_card:
                if(bankCardList.size() >= 5){
                    ToastUtil.Toast(this,"最多允许绑定5张银行卡");
                    return;
                }
                Intent intent = new Intent(this, AddBankCardActivity.class);
                intent.putExtra("openerName",bankCardList.size() == 0 ? "" : bankCardList.get(0).getReal_name());
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK){
            getData();
        }
    }
}

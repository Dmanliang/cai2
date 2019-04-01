package com.sp.caitwo.ui.adapter.securitycenter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.UserBankList;
import com.sp.caitwo.ui.activity.securitycenter.AddBankCardActivity;
import com.sp.caitwo.ui.activity.securitycenter.BankCardManageActivity;
import com.sp.caitwo.ui.holder.BankCardManageHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;

import java.util.ArrayList;

public class BankCardManageAdap extends RVBaseAdap<BankCardManageHolder> {

    private BankCardManageActivity activity;
    private ArrayList<UserBankList.DataBean> bankCardList;

    public BankCardManageAdap(BankCardManageActivity activity, ArrayList<UserBankList.DataBean> bankCardList) {
        this.activity = activity;
        this.bankCardList = bankCardList;
    }

    @Override
    public BankCardManageHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new BankCardManageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_card_manage_list,parent,false));
    }

    @Override
    public int getCount() {
        return bankCardList.size();
    }

    @Override
    public void onBindHolder(BankCardManageHolder holder, int position) {
        holder.tvBankType.setText(bankCardList.get(position).getBank_name());
        String bankNo = bankCardList.get(position).getBank_no();
        holder.tvTailNum.setText(String.format(activity.getString(R.string.tail_num_4), bankNo.length() <= 4 ? bankNo : bankNo.substring(bankNo.length() - 4)));
        if (Constants.bankMap.containsKey(bankCardList.get(position).getBank_name())){
            holder.ivBankLogo.setImageResource(Constants.bankMap.get(bankCardList.get(position).getBank_name()));
        }else {
            holder.ivBankLogo.setImageResource(R.drawable.yinlian);
        }
        holder.tvChange.setTag(position);
        holder.tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Intent intent = new Intent(activity, AddBankCardActivity.class);
                intent.putExtra("bankCard",bankCardList.get(pos));
                activity.startActivityForResult(intent,0);
            }
        });
    }
}

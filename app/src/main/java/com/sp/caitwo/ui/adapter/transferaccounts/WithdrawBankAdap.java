package com.sp.caitwo.ui.adapter.transferaccounts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.WithdrawInfoBean;

import java.util.ArrayList;

import kankan.wheel.widget.adapters.AbstractWheelAdapter;

public class WithdrawBankAdap extends AbstractWheelAdapter {

    private ArrayList<WithdrawInfoBean.DataBean.BankListBean> list;
    private String itemStr = "%s(%s)";

    public WithdrawBankAdap(ArrayList<WithdrawInfoBean.DataBean.BankListBean> list) {
        this.list = list;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        View inflate;
        TextView tv;
        if (convertView == null) {
            inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wheel_text, parent, false);
            tv = inflate.findViewById(R.id.tv_content);
            inflate.setTag(tv);
        } else {
            inflate = convertView;
            tv = (TextView) inflate.getTag();
        }
        tv.setText(String.format(itemStr,list.get(index).getBank_name(),list.get(index).getBank_no()));
        return inflate;
    }
}
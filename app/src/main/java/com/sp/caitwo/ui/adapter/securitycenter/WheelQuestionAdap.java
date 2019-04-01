package com.sp.caitwo.ui.adapter.securitycenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;

import java.util.ArrayList;

import kankan.wheel.widget.adapters.AbstractWheelAdapter;

public class WheelQuestionAdap extends AbstractWheelAdapter {

    private ArrayList<String> list;

    public WheelQuestionAdap(ArrayList<String> list) {
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
        tv.setText(list.get(index));
        return inflate;
    }
}
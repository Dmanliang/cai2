package com.sp.xwjlibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sp.xwjlibrary.R;
import com.sp.xwjlibrary.myinterface.OnTimeDialogListener;


/**
 * Created by vinson on 2017/12/20.
 */

public class TimeDialog extends AlertDialog {

    private OnTimeDialogListener listener;
    private String time;
    private TextView tvTitle;
    private TimePicker timePicker;
    private int hourOfDay = 9;
    private int minute = 0;

    public TimeDialog(Context context) {
        super(context);
        setContentView();
    }

    public void setOnTimeDialogListener(OnTimeDialogListener listener){
        this.listener = listener;
    }

    private void setContentView() {
        this.show();
        // 设置点击在区域外不消失
        this.setCanceledOnTouchOutside(false);
        // 获取dialog窗体对象
        Window window = this.getWindow();
        // 设置dialog窗体样式
        window.setContentView(R.layout.dialog_time_selector);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        this.cancel();

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                StringBuilder sb = new StringBuilder();
                if (hourOfDay < 10){
                    sb.append(0);
                }
                sb.append(hourOfDay);
                sb.append(":");
                if (minute < 10){
                    sb.append(0);
                }
                sb.append(minute);
                time = sb.toString();
            }
        });
        timePicker.setCurrentHour(hourOfDay);
        timePicker.setCurrentMinute(minute);
        tvTitle = findViewById(R.id.tv_header);

        setClickListener(window);
    }

    public void setTitle(String title){
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    public void setTime(int hourOfDay, int minute){
        timePicker.setCurrentHour(hourOfDay);
        timePicker.setCurrentMinute(minute);
    }

    private void setClickListener(Window window) {
        window.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        window.findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onData(time);
                dismiss();
            }
        });
    }

}

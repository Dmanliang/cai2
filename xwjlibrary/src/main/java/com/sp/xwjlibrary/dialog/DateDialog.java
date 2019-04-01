package com.sp.xwjlibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;

import com.sp.xwjlibrary.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vinson on 2017/8/31.
 */

public class DateDialog extends AlertDialog {

    private final String pattern;
    private int defYear;
    private int defMonth;
    private int defDay;
    private final OnDateSetListener onDateSetListener;
    private TextView tvTitle;
    private DatePicker datePicker;
    private String date;

    public interface OnDateSetListener{
        void onDateSet(String date);
    }

    public DateDialog(Context context,String pattern,int defYear,int defMonth,
                      int defDay,OnDateSetListener onDateSetListener) {
        super(context);
        this.pattern = pattern;
        this.defYear = defYear;
        this.defMonth = defMonth;
        this.defDay = defDay;
        this.onDateSetListener = onDateSetListener;
        initUI(context);
    }

    public DateDialog(Context context,String pattern,OnDateSetListener onDateSetListener) {
        super(context);
        this.pattern = pattern;
        this.onDateSetListener = onDateSetListener;
        initUI(context);
    }

    private void initUI(Context context){
        this.show();
        this.setCanceledOnTouchOutside(false);
        Window window = this.getWindow();
        window.setContentView(R.layout.dialog_date_selector);
        this.cancel();

        tvTitle = window.findViewById(R.id.tv_header);
        datePicker = window.findViewById(R.id.datePicker);

        initDatePicker();

        setClickListener(window);
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
                onDateSetListener.onDateSet(date);
                dismiss();
            }
        });
    }

    private void initDatePicker(){
        if(defYear == 0 || defMonth == 0 || defDay == 0){
            Calendar calendar = Calendar.getInstance();
            defYear = calendar.get(Calendar.YEAR);
            defMonth = calendar.get(Calendar.MONTH);
            defDay = calendar.get(Calendar.DAY_OF_MONTH);
            date = new SimpleDateFormat(pattern).format(new Date(System.currentTimeMillis()));
        }else{
            StringBuilder sb = new StringBuilder();
            if (defMonth < 10) {
                sb.append("0");
            }
            sb.append(defMonth);

            if (defDay < 10) {
                sb.append("0");
            }
            sb.append(defDay);

            date = defYear + sb.toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            try {
                date = new SimpleDateFormat(pattern).format(sdf.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            defMonth = defMonth - 1;
        }

        datePicker.init(defYear, defMonth, defDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;
                StringBuilder sb = new StringBuilder();
                if (monthOfYear < 10) {
                    sb.append("0");
                }
                sb.append(monthOfYear);

                if (dayOfMonth < 10) {
                    sb.append("0");
                }
                sb.append(dayOfMonth);

                date = year + sb.toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                try {
                    date = new SimpleDateFormat(pattern).format(sdf.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DateDialog setTitle(String title){
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
        return this;
    }

    /**
     * 设置时间区间
     * @param pattern
     * @param minDate ""表示不限制
     * @param maxDate ""表示不限制
     * @return
     */
    public DateDialog setMinAndMax(String pattern, String minDate, String maxDate){
        try {
            if(!TextUtils.isEmpty(minDate)){
                long minTime = new SimpleDateFormat(pattern).parse(minDate).getTime();
                datePicker.setMinDate(minTime);
            }
            if(!TextUtils.isEmpty(maxDate)){
                long maxTime = new SimpleDateFormat(pattern).parse(maxDate).getTime();
                datePicker.setMaxDate(maxTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置时间区间
     * @param minDate 0表示不限制
     * @param maxDate 0表示不限制
     * @return
     */
    public DateDialog setMinAndMax(long minDate, long maxDate){
        if(minDate != 0){
            datePicker.setMinDate(minDate);
        }
        if(maxDate != 0){
            datePicker.setMaxDate(maxDate);
        }
        return this;
    }

}












package com.sp.caitwo.ui.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.ui.fragment.home.CathecticFragment;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2018/10/24.
 */

public class LongDragonCountDown extends LinearLayout {

    private Context context;
    private CountDownTimer mTimer;
    private TextView longdragon_timer;
    public OnDragonCountDownListener listener;

    public LongDragonCountDown(Context context) {
        super(context);
        init(context);
    }

    public LongDragonCountDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LongDragonCountDown(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context){
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_longdragon_timer,this);
        longdragon_timer = (TextView)findViewById(R.id.longdragon_timer);
    }

    public void setText(String hour, String minu, String secd){
        longdragon_timer.setText(hour+":"+minu+":"+secd);
    }

    public void setText(String s){
        longdragon_timer.setText(s);
    }

    public void startCountDown( long second, int time_speed, final int type, long curtime) {
        if (mTimer != null){
            stopCountDown();
        }
        int cTime = (int) ( System.currentTimeMillis() - curtime)/1000;
        second = second -cTime;
        long time = second * time_speed;
        if (time <= 0) {
//            EventBus.getDefault().post(CathecticFragment.REFRESH_DATA);
            setText("开奖中");
            time = 1000;
//            return;
        }
        mTimer = new CountDownTimer(time, time_speed) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (type == 1) {
                    getTime(millisUntilFinished);
                } else if (type == 2) {
                    setText("开奖中");
                } else if (type == 3) {
                    setText("封盘中");
                }
            }

            @Override
            public void onFinish() {
                EventBus.getDefault().post(CathecticFragment.REFRESH_DATA);
                if (listener != null)
                    listener.onFinish();
            }
        };
        mTimer.start();
    }

    /**
     * 计算时间差
     *
     * @return 返回时间差
     */
    public void getTime(long time) {
        if(time == 0){
            longdragon_timer.setText("开奖中");
            return;
        }
        long day = time / (24 * 60 * 60 * 1000);
        long hour = (time / (60 * 60 * 1000) - day * 24);
        long min = ((time / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (time / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String hours = hour + "";
        String mins = min + "";
        String ss = s + "";
        if (day > 0) {
            if (hour >= 0) {
                hour = hour + day * 24;
                hours = hour + "";
            } else if (hour < 0) {
                hours = "00";
            }
        } else {
            if (hour < 10 && hour >= 0) {
                hours = "0" + hour;
            } else if (hour < 0) {
                hours = "00";
            } else {
                hours = hour + "";
            }
        }
        if (min < 10 && min >= 0) {
            mins = "0" + min;
        } else if (min < 0) {
            mins = "00";
        }
        if (s < 10 && s >= 0) {
            ss = "0" + s;
        } else if (s < 0) {
            ss = "00";
        }
        if("00".contains(hours) && "00".contains(mins)&&"00".contains(ss)){
            longdragon_timer.setText(  "开奖中");
        }else if("00".contains(hours)){
            longdragon_timer.setText( mins+":"+ss);
        }else{
            longdragon_timer.setText(hours+":"+mins+":"+ss);
        }

    }

    public void stopCountDown() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void setTextColor(int color) {
        longdragon_timer.setTextColor(getResources().getColor(color));
    }

    public void setTextSize(int size) {
        longdragon_timer.setTextSize(size);
    }

    public void setOnCountDownListener(OnDragonCountDownListener listener) {
        this.listener = listener;
    }

    public interface OnDragonCountDownListener {
        void onFinish();
    }
}

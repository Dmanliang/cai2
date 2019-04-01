package com.sp.caitwo.ui.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.sp.caitwo.R;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.ui.activity.lottery.LotteryActivity;

import de.greenrobot.event.EventBus;

import static com.sp.caitwo.base.LotteryCons.REFRESH_DATA;
import static com.sp.caitwo.ui.activity.lottery.LotteryCathecticActivity.UPDATE_TIME;

/**
 * Created by Administrator on 2018/10/24.
 */

public class LotteryCountDown extends LinearLayout {

    private Context context;
    private CountDownTimer mTimer;
    private TextView tv_countdown;
    public OnDragonCountDownListener listener;
    private long second;
    private int time_type = 1;
    private int game_type;
    public boolean isChange = false;

    public LotteryCountDown(Context context) {
        super(context);
        init(context);
    }

    public LotteryCountDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LotteryCountDown(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_lottery_timer, this);
        tv_countdown = findViewById(R.id.tv_countdown);
    }

    public void setText(String hour, String minu, String secd) {
        tv_countdown.setText(hour + ":" + minu + ":" + secd);
    }

    public void setText(String s) {
        tv_countdown.setText(s);
    }

    public void startCountDown(long seconds, int time_speed, final int type, long curtime, int game_type) {
        this.second = seconds;
        this.game_type = game_type;
        if (mTimer != null) {
            stopCountDown();
        }
        int cTime = (int) (System.currentTimeMillis() - curtime) / 1000;
        this.second = seconds - cTime;
        long time = seconds * time_speed;
        handler.removeCallbacks(runnable);
        handler.post(runnable);
        mTimer = new CountDownTimer(time, time_speed) {
            @Override
            public void onTick(long millisUntilFinished) {
                second = millisUntilFinished / 1000;
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
                handler.removeCallbacks(runnable);
                handler.post(runnable);
                setText("开奖中");
                EventBus.getDefault().post(LotteryActivity.START_ANIMATION);
                if (listener != null)
                    listener.onFinish();
            }
        };
        mTimer.start();
    }

    public Handler handler = new Handler();

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(runnable);
            if (time_type == 1) {
                EventBus.getDefault().post(LotteryActivity.REFRESH_DATA);
            } else {
                EventBus.getDefault().post(UPDATE_TIME);
            }
            if (!isChange) {
                switch (game_type) {
                    case LotteryCons.CQSSC:
                    case LotteryCons.TJSSC:
                    case LotteryCons.JSK3:
                    case LotteryCons.BJK3:
                    case LotteryCons.AHK3:
                    case LotteryCons.XJP28:
                    case LotteryCons.JND_28:
                    case LotteryCons.BJ_28:
                    case LotteryCons.ELEVENXFIVE:
                    case LotteryCons.BJPK10:
                    case LotteryCons.FENFENCAI:
                    case LotteryCons.YDWFC:
                        handler.postDelayed(runnable, 3000);
                        break;
                    case LotteryCons.XYPK10:
                    case LotteryCons.SIXHECAI:
                    case LotteryCons.PAILIE3:
                    case LotteryCons.FUCAI3D:
                    case LotteryCons.PAILIE5:
                        handler.postDelayed(runnable, 10000);
                        break;
                }
            }
        }
    };


    /**
     * 计算时间差
     *
     * @return 返回时间差
     */
    public void getTime(long time) {
        if (time == 0) {
            tv_countdown.setText("开奖中");
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
        if ("00".contains(hours) && "00".contains(mins) && "00".contains(ss)) {
            tv_countdown.setText("开奖中");
        } else if ("00".contains(hours)) {
            tv_countdown.setText(mins + ":" + ss);
        } else {
            tv_countdown.setText(hours + ":" + mins + ":" + ss);
        }

    }

    public void stopCountDown() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void setTextColor(int color) {
        tv_countdown.setTextColor(getResources().getColor(color));
    }

    public void setTextSize(int size) {
        tv_countdown.setTextSize(size);
    }

    public void setOnCountDownListener(OnDragonCountDownListener listener) {
        this.listener = listener;
    }

    public interface OnDragonCountDownListener {
        void onFinish();
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public void removeCallback() {
        handler.removeCallbacks(runnable);
    }

    public int getTime_type() {
        return time_type;
    }

    public void setTime_type(int time_type) {
        this.time_type = time_type;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }

    public int getGame_type() {
        return game_type;
    }

    public void setGame_type(int game_type) {
        this.game_type = game_type;
    }
}

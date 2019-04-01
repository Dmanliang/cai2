package com.sp.caitwo.ui.info;

/**
 * Created by Administrator on 2018/6/21.
 */

public class LotteryBall {

    public String num;
    public boolean isFocus ;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public LotteryBall(String title){
        this.num = title;
    }

    public String getTitle() {
        return num;
    }

    public void setTitle(String title) {
        this.num = title;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }
}

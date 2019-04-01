package com.sp.caitwo.ui.info;

import java.util.ArrayList;


public class LotteryPlayInfo {

    private ArrayList<LotteryBall> balls;
    private String bit_text;
    private String select_text;
    private int select_balls;
    private int bit_number;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBit_text() {
        return bit_text;
    }

    public void setBit_text(String bit_text) {
        this.bit_text = bit_text;
    }

    public int getBit_number() {
        return bit_number;
    }

    public void setBit_number(int bit_number) {
        this.bit_number = bit_number;
    }

    public ArrayList<LotteryBall> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<LotteryBall> balls) {
        this.balls = balls;
    }

    public String getSelect_text() {
        return select_text;
    }

    public void setSelect_text(String select_text) {
        this.select_text = select_text;
    }

    public int getSelect_balls() {
        return select_balls;
    }

    public void setSelect_balls(int select_balls) {
        this.select_balls = select_balls;
    }
}

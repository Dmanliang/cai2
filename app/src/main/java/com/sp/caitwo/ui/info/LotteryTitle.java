package com.sp.caitwo.ui.info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Decml on 2017/3/18.
 */

public class LotteryTitle {

    private String lottery_title;
    private boolean isFocus = false;
    private List<LotteryTabInfo> lottery_tab_title = new ArrayList<>();
    private List<String> lottery_tab_bitcount = new ArrayList<>();
    private List<String> lottery_tab_selectcount = new ArrayList<>();
    private List<String> play_explain=new ArrayList<>();
    private List<String> ball_value = new ArrayList<>();
    private int type;

    public List<String> getLottery_tab_selectcount() {
        return lottery_tab_selectcount;
    }

    public void setLottery_tab_selectcount(List<String> lottery_tab_selectcount) {
        this.lottery_tab_selectcount = lottery_tab_selectcount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getBall_value() {
        return ball_value;
    }

    public void setBall_value(List<String> ball_value) {
        this.ball_value = ball_value;
    }

    public List<String> getLottery_tab_bitcount() {
        return lottery_tab_bitcount;
    }

    public void setLottery_tab_bitcount(List<String> lottery_tab_bitcount) {
        this.lottery_tab_bitcount = lottery_tab_bitcount;
    }

    public LotteryTitle(String lottery_title) {
        this.lottery_title = lottery_title;
    }

    public List<String> getPlay_explain() {
        return play_explain;
    }

    public void setPlay_explain(List<String> play_explain) {
        this.play_explain = play_explain;
    }

    public List<LotteryTabInfo> getLottery_tab_title() {
        return lottery_tab_title;
    }

    public void setLottery_tab_title(List<LotteryTabInfo> lottery_tab_title) {
        this.lottery_tab_title = lottery_tab_title;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }

    public String getLottery_title() {
        return lottery_title;
    }

    public void setLottery_title(String lottery_title) {
        this.lottery_title = lottery_title;
    }

}

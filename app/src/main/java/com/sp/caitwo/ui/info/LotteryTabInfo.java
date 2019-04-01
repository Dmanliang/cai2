package com.sp.caitwo.ui.info;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/21.
 */

public class LotteryTabInfo {

    public String title;
    public String tabcontent;
    public int tabcount;
    public int selectcount;
    public boolean isFocus= false;
    public ArrayList<String> ball_value = new ArrayList<>();
    public int type;

    public LotteryTabInfo(){
    }

    public int getSelectcount() {
        return selectcount;
    }

    public void setSelectcount(int selectcount) {
        this.selectcount = selectcount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<String> getBall_value() {
        return ball_value;
    }

    public void setBall_value(ArrayList<String> ball_value) {
        this.ball_value = ball_value;
    }

    public int getTabcount() {
        return tabcount;
    }

    public void setTabcount(int tabcount) {
        this.tabcount = tabcount;
    }

    public String getTabcontent() {
        return tabcontent;
    }

    public void setTabcontent(String tabcontent) {
        this.tabcontent = tabcontent;
    }

    public LotteryTabInfo(String title, String tabcontent){
        this.title = title;
        this.tabcontent = tabcontent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }
}

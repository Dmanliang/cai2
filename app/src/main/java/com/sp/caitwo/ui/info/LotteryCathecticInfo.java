package com.sp.caitwo.ui.info;

import android.os.Parcel;
import android.os.Parcelable;

public class LotteryCathecticInfo implements Parcelable {

    private double point;
    private int count;
    private int unit_type;
    private String wanfa_type;
    private String betStr;
    private String typeContent;
    private double allMoney;

    public LotteryCathecticInfo(){}

    protected LotteryCathecticInfo(Parcel in) {
        point = in.readDouble();
        count = in.readInt();
        unit_type = in.readInt();
        wanfa_type = in.readString();
        betStr = in.readString();
        typeContent = in.readString();
    }

    public static final Creator<LotteryCathecticInfo> CREATOR = new Creator<LotteryCathecticInfo>() {
        @Override
        public LotteryCathecticInfo createFromParcel(Parcel in) {
            return new LotteryCathecticInfo(in);
        }

        @Override
        public LotteryCathecticInfo[] newArray(int size) {
            return new LotteryCathecticInfo[size];
        }
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent() {
        typeContent = "["+wanfa_type+"]  "+count+"注  "+"每注"+point+"元";
    }

    public String getBetStr() {
        return betStr;
    }

    public void setBetStr(String betStr) {
        this.betStr = betStr;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public int getUnit_type() {
        return unit_type;
    }

    public void setUnit_type(int unit_type) {
        this.unit_type = unit_type;
    }

    public String getWanfa_type() {
        return wanfa_type;
    }

    public void setWanfa_type(String wanfa_type) {
        this.wanfa_type = wanfa_type;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public double getAllMoney() {
        allMoney = count * point;
        return allMoney;
    }

    public void setAllMoney(double allMoney) {
        this.allMoney = allMoney;
    }

    public static Creator<LotteryCathecticInfo> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(point);
        parcel.writeInt(count);
        parcel.writeInt(unit_type);
        parcel.writeString(wanfa_type);
        parcel.writeString(betStr);
        parcel.writeString(typeContent);
    }
}

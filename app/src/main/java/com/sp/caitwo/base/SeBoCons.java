package com.sp.caitwo.base;

import java.util.ArrayList;
import java.util.List;

public class SeBoCons {

    public List<String> redball = new ArrayList<>();
    public List<String> blueball = new ArrayList<>();
    public List<String> greenball = new ArrayList<>();
    public List<String> grayball =new ArrayList<>();
    public static final String[] grayballNumbers = {"0","13","14","27"};
    public static final SeBoCons seBoCons = new SeBoCons();

    public static final SeBoCons getInstant() {
        return seBoCons;
    }

    public List<String> getRedball() {
        return redball;
    }

    public void setRedball(List<String> redball) {
        this.redball = redball;
    }

    public List<String> getBlueball() {
        return blueball;
    }

    public void setBlueball(List<String> blueball) {
        this.blueball = blueball;
    }

    public List<String> getGreenball() {
        return greenball;
    }

    public void setGreenball(List<String> greenball) {
        this.greenball = greenball;
    }

    public List<String> getGrayball() {
        return grayball;
    }

    public void setGrayball(List<String> grayball) {
        this.grayball = grayball;
    }

    public static SeBoCons getSeBoCons() {
        return seBoCons;
    }
}

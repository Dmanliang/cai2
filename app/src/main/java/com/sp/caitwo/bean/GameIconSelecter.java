package com.sp.caitwo.bean;

public class GameIconSelecter {

    private String gameForShort;
    private int notPlayIcon;
    private int playIcon;

    public GameIconSelecter(String gameForShort, int notPlayIcon, int playIcon) {
        this.gameForShort = gameForShort;
        this.notPlayIcon = notPlayIcon;
        this.playIcon = playIcon;
    }

    public String getGameForShort() {
        return gameForShort;
    }

    public void setGameForShort(String gameForShort) {
        this.gameForShort = gameForShort;
    }

    public int getNotPlayIcon() {
        return notPlayIcon;
    }

    public void setNotPlayIcon(int notPlayIcon) {
        this.notPlayIcon = notPlayIcon;
    }

    public int getPlayIcon() {
        return playIcon;
    }

    public void setPlayIcon(int playIcon) {
        this.playIcon = playIcon;
    }
}

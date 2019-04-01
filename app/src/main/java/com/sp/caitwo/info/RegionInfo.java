package com.sp.caitwo.info;

public class RegionInfo {

    private int id;
    private int parentId;
    private String name;

    public RegionInfo(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }
}

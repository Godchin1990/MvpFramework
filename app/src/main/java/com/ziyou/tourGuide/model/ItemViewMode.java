package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/1/15.
 */
public class ItemViewMode {
    int titleId;
    int iconId;
    public ItemViewMode(int titleId) {
        this.titleId = titleId;
    }

    public ItemViewMode(int titleId,int iconId) {
        this.titleId = titleId;
        this.iconId = iconId;
    }

    public int getTitleId() {
        return titleId;
    }

    public int getIconId() {
        return iconId;
    }
}

package com.ziyou.tourGuide.command.base;

import android.os.Bundle;

/**
 * Created by Edward on 16/1/13.
 */
public abstract class WebBundleCommand implements Command {
    private final String json;
    private final Bundle bundle;
    private Object[] objects;

    /**
     * @param json 从JS传过来的数据
     * @param bundle 传递已经实现Parcel接口的对象
     * @param objects 传递未实现Parcelable接口的对象
     */
    public WebBundleCommand(String json, Bundle bundle,Object... objects) {
        this.json = json;
        this.bundle = bundle;
        this.objects = objects;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public String getJson() {
        return json;
    }

    public Object[] getObjects() {
        return objects;
    }
}

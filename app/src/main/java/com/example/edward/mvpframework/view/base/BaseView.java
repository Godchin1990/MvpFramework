package com.example.edward.mvpframework.view.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Edward on 16/1/1.
 */
public abstract class BaseView {
    private final Context context;
    private final View bootView;

    public BaseView(Context context) {
        this.context = context;
        bootView = initView();
    }
    abstract protected View initView();

    public Context getContext() {
        return context;
    }
    public View getBootView() {
        return bootView;
    }
}

package com.example.edward.mvpframework.view.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Edward on 16/1/1.
 */
public abstract class BaseView {
    public final String TAG;
    {
        TAG = getClass().getSimpleName();
    }
    private final Context context;
    private final View rootView;

    public BaseView(Context context) {
        this.context = context;
        rootView = initView();
    }
    abstract protected View initView();

    public Context getContext() {
        return context;
    }
    public View getRootView() {
        return rootView;
    }
}

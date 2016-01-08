package com.example.edward.mvpframework.widget.refreshview;

import android.content.Context;
import android.view.View;

/**
 * Created by Edward on 16/1/8.
 */
public abstract class RefreshViewAdapter {
    private Context context;
    public RefreshViewAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract View setSuccessView();
    public abstract View setErrorView();
    public abstract View setEmptyView();
    public abstract View setLoadingView();
}

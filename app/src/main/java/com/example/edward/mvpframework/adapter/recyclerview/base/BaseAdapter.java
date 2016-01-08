package com.example.edward.mvpframework.adapter.recyclerview.base;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Edward on 16/1/6.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter {
    protected final String TAG;
    {
        TAG = getClass().getSimpleName();
    }
}

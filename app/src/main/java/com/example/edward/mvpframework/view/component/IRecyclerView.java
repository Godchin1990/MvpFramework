package com.example.edward.mvpframework.view.component;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Edward on 16/1/4.
 */
public interface IRecyclerView<T extends RecyclerView.Adapter> {
    RecyclerView getRecyclerView();
    T getAdapter();
}

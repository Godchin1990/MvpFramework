package com.example.edward.mvpframework.view.interfaze;

import android.support.v7.widget.RecyclerView;
import android.widget.PopupWindow;

import com.example.edward.mvpframework.adapter.recyclerview.DiscoveryAdapter;
import com.example.edward.mvpframework.view.component.IPullToRefreshRecyclerView;
import com.example.edward.mvpframework.view.component.IRecyclerView;
import com.example.edward.mvpframework.view.component.IRefreshViewContainer;

/**
 * Created by Edward on 16/1/10.
 */
public interface IDiscoveryView<T extends RecyclerView.Adapter> extends IRecyclerView<DiscoveryAdapter>, IPullToRefreshRecyclerView,IRefreshViewContainer {
    PopupWindow getCityListPopupWindow();
    RecyclerView getCityListRecyclerView();
    T getCityListAdapter();
}

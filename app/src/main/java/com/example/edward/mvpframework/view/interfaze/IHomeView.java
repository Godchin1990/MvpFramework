package com.example.edward.mvpframework.view.interfaze;

import com.example.edward.mvpframework.adapter.recyclerview.HomeAdapter;
import com.example.edward.mvpframework.view.component.IPullToRefreshRecyclerView;
import com.example.edward.mvpframework.view.component.IRecyclerView;
import com.example.edward.mvpframework.view.component.IRefreshViewContainer;

/**
 * Created by Edward on 16/1/1.
 */
public interface IHomeView extends IRecyclerView<HomeAdapter>, IPullToRefreshRecyclerView,IRefreshViewContainer {

}

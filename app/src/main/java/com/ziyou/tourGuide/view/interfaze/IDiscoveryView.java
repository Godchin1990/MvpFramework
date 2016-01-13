package com.ziyou.tourGuide.view.interfaze;

import android.support.v7.widget.RecyclerView;
import android.widget.PopupWindow;

import com.ziyou.tourGuide.adapter.recyclerview.DiscoveryAdapter;
import com.ziyou.tourGuide.view.component.IPullToRefreshRecyclerView;
import com.ziyou.tourGuide.view.component.IRecyclerView;
import com.ziyou.tourGuide.view.component.IRefreshViewContainer;

/**
 * Created by Edward on 16/1/10.
 */
public interface IDiscoveryView<T extends RecyclerView.Adapter> extends IRecyclerView<DiscoveryAdapter>, IPullToRefreshRecyclerView,IRefreshViewContainer {
    PopupWindow getCityListPopupWindow();
    RecyclerView getCityListRecyclerView();
    T getCityListAdapter();
}

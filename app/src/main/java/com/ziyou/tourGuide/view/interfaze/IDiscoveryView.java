package com.ziyou.tourGuide.view.interfaze;

import android.support.v7.widget.RecyclerView;
import android.widget.PopupWindow;

import com.ziyou.tourGuide.adapter.recyclerview.CityListAdapter;

/**
 * Created by Edward on 16/1/10.
 */
public interface IDiscoveryView/*<T extends RecyclerView.Adapter> extends IRecyclerView2<T>, IPullToRefreshRecyclerView,IRefreshViewContainer*/ {
    PopupWindow getCityListPopupWindow();
    RecyclerView getCityListRecyclerView();
    CityListAdapter getCityListAdapter();
}

package com.ziyou.tourGuide.view.interfaze;

import android.support.v7.widget.RecyclerView;

import com.ziyou.tourGuide.view.component.IPullToRefreshRecyclerView;
import com.ziyou.tourGuide.view.component.IRefreshViewContainer;

/**
 * Created by Edward on 16/1/28.
 */
public interface IRefreshRecyclerView<T extends RecyclerView.Adapter> extends IPullToRefreshRecyclerView,IRefreshViewContainer,IRecyclerView2<T> {
}

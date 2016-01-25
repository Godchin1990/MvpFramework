package com.ziyou.tourGuide.view.interfaze;

import com.ziyou.tourGuide.adapter.recyclerview.RouteCommunityAdapter;
import com.ziyou.tourGuide.view.component.IPullToRefreshRecyclerView;
import com.ziyou.tourGuide.view.component.IRecyclerView;
import com.ziyou.tourGuide.view.component.IRefreshViewContainer;

/**
 * Created by Edward on 16/1/25.
 */
public interface IMyRouteView extends IRecyclerView<RouteCommunityAdapter>, IPullToRefreshRecyclerView,IRefreshViewContainer {
}

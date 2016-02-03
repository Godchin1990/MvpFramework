package com.ziyou.tourGuide.view.interfaze;

import com.ziyou.tourGuide.adapter.recyclerview.DiscoveryDetailAdapter;
import com.ziyou.tourGuide.view.component.IPullToRefreshRecyclerView;
import com.ziyou.tourGuide.view.component.IRecyclerView;
import com.ziyou.tourGuide.view.component.IRefreshViewContainer;

/**
 * @deprecated
 * Created by Edward on 16/1/8.
 */
public interface IDiscoveryDetailView extends IRecyclerView<DiscoveryDetailAdapter>, IPullToRefreshRecyclerView,IRefreshViewContainer {
}

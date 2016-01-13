package com.ziyou.tourGuide.view.interfaze;

import com.ziyou.tourGuide.adapter.recyclerview.HomeAdapter;
import com.ziyou.tourGuide.view.component.IPullToRefreshRecyclerView;
import com.ziyou.tourGuide.view.component.IRecyclerView;
import com.ziyou.tourGuide.view.component.IRefreshViewContainer;

/**
 * Created by Edward on 16/1/1.
 */
public interface IHomeView extends IRecyclerView<HomeAdapter>, IPullToRefreshRecyclerView,IRefreshViewContainer {

}

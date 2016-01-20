package com.ziyou.tourGuide.view.interfaze;

import com.ziyou.tourGuide.adapter.recyclerview.GuiderOrderAdapter;
import com.ziyou.tourGuide.view.component.IPullToRefreshRecyclerView;
import com.ziyou.tourGuide.view.component.IRecyclerView;
import com.ziyou.tourGuide.view.component.IRefreshViewContainer;

/**
 * Created by Edward on 16/1/19.
 */
public interface IGuiderOrderView extends IRecyclerView<GuiderOrderAdapter>, IPullToRefreshRecyclerView,IRefreshViewContainer {
}

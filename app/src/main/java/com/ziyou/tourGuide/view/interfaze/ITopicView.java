package com.ziyou.tourGuide.view.interfaze;

import com.ziyou.tourGuide.adapter.recyclerview.TopicAdapter;
import com.ziyou.tourGuide.view.component.IPullToRefreshRecyclerView;
import com.ziyou.tourGuide.view.component.IRecyclerView;
import com.ziyou.tourGuide.view.component.IRefreshViewContainer;

/**
 * Created by Edward on 16/1/8.
 */
public interface ITopicView extends IRecyclerView<TopicAdapter>, IPullToRefreshRecyclerView,IRefreshViewContainer {
}

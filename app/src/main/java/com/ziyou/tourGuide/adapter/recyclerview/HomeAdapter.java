package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.EmptyViewHolder;
import com.ziyou.tourGuide.adapter.recyclerview.holder.HomeBannerViewHolder;
import com.ziyou.tourGuide.adapter.recyclerview.holder.HomeRouteViewHolder;
import com.ziyou.tourGuide.adapter.recyclerview.holder.HomeTopicViewHolder;
import com.ziyou.tourGuide.model.HomeBanner;
import com.ziyou.tourGuide.model.HomeRoute;
import com.ziyou.tourGuide.model.HomeTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/4.
 */
public class HomeAdapter extends BaseAdapter {

    public static final int TYPE_BANNER = 0;
    public static final int TYPE_TOPIC = 1;
    public static final int TYPE_ROUTE = 2;
    public static final int TYPE_EMPTY = 3;

    private List<HomeBanner> banners = new ArrayList<>();
    private List<HomeTopic> topics = new ArrayList<>();
    private List<HomeRoute> routes = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BANNER:
                View bannerView = HomeBannerViewHolder.getView(parent.getContext());
                HomeBannerViewHolder homeBannerViewHolder = new HomeBannerViewHolder(bannerView);
                return homeBannerViewHolder;
            case TYPE_TOPIC:
                View topicView = HomeTopicViewHolder.getView(parent.getContext());
                HomeTopicViewHolder homeTopicViewHolder = new HomeTopicViewHolder(topicView);
                return homeTopicViewHolder;
            case TYPE_ROUTE:
                View routeView = HomeRouteViewHolder.getView(parent.getContext());
                HomeRouteViewHolder routeViewHolder = new HomeRouteViewHolder(routeView);
                return routeViewHolder;
            default:
                View view = EmptyViewHolder.getView(parent.getContext());
                return new EmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            HomeBannerViewHolder bannerViewHolder = (HomeBannerViewHolder) holder;
            bannerViewHolder.setData(banners);
        } else if (position > 0 && position < topics.size() + 1) {
            HomeTopicViewHolder topicViewHolder = (HomeTopicViewHolder) holder;
            topicViewHolder.setData(topics.get(position - 1));
        } else if (position >= topics.size() + 1 && position < routes.size() + topics.size() + 1) {
            HomeRouteViewHolder routeViewHolder = (HomeRouteViewHolder) holder;
            routeViewHolder.setData(routes.get(position - topics.size() - 1));
        } else {
            //加载更多界面
        }
    }

    @Override
    public int getItemCount() {
        return 1 + topics.size() + routes.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position < topics.size() + 1) {
            return TYPE_TOPIC;
        } else if (position < routes.size() + topics.size() + 1) {
            return TYPE_ROUTE;
        } else {
            return TYPE_EMPTY;
        }
    }

    public void setBanners(List<HomeBanner> list) {
        banners = list;
        notifyItemRangeChanged(0, 1);
    }

    public void setTopics(List<HomeTopic> list) {
        topics = list;
        notifyItemRangeChanged(1, list.size());
    }

    public void setRoutes(List<HomeRoute> list) {
        routes = list;
        notifyItemRangeChanged(1 + topics.size(), list.size());
    }

    public void appendRoutes(List<HomeRoute> list) {
        routes.addAll(routes.size(), list);
        notifyItemRangeInserted(getItemCount(), list.size() + 1);
    }

}

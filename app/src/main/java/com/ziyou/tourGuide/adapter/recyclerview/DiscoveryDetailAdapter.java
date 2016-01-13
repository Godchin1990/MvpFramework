package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.DiscoveryDetailBannerViewHolder;
import com.ziyou.tourGuide.adapter.recyclerview.holder.EmptyViewHolder;
import com.ziyou.tourGuide.adapter.recyclerview.holder.HomeRouteViewHolder;
import com.ziyou.tourGuide.model.DiscoveryDetail;
import com.ziyou.tourGuide.model.HomeRoute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/12.
 */
public class DiscoveryDetailAdapter extends BaseAdapter {

    private static final int TYPE_BANNER = 0;
    private static final int TYPE_ROUTE = 1;
    private static final int TYPE_EMPTY = 2;

    private DiscoveryDetail.BannerEntity bannerEntity;
    private List<HomeRoute> routes = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        TextView view = new TextView(parent.getContext());
//        view.setText("discovery demo");
//        return new EmptyViewHolder(view);
        switch (viewType) {
            case TYPE_BANNER:
                View bannerView = DiscoveryDetailBannerViewHolder.getView(parent.getContext());
                DiscoveryDetailBannerViewHolder discoveryDetailBannerViewHolder = new DiscoveryDetailBannerViewHolder(bannerView);
                return discoveryDetailBannerViewHolder;
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
        if (position == 0 ) {
            if(bannerEntity!=null){
                DiscoveryDetailBannerViewHolder bannerViewHolder = (DiscoveryDetailBannerViewHolder) holder;
                bannerViewHolder.setData(bannerEntity);
            }
        } else if (position > 0 && position < routes.size() + 1) {
            HomeRouteViewHolder topicViewHolder = (HomeRouteViewHolder) holder;
            topicViewHolder.setData(routes.get(position - 1));
        } else {
            //加载更多界面
        }
    }

    @Override
    public int getItemCount() {
        return routes.size()+1;
    }

    public void setBanner(DiscoveryDetail.BannerEntity banner){
        if(bannerEntity==null){
            bannerEntity = banner;
        }
    }

    public void setRoutes(List<HomeRoute> routes) {
        this.routes = routes;
        notifyDataSetChanged();
    }

    public void appendDiscoveryDetail(List<HomeRoute> discoveryDetail) {
        discoveryDetail.addAll(discoveryDetail.size(), discoveryDetail);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position < routes.size() + 1) {
            return TYPE_ROUTE;
        } else {
            return TYPE_EMPTY;
        }
    }
}

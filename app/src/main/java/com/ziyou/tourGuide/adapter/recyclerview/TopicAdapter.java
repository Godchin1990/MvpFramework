package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.HomeRouteViewHolder;
import com.ziyou.tourGuide.model.HomeRoute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/8.
 */
public class TopicAdapter extends BaseAdapter {

    private List<HomeRoute> routes = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View routeView = HomeRouteViewHolder.getView(parent.getContext());
        return new HomeRouteViewHolder(routeView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeRouteViewHolder routeViewHolder = (HomeRouteViewHolder) holder;
        routeViewHolder.setData(routes.get(position));
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public void setRoutes(List<HomeRoute> list) {
        routes = list;
        notifyItemRangeChanged(0, list.size());
    }

    public void appendRoutes(List<HomeRoute> list) {
        routes.addAll(routes.size(), list);
        notifyItemRangeInserted(getItemCount(), list.size());
    }
}

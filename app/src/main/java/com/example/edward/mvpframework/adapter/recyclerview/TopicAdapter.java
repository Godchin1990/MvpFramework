package com.example.edward.mvpframework.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.edward.mvpframework.adapter.recyclerview.base.BaseAdapter;
import com.example.edward.mvpframework.adapter.recyclerview.holder.HomeRouteViewHolder;

/**
 * Created by Edward on 16/1/8.
 */
public class TopicAdapter extends BaseAdapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View routeView = HomeRouteViewHolder.getView(parent.getContext());
        return new HomeRouteViewHolder(routeView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}

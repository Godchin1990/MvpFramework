package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.RouteCommunityViewHolder;
import com.ziyou.tourGuide.model.RouteCommunity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/25.
 */
public class RouteCommunityAdapter extends BaseAdapter {
    List<RouteCommunity> routeCommunities = new ArrayList<>();
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = RouteCommunityViewHolder.getView(parent.getContext());
        return new RouteCommunityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RouteCommunityViewHolder routeCommunityViewHolder = (RouteCommunityViewHolder) holder;
        routeCommunityViewHolder.setData(routeCommunities.get(position));
    }

    @Override
    public int getItemCount() {
        return routeCommunities.size();
    }

    public void setRouteCommunities(List<RouteCommunity> list) {
        routeCommunities = list;
        notifyDataSetChanged();
    }
    public void appendRouteCommunities(List<RouteCommunity> list) {
        routeCommunities.addAll(routeCommunities.size(), list);
        notifyItemRangeInserted(getItemCount(), list.size());
    }
}

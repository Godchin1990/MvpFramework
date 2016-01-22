package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.ReceiveRouteViewHolder;
import com.ziyou.tourGuide.model.ReceiveRoute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/21.
 */
public class ReceiveRouteAdapter extends BaseAdapter {

    private List<ReceiveRoute> receiveRoutes = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ReceiveRouteViewHolder.getView(parent.getContext());
        return new ReceiveRouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReceiveRouteViewHolder receiveRouteViewHolder = (ReceiveRouteViewHolder) holder;
        receiveRouteViewHolder.setData(receiveRoutes.get(position));
    }

    @Override
    public int getItemCount() {
        return receiveRoutes.size();
    }

    public void setReceiveRoutes(List<ReceiveRoute> list) {
        receiveRoutes = list;
        notifyDataSetChanged();
    }

    public void appendReceiveRoutes(List<ReceiveRoute> list) {
        receiveRoutes.addAll(receiveRoutes.size(), list);
        notifyItemRangeInserted(getItemCount(), list.size());
    }
}

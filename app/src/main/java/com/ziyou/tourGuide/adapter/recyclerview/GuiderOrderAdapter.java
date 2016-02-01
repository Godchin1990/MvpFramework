package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.GuiderOrderViewHolder;
import com.ziyou.tourGuide.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/19.
 */
public class GuiderOrderAdapter extends BaseAdapter {

    List<Order> orders = new ArrayList<>();
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = GuiderOrderViewHolder.getView(parent.getContext());
        return new GuiderOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GuiderOrderViewHolder bannerViewHolder = (GuiderOrderViewHolder) holder;
        bannerViewHolder.setData(position, orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(List<Order> list) {
        orders = list;
        notifyDataSetChanged();
    }

}

package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.OrderViewHolder;
import com.ziyou.tourGuide.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/8.
 */
public class MyTourAdapter extends BaseAdapter {

    private List<Order> orders = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = OrderViewHolder.getView(parent.getContext());
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
        orderViewHolder.setData(position, orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(List<Order> list) {
        orders = list;
        notifyDataSetChanged();
    }

    public void appendOrders(List<Order> list) {
        orders.addAll(orders.size(), list);
        notifyItemRangeInserted(getItemCount(), list.size());
    }
}

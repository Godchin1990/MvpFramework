package com.example.edward.mvpframework.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edward.mvpframework.adapter.recyclerview.base.BaseAdapter;
import com.example.edward.mvpframework.adapter.recyclerview.holder.EmptyViewHolder;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryAdapter extends BaseAdapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setText("测试");
        return new EmptyViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

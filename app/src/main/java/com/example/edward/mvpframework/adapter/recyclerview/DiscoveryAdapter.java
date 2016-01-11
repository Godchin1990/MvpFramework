package com.example.edward.mvpframework.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.edward.mvpframework.adapter.recyclerview.base.BaseAdapter;
import com.example.edward.mvpframework.adapter.recyclerview.holder.DiscoveryFullViewHolder;
import com.example.edward.mvpframework.adapter.recyclerview.holder.DiscoveryHasMapViewHolder;
import com.example.edward.mvpframework.adapter.recyclerview.holder.EmptyViewHolder;
import com.example.edward.mvpframework.model.Discovery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryAdapter extends BaseAdapter {

    public static final int TYPE_HAS_MAP = 0;
    public static final int TYPE_FULL_VIEW = 1;
    public static final int TYPE_EMPTY = 2;

    private List<Discovery> discoveries = new ArrayList<>();



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HAS_MAP : // 带有map的
                View hasMapView = DiscoveryHasMapViewHolder.getView(parent.getContext());
                return new DiscoveryHasMapViewHolder(hasMapView);
            case TYPE_FULL_VIEW: // 占全部宽度的
                View fullView = DiscoveryFullViewHolder.getView(parent.getContext());
                return new DiscoveryFullViewHolder(fullView);
//            case TYPE_NORMAL_PLAIN : // 普通的

            default:
                View view = EmptyViewHolder.getView(parent.getContext());
                return new EmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Discovery discovery = discoveries.get(position);
        switch (discovery.getType()){
            case 0:
                DiscoveryHasMapViewHolder hasMapViewHolder = (DiscoveryHasMapViewHolder) holder;
                hasMapViewHolder.setData(discovery);
                break;
            case 1:
                DiscoveryFullViewHolder fullViewHolder = (DiscoveryFullViewHolder) holder;
                fullViewHolder.setData(discovery);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return discoveries.size();
    }

    @Override
    public int getItemViewType(int position) {
        Discovery discovery = discoveries.get(position);
        switch (discovery.getType()){
            case 0:
                return TYPE_HAS_MAP;
            case 1:
                return TYPE_FULL_VIEW;
            default:
                return TYPE_EMPTY;
        }
    }

    public void setDiscovery(List<Discovery> list) {
        this.discoveries = list;
        notifyDataSetChanged();
    }
    public void appendDiscovery(List<Discovery> list) {
        discoveries.addAll(discoveries.size(),list);
        notifyItemRangeInserted(getItemCount(), list.size());
    }
}

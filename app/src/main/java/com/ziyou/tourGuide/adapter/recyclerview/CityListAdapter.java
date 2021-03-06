package com.ziyou.tourGuide.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziyou.tourGuide.adapter.recyclerview.base.BaseAdapter;
import com.ziyou.tourGuide.adapter.recyclerview.holder.CityViewHolder;
import com.ziyou.tourGuide.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/10.
 */
public class CityListAdapter extends BaseAdapter {

    private List<City> cities = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        return new CityViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CityViewHolder routeViewHolder = (CityViewHolder) holder;
        routeViewHolder.setData(position, cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void setCity(List<City> list) {
        cities = list;
        notifyDataSetChanged();
    }
}

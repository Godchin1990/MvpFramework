package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryHasMapViewHolder extends BaseViewHolder {

    public static View getView(Context context) {
//        View bannerView = LayoutInflater.from(context).inflate(R.layout.item_home_discover_plain_list, null);
        TextView bannerView = new TextView(context);
        bannerView.setText("有地图的");
        return bannerView;
    }

    public DiscoveryHasMapViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void inflateView(Object data) {

    }
}

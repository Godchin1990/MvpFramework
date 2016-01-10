package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryFullViewHolder extends BaseViewHolder {

    public static View getView(Context context) {
//        View fullView = LayoutInflater.from(activity).inflate(R.layout.item_home_discover_plain, null);
        TextView bannerView = new TextView(context);
        bannerView.setText("没有地图的");
        return bannerView;
    }

    public DiscoveryFullViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void inflateView(Object data) {

    }
}

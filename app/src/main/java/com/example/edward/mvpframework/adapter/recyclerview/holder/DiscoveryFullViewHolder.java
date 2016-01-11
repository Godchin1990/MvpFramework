package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.model.Discovery;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryFullViewHolder extends BaseViewHolder<Discovery> {

    public static View getView(Context context) {
        View fullView = View.inflate(context, R.layout.item_discovery_no_map,null);
        return fullView;
    }

    public DiscoveryFullViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void inflateView(Discovery data) {

    }
}

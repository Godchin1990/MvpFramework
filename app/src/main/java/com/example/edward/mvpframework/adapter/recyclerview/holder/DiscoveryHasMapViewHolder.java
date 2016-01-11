package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;

import com.amap.api.maps.AMap;
import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.model.Discovery;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryHasMapViewHolder extends BaseViewHolder<Discovery> {

    private final SimpleDraweeView left_top;
    private final SimpleDraweeView left_bottom;
    private final SimpleDraweeView right_top;
    private final SimpleDraweeView right_bottom;
    private AMap aMap;

    public static View getView(Context context) {
        View hasmapview = View.inflate(context,R.layout.item_discovery_has_map,null);
        return hasmapview;
    }

    public DiscoveryHasMapViewHolder(View itemView) {
        super(itemView);

        left_top = (SimpleDraweeView) itemView.findViewById(R.id.left_top);
        left_bottom = (SimpleDraweeView) itemView.findViewById(R.id.left_bottom);
        right_top = (SimpleDraweeView) itemView.findViewById(R.id.right_top);
        right_bottom = (SimpleDraweeView) itemView.findViewById(R.id.right_bottom);

    }

    @Override
    protected void inflateView(Discovery data) {
        if(data.getList().size()<4){
            //高德地图静态api
//            String url = "http://restapi.amap.com/v3/staticmap?location="
//                    + longitude + ","
//                    + latitude + "&zoom=16&size="
//                    + (screenWidth /2/3) + "*"
//                    + (mapImageViewHeight /3 +200) + "&markers=large,0x77B4EF,:" +
//                    + longitude + ","
//                    + latitude + "&key=ee95e52bf08006f63fd29bcfbcf21df0&scale=2";
        }

    }
}

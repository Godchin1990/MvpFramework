package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.activity.DiscoveryDetailActivity;
import com.example.edward.mvpframework.activity.base.Const;
import com.example.edward.mvpframework.command.SimpleDraweeViewCommand;
import com.example.edward.mvpframework.command.base.Command;
import com.example.edward.mvpframework.helper.LocationHelper;
import com.example.edward.mvpframework.model.Discovery;
import com.example.edward.mvpframework.util.ScreenHelper;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryHasMapViewHolder extends BaseViewHolder<Discovery> implements View.OnClickListener {

    private final SimpleDraweeView left_top;
    private final SimpleDraweeView left_bottom;
    private final SimpleDraweeView right_top;
    private final SimpleDraweeView right_bottom;

    private View leftTop;
    private View leftBottom;
    private View rightTop;
    private View rightBottom;


    private int screenWidth;
    private String key = "ee95e52bf08006f63fd29bcfbcf21df0";


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

        leftTop = itemView.findViewById(R.id.left_top_desc);
        leftBottom = itemView.findViewById(R.id.left_bottom_desc);
        rightTop = itemView.findViewById(R.id.right_top_desc);
        rightBottom = itemView.findViewById(R.id.right_bottom_desc);

        if(screenWidth==0){
            screenWidth = ScreenHelper.getScreenWidth(itemView.getContext());
        }
    }

    @Override
    protected void inflateView(Discovery data) {

        if(data.getList().size()>=3){
            if(data.getPosition()==0){
                bindMap(right_top,rightTop);
            }else {
                bindDiscovery(right_top,rightTop,data.getList().get(3));
            }
            bindDiscovery(left_top,leftTop,data.getList().get(0));
            bindDiscovery(left_bottom,leftBottom,data.getList().get(1));
            bindDiscovery(right_bottom,rightBottom,data.getList().get(2));

        }

    }

    private void bindDiscovery(SimpleDraweeView simpleDraweeView,View desc,Discovery.ListEntity model){
        desc.setVisibility(View.VISIBLE);
        TextView tv_title = (TextView) desc.findViewById(R.id.tv_title);
        TextView tv_desc = (TextView) desc.findViewById(R.id.tv_desc);
        Command simpleDraweeViewCommand = new SimpleDraweeViewCommand(simpleDraweeView,model.getCover());
        tv_title.setText(model.getTitle());
        tv_desc.setText(model.getRoute_num()+"条路线");
        simpleDraweeViewCommand.execute();
        simpleDraweeView.setOnClickListener(this);
        simpleDraweeView.setTag(model);
    }

    private void bindMap(final SimpleDraweeView simpleDraweeView,View desc){
        desc.setVisibility(View.GONE);
        LocationHelper.getInstance().getCurrentLocation(itemView.getContext(), new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                String url = "http://restapi.amap.com/v3/staticmap?location="
                        + aMapLocation.getLongitude() + ","
                        + aMapLocation.getLatitude() + "&zoom=16&size="
                        + (screenWidth / 2 / 3) + "*"
                        + (right_top.getHeight() / 3 + 200) + "&markers=large,0x77B4EF,:" +
                        +aMapLocation.getLongitude() + ","
                        + aMapLocation.getLatitude() + "&key=" + key + "&scale=2";

                Log.d(TAG, "lat=" + aMapLocation.getLatitude() + "  log" + aMapLocation.getLongitude());
                Command mapCommand = new SimpleDraweeViewCommand(simpleDraweeView, url);
                mapCommand.execute();
                simpleDraweeView.setTag(aMapLocation);
            }
        });

    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if(tag instanceof Discovery.ListEntity){
            Discovery.ListEntity entity = (Discovery.ListEntity) tag;
            Intent intent = new Intent(v.getContext(), DiscoveryDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Const.DISCOVERY_ID,entity.getId()+"");
            intent.putExtra(Const.BUNDLE,bundle);
            v.getContext().startActivity(intent);
        }
        if(tag instanceof AMapLocation){
            AMapLocation aMapLocation = (AMapLocation) tag;
            Log.d(TAG, "pass lat=" + aMapLocation.getLatitude() + "  log" + aMapLocation.getLongitude());
        }
    }
}

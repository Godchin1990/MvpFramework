package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.command.SimpleDraweeViewCommand;
import com.example.edward.mvpframework.command.base.Command;
import com.example.edward.mvpframework.model.DiscoveryDetail;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 16/1/12.
 */
public class DiscoveryDetailBannerViewHolder extends BaseViewHolder<DiscoveryDetail.BannerEntity> {

    private final SimpleDraweeView banner_image;

    public static View getView(Context context) {
        View view = View.inflate(context, R.layout.item_discovery_detail_banner,null);
        return view;
    }

    public DiscoveryDetailBannerViewHolder(View itemView) {
        super(itemView);
        banner_image = (SimpleDraweeView) itemView.findViewById(R.id.banner_image);
    }

    @Override
    protected void inflateView(DiscoveryDetail.BannerEntity data) {
        Command simpleDraweeViewCommand = new SimpleDraweeViewCommand(banner_image,data.getCover());
        simpleDraweeViewCommand.execute();
    }
}

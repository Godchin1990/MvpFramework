package com.ziyou.tourGuide.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.command.SimpleDraweeViewCommand;
import com.ziyou.tourGuide.command.base.Command;
import com.ziyou.tourGuide.model.DiscoveryDetail;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 16/1/12.
 */
public class DiscoveryDetailBannerViewHolder extends BaseViewHolder<DiscoveryDetail.BannerEntity> {

    private final SimpleDraweeView banner_image;
    private final TextView title;

    public static View getView(Context context) {
        View view = View.inflate(context, R.layout.item_discovery_detail_banner,null);
        return view;
    }

    public DiscoveryDetailBannerViewHolder(View itemView) {
        super(itemView);
        banner_image = (SimpleDraweeView) itemView.findViewById(R.id.banner_image);
        title = (TextView) itemView.findViewById(R.id.title);
    }

    @Override
    protected void inflateView(DiscoveryDetail.BannerEntity data) {
        title.setText(data.getTitle());
        Command simpleDraweeViewCommand = new SimpleDraweeViewCommand(banner_image,data.getCover());
        simpleDraweeViewCommand.execute();
    }
}

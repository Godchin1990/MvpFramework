package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.command.SimpleDraweeViewCommand;
import com.example.edward.mvpframework.command.base.Command;
import com.example.edward.mvpframework.model.Discovery;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryHasMapViewHolder extends BaseViewHolder<Discovery> {

//    private final SimpleDraweeView left_top;
    private final SimpleDraweeView left_bottom;
    private final SimpleDraweeView right_top;
    private final SimpleDraweeView right_bottom;

    public static View getView(Context context) {
        View hasmapview = View.inflate(context,R.layout.item_discovery_has_map,null);
        return hasmapview;
    }

    public DiscoveryHasMapViewHolder(View itemView) {
        super(itemView);

//        left_top = (SimpleDraweeView) itemView.findViewById(R.id.left_top);
        left_bottom = (SimpleDraweeView) itemView.findViewById(R.id.left_bottom);
        right_top = (SimpleDraweeView) itemView.findViewById(R.id.right_top);
        right_bottom = (SimpleDraweeView) itemView.findViewById(R.id.right_bottom);

    }

    @Override
    protected void inflateView(Discovery data) {
//        Command commandForAvatar1 = new SimpleDraweeViewCommand(left_top,data.getList().get(0).getCover());
//        commandForAvatar1.execute();
        Command commandForAvatar2 = new SimpleDraweeViewCommand(left_bottom,data.getList().get(0).getCover());
        commandForAvatar2.execute();
        Command commandForAvatar3 = new SimpleDraweeViewCommand(right_top,data.getList().get(0).getCover());
        commandForAvatar3.execute();
        Command commandForAvatar4 = new SimpleDraweeViewCommand(right_bottom,data.getList().get(0).getCover());
        commandForAvatar4.execute();

    }
}

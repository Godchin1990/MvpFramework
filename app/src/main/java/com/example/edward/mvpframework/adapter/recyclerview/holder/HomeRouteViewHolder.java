package com.example.edward.mvpframework.adapter.recyclerview.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.command.base.Command;
import com.example.edward.mvpframework.command.SimpleDraweeViewCommand;
import com.example.edward.mvpframework.model.HomeRoute;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edward on 15/11/8.
 */
public class HomeRouteViewHolder extends BaseViewHolder<HomeRoute> implements View.OnClickListener {

    public SimpleDraweeView routeCover;

    public SimpleDraweeView riv_guide_avatar;
    public TextView tv_server_pay;
    public TextView route_title;

    public static View getView(Context context) {
        View routeView = LayoutInflater.from(context).inflate(R.layout.item_home_route, null);
        return routeView;
    }

    public HomeRouteViewHolder(View itemView) {
        super(itemView);
        routeCover = (SimpleDraweeView) itemView.findViewById(R.id.home_route_iv);
        riv_guide_avatar = (SimpleDraweeView) itemView.findViewById(R.id.riv_guide_avatar);
        route_title = (TextView) itemView.findViewById(R.id.route_title);
        tv_server_pay = (TextView) itemView.findViewById(R.id.tv_server_pay);

    }

    @Override
    protected void inflateView(HomeRoute data) {

        Command commandForAvatar = new SimpleDraweeViewCommand(riv_guide_avatar,data.getAvatar());
        commandForAvatar.execute();

        Command commandForCover = new SimpleDraweeViewCommand(routeCover,data.getCover());
        commandForCover.execute();

        route_title.setText(data.getTitle());

        tv_server_pay.setText(data.getPrice());

//        wv_tag.setTagColor(R.color.home_route_text_tag_color);
//        wv_tag.setTagBackground(R.drawable.bg_item_search_tag_selector);
//        wv_tag.setTagSize(15);
//        wv_tag.addChildTagView(data.getLabel(), 3);

//        routeCover.setTag(data);
//        routeCover.setOnClickListener(this);
//        riv_guide_avatar.setTag(data);
//        riv_guide_avatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.home_route_iv:
//                HomeRoute tagForRoute = (HomeRoute) v.getTag();
//                Intent routeIntent = new Intent(v.getContext(), RouteDetailWebActivity.class);
//                Bundle bundleForRoute = new Bundle();
//                bundleForRoute.putString(IntentConst.ROUTE_TITLE, tagForRoute.getTitle());
//                bundleForRoute.putString(IntentConst.ROUTE_COVER, tagForRoute.getCover());
//                bundleForRoute.putInt(IntentConst.ROUTE_ID, tagForRoute.getRouteId());
//                bundleForRoute.putInt(IntentConst.GUIDE_ID, tagForRoute.getGuideId());
//                bundleForRoute.putString(IntentConst.ROUTE_PRICE, tagForRoute.getPrice());
//                bundleForRoute.putString(IntentConst.ROUTE_DISCOUNT, tagForRoute.getPrice());
//
//                routeIntent.putExtra(IntentConst.BUNDLE, bundleForRoute);
//                v.getContext().startActivity(routeIntent);
//                break;
//            case R.id.riv_guide_avatar:
//                HomeRoute tagForGuider = (HomeRoute) v.getTag();
//                Intent guideIntent = new Intent(v.getContext(), GuiderDetailWebActivity.class);
//                Bundle bundleForGuider = new Bundle();
//                bundleForGuider.putInt(IntentConst.GUIDE_ID, tagForGuider.getGuideId());
//
//                guideIntent.putExtra(IntentConst.BUNDLE, bundleForGuider);
//                v.getContext().startActivity(guideIntent);
//                break;
//        }
    }
}

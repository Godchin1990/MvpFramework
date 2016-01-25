package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.view.base.WebContentBottomView;
import com.ziyou.tourGuide.view.interfaze.IRouteDetailWebView;

/**
 * Created by Edward on 16/1/25.
 */
public class RouteDetailWebView extends WebContentBottomView implements IRouteDetailWebView {


    private TextView appoint;
    private TextView price;

    public RouteDetailWebView(Context context) {
        super(context);
    }

    @Override
    public View setBottomLayout() {
        View view = View.inflate(getContext(), R.layout.layout_route_detail_bottom,null);
        price = (TextView) view.findViewById(R.id.price);
        appoint = (TextView) view.findViewById(R.id.appoint);
        return view;
    }

    @Override
    public TextView getPriceTextView() {
        return price;
    }

    @Override
    public TextView getAppointTextView() {
        return appoint;
    }
}

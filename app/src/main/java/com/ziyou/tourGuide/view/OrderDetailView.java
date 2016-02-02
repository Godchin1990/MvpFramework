package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IOrderDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/2/2.
 */
public class OrderDetailView extends TitleBarContentView implements IOrderDetailView {

    @Bind(R.id.item_route)
    View item_route;

    @Bind(R.id.start_date)
    View start_date;
    @Bind(R.id.appoint_people_count)
    View appoint_people_count;
    @Bind(R.id.pay_cash)
    View pay_cash;
    @Bind(R.id.order_pay_number)
    View order_pay_number;
    @Bind(R.id.appoint_date)
    View appoint_date;

    @Bind(R.id.route_sdv)
    SimpleDraweeView route_sdv;
    @Bind(R.id.button)
    Button button;

    TextView routeTitle;
    TextView startDate;
    TextView peopleCount;
    TextView payCash;
    TextView paynumber;
    TextView appointDate;


    public OrderDetailView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_order_detail,null);
        ButterKnife.bind(this,view);
        initItemList();
        return view;
    }

    private void initItemList() {
        List<View> viewList = new ArrayList<>();
        viewList.add(start_date);
        viewList.add(appoint_people_count);
        viewList.add(pay_cash);
        viewList.add(order_pay_number);
        viewList.add(appoint_date);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.start_date));
        viewModeList.add(new ItemViewMode(R.string.appoint_people_count));
        viewModeList.add(new ItemViewMode(R.string.pay_cash));
        viewModeList.add(new ItemViewMode(R.string.order_pay_number));
        viewModeList.add(new ItemViewMode(R.string.appoint_date));

        for(int i = 0;i<viewList.size();i++){
            TextView itemText = (TextView) viewList.get(i).findViewById(R.id.item_text);
            itemText.setText(getContext().getResources().getString(viewModeList.get(i).getTitleId()));
        }

        routeTitle = (TextView) item_route.findViewById(R.id.item_text);

        startDate = (TextView) start_date.findViewById(R.id.item_right_desc);
        peopleCount = (TextView) appoint_people_count.findViewById(R.id.item_right_desc);
        payCash = (TextView) pay_cash.findViewById(R.id.item_right_desc);
        paynumber = (TextView) order_pay_number.findViewById(R.id.item_right_desc);
        appointDate = (TextView) appoint_date.findViewById(R.id.item_right_desc);

    }

    @Override
    public SimpleDraweeView getRouteSimpleDraweeView() {
        return route_sdv;
    }

    @Override
    public TextView getRouteTitleTextView() {
        return routeTitle;
    }

    @Override
    public View getRouteLayout() {
        return item_route;
    }

    @Override
    public TextView getStartDateTextView() {
        return startDate;
    }

    @Override
    public TextView getAppointPeopleCountTextView() {
        return peopleCount;
    }

    @Override
    public TextView getPayCashTextView() {
        return payCash;
    }

    @Override
    public TextView getAppointDateTextView() {
        return appointDate;
    }

    @Override
    public TextView getOrderPayNumberTextView() {
        return paynumber;
    }

    @Override
    public Button getBottomButton() {
        return button;
    }
}

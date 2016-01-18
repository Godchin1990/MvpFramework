package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.command.view.InitItemCommand;
import com.ziyou.tourGuide.model.ItemViewMode;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IGuiderAreaView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/18.
 */
public class GuiderAreaView extends TitleBarContentView implements IGuiderAreaView {

    @Bind(R.id.my_order)
    View my_order;
    @Bind(R.id.guider_information)
    View guider_information;
    @Bind(R.id.receive_route)
    View receive_route;
    @Bind(R.id.order_statistic)
    View order_statistic;
    @Bind(R.id.route_community)
    View route_community;

    public GuiderAreaView(Context context) {
        super(context);
    }

    @Override
    public View getMyOrder() {
        return my_order;
    }

    @Override
    public View getGuiderInformation() {
        return guider_information;
    }

    @Override
    public View getReceiveRoute() {
        return receive_route;
    }

    @Override
    public View getOrderStatistic() {
        return order_statistic;
    }

    @Override
    public View getRouteCommunity() {
        return route_community;
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_guider_area,null);
        ButterKnife.bind(this,view);
        initGuiderAreaItemList();
        return view;
    }

    private void initGuiderAreaItemList() {
        List<View> viewList = new ArrayList<>();
        viewList.add(my_order);
        viewList.add(guider_information);
        viewList.add(receive_route);
        viewList.add(order_statistic);
        viewList.add(route_community);

        List<ItemViewMode> viewModeList = new ArrayList<>();
        viewModeList.add(new ItemViewMode(R.string.my_order));
        viewModeList.add(new ItemViewMode(R.string.guider_information));
        viewModeList.add(new ItemViewMode(R.string.receive_route));
        viewModeList.add(new ItemViewMode(R.string.order_statistic));
        viewModeList.add(new ItemViewMode(R.string.route_community));

        InitItemCommand initItemCommand = new InitItemCommand(getContext(),viewList,viewModeList);
        initItemCommand.execute();
    }
}

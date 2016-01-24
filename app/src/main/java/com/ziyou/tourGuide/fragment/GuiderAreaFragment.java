package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.GuiderInformationActivity;
import com.ziyou.tourGuide.activity.GuiderOrderActivity;
import com.ziyou.tourGuide.activity.OrderStatisticActivity;
import com.ziyou.tourGuide.activity.ReceiveRouteActivity;
import com.ziyou.tourGuide.activity.RouteCommunityActivity;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.GuiderAreaView;

/**
 * Created by Edward on 16/1/18.
 */
public class GuiderAreaFragment extends BaseFragment implements View.OnClickListener {

    private GuiderAreaView guiderAreaView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {

    }

    private void initListener() {
        guiderAreaView.getActionBarView().getLeftView().setOnClickListener(this);
        guiderAreaView.getMyOrder().setOnClickListener(this);
        guiderAreaView.getReceiveRoute().setOnClickListener(this);
        guiderAreaView.getGuiderInformation().setOnClickListener(this);
        guiderAreaView.getRouteCommunity().setOnClickListener(this);
        guiderAreaView.getOrderStatistic().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        guiderAreaView = new GuiderAreaView(getContext());
        guiderAreaView.getActionBarView().getTitleView().setText(getResources().getString(R.string.guider_area));
        return guiderAreaView.getRootView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
            case R.id.my_order:
                Log.d(TAG,"click guider order");
                intent = new Intent(getContext(), GuiderOrderActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.guider_information:
                Log.d(TAG,"click guider_information");
                intent = new Intent(getContext(), GuiderInformationActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.receive_route:
                Log.d(TAG,"click receive_route");
                intent = new Intent(getContext(), ReceiveRouteActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.route_community:
                Log.d(TAG,"click receive_route");
                intent = new Intent(getContext(),RouteCommunityActivity.class);
                startActivity(intent);
            case R.id.order_statistic:
                Log.d(TAG,"click order_statistic");
                intent = new Intent(getContext(),OrderStatisticActivity.class);
                startActivity(intent);
        }
    }
}

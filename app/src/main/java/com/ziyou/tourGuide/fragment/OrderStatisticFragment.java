package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.RouteCommunity;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.OrderStatisticView;

/**
 * Created by Edward on 16/1/24.
 */
public class OrderStatisticFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private OrderStatisticView orderStatisticView;
    private String finished_order;
    private String order_amount_price;
    private String deduct_profit_share;
    private String provide_route;
    private String order_number;
    private String acquire_profit_share;
    private String share_route;
    private String total_profit;

    @Override
    protected void initData() {
        finished_order = getResources().getString(R.string.finished_order);
        order_amount_price = getResources().getString(R.string.order_amount_price);
        deduct_profit_share = getResources().getString(R.string.deduct_profit_share);

        provide_route = getResources().getString(R.string.provide_route);
        order_number = getResources().getString(R.string.order_number);
        acquire_profit_share = getResources().getString(R.string.acquire_profit_share);

        share_route = getResources().getString(R.string.share_route);

        total_profit = getResources().getString(R.string.total_profit);

        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String url = ServerAPI.RouteCommunity.buildGetRouteDetailUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    private void initListener() {
        orderStatisticView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        orderStatisticView = new OrderStatisticView(getContext());
        orderStatisticView.getActionBarView().getTitleView().setText(getResources().getString(R.string.order_statistic));
        return orderStatisticView.getRootView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag){
                    case "refresh":
                        RouteCommunity routeCommunity = gson.fromJson(data, RouteCommunity.class);
                        orderStatisticView.getFinishedOrderTextView().setText(String.format(finished_order
                                ,routeCommunity.getFinish_order()+""));
                        orderStatisticView.getOrderAmountPriceTextView().setText(String.format(order_amount_price
                                ,routeCommunity.getFinish_sum()+""));
                        orderStatisticView.getDeductProfitShareTextView().setText(String.format(deduct_profit_share
                                ,routeCommunity.getDeduct()+""));

                        orderStatisticView.getProvideRouteTextView().setText(String.format(provide_route
                                ,routeCommunity.getFetch_route()+""));
                        orderStatisticView.getRouteOrderNumberTextView().setText(String.format(order_number
                                ,routeCommunity.getFetcher_order()+""));
                        orderStatisticView.getRouteAcquireProfitShareTextView().setText(String.format(acquire_profit_share
                                ,routeCommunity.getFetch_sum()+""));

                        orderStatisticView.getShareRouteTextView().setText(String.format(share_route
                                ,routeCommunity.getShare_route()+""));
                        orderStatisticView.getShareOrderNumberTextView().setText(String.format(order_number
                                ,routeCommunity.getShare_order()+""));
                        orderStatisticView.getShareAcquireProfitShareTextView().setText(String.format(acquire_profit_share
                                , routeCommunity.getShare_sum() + ""));

                        orderStatisticView.getTotalProfitTextView().setText(String.format(total_profit
                                ,routeCommunity.getSum_input()+""));
                        break;
                }
            }
        });
    }

    @Override
    public void onFail(int code, String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}

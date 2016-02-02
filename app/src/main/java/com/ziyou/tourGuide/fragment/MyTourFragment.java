package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.MyTourAdapter;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.Order;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.RefreshRecyclerView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 16/1/18.
 */
public class MyTourFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private RefreshRecyclerView<MyTourAdapter> myTourView;
    private MyTourAdapter myTourAdapter;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void initListener() {
        myTourView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    private void requestNetwork() {
        String url = ServerAPI.Order.buildGetUserOrdersUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url,null,this,"refresh");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myTourView = new RefreshRecyclerView<>(getContext());
        myTourAdapter = new MyTourAdapter();
        myTourView.setAdapter(myTourAdapter);
        myTourView.getActionBarView().getTitleView().setText(getResources().getString(R.string.my_tour));
        return myTourView.getRootView();
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
                        Order.OrderList orderList = gson.fromJson(data, Order.OrderList.class);
                        //对数据做处理,删除无效的订单
                        List<Order> temp = new ArrayList<>();
                        for(Order order:orderList.list){
                            if(order.getTrade_status()==0){
                                temp.add(order);
                            }
                        }
                        orderList.list.removeAll(temp);
                        myTourAdapter.setOrders(orderList.list);
                        break;
                }
                myTourView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
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

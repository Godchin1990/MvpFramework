package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.GuiderOrderAdapter;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.Order;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.RefreshRecyclerView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/19.
 */
public class GuiderOrderFragment extends BaseFragment implements StringCallBack<String>, View.OnClickListener {

    private RefreshRecyclerView<GuiderOrderAdapter> guiderOrderView;
    private GuiderOrderAdapter guiderOrderAdapter;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String url = ServerAPI.Order.buildGetGuideOrdersUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url,null,this,"refresh");
    }

    private void initListener() {
        guiderOrderView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        guiderOrderView = new RefreshRecyclerView<>(getContext());
        guiderOrderAdapter = new GuiderOrderAdapter();
        guiderOrderView.setAdapter(guiderOrderAdapter);
        guiderOrderView.getActionBarView().getTitleView().setText(getResources().getString(R.string.guider_order));
        return guiderOrderView.getRootView();
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
                        if(orderList.list.isEmpty()){
                            guiderOrderView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_EMPTY);
                        }else {
                            guiderOrderAdapter.setOrders(orderList.list);
                            guiderOrderView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
                        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_bar_left:
                getActivity().finish();
                break;
        }
    }
}

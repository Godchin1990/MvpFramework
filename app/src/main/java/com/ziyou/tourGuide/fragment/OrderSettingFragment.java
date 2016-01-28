package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.OrderSettingView;
import com.ziyou.tourGuide.widget.SelectNumberView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Edward on 16/1/28.
 */
public class OrderSettingFragment extends BaseFragment implements View.OnClickListener, StringCallBack<String> {

    private OrderSettingView orderSettingView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void initListener() {
        orderSettingView.getActionBarView().getLeftView().setOnClickListener(this);
        orderSettingView.getSelectNumberView().setCallBack(new SelectNumberView.CallBack() {

            @Override
            public void onItemClick(int currentNumber) {
                setTotalPrice(currentNumber);
            }
        });
    }

    private void setTotalPrice(int currentNumber) {
        String s = orderSettingView.getAppointPriceTextView().getText().toString();
        float totalPrice = Float.parseFloat(s) * currentNumber;
        orderSettingView.getPriceTextView().setText(Float.toString(totalPrice));
    }

    private void requestNetwork() {
        String routeId = getArguments().getString(Const.ROUTE_ID);
        String url = ServerAPI.Route.buildGetRouteDetailUrl(routeId);
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        orderSettingView = new OrderSettingView(getContext());
        orderSettingView.getActionBarView().getTitleView().setText(getResources().getString(R.string.appoint));
        return orderSettingView.getRootView();
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
    public void onSuccess(final String data, String tag) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String title = jsonObject.getString("title");
                    String price = jsonObject.getString("price");
                    orderSettingView.getTitleTextView().setText(title);
                    orderSettingView.getAppointPriceTextView().setText(price);
                    setTotalPrice(orderSettingView.getSelectNumberView().getCurrentNumber());
                } catch (JSONException e) {
                    e.printStackTrace();
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

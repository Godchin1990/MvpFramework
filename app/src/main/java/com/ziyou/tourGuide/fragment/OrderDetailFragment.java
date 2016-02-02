package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.command.SimpleDraweeViewCommand;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.OrderDetailView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 数据显示可能有点问题 底部点击事件的接口没调
 * Created by Edward on 16/1/18.
 */
public class OrderDetailFragment extends BaseFragment implements StringCallBack<String>, View.OnClickListener {

    private OrderDetailView view;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void initListener() {
        view.getActionBarView().getLeftView().setOnClickListener(this);
    }

    private void requestNetwork() {
        String url = ServerAPI.Order.buildOrderDetailsUrl();
        Map<String,String> params = new HashMap<>();
        params.put("id",getArguments().getString(Const.ORDER_ID));
        NetworkHelper.getInstance().sendGetStringRequest(url,params,this,"refresh");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new OrderDetailView(getContext());
        view.getActionBarView().getTitleView().setText(getResources().getString(R.string.order_detail));
        return view.getRootView();
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        Log.d(TAG,data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (tag){
                    case "refresh":
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            String create_time = jsonObject.getString("create_time");
                            String start_date = jsonObject.getString("start_date");
                            String trade_no = jsonObject.getString("trade_no");
                            String route_title = jsonObject.getString("route_title");
                            String route_cover = jsonObject.getString("route_cover");
                            String act_price = jsonObject.getString("act_price");
                            String people = jsonObject.getString("people");

                            float total = Float.parseFloat(act_price) * Integer.parseInt(people);

                            SimpleDraweeViewCommand command = new SimpleDraweeViewCommand(view.getRouteSimpleDraweeView(),route_cover);
                            command.execute();
                            view.getRouteTitleTextView().setText(route_title);
                            view.getOrderPayNumberTextView().setText(trade_no);
                            view.getStartDateTextView().setText(start_date);
                            view.getAppointDateTextView().setText(create_time);
                            view.getPayCashTextView().setText(String.valueOf(total));
                            view.getAppointPeopleCountTextView().setText(people);
                        } catch (JSONException e) {
                            e.printStackTrace();
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

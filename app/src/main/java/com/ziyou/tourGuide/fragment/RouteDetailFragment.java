package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.base.WebContentView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Edward on 16/1/10.
 */
public class RouteDetailFragment extends BaseFragment implements WebContentView.GuideJavaScriptCallback, View.OnClickListener, StringCallBack<String> {

    private WebContentView webContentView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String routeId = getArguments().getString(Const.ROUTE_ID);
        String url = ServerAPI.Route.buildGetRouteDetailUrl(routeId);
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    private void initListener() {
        webContentView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        webContentView = new WebContentView(getContext());
        return webContentView.getRootView();
    }

    @Override
    public void parseJsonToSkip(String json) {

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
                Log.d(TAG, data);
//                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String title = jsonObject.getString("title");

                    webContentView.getActionBarView().getTitleView().setText(title);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String routeId = getArguments().getString(Const.ROUTE_ID);
                String routeDetailH5Url = ServerAPI.H5.getRouteDetailH5Url(routeId);
                if (!TextUtils.isEmpty(routeDetailH5Url)) {
                    webContentView.getWebView().loadUrl(routeDetailH5Url);
                }
                webContentView.setCallback(RouteDetailFragment.this);
                webContentView.getActionBarView().getLeftView().setOnClickListener(RouteDetailFragment.this);
            }
        });

    }

    @Override
    public void onFail(int code, String message, Object object) {

    }
}

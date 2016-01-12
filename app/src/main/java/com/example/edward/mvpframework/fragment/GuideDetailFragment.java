package com.example.edward.mvpframework.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.activity.base.Const;
import com.example.edward.mvpframework.fragment.base.BaseFragment;
import com.example.edward.mvpframework.network.NetworkHelper;
import com.example.edward.mvpframework.network.ServerAPI;
import com.example.edward.mvpframework.network.StringCallBack;
import com.example.edward.mvpframework.view.base.WebContentView;

/**
 * Created by Edward on 16/1/10.
 */
public class GuideDetailFragment extends BaseFragment implements WebContentView.GuideJavaScriptCallback, View.OnClickListener, StringCallBack<String> {

    private WebContentView webContentView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String guideId = getArguments().getString(Const.GUIDE_ID);
        String url = ServerAPI.GuideDetail.buildGuideDetailUrl(guideId);
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        webContentView = new WebContentView(getContext());
        String string = getResources().getString(R.string.guider_detail);
        webContentView.getActionBarView().getTitleView().setText(string);
        return webContentView.getRootView();
    }

    private void initListener() {
        webContentView.getActionBarView().getLeftView().setOnClickListener(this);
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
    public void onSuccess(String data, String tag) {
        Log.d(TAG,data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String guiderId = getArguments().getString(Const.GUIDE_ID);
                String url = ServerAPI.H5.getGuiderDetailH5Url(guiderId);
                webContentView.getWebView().loadUrl(url);
            }
        });
    }

    @Override
    public void onFail() {

    }
}

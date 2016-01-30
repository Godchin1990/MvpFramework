package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
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
import com.ziyou.tourGuide.view.GuiderDetailWebView;
import com.ziyou.tourGuide.view.base.GuideJavaScriptCallback;

/**
 * Created by Edward on 16/1/10.
 */
public class GuideDetailFragment extends BaseFragment implements GuideJavaScriptCallback, View.OnClickListener, StringCallBack<String> {

    private GuiderDetailWebView webContentView;

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
        webContentView = new GuiderDetailWebView(getContext());
        String string = getResources().getString(R.string.guider_detail);
        webContentView.getActionBarView().getTitleView().setText(string);
        return webContentView.getRootView();
    }

    private void initListener() {
        webContentView.getActionBarView().getLeftView().setOnClickListener(this);
        webContentView.getBottomLayout().setOnClickListener(this);
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
            case R.id.layout_bottom:
                Log.d(TAG,"click layout_bottom");
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
    public void onFail(int code, String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}

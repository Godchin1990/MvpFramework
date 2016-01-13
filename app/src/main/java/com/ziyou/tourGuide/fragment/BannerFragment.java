package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.HomeBanner;
import com.ziyou.tourGuide.view.base.WebContentView;

/**
 * Created by Edward on 16/1/7.
 */
public class BannerFragment extends BaseFragment implements WebContentView.GuideJavaScriptCallback, View.OnClickListener {

    private WebContentView webContentView;

    @Override
    protected void initData() {
        HomeBanner homeBanner = getArguments().getParcelable(Const.BANNER);
        if(homeBanner!=null){
            webContentView.getWebView().loadUrl(homeBanner.getAct_url());
            webContentView.getActionBarView().getTitleView().setText(homeBanner.getTitle());
        }
        webContentView.setCallback(this);
        webContentView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        webContentView = new WebContentView(getContext());
        return webContentView.getRootView();
    }

    @Override
    public void parseJsonToSkip(String json) {
        Log.d(TAG,json);
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

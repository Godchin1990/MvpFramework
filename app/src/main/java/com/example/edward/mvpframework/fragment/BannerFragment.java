package com.example.edward.mvpframework.fragment;

import android.util.Log;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.activity.base.Const;
import com.example.edward.mvpframework.fragment.base.BaseFragment;
import com.example.edward.mvpframework.model.HomeBanner;
import com.example.edward.mvpframework.view.base.WebContentView;

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
    protected View initView() {
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

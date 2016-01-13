package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.base.Const;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.helper.ShareHelper;
import com.ziyou.tourGuide.model.HomeBanner;
import com.ziyou.tourGuide.view.BannerView;
import com.ziyou.tourGuide.view.base.WebContentView;

/**
 * Created by Edward on 16/1/7.
 */
public class BannerFragment extends BaseFragment implements WebContentView.GuideJavaScriptCallback, View.OnClickListener {

    private BannerView webContentView;
    private HomeBanner homeBanner;

    @Override
    protected void initData() {
        homeBanner = getArguments().getParcelable(Const.BANNER);
        if(homeBanner !=null){
            webContentView.getWebView().loadUrl(homeBanner.getAct_url());
            webContentView.getActionBarView().getTitleView().setText(homeBanner.getTitle());
        }
        webContentView.setCallback(this);
        webContentView.getActionBarView().getLeftView().setOnClickListener(this);
        webContentView.getActionBarView().getRightView().setVisibility(View.VISIBLE);
        webContentView.getActionBarView().getRightView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        webContentView = new BannerView(getContext());
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
            case R.id.action_bar_right:
                Log.d(TAG, "share click");
                Bundle bundle = new Bundle();
                bundle.putString(ShareHelper.PARAM_CONTENT,homeBanner.getTitle());
                bundle.putString(ShareHelper.PARAM_TITLEL,homeBanner.getTitle());
                bundle.putString(ShareHelper.PARAM_IMAGE_URL,homeBanner.getImage());
                bundle.putString(ShareHelper.PARAM_SHARE_URL,homeBanner.getAct_url());
                ShareHelper.getInstance().shareUrl(getActivity(), bundle);
                ShareHelper.getInstance().shareUrl(getActivity(), bundle);
                break;
        }
    }
}

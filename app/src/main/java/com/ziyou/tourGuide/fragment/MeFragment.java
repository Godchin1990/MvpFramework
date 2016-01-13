package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.LazyFragment;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.MeView;

/**
 * Created by Edward on 16/1/12.
 */
public class MeFragment extends LazyFragment implements StringCallBack<String> {

    private MeView meView;

    @Override
    protected void initData() {
        View view = View.inflate(getContext(), R.layout.item_me_information_unlogin_part,null);
        meView.getInfomationLayout().addView(view);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        meView = new MeView(getContext());
        return meView.getRootView();
    }

    @Override
    public void onSuccess(String data, String tag) {

    }

    @Override
    public void onFail() {

    }
}

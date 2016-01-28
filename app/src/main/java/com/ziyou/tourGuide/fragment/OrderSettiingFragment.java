package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.OrderSettiingView;

/**
 * Created by Edward on 16/1/28.
 */
public class OrderSettiingFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OrderSettiingView orderSettiingView = new OrderSettiingView(getContext());
        return orderSettiingView.getRootView();
    }
}

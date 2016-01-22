package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.GuiderInformationView;

/**
 * Created by Edward on 16/1/22.
 */
public class GuiderInformationFragment extends BaseFragment {

    private GuiderInformationView amendGuiderInformationView;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        amendGuiderInformationView = new GuiderInformationView(getContext());
        return amendGuiderInformationView.getRootView();
    }
}

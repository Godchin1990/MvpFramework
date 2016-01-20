package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.AmendUserInformationView;

/**
 * Created by Edward on 16/1/15.
 */
public class AmendUserInformationFragment extends BaseFragment {

    private AmendUserInformationView amendUserInformationView;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        amendUserInformationView = new AmendUserInformationView(getContext());
        amendUserInformationView.getActionBarView().getTitleView().setText(getResources().getString(R.string.edit));
        return amendUserInformationView.getRootView();
    }
}

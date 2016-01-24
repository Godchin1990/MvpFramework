package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.RouteCommunityView;

/**
 * Created by Edward on 16/1/24.
 */
public class RouteCommunityFragment extends BaseFragment implements View.OnClickListener {

    private RouteCommunityView routeCommunityView;

    @Override
    protected void initData() {
        initListener();
    }

    private void initListener() {
        routeCommunityView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        routeCommunityView = new RouteCommunityView(getContext(),getFragmentManager());
        routeCommunityView.getActionBarView().getTitleView().setText(getResources().getString(R.string.route_community));
        return routeCommunityView.getRootView();
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

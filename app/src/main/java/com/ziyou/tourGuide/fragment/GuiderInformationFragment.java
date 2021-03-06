package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.GuiderInformationView;

/**
 * Created by Edward on 16/1/22.
 */
public class GuiderInformationFragment extends BaseFragment implements View.OnClickListener {

    private GuiderInformationView amendGuiderInformationView;

    @Override
    protected void initData() {
        initListener();
    }

    private void initListener() {
        amendGuiderInformationView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        amendGuiderInformationView = new GuiderInformationView(getContext());
        amendGuiderInformationView.getActionBarView().getTitleView().setText(getResources().getString(R.string.guider_information));
        return amendGuiderInformationView.getRootView();
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

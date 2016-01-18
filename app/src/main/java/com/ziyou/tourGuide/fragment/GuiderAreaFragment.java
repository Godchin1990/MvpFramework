package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.GuiderAreaView;

/**
 * Created by Edward on 16/1/18.
 */
public class GuiderAreaFragment extends BaseFragment implements View.OnClickListener {

    private GuiderAreaView guiderAreaView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {

    }

    private void initListener() {
        guiderAreaView.getActionBarView().getTitleView().setText(getResources().getString(R.string.guider_area));
        guiderAreaView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        guiderAreaView = new GuiderAreaView(getContext());
        return guiderAreaView.getRootView();
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

package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.ReceiveRouteView;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/21.
 */
public class ReceiveRouteFragment extends BaseFragment implements View.OnClickListener {

    private ReceiveRouteView receiveRouteView;

    @Override
    protected void initData() {
        initListener();
        receiveRouteView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
    }

    private void initListener() {
        receiveRouteView.getActionBarView().getLeftView().setOnClickListener(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        receiveRouteView = new ReceiveRouteView(getContext());
        receiveRouteView.getActionBarView().getTitleView().setText(getResources().getString(R.string.receive_route));
        return receiveRouteView.getRootView();
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

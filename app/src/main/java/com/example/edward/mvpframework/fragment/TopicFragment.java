package com.example.edward.mvpframework.fragment;

import android.view.View;

import com.example.edward.mvpframework.fragment.base.BaseFragment;
import com.example.edward.mvpframework.view.TopicView;
import com.example.edward.mvpframework.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/8.
 */
public class TopicFragment extends BaseFragment {

    private TopicView topicView;

    @Override
    protected void initData() {
        topicView.getRefreshViewContainer().setCurrentState(RefreshViewContainer.STATE_SUCCESS);
    }

    @Override
    protected View initView() {
        topicView = new TopicView(getContext());
        return topicView.getRootView();
    }
}

package com.example.edward.mvpframework.fragment;

import android.view.View;

import com.example.edward.mvpframework.fragment.base.BaseFragment;
import com.example.edward.mvpframework.view.IndexView;

/**
 * Created by Edward on 16/1/1.
 */
public class IndexFragment extends BaseFragment {

    private IndexView indexView;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView() {
        indexView = new IndexView(getContext(),getFragmentManager());
        return indexView.getRootView();
    }
}

package com.ziyou.tourGuide.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.IndexView;

/**
 * Created by Edward on 16/1/1.
 */
public class IndexFragment extends BaseFragment {

    private IndexView indexView;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        indexView = new IndexView(getContext(),getFragmentManager());
        return indexView.getRootView();
    }

}

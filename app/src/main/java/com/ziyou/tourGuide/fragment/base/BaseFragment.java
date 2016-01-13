package com.ziyou.tourGuide.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Edward on 16/1/1.
 */
public abstract class BaseFragment extends Fragment {
    protected final String TAG;
    {
        TAG = getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater,container,savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 设置数据(包括事件)
     */
    protected abstract void initData();

    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public String getTAG() {
        return TAG;
    }
}

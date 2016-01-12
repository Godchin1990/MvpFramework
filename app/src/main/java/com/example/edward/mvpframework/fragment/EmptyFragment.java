package com.example.edward.mvpframework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edward.mvpframework.fragment.base.LazyFragment;

/**
 * Created by Edward on 16/1/5.
 */
public class EmptyFragment extends LazyFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("空白页面");
        return textView;
    }
}

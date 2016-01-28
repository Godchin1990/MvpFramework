package com.ziyou.tourGuide.adapter.refreshviewcontainer;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewAdapter;

/**
 * Created by Edward on 16/1/8.
 */
public abstract class SimpleRefreshViewAdapter extends RefreshViewAdapter {
    public SimpleRefreshViewAdapter(Context context) {
        super(context);
    }

    @Override
    public View setErrorView() {
        TextView textView = new TextView(getContext());
        textView.setText("加载失败");
        return textView;
    }

    @Override
    public View setEmptyView() {
        View view = View.inflate(getContext(), R.layout.fragment_empty,null);
        return view;
    }

    @Override
    public View setLoadingView() {
        TextView textView = new TextView(getContext());
        textView.setText("正在加载");
        return textView;
    }


}

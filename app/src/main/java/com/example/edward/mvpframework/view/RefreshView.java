package com.example.edward.mvpframework.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.edward.mvpframework.view.base.BaseView;
import com.example.edward.mvpframework.widget.RefreshViewContainer;

/**
 * Created by Edward on 16/1/4.
 */
public abstract class RefreshView extends BaseView implements IRefreshView {

    private RefreshViewContainer container;

    public RefreshView(Context context) {
        super(context);
    }

    @Override
    protected View initView() {

        container = new RefreshViewContainer(getContext());

        View successView = initSuccessView();
        View errorView = initErrorView();
        View emptyView = initEmptyView();
        View loadingView = initLoadingView();

        container.setSuccessView(successView);
        container.setErrorView(errorView);
        container.setEmptyView(emptyView);
        container.setLoadingView(loadingView);
        return container;
    }

    private View initLoadingView() {
        TextView textView = new TextView(getContext());
        textView.setText("正在加载");
        return textView;
    }

    private View initEmptyView() {
        TextView textView = new TextView(getContext());
        textView.setText("加载为空");
        return textView;
    }

    private View initErrorView() {
        TextView textView = new TextView(getContext());
        textView.setText("加载失败");
        return textView;
    }

    abstract protected  View initSuccessView();

    public RefreshViewContainer getRefreshViewContainer(){
        return container;
    }


}

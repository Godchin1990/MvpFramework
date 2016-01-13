package com.ziyou.tourGuide.view.base;

import android.content.Context;
import android.view.View;

import com.ziyou.tourGuide.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

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
        container.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
            @Override
            public View setSuccessView() {
                return initSuccessView();
            }
        });
        return container;
    }

    abstract protected  View initSuccessView();

    public RefreshViewContainer getRefreshViewContainer(){
        return container;
    }


}

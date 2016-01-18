package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.MyMessageAdapter;
import com.ziyou.tourGuide.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IMyMessageView;
import com.ziyou.tourGuide.widget.PullToRefreshRecyclerView;
import com.ziyou.tourGuide.widget.recyclerview.DividerItemDecoration;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/17.
 */
public class MyMessageView extends TitleBarContentView implements IMyMessageView {
    private RefreshViewContainer refreshViewContainer;
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    private MyMessageAdapter adapter;

    public MyMessageView(Context context) {
        super(context);
    }

    @Override
    public PullToRefreshRecyclerView getPullToRefreshRecyclerView() {
        return pullToRefreshRecyclerView;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public MyMessageAdapter getAdapter() {
        return adapter;
    }

    @Override
    public RefreshViewContainer getRefreshViewContainer() {
        return refreshViewContainer;
    }

    @Override
    public View setContentView() {
        refreshViewContainer = new RefreshViewContainer(getContext());
        refreshViewContainer.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
            @Override
            public View setSuccessView() {
                View view = View.inflate(getContext(), R.layout.layout_refresh_recyclerview, null);
                pullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.pull_to_refresh_recyclerview);
                pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
                recyclerView = pullToRefreshRecyclerView.getRefreshableView();
                adapter = new MyMessageAdapter();
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
                recyclerView.setAdapter(adapter);
                return view;
            }
        });
        return refreshViewContainer;
    }
}

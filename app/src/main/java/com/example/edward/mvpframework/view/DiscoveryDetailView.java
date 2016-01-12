package com.example.edward.mvpframework.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.adapter.recyclerview.DiscoveryDetailAdapter;
import com.example.edward.mvpframework.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.example.edward.mvpframework.view.base.TitleBarContentView;
import com.example.edward.mvpframework.view.interfaze.IDiscoveryDetailView;
import com.example.edward.mvpframework.widget.PullToRefreshRecyclerView;
import com.example.edward.mvpframework.widget.recyclerview.DividerItemDecoration;
import com.example.edward.mvpframework.widget.refreshview.RefreshViewContainer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 16/1/12.
 */
public class DiscoveryDetailView extends TitleBarContentView implements IDiscoveryDetailView {

    private RefreshViewContainer refreshViewContainer;
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    private DiscoveryDetailAdapter adapter;

    public DiscoveryDetailView(Context context) {
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
    public DiscoveryDetailAdapter getAdapter() {
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
                adapter = new DiscoveryDetailAdapter();
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
                recyclerView.setAdapter(adapter);
                return view;
            }
        });
        return refreshViewContainer;
    }
}

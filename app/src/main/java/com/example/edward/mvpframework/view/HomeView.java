package com.example.edward.mvpframework.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.adapter.recyclerview.HomeAdapter;
import com.example.edward.mvpframework.widget.PullToRefreshRecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/1.
 */
public class HomeView extends RefreshView implements IHomeView {

//    @Bind(R.id.recycler_view)
//    RecyclerView recyclerView;
//    @Bind(R.id.swipe_refresh_layout)
//    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.pull_to_refresh_recyclerview)
    PullToRefreshRecyclerView pullToRefreshRecyclerView;
    RecyclerView recyclerView;

    HomeAdapter adapter;

    public HomeView(Context context) {
        super(context);
    }

    @Override
    protected View initSuccessView() {
        View view = View.inflate(getContext(),R.layout.layout_refresh_recyclerview,null);
        ButterKnife.bind(this,view);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        adapter = new HomeAdapter();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public HomeAdapter getAdapter() {
        return adapter;
    }

    @Override
    public PullToRefreshRecyclerView getPullToRefreshRecyclerView() {
        return pullToRefreshRecyclerView;
    }
}
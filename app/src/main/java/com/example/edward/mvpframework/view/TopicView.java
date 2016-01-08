package com.example.edward.mvpframework.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.adapter.recyclerview.TopicAdapter;
import com.example.edward.mvpframework.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.example.edward.mvpframework.view.base.TitleBarContentView;
import com.example.edward.mvpframework.view.interfaze.ITopicView;
import com.example.edward.mvpframework.widget.PullToRefreshRecyclerView;
import com.example.edward.mvpframework.widget.refreshview.RefreshViewContainer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 16/1/8.
 */
public class TopicView extends TitleBarContentView implements ITopicView {

    private PullToRefreshRecyclerView pullToRefreshRecyclerView;

    private RecyclerView recyclerView;
    private TopicAdapter adapter;
    private RefreshViewContainer refreshViewContainer;

    public TopicView(Context context) {
        super(context);
    }

    @Override
    public PullToRefreshRecyclerView getPullToRefreshRecyclerView() {
        return pullToRefreshRecyclerView;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return pullToRefreshRecyclerView.getRefreshableView();
    }

    @Override
    public TopicAdapter getAdapter() {
        return adapter;
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
                adapter = new TopicAdapter();
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                return view;
            }
        });
        return refreshViewContainer;
    }

    @Override
    public RefreshViewContainer getRefreshViewContainer() {
        return refreshViewContainer;
    }
}

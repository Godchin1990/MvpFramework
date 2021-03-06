package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.TopicAdapter;
import com.ziyou.tourGuide.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.ITopicView;
import com.ziyou.tourGuide.widget.PullToRefreshRecyclerView;
import com.ziyou.tourGuide.widget.recyclerview.DividerItemDecoration;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 *
 * @deprecated
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
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
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

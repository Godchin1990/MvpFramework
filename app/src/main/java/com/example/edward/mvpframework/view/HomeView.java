package com.example.edward.mvpframework.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.adapter.recyclerview.HomeAdapter;
import com.example.edward.mvpframework.adapter.recyclerview.holder.HomeTopicViewHolder;
import com.example.edward.mvpframework.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.example.edward.mvpframework.view.base.BaseView;
import com.example.edward.mvpframework.view.interfaze.IHomeView;
import com.example.edward.mvpframework.widget.PullToRefreshRecyclerView;
import com.example.edward.mvpframework.widget.recyclerview.DividerItemDecoration;
import com.example.edward.mvpframework.widget.refreshview.RefreshViewContainer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 16/1/1.
 */
public class HomeView extends BaseView implements IHomeView {

    private RefreshViewContainer refreshViewContainer;
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;

    private HomeAdapter adapter;

    public HomeView(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        refreshViewContainer = new RefreshViewContainer(getContext());
        refreshViewContainer.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
            @Override
            public View setSuccessView() {
                View view = View.inflate(getContext(), R.layout.layout_refresh_recyclerview,null);
                pullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.pull_to_refresh_recyclerview);
                pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
                recyclerView = pullToRefreshRecyclerView.getRefreshableView();
                adapter = new HomeAdapter();
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
                recyclerView.setAdapter(adapter);
                /**添加RecyclerView的滑动效果*/
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int childCount = recyclerView.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            View view = recyclerView.getChildAt(i);
                            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
                            switch (holder.getItemViewType()) {
                                case HomeAdapter.TYPE_TOPIC:
                                    HomeTopicViewHolder topicViewHolder = (HomeTopicViewHolder) holder;
                                    topicViewHolder.setParentHeight(recyclerView.getHeight());
                                    topicViewHolder.setOffset();
                            }
                        }
                    }
                });
                return view;
            }
        });

        return refreshViewContainer;
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
    public HomeAdapter getAdapter() {
        return adapter;
    }

    @Override
    public RefreshViewContainer getRefreshViewContainer() {
        return refreshViewContainer;
    }
}

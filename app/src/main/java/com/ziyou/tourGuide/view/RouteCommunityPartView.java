package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.ziyou.tourGuide.view.base.BaseView;
import com.ziyou.tourGuide.view.interfaze.IRefreshRecyclerView;
import com.ziyou.tourGuide.widget.PullToRefreshRecyclerView;
import com.ziyou.tourGuide.widget.recyclerview.CommenDividerItemDecoration;
import com.ziyou.tourGuide.widget.recyclerview.DividerItemDecoration;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/25.
 */
public class RouteCommunityPartView<T extends RecyclerView.Adapter> extends BaseView implements IRefreshRecyclerView<T> {

    private RefreshViewContainer refreshViewContainer;
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;

    public RouteCommunityPartView(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        refreshViewContainer = new RefreshViewContainer(getContext());
        refreshViewContainer.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
            @Override
            public View setSuccessView() {
                View view = View.inflate(getContext(), R.layout.layout_refresh_recyclerview, null);
                pullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.pull_to_refresh_recyclerview);
                pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
                recyclerView = pullToRefreshRecyclerView.getRefreshableView();
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.addItemDecoration(new CommenDividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST,R.drawable.recyclerview_order_divide_decoration));
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
    public void setAdapter(T adapter) {
        recyclerView.setAdapter(adapter);
    }


    @Override
    public RefreshViewContainer getRefreshViewContainer() {
        return refreshViewContainer;
    }
}

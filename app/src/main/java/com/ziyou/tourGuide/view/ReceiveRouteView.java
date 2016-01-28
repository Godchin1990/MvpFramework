package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.widget.recyclerview.DividerItemSeparateDecoration;

/**
 *
 * Created by Edward on 16/1/21.
 */
public class ReceiveRouteView<T extends RecyclerView.Adapter> extends RefreshRecyclerView<T> {

    public ReceiveRouteView(Context context) {
        super(context);
    }

    @Override
    protected void setDecoration(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new DividerItemSeparateDecoration());
    }

    //    private RefreshViewContainer refreshViewContainer;
//    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
//    private RecyclerView recyclerView;
//    private ReceiveRouteAdapter adapter;
//
//    public ReceiveRouteView(Context context) {
//        super(context);
//    }
//
//    @Override
//    public PullToRefreshRecyclerView getPullToRefreshRecyclerView() {
//        return pullToRefreshRecyclerView;
//    }
//
//    @Override
//    public RecyclerView getRecyclerView() {
//        return recyclerView;
//    }
//
//    @Override
//    public ReceiveRouteAdapter getAdapter() {
//        return adapter;
//    }
//
//    @Override
//    public RefreshViewContainer getRefreshViewContainer() {
//        return refreshViewContainer;
//    }
//
//    @Override
//    public View setContentView() {
//        refreshViewContainer = new RefreshViewContainer(getContext());
//        refreshViewContainer.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
//            @Override
//            public View setSuccessView() {
//                View view = View.inflate(getContext(), R.layout.layout_refresh_recyclerview, null);
//                pullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.pull_to_refresh_recyclerview);
//                pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
//                recyclerView = pullToRefreshRecyclerView.getRefreshableView();
//                adapter = new ReceiveRouteAdapter();
//                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//                recyclerView.setLayoutManager(manager);
//                recyclerView.addItemDecoration(new DividerItemSeparateDecoration());
//                recyclerView.setAdapter(adapter);
//                return view;
//            }
//        });
//        return refreshViewContainer;
//    }
    protected void initActionBar() {
        super.initActionBar();
        getActionBarView().getRightTextView().setVisibility(View.VISIBLE);
        getActionBarView().getRightTextView().setText(getContext().getResources().getString(R.string.get_route));
        getActionBarView().getRightTextView().setTextColor(getContext().getResources().getColor(R.color.theme_color));
    }
}

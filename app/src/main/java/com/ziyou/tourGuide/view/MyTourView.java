package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.MyTourAdapter;
import com.ziyou.tourGuide.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.IMyTourView;
import com.ziyou.tourGuide.widget.recyclerview.CommenDividerItemDecoration;
import com.ziyou.tourGuide.widget.recyclerview.DividerItemDecoration;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/1/8.
 */
public class MyTourView extends TitleBarContentView implements IMyTourView {

    private RecyclerView recyclerView;
    private MyTourAdapter adapter;
    private RefreshViewContainer refreshViewContainer;

    public MyTourView(Context context) {
        super(context);
    }


    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public MyTourAdapter getAdapter() {
        return adapter;
    }


    @Override
    public View setContentView() {
        refreshViewContainer = new RefreshViewContainer(getContext());
        refreshViewContainer.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
            @Override
            public View setSuccessView() {
//                View view = View.inflate(getContext(), R.layout.layout_refresh_recyclerview, null);
                recyclerView = new RecyclerView(getContext());
                adapter = new MyTourAdapter();
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.addItemDecoration(new CommenDividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST,R.drawable.recyclerview_order_divide_decoration));
                recyclerView.setAdapter(adapter);
                return recyclerView;
            }
        });
        return refreshViewContainer;
    }

    @Override
    public RefreshViewContainer getRefreshViewContainer() {
        return refreshViewContainer;
    }
}

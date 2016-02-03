package com.ziyou.tourGuide.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ziyou.tourGuide.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.ziyou.tourGuide.view.base.TitleBarBottomContentView;
import com.ziyou.tourGuide.view.component.IRefreshViewContainer;
import com.ziyou.tourGuide.view.interfaze.IRecyclerView2;
import com.ziyou.tourGuide.widget.recyclerview.DividerItemSeparateDecoration2;
import com.ziyou.tourGuide.widget.refreshview.RefreshViewContainer;

/**
 * Created by Edward on 16/2/3.
 */
public class VoucherView<T extends RecyclerView.Adapter> extends TitleBarBottomContentView implements IRefreshViewContainer,IRecyclerView2<T> {
    private RefreshViewContainer refreshViewContainer;
    private RecyclerView recyclerView;

    public VoucherView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        refreshViewContainer = new RefreshViewContainer(getContext());
        refreshViewContainer.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
            @Override
            public View setSuccessView() {
                recyclerView = new RecyclerView(getContext());
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                setDecoration(recyclerView);
                return recyclerView;
            }
        });
        return refreshViewContainer;
    }

    protected void setDecoration(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new DividerItemSeparateDecoration2());
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void setAdapter(T adapter) {
        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public View setBottomLayout() {
//        TextView textView = new TextView(getContext());
//        textView.setText("底部");
//        return textView;
//    }

    @Override
    public RefreshViewContainer getRefreshViewContainer() {
        return refreshViewContainer;
    }
}

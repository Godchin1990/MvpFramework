package com.example.edward.mvpframework.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.adapter.recyclerview.CityListAdapter;
import com.example.edward.mvpframework.adapter.recyclerview.DiscoveryAdapter;
import com.example.edward.mvpframework.adapter.refreshviewcontainer.SimpleRefreshViewAdapter;
import com.example.edward.mvpframework.util.ScreenHelper;
import com.example.edward.mvpframework.view.base.TitleBarContentView;
import com.example.edward.mvpframework.view.interfaze.IDiscoveryView;
import com.example.edward.mvpframework.widget.MyActionBar;
import com.example.edward.mvpframework.widget.PullToRefreshRecyclerView;
import com.example.edward.mvpframework.widget.refreshview.RefreshViewContainer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryView extends TitleBarContentView implements IDiscoveryView<CityListAdapter> {

    private RefreshViewContainer refreshViewContainer;
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    private DiscoveryAdapter adapter;
    private PopupWindow cityPopWindow;
    private RecyclerView cityListRecyclerView;
    private CityListAdapter cityListAdapter;

    public DiscoveryView(Context context) {
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
    public DiscoveryAdapter getAdapter() {
        return adapter;
    }

    @Override
    public RefreshViewContainer getRefreshViewContainer() {
        return refreshViewContainer;
    }

    @Override
    public View setContentView() {
        //设置popupwindow
        View cityListView = View.inflate(getContext(), R.layout.window_city_list, null);
        cityListRecyclerView = (RecyclerView) cityListView.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        cityListRecyclerView.setLayoutManager(manager);
        cityListAdapter = new CityListAdapter();
        cityListRecyclerView.setAdapter(cityListAdapter);
        cityPopWindow = new PopupWindow(cityListView,
                ScreenHelper.dpToPxInt(getContext(), 140)
                , ScreenHelper.dpToPxInt(getContext(), 180), true);
        cityPopWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置refreshviewcontainer
        refreshViewContainer = new RefreshViewContainer(getContext());
        refreshViewContainer.setAdapter(new SimpleRefreshViewAdapter(getContext()) {
            @Override
            public View setSuccessView() {
                View view = View.inflate(getContext(), R.layout.layout_refresh_recyclerview, null);
                pullToRefreshRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.pull_to_refresh_recyclerview);
                pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
                recyclerView = pullToRefreshRecyclerView.getRefreshableView();
                adapter = new DiscoveryAdapter();
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                return view;
            }
        });
        return refreshViewContainer;
    }

    /**
     * 初始化头
     * @param view
     */
    @Override
    protected void initActionBar(View view) {
        actionBar = (MyActionBar) view.findViewById(R.id.action_bar);
        actionBar.getLeftTextView().setVisibility(View.VISIBLE);
        actionBar.getLeftTextView().setCompoundDrawablePadding(10);
        Drawable drawable = getContext().getResources().getDrawable(R.mipmap.ic_arrow_down);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            actionBar.getLeftTextView()
                    .setCompoundDrawables(null, null, drawable, null);
        }
    }

    @Override
    public PopupWindow getCityListPopupWindow() {
        return cityPopWindow;
    }

    @Override
    public RecyclerView getCityListRecyclerView() {
        return cityListRecyclerView;
    }

    @Override
    public CityListAdapter getCityListAdapter() {
        return cityListAdapter;
    }
}
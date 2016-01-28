package com.ziyou.tourGuide.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.adapter.recyclerview.CityListAdapter;
import com.ziyou.tourGuide.util.ScreenHelper;
import com.ziyou.tourGuide.view.interfaze.IDiscoveryView;

/**
 * Created by Edward on 16/1/10.
 */
public class DiscoveryView<T extends RecyclerView.Adapter> extends RefreshRecyclerView<T> implements IDiscoveryView {

    public static final String TAG_CHECK_CITY = "tag_check_city";

    private PopupWindow cityPopWindow;
    private RecyclerView cityListRecyclerView;
    private CityListAdapter cityListAdapter;

    public DiscoveryView(Context context) {
        super(context);
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
        return super.setContentView();
    }

    /**
     * 初始化头
     */
    @Override
    protected void initActionBar() {
        super.initActionBar();
        getActionBarView().getLeftView().setVisibility(View.GONE);
        getActionBarView().getLeftTextView().setVisibility(View.VISIBLE);
        getActionBarView().getLeftTextView().setCompoundDrawablePadding(10);
        Drawable drawable = getContext().getResources().getDrawable(R.mipmap.ic_arrow_down);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            getActionBarView().getLeftTextView()
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

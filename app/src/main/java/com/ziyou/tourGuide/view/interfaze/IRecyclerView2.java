package com.ziyou.tourGuide.view.interfaze;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Edward on 16/1/28.
 */
public interface IRecyclerView2<T extends RecyclerView.Adapter> {
    RecyclerView getRecyclerView();
    void setAdapter(T adapter);
}

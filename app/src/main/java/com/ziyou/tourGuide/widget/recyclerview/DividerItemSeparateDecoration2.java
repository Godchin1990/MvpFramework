package com.ziyou.tourGuide.widget.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Edward on 16/1/11.
 */
public class DividerItemSeparateDecoration2 extends RecyclerView.ItemDecoration {
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect,view,parent,state);
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            view.setPadding(20, 10, 20, 10);
        }
    }
}

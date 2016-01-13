package com.ziyou.tourGuide.widget.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Edward on 16/1/11.
 */
public class DividerItemMeDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        Drawable mDivider = parent.getContext().getResources().getDrawable(R.drawable.recyclerview_divide_type_decoration);
//        final int left = parent.getPaddingLeft();
//        final int right = parent.getWidth() - parent.getPaddingRight();
//
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
//                    .getLayoutParams();
//            final int top = child.getBottom() + params.bottomMargin;
//            final int bottom = top + mDivider.getIntrinsicHeight();
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(c);
//        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect,view,parent,state);
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if(i == 3){
                view.setPadding(0, 0, 0, 30);
//                outRect.set(0, 0, 0, 30);
            }else {
                view.setPadding(0, 0, 0, 10);
            }
        }
    }
}

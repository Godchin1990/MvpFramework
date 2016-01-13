package com.ziyou.tourGuide.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Edward on 15/11/16.
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView;
        recyclerView = new RecyclerView(context, attrs);
        recyclerView.setId(R.id.recyclerview);
        return recyclerView;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        int lastVisiblePosition = getRefreshableView().getChildPosition(
                getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1));
        if (lastVisiblePosition >= getRefreshableView().getAdapter().getItemCount() - 1) {
            View lastView = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
            if (lastView == null) {
                return false;
            }
            return lastView.getBottom() <= getRefreshableView().getBottom();
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        if (getRefreshableView().getChildCount() <= 0) {
            return true;
        }
        int firstVisiblePosition = getRefreshableView().getChildPosition(
                getRefreshableView().getChildAt(0));
        if (firstVisiblePosition == 0) {
            return getRefreshableView().getChildAt(0).getTop() >= getRefreshableView().getTop();
        } else {
            return false;
        }
    }
}

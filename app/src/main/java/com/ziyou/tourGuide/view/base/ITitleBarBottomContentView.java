package com.ziyou.tourGuide.view.base;

import android.view.View;
import android.widget.FrameLayout;

import com.ziyou.tourGuide.widget.MyActionBar;

/**
 * Created by Edward on 16/1/7.
 */
public interface ITitleBarBottomContentView {
    MyActionBar getActionBarView();
    FrameLayout getContentView();
    View setContentView();
    FrameLayout getBottomLayout();
    View setBottomLayout();
}

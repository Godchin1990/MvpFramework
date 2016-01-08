package com.example.edward.mvpframework.view.base;

import android.view.View;
import android.widget.FrameLayout;

import com.example.edward.mvpframework.widget.MyActionBar;

/**
 * Created by Edward on 16/1/7.
 */
public interface ITitleBarContentView {
    MyActionBar getActionBarView();
    FrameLayout getContentView();
    void setContentView(View view);
}

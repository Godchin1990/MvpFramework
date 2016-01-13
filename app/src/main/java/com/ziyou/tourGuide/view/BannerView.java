package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.view.base.WebContentView;

/**
 * Created by Edward on 16/1/13.
 */
public class BannerView extends WebContentView {
    public BannerView(Context context) {
        super(context);
    }

    /**
     * 初始化头
     * @param view
     */
    protected void initActionBar(View view) {
        super.initActionBar(view);
        actionBar.getRightView().setImageResource(R.mipmap.ic_share);
    }
}

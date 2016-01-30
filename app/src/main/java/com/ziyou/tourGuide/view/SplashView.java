package com.ziyou.tourGuide.view;

import android.content.Context;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.view.base.BaseView;
import com.ziyou.tourGuide.view.interfaze.ISplashView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/29.
 */
public class SplashView extends BaseView implements ISplashView {

    @Bind(R.id.splash_img)
    SimpleDraweeView splash_img;

    public SplashView(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_splash,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public SimpleDraweeView getSplashSimpleDraweeView() {
        return splash_img;
    }
}

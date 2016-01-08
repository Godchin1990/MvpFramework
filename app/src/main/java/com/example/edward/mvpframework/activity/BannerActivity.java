package com.example.edward.mvpframework.activity;

import com.example.edward.mvpframework.activity.base.BaseFragmentActivity;
import com.example.edward.mvpframework.fragment.BannerFragment;
import com.example.edward.mvpframework.fragment.base.BaseFragment;

/**
 * Created by Edward on 16/1/7.
 */
public class BannerActivity extends BaseFragmentActivity {
    @Override
    protected BaseFragment createFragmentForActivity() {
        BannerFragment bannerFragment = new BannerFragment();
//        if(getIntent().getExtras()!=null){
////            Bundle bundleExtra = getIntent().getBundleExtra(Const.BUNDLE);
////            Bundle bundleExtra = getIntent().getExtras().getBundle(Const.BUNDLE);
//            HomeBanner homeBanner = getIntent().getParcelableExtra(Const.BANNER);
//            Bundle bundle = new Bundle();
//            bundle.putParcelable(Const.BANNER, homeBanner);
//            bannerFragment.setArguments(bundle);
//        }
        return bannerFragment;
    }
}

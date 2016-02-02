package com.ziyou.tourGuide.activity.base;

import android.os.Bundle;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.view.base.FragmentView;

/**
 * 整个界面用Fragment做时,使用该抽象类作为父类
 * Created by Edward on 15/10/15.
 */
public abstract class BaseFragmentActivity extends BaseActivity {

    private BaseFragment baseFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentView fragmentView = new FragmentView(this);
        setContentView(fragmentView.getRootView());
        baseFragment = createFragmentForActivity();
        if(getIntent().getBundleExtra(Const.BUNDLE)!=null){
            Bundle bundleExtra = getIntent().getBundleExtra(Const.BUNDLE);
            baseFragment.setArguments(bundleExtra);
        }
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.activity_fragment, baseFragment, baseFragment.getTAG())
                .commit();
    }

    /**
     * 为activity创建一个fragment
     * @return 返回创建的fragment
     */
    protected abstract BaseFragment createFragmentForActivity();

    protected BaseFragment getBaseFragment() {
        return baseFragment;
    }
}

package com.example.edward.mvpframework.activity.base;

import android.os.Bundle;

import com.example.edward.mvpframework.R;
import com.example.edward.mvpframework.fragment.base.BaseFragment;
import com.example.edward.mvpframework.view.FragmentView;

/**
 * 整个界面用Fragment做时,使用该抽象类作为父类
 * Created by Edward on 15/10/15.
 */
public abstract class BaseFragmentActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentView fragmentView = new FragmentView(this);
        setContentView(fragmentView.getBootView());
        BaseFragment fragment = createFragmentForActivity();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.activity_fragment, fragment, fragment.getFragmentTag())
                .commit();
    }

    /**
     * 为activity创建一个fragment
     * @return 返回创建的fragment
     */
    protected abstract BaseFragment createFragmentForActivity();
}

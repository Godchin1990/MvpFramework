package com.ziyou.tourGuide.activity;

import android.os.Bundle;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.activity.base.BaseActivity;
import com.ziyou.tourGuide.view.base.FragmentView;

/**
 * Created by Edward on 16/1/30.
 */
public class ChatActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //new出EaseChatFragment或其子类的实例
        FragmentView fragmentView = new FragmentView(this);
        setContentView(fragmentView.getRootView());
        EaseChatFragment chatFragment = new EaseChatFragment();
        String username = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        //传入参数
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, username);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.activity_fragment, chatFragment).commit();

    }
}

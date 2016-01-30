package com.ziyou.tourGuide.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;

import com.easemob.chat.EMConversation;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseConversationListFragment;
import com.easemob.easeui.ui.EaseConversationListFragment.EaseConversationListItemClickListener;
import com.ziyou.tourGuide.activity.ChatActivity;
import com.ziyou.tourGuide.fragment.DiscoveryFragment;
import com.ziyou.tourGuide.fragment.EmptyFragment;
import com.ziyou.tourGuide.fragment.HomeFragment;
import com.ziyou.tourGuide.fragment.MeFragment;

/**
 * Created by Edward on 16/1/1.
 */
public class IndexViewPagerAdapter extends FragmentPagerAdapter {

    private RadioGroup radioGroup;

    public IndexViewPagerAdapter(FragmentManager fm, RadioGroup radioGroup) {
        super(fm);
        this.radioGroup = radioGroup;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                return fragment;
            case 1:
                fragment = new DiscoveryFragment();
                return fragment;
            case 2:
                EaseConversationListFragment conversationListFragment = new EaseConversationListFragment();
                conversationListFragment.setConversationListItemClickListener(new EaseConversationListItemClickListener() {
                    @Override
                    public void onListItemClicked(EMConversation conversation) {
                        radioGroup.getContext().startActivity(new Intent(radioGroup.getContext(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName()));
                    }
                });
                return conversationListFragment;
            case 3:
                fragment = new MeFragment();
                return fragment;
            default:
                fragment = new EmptyFragment();
                return fragment;
        }

    }

    @Override
    public int getCount() {
        return radioGroup.getChildCount();
    }
}

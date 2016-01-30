package com.ziyou.tourGuide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ziyou.tourGuide.activity.IndexActivity;
import com.ziyou.tourGuide.command.SimpleDraweeViewCommand;
import com.ziyou.tourGuide.fragment.base.BaseFragment;
import com.ziyou.tourGuide.model.Splash;
import com.ziyou.tourGuide.network.NetworkHelper;
import com.ziyou.tourGuide.network.ServerAPI;
import com.ziyou.tourGuide.network.StringCallBack;
import com.ziyou.tourGuide.view.SplashView;

/**
 * Created by Edward on 16/1/29.
 */
public class SplashFragment extends BaseFragment implements StringCallBack<String> {

    private static final int sleepTime = 2000;

    private SplashView splashView;

    @Override
    protected void initData() {
        initListener();
        requestNetwork();
    }

    private void requestNetwork() {
        String url = ServerAPI.Splash.buildStartPageUrl();
        NetworkHelper.getInstance().sendGetStringRequest(url, null, this, "refresh");
    }

    private void initListener() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        splashView = new SplashView(getContext());
        return splashView.getRootView();
    }

    @Override
    public void onSuccess(final String data, final String tag) {
        Log.d(TAG, data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                switch (tag) {
                    case "refresh":
                        Splash splash = gson.fromJson(data, Splash.class);
                        SimpleDraweeViewCommand command = new SimpleDraweeViewCommand(splashView.getSplashSimpleDraweeView(), splash.getImage_url());
                        command.execute();

                        new Thread(new Runnable() {
                            public void run() {
                                long start = System.currentTimeMillis();
//                                if (UserHelper.getInstance().isLogin()) {
//                                    EMGroupManager.getInstance().loadAllGroups();
//                                    EMChatManager.getInstance().loadAllConversations();
//                                }
                                long costTime = System.currentTimeMillis() - start;
                                //等待sleeptime时长
                                if (sleepTime - costTime > 0) {
                                    try {
                                        Thread.sleep(sleepTime - costTime);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                //进入主页面
                                if(getContext()!=null){
                                    startActivity(new Intent(getContext(), IndexActivity.class));
                                    getActivity().finish();
                                }
                            }
                        }).start();
                        break;
                }
            }
        });
    }

    @Override
    public void onFail(int code, String message, Object object) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}

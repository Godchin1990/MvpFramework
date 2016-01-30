package com.ziyou.tourGuide.helper;

import android.content.Context;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.util.NetUtils;
import com.ziyou.tourGuide.event.ClickEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Edward on 16/1/30.
 */
public class HXHelper {
    private Context appContext;
    public static final String TAG_HX_LOGOUT_SUCCESS = "hx_logout_success";
    public static final String TAG_HX_LOGIN_SUCCESS = "hx_login_success";
    public static final String TAG_HX_LOGIN_ERROR = "hx_login_error";
    private final String TAG;

    {
        TAG = getClass().getSimpleName();
    }

    private volatile static HXHelper helper;
    /**
     * 环信是否初始化
     */
    private boolean sdkInited;

    private HXHelper() {

    }

    public static HXHelper getInstance() {
        if (helper == null) {
            synchronized (HXHelper.class) {
                if (helper == null) {
                    helper = new HXHelper();
                }
            }
        }
        return helper;
    }

    /**
     * 初始化环信
     *
     */
    public void init(Context context) {
        if (EaseUI.getInstance().init(appContext)) {
            appContext = context;
            EMChat.getInstance().setDebugMode(true);
            //设置全局监听
            setGlobalListeners();
        }
    }

    /**
     * 退出账户时,登出环信聊天
     */
    public void logout() {
        //此方法为异步方法
        EMChatManager.getInstance().logout(new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                ClickEvent clickEvent = new ClickEvent(TAG_HX_LOGOUT_SUCCESS);
                EventBus.getDefault().post(clickEvent);
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub

            }
        });

    }

    /**
     * 登录环信
     *
     * @param userName
     * @param password
     */
    public void login(String userName, String password) {
        EMChatManager.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Log.d("main", "登陆聊天服务器成功！");

                ClickEvent clickEvent = new ClickEvent(TAG_HX_LOGIN_SUCCESS);
                EventBus.getDefault().post(clickEvent);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登陆聊天服务器失败！");
                ClickEvent clickEvent = new ClickEvent(TAG_HX_LOGIN_ERROR);
                EventBus.getDefault().post(clickEvent);
            }
        });
    }

    /**
     * 设置全局事件监听
     */
    protected void setGlobalListeners() {
        // create the global connection listener
        EMConnectionListener connectionListener = new EMConnectionListener() {
            @Override
            public void onDisconnected(int error) {
                if (error == EMError.USER_REMOVED) {
                    // 显示帐号已经被移除

                } else if (error == EMError.CONNECTION_CONFLICT) {
                    // 显示帐号在其他设备登陆

                } else if (NetUtils.hasNetwork(appContext)) {
                    //连接不到聊天服务器

                } else {
                    //当前网络不可用，请检查网络设置

                }
            }

            @Override
            public void onConnected() {

            }
        };

        //注册连接监听
        EMChatManager.getInstance().addConnectionListener(connectionListener);
    }

}

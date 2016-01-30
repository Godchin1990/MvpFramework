package com.ziyou.tourGuide.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.util.Pair;

import com.easemob.EMCallBack;
import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroupManager;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.util.NetUtils;
import com.ziyou.tourGuide.event.ClickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        if (initSDK(context)) {
            appContext = context;
            EMChat.getInstance().init(appContext);
            EaseUI.getInstance().init(appContext);
            EMChat.getInstance().setDebugMode(true);
            //设置全局监听
            setGlobalListeners();
        }
    }

    /**
     * this function will initialize the HuanXin SDK
     *
     * @param context
     * @return
     */
    private synchronized boolean initSDK(Context context) {
        if (sdkInited) {
            return true;
        }
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(context, pid);
        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
            Log.e(TAG, "enter the service process!");
            //"com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名

            // 则此application::onCreate 是被service 调用的，直接返回
            return false;
        }
        EMChat.getInstance().init(context);
        sdkInited = true;
        return true;
    }

    /**
     * 获得应用包名
     *
     * @param context
     * @param pID
     * @return
     */
    private String getAppName(Context context, int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = context.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
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


    /**
     * 获取所有会话
     *
     * @param context
     * @return
    +    */
    public List<EMConversation> loadConversationsWithRecentChat() {
        // 获取所有会话，包括陌生人
        Map<String, EMConversation> conversations = EMChatManager.getInstance().getAllConversations();
        // 过滤掉messages size为0的conversation
        /**
         * 如果在排序过程中有新消息收到，lastMsgTime会发生变化
         * 影响排序过程，Collection.sort会产生异常
         * 保证Conversation在Sort过程中最后一条消息的时间不变
         * 避免并发问题
         */
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }
        try {
            // Internal is TimSort algorithm, has bug
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }
        return list;
    }

    /**
     * 根据最后一条消息的时间排序
     *
     * @param usernames
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {

                if (con1.first == con2.first) {
                    return 0;
                } else if (con2.first > con1.first) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
    }
}

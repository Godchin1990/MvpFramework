package com.ziyou.tourGuide.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.ziyou.tourGuide.event.ClickEvent;

import java.util.Iterator;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Edward on 16/1/30.
 */
public class HXHelper {
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

    private HXHelper(){

    }
    public static HXHelper getInstance(){
        if(helper == null){
            synchronized (HXHelper.class){
                if(helper == null){
                    helper = new HXHelper();
                }
            }
        }
        return helper;
    }

    /**
     * 初始化环信
     * @param context
     */
    public void init(Context context){
        if(initSDK(context)){
//            DemoHelper.getInstance().init(context);
            EMChat.getInstance().init(context);
            EMChat.getInstance().setDebugMode(true);
        }
    }

    /**
     *this function will initialize the HuanXin SDK
     *
     * @return boolean true if caller can continue to call HuanXin related APIs after calling onInit, otherwise false.
     *
     * 初始化环信sdk及easeui库
     * 返回true如果正确初始化，否则false，如果返回为false，请在后续的调用中不要调用任何和环信相关的代码
     * @param context
     * @return
     */
    private synchronized boolean initSDK(Context context){
        if(sdkInited){
            return true;
        }
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(context,pid);
        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(context.getPackageName())) {
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
     * @param context
     * @param pID
     * @return
     */
    private String getAppName(Context context,int pID) {
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
     * @param userName
     * @param password
     */
    public void login(String userName, String password) {
        EMChatManager.getInstance().login(userName,password,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Log.d("main", "登陆聊天服务器成功！");
                EMGroupManager.getInstance().loadAllGroups();
                EMChatManager.getInstance().loadAllConversations();
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
}

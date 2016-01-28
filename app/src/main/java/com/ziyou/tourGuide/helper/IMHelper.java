package com.ziyou.tourGuide.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Edward on 16/1/25.
 */
public class IMHelper {
    public static final String TAG = "IMHelper";

    private static volatile IMHelper helper;
    private IMHelper(){

    }
    public static IMHelper getInstance(){
        if (helper==null){
            synchronized (IMHelper.class){
                if (helper == null){
                    helper = new IMHelper();
                }
            }
        }
        return helper;
    }

    /**
     * 开启应用时调用
     * @param context
     */
    public void init(Context context){
        EMChat.getInstance().init(context);
    }

    /**
     * @param debug
     */
    public void setDebugMode(boolean debug){
        /**
         * debugMode == true 时为打开，sdk 会在log里输入调试信息
         * @param debugMode
         * 在做代码混淆的时候需要设置成false
         */
        EMChat.getInstance().setDebugMode(debug);//在做打包混淆时，要关闭debug模式，避免消耗不必要的资源
    }

    /**
     * 此方法主要为了在苹果推送时能够推送昵称(nickname)而不是userid,一般可以在登陆成功后从自己服务器获取到个人信息，然后拿到nickname更新到环信服务器。
     * @param nickname
     */
    public void updateCurrentUserNick(String nickname){
        //此方法传入一个字符串String类型的参数，返回成功或失败的一个Boolean类型的返回值
        EMChatManager.getInstance().updateCurrentUserNick(nickname);
    }

    public boolean isTwiceCall(Context context){
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(context,pid);
        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase("com.easemob.chatuidemo")) {
            Log.e(TAG, "enter the service process!");
            //"com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名

            // 则此application::onCreate 是被service 调用的，直接返回
            return true;
        }
        return false;
    }
    /**
     * 获得App名
     * <p>
     *     用于处理第三方服务启动,导致Application调用两次的情况
     * </p>
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
}

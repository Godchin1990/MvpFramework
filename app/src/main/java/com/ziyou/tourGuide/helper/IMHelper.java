package com.ziyou.tourGuide.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;

import com.easemob.chat.EMChat;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Edward on 16/1/25.
 */
public class IMHelper {
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
     *
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

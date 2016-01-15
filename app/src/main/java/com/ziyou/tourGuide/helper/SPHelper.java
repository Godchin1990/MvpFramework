package com.ziyou.tourGuide.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 使用这个方法,需要调用Context初始化context
 * Created by Edward on 16/1/6.
 */
public class SPHelper {

    public static final String CONFIG = "config";

    private volatile static SPHelper instance ;
    private Context context;

    private SPHelper(){
    }
    public static SPHelper getInstance(){
        if(instance==null){
            synchronized (SPHelper.class){
                if(instance==null){
                    instance = new SPHelper();
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
    }

    public SharedPreferences getSharedPreference(){
        if(context!=null){
            return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE);
        }
        return null;
    }

    public SharedPreferences getSharedPreference(int mode){
        if(context!=null){
            return context.getSharedPreferences(CONFIG, mode);
        }
        return null;
    }

    public SharedPreferences getSharedPreference(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreference(Context context,int mode){
        return context.getSharedPreferences(CONFIG, mode);
    }

    public class Const{
        public static final String windowWidth = "window_width";
        public static final String windowHeight = "window_height";
        public static final String token = "token";
        public static final String user = "user";

    }

}

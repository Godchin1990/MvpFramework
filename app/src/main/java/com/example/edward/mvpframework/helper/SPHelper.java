package com.example.edward.mvpframework.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Edward on 16/1/6.
 */
public class SPHelper {

    public static final String CONFIG = "config";

    private volatile static SPHelper instance ;
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

    public SharedPreferences getSharedPreference(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreference(Context context,int mode){
        return context.getSharedPreferences(CONFIG, mode);
    }

    public class Const{
        public static final String windowWidth = "window_width";
        public static final String windowHeight = "window_height";
    }

}

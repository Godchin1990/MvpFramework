package com.ziyou.tourGuide.helper;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ziyou.tourGuide.model.TokenInfomation;

/**
 * 这个类依赖SPHelper
 * Created by Edward on 16/1/15.
 */
public class TokenHelper {
    private static volatile TokenHelper helpter;
    private final Gson gson;

    private TokenHelper(){
        gson = new Gson();
    }
    public static TokenHelper getInstance(){
        if(helpter==null){
            synchronized (TokenHelper.class){
                if(helpter==null){
                    helpter = new TokenHelper();
                }
            }
        }
        return helpter;
    }

    public void setTokenInformation(Context context,TokenInfomation tokenInfomation){
        String s = gson.toJson(tokenInfomation);
        SPHelper.getInstance().getSharedPreference(context).edit().putString(SPHelper.Const.token,s).apply();
    }

    public TokenInfomation getTokenInformation(Context context){
        String token = SPHelper.getInstance().getSharedPreference(context).getString(SPHelper.Const.token, "");
        if(!TextUtils.isEmpty(token)){
            return gson.fromJson(token,TokenInfomation.class);
        }
        return null;
    }




    public void setTokenInformation(TokenInfomation tokenInfomation){
        String s = gson.toJson(tokenInfomation);
        SPHelper.getInstance().getSharedPreference().edit().putString(SPHelper.Const.token,s).apply();
    }

    public TokenInfomation getTokenInformation(){
        String token = SPHelper.getInstance().getSharedPreference().getString(SPHelper.Const.token, "");
        if(!TextUtils.isEmpty(token)){
            return gson.fromJson(token,TokenInfomation.class);
        }
        return null;
    }
    public void clearTokenInformation(){
        SPHelper.getInstance().getSharedPreference().edit().remove(SPHelper.Const.token).apply();
    }
}

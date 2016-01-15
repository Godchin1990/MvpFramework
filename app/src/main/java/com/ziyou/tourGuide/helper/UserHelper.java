package com.ziyou.tourGuide.helper;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ziyou.tourGuide.model.UserInformation;

/**
 * Created by Edward on 16/1/14.
 */
public class UserHelper {
    private volatile static UserHelper helper;
    private final Gson gson;

    private UserHelper(){
        gson = new Gson();
    }
    public static UserHelper getInstance(){
        if(helper==null){
            synchronized (UserHelper.class){
                if(helper==null){
                    helper = new UserHelper();
                }
            }
        }
        return helper;
    }

    public UserInformation getUserInformation(){
        String token = SPHelper.getInstance().getSharedPreference().getString(SPHelper.Const.user, "");
        if(!TextUtils.isEmpty(token)){
            return gson.fromJson(token, UserInformation.class);
        }
        return null;
    }

    public void setUserInformation(UserInformation tokenInfomation){
        String s = gson.toJson(tokenInfomation);
        SPHelper.getInstance().getSharedPreference().edit().putString(SPHelper.Const.user,s).apply();
    }


    public String getPhoneNumber(){
        String phone = "";
        UserInformation userInformation = getUserInformation();
        if(userInformation!=null){
            phone = userInformation.getPhone();
        }
        return phone;
    }

    public void clearUserInformation(Context context){
        SPHelper.getInstance().getSharedPreference(context).edit().remove(SPHelper.Const.user).apply();
    }
}

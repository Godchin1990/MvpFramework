package com.ziyou.tourGuide.helper;

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

    public boolean isLogin(){
        UserInformation userInformation = getUserInformation();
        if(userInformation==null){
            return false;
        }else {
            return true;
        }

    }

    public void clearUserInformation(){
        SPHelper.getInstance().getSharedPreference().edit().remove(SPHelper.Const.user).apply();
    }

    public String getGenderCode(String gender){
        switch (gender){
            case "男":
                return "1";
            case "女":
                return "2";
            default:
                return "0";
        }
    }
}

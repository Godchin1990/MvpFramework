package com.ziyou.tourGuide.helper;

/**
 * Created by Edward on 16/1/14.
 */
public class UserInformationHelper {
    private volatile static UserInformationHelper helper;
    private UserInformationHelper(){

    }
    public static UserInformationHelper getInstance(){
        if(helper==null){
            synchronized (UserInformationHelper.class){
                if(helper==null){
                    helper = new UserInformationHelper();
                }
            }
        }
        return helper;
    }
    public void getUserInformation(){

    }

    public String getPhoneNumber(){
        return "";
    }
}

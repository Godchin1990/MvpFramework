package com.example.edward.mvpframework.helper;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by Edward on 16/1/11.
 */
public class LocationHelper {
    private volatile static LocationHelper instance ;
    private AMapLocationClient locationClient;

    private LocationHelper(){
    }
    public static LocationHelper getInstance(){
        if(instance==null){
            synchronized (SPHelper.class){
                if(instance==null){
                    instance = new LocationHelper();
                }
            }
        }
        return instance;
    }

    public void stopLocation(){
        locationClient.stopLocation();
    }

    public void onDestroy(){
        locationClient.onDestroy();
    }

    public void getCurrentLocation(Context context/*,LocationCallback callback*/,AMapLocationListener mLocationListener){
        locationClient = new AMapLocationClient(context);
        AMapLocationClientOption locationOption = new AMapLocationClientOption();
//        initOption(locationOption);
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationOption.setOnceLocation(true);
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(mLocationListener);

        locationClient.startLocation();

    }

}

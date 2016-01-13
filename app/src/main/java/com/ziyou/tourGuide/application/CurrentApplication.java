package com.ziyou.tourGuide.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;

import com.ziyou.tourGuide.helper.SPHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.ziyou.tourGuide.helper.ShareHelper;

/**
 * Created by Edward on 15/10/22.
 */
public class CurrentApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        
        ImagePipelineConfig imagePipelineConfig = ConfigConstants.getImagePipelineConfig(getApplicationContext());
        Fresco.initialize(this, imagePipelineConfig);

        init();
        ShareHelper.getInstance().init();
    }

    private void init() {
        WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        SharedPreferences sp = SPHelper.getInstance().getSharedPreference(getApplicationContext());
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(SPHelper.Const.windowHeight,height);
        edit.putInt(SPHelper.Const.windowWidth,width);
        edit.apply();
    }


}

package com.ziyou.tourGuide.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.ziyou.tourGuide.helper.HXHelper;
import com.ziyou.tourGuide.helper.SPHelper;
import com.ziyou.tourGuide.helper.ShareHelper;

/**
 * Created by Edward on 15/10/22.
 */
public class CurrentApplication extends Application implements Thread.UncaughtExceptionHandler {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        ImagePipelineConfig imagePipelineConfig = ConfigConstants.getImagePipelineConfig(getApplicationContext());
        Fresco.initialize(this, imagePipelineConfig);

        SPHelper.getInstance().init(getApplicationContext());
        init();

        ShareHelper.getInstance().init();

        initIM();

    }

    private void initIM() {
        HXHelper.getInstance().init(this);
    }

    private void init() {
        WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        SharedPreferences sp = SPHelper.getInstance().getSharedPreference(getApplicationContext());
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(SPHelper.Const.windowHeight, height);
        edit.putInt(SPHelper.Const.windowWidth, width);
        edit.apply();
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.out.println("uncaughtException");
        System.exit(0);
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }
}

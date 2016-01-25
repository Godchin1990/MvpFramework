package com.ziyou.tourGuide.view.base;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by Edward on 16/1/25.
 */
public class GuideJavaScriptInterFace {

    public final String TAG;
    {
        TAG = getClass().getSimpleName();
    }

    private GuideJavaScriptCallback callback;

    public GuideJavaScriptInterFace(GuideJavaScriptCallback callback) {
        this.callback = callback;
    }

    @JavascriptInterface
    public void turn_app(String json) {
        Log.d(TAG, json);
        if(callback!=null){
            callback.parseJsonToSkip(json);
        }
    }
}

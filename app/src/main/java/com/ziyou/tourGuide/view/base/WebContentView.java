package com.ziyou.tourGuide.view.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;

import com.ziyou.tourGuide.helper.WebViewHelper;
import com.ziyou.tourGuide.view.component.IWebView;

/**
 * Created by Edward on 16/1/8.
 */
public class WebContentView extends TitleBarContentView implements IWebView {

    private WebView webView;

    public WebContentView(Context context) {
        super(context);
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public View setContentView() {
        webView = WebViewHelper.gtInstance().getWebView(getContext());
        return webView;
    }

    /**
     * 设置H5监听回调,如果不设置,就不会回调Js代码,因为被turn_app方法屏蔽了
     * @param callback
     */
    public void setCallback(JavaScriptCallback callback) {
        webView.addJavascriptInterface(new GuideJavaScriptInterFace(callback),"guide");
    }

}

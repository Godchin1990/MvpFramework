package com.example.edward.mvpframework.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.edward.mvpframework.view.base.TitleBarContentView;
import com.example.edward.mvpframework.view.component.IWebView;

/**
 * Created by Edward on 16/1/8.
 */
public class WebContentView extends TitleBarContentView implements IWebView {

    private WebView webView;

    private GuideJavaScriptCallback callback;

    public WebContentView(Context context) {
        super(context);
        webView = new WebView(getContext());
        setContentView(webView);
        initWebView();
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        String ua = webView.getSettings().getUserAgentString() + "/jieke";
        webView.getSettings().setUserAgentString(ua);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);// 使用当前WebView处理跳转
                return true;// true表示此事件在此处被处理，不需要再广播
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new GuideJavaScriptInterFace(),"guide");
    }

    @Override
    public void setContentView(View view) {
        getContentView().removeAllViews();
        getContentView().addView(view);
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    /**
     * webview的回调函数
     */
    private class GuideJavaScriptInterFace {
        @JavascriptInterface
        public void turn_app(String json) {
            Log.d(TAG, json);
            if(callback!=null){
                callback.parseJsonToSkip(json);
            }
        }
    }

    /**
     * 设置H5监听回调
     * @param callback
     */
    public void setCallback(GuideJavaScriptCallback callback) {
        this.callback = callback;
    }

    /**
     * 设置webview
     */
    public interface GuideJavaScriptCallback{
        void parseJsonToSkip(String json);
    }
}

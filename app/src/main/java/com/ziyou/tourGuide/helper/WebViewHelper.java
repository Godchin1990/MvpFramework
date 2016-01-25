package com.ziyou.tourGuide.helper;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Edward on 16/1/25.
 */
public class WebViewHelper {
    private volatile static WebViewHelper helper = null;
    private WebViewHelper(){

    }
    public static WebViewHelper gtInstance(){
        if(helper==null){
            synchronized (WebViewHelper.class){
                if(helper==null){
                    helper = new WebViewHelper();
                }
            }
        }
        return helper;
    }

    public WebView getWebView(Context context){

        WebView webView = new WebView(context);
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
        return webView;
    }
}

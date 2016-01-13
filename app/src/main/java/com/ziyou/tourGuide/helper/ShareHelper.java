package com.ziyou.tourGuide.helper;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.ziyou.tourGuide.R;

/**
 * Created by Edward on 16/1/13.
 */
public class ShareHelper {

    public static final String PARAM_IMAGE_URL = "image_url";
    public static final String PARAM_SHARE_URL = "share_url";
    public static final String PARAM_TITLEL = "title";
    public static final String PARAM_CONTENT = "content";

    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
            {
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA,
                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
            };

    public static final String WECHAT_APP_ID = "wx2355a48e3af3ef0b";
    public static final String WECHAT_APP_SECRET = "2369e1fa75627ce7e2e7c8052f48ec62";
    public static final String QQ_APP_ID = "1104627825";
    public static final String QQ_APP_KEY = "ENw3gkOspcAEcqdv";

    private volatile static ShareHelper shareHelper;
    private ShareHelper(){

    }
    public static ShareHelper getInstance(){
        if(shareHelper==null){
            synchronized (ShareHelper.class){
                if(shareHelper==null){
                    shareHelper = new ShareHelper();
                }
            }
        }
        return shareHelper;
    }

    public void init(){
        PlatformConfig.setQQZone(QQ_APP_ID, QQ_APP_KEY);
    }

    public void shareUrl(Activity activity, Bundle bundle){
        shareUrl(activity, bundle, null);
    }

    public void shareUrl(Activity activity, Bundle bundle, ShareBoardlistener shareBoardlistener){
        shareUrl(activity, bundle, shareBoardlistener);
    }

    public void shareUrl(Activity activity, Bundle bundle, ShareBoardlistener shareBoardlistener, UMShareListener... umShareListener){
        String imageUrl = bundle.getString(PARAM_IMAGE_URL);
        String shareUrl = bundle.getString(PARAM_SHARE_URL);
        String title = bundle.getString(PARAM_TITLEL);
        String content = bundle.getString(PARAM_CONTENT);
        UMImage image;
        if(!TextUtils.isEmpty(imageUrl)){
            image = new UMImage(activity, imageUrl);
        }else {
            image = new UMImage(activity,
                    BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher));
        }
        new ShareAction(activity).setDisplayList( displaylist )
                .withText(content)
                .withTitle(title)
                .withTargetUrl(shareUrl)
                .withMedia(image)
                .setListenerList(umShareListener)
                .setShareboardclickCallback(shareBoardlistener)
                .open();
    }
}

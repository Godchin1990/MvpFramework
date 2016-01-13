package com.ziyou.tourGuide.command.h5;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.ziyou.tourGuide.command.base.WebBundleCommand;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Edward on 16/1/13.
 */
public class WebCommandInvoker {


    private static volatile WebCommandInvoker invoker;
    private WebCommandInvoker(){
    }
    public static WebCommandInvoker getInstance(){
        if(invoker==null){
            synchronized (WebCommandInvoker.class){
                if(invoker==null){
                    invoker = new WebCommandInvoker();
                }
            }
        }
        return invoker;
    }

    public void parseCommand(Context context,String json,Bundle bundle,Object... object){
        WebBundleCommand webBundleCommand = new EmptyWebBundleCommand(json,bundle,object);
        try {
            JSONObject jsonObject = new JSONObject(json);
            int type = jsonObject.getInt("type");
            switch (type){
                case SkipType.TYPE_CALENDAR:
                    webBundleCommand = new EmptyWebBundleCommand(json,bundle, object);
                    break;
            }

            webBundleCommand.execute();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context,"参数无效",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 跳转类型
     */
    public final class SkipType{
        /** 点击H5中的按钮跳转环信聊天 */
        public static final int TYPE_CLICK_TO_CHAT              = 1;
        /** 跳转导游页面 */
        public static final int TYPE_GUIDER                     = 2;
        /** 跳转地图页面 */
        public static final int TYPE_MAP                        = 3;
        /** 跳转拨打电话页面 */
        public static final int TYPE_PHONE                      = 4;
        /** H5跳转到预约页面 */
        public static final int TYPE_ORDER_SETTING              = 5;
        /** 跳转日历页面(h5) */
        public static final int TYPE_CALENDAR                   = 6;
        /** 路线详情(actionbar信息) */
        public static final int TYPE_ROUTE                      = 7;
        /** 评论列表 */
        public static final int TYPE_COMMENT                    = 8;
        /** 跳转环信聊天 */
        public static final int TYPE_CHAT_LOADING_OVER          = 9;
        /** 图片浏览 */
        public static final int TYPE_GALLERY                    = 10;
        /** 跳转首页 */
        public static final int TYPE_HOME                       = 11;
        /** 跳转到路线列表页面 */
        public static final int TYPE_ROUTE_LIST                 = 12;
        /** 跳转到发布路线页面 */
        public static final int TYPE_PICK_COMMUNITY_ROUTE       = 13;
        /** 跳转到社区详细页面 */
        public static final int TYPE_COMMUNITY_ROUTE_DETAIL     = 14;
        /** 日历中获取多个日期 type */
        public static final int TYPE_SELECT_MULTI_DATE_OVER     = 15;
    }

}

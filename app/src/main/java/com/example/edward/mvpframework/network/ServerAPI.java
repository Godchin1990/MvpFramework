package com.example.edward.mvpframework.network;

import android.net.Uri;

/**
 * 保存接口地址
 * Created by Edward on 15/11/9.
 */
public class ServerAPI {
    //debug
    private static boolean debug = true;

    /*接客debug版url*/
    private static final String SERVER_BASE_TEST = "http://tguide.selftravel.com.cn/api";
    /*接客发布版url*/
    private static final String SERVER_BASE_PRODUCT = "http://guide.selftravel.com.cn/api";

    //参数常量
    public static String VERSION_V3 = "v3";

    private static String getServerBase() {
        return debug ? SERVER_BASE_TEST : SERVER_BASE_PRODUCT;
    }
    /**
     * HomeFragment相关的接口
     */
    public static class Home{
        public static final String HOME_BANNER_LIST = "get_banner_list";
        public static final String HOME_TOPIC_LIST = "get_main";
        public static final String HOME_ROUTE_LIST = "recommend_route_list";
        public static String buildHomeBannerUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(HOME_BANNER_LIST);
            return builder.toString();
        }
        public static String buildHomeTopicUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(VERSION_V3);
            builder.appendEncodedPath(HOME_TOPIC_LIST);
            return builder.toString();
        }
        public static String buildHomeRouteUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(HOME_ROUTE_LIST);
            return builder.toString();
        }
    }
    /**
     * TopicFragment相关的接口
     */
    public static class Topic{
        public static final String TOPIC_ROUTE_LIST = "get_routes_list";
        public static final String PARAM_TYPE = "ctype";
        public static final String PARAM_LABEL = "label";
        public static final String PARAM_CITY = "city";
        public static String buildTopicRouteUrl(int ctype,String label){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(VERSION_V3);
            builder.appendEncodedPath(TOPIC_ROUTE_LIST);
            builder.appendQueryParameter(PARAM_TYPE, ctype + "");
            if(ctype == 0){
                builder.appendQueryParameter(PARAM_CITY,label);
            }else if(ctype == 1 ){
                builder.appendQueryParameter(PARAM_LABEL,label);
            }
            return builder.toString();
        }
    }

    /**
     * TopicFragment相关的接口
     */
    public static class User{
        public static final String USER = "user";
        public static final String LOGIN = "verify_code_login";
        public static final String IDENTIFY_CODE = "send_code";
        public static final String USER_INFO = "get_userinfo";

        public static final String PARAM_PHONE = "phone";
        public static final String PARAM_IDENTIFY_CODE = "activation_code";
        public static final String PARAM_CLIENT_ID = "client_id";
        public static final String PARAM_CLIENT_INFO = "client_info";
        public static final String PARAM_VERIFY_CODE_TYPE = "type";

        public static String buildLoginUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(USER);
            builder.appendEncodedPath(LOGIN);
            return builder.toString();
        }

        public static String buildIdentifyCodeUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(USER);
            builder.appendEncodedPath(IDENTIFY_CODE);
            return builder.toString();
        }

        public static String buildUserInfoUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(USER);
            builder.appendEncodedPath(USER_INFO);
            return builder.toString();
        }

    }

    /**
     * H5相关的接口
     */
    public static class H5{
        private static final String H5_URL_TEST_BASE = "http://th5.jieke100.com" ;
        private static final String H5_URL_PRODUCT_BASE = "http://h5.jieke100.com" ;

        public static final String PARAM_ID = "id";

        /**路线*/
        private static final String ROUTE_DETAIL_URL = getH5BaseUrl() +"/client/jieke/pages/route.html";
        /**导游*/
        private static final String GUIDER_DETAIL_URL = getH5BaseUrl() +"/client/jieke/pages/guide.html";
        /**日历*/
        private static final String CALENDAR_URL = getH5BaseUrl() +"/client/jieke/pages/calendar.html";

        private static String getH5BaseUrl(){
            return debug ? H5_URL_TEST_BASE : H5_URL_PRODUCT_BASE ;
        }
        /**路线详细url*/
        public static String getRouteDetailH5Url(String routeId) {
            Uri.Builder builder = Uri.parse(ROUTE_DETAIL_URL).buildUpon();
            builder.appendQueryParameter(PARAM_ID,routeId);
            return builder.toString() ;
        }
        /**路线详细url*/
        public static String getGuiderDetailH5Url(String guiderId) {
            Uri.Builder builder = Uri.parse(GUIDER_DETAIL_URL).buildUpon();
            builder.appendQueryParameter(PARAM_ID,guiderId);
            return builder.toString() ;
        }
        /**日历url*/
        public static String getCalendarH5Url(String routeId) {
            Uri.Builder builder = Uri.parse(CALENDAR_URL).buildUpon();
            builder.appendQueryParameter(PARAM_ID,routeId);
            return builder.toString() ;
        }
    }
}
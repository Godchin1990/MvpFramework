package com.ziyou.tourGuide.network;

import android.net.Uri;

/**
 * 保存接口地址
 * Created by Edward on 15/11/9.
 */
public class ServerAPI {
    //DEBUG
    public static boolean DEBUG = true;

    /*接客debug版url*/
    private static final String SERVER_BASE_TEST = "http://tguide.selftravel.com.cn/api";
    /*接客发布版url*/
    private static final String SERVER_BASE_PRODUCT = "http://guide.selftravel.com.cn/api";

    //参数常量
    public static String VERSION_V3 = "v3";
    public static String ID = "id";

    private static String getServerBase() {
        return DEBUG ? SERVER_BASE_TEST : SERVER_BASE_PRODUCT;
    }

    /**
     * SpashFragment相关接口
     */
    public static class Splash{
        public static final String START_PAGE = "start_page";
        public static String buildStartPageUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(START_PAGE);
            return builder.toString();
        }
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
     *  路线详情相关的接口
     */
    public static class Route {
        public static final String GET_ROUTE_DETAIL = "get_route_detail";
        public static final String GET_FETCH_ROUTES = "get_fetch_routes";

        public static final String PARAM_ROUTE = "ctype";
        public static String buildGetRouteDetailUrl(String routeId){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(VERSION_V3);
            builder.appendEncodedPath(GET_ROUTE_DETAIL);
            builder.appendQueryParameter(ID, routeId);
            return builder.toString();
        }

        public static String buildGetFetchRouteslUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(GET_FETCH_ROUTES);
            return builder.toString();
        }
    }

    /**
     * 路线社区
     * 需登陆
     */
    public static class RouteCommunity{
        public static final String GET_WRITE_ROUTE = "get_write_routes";
        public static final String GET_SHARE_ROUTES = "get_share_routes";

        public static String buildGetWriteRouteUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(GET_WRITE_ROUTE);
            return builder.toString();
        }

        public static String buildGetShareRouteUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(GET_SHARE_ROUTES);
            return builder.toString();
        }

    }
    /**
     *  路线详情相关的接口
     */
    public static class GuideDetail{
        //api/v3/get_guide_details/?id=740
        public static final String GET_GUIDE_DETAIL = "get_guide_details";
        public static final String PARAM_ROUTE = "ctype";
        public static String buildGuideDetailUrl(String routeId){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(VERSION_V3);
            builder.appendEncodedPath(GET_GUIDE_DETAIL);
            builder.appendQueryParameter(ID, routeId);
            return builder.toString();
        }
    }

    /**
     *  发现相关的接口
     */
    public static class Discovery{
        //api/v3/get_guide_details/?id=740
        public static final String GET_DISCOVERY_LIST = "get_discover_list";
        public static final String GET_CITY_LIST = "get_city_list";
        public static final String PARAM_CITY = "city";
        public static String buildCityListUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(GET_CITY_LIST);
            return builder.toString();
        }
        public static String buildDiscoveryListUrl(String city){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(VERSION_V3);
            builder.appendEncodedPath(GET_DISCOVERY_LIST);
            builder.appendQueryParameter(PARAM_CITY, city);
            return builder.toString();
        }
    }

    /**
     *  发现详情相关的接口
     */
    public static class DiscoveryDetail{
        //api/v3/get_guide_details/?id=740
        public static final String DEST_ROUTE = "dest_route";

        public static String buildDestRouteUrl(String id){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(DEST_ROUTE);
            builder.appendQueryParameter(ID, id);
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
        public static final String PASSWORDLOGIN = "password_login";
        public static final String GET_MESSAGE_LIST = "get_message_list";
        public static final String CHANGE_PASSWORD = "change_password";
        public static final String MODIFY_USER = "modify_user";

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
        public static String buildPasswordLoginUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(USER);
            builder.appendEncodedPath(PASSWORDLOGIN);
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

        public static String buildGetMessageListUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(USER);
            builder.appendEncodedPath(GET_MESSAGE_LIST);
            return builder.toString();
        }

        public static String buildChangePasswordUrl() {
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(USER);
            builder.appendEncodedPath(CHANGE_PASSWORD);
            return builder.build().toString();
        }
        public static String buildModifyUserUrl() {
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(USER);
            builder.appendEncodedPath(MODIFY_USER);
            return builder.build().toString();
        }

    }

    /**
     * 我的钱包
     */
    public static class Wallet{
        public static final String MY_WALLET = "my_wallet";
        public static String buildMyWalletUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(MY_WALLET);
            return builder.toString();
        }
    }

    /**
     * 用户订单接口 ( 包括 我的旅行 MyTourActivity)
     */
    public static class Order{
        public static final String GET_USER_ORDERS = "get_user_orders";
        public static final String GET_GUIDE_ORDERS = "get_guide_orders";

        public static String buildGetUserOrdersUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(VERSION_V3);
            builder.appendEncodedPath(GET_USER_ORDERS);
            return builder.toString();
        }

        public static String buildGetGuideOrdersUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(VERSION_V3);
            builder.appendEncodedPath(GET_GUIDE_ORDERS);
            return builder.toString();
        }

    }

    public static class CustomerService{
        public static final String FEEDBACK = "feedback";
        public static String buildFeedbackUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(FEEDBACK);
            return builder.toString();
        }
    }

    /**
     * 订单统计
     */
    public static class OrderStatistic{
        public static final String GET_STATIST_ORDER = "get_statist_order";
        public static String buildGetStatistOrderUrl(){
            Uri.Builder builder = Uri.parse(getServerBase()).buildUpon();
            builder.appendEncodedPath(GET_STATIST_ORDER);
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
            return DEBUG ? H5_URL_TEST_BASE : H5_URL_PRODUCT_BASE ;
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
            builder.appendQueryParameter(PARAM_ID, routeId);
            return builder.toString() ;
        }
    }
}

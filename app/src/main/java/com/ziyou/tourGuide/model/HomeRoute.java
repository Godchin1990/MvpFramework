package com.ziyou.tourGuide.model;

import java.util.List;

/**
 * Created by Edward on 15/11/14.
 */
public class HomeRoute {
    /**
     * activity_icon : http://7rfh5h.com1.z0.glb.clouddn.com/activitytehui_1212%403x.png
     * avatar : http://selftravel-image.qiniudn.com/Frt2NGeYzoRRd4-yr2jaJyEqDizI
     * cover : http://selftravel-image.qiniudn.com/46432ca98b7f19a75ceeb9f1575d7dd1.jpg
     * discount : 8.5
     * discount_price : 0.85
     * finish_order : 35
     * guide_id : 1520
     * is_activity : 0
     * is_recommend : true
     * label : []
     * price : 0.1
     * route_id : 482
     * spot_list : [{"id":2715,"scenic_name":"二郎山"},{"id":2716,"scenic_name":"海螺沟"},{"id":2717,"scenic_name":"康定"},{"id":2718,"scenic_name":"折多山"},{"id":2719,"scenic_name":"新都桥"},{"id":2720,"scenic_name":"理塘"},{"id":2721,"scenic_name":"稻城海子山"},{"id":2722,"scenic_name":"稻城"},{"id":2723,"scenic_name":"香格里拉"},{"id":2724,"scenic_name":"香格里拉"},{"id":2725,"scenic_name":"香格里拉乡"},{"id":2726,"scenic_name":"理塘"},{"id":2727,"scenic_name":"巴塘"},{"id":2728,"scenic_name":"芒康"},{"id":2729,"scenic_name":"左贡"},{"id":2730,"scenic_name":"八宿"},{"id":2731,"scenic_name":"然乌湖"},{"id":2732,"scenic_name":"米堆冰川"},{"id":2733,"scenic_name":"波密"},{"id":2734,"scenic_name":"通麦"},{"id":2735,"scenic_name":"鲁朗"},{"id":2736,"scenic_name":"林芝"},{"id":2737,"scenic_name":"尼洋河"},{"id":2738,"scenic_name":"拉萨"},{"id":2739,"scenic_name":"羊卓雍错"},{"id":2740,"scenic_name":"拉萨"},{"id":2741,"scenic_name":"拉萨"},{"id":2742,"scenic_name":"纳木错"},{"id":2743,"scenic_name":"那曲"},{"id":2744,"scenic_name":"唐古拉山"},{"id":2745,"scenic_name":"可可西里"},{"id":2746,"scenic_name":"格尔木"},{"id":2747,"scenic_name":"都兰"},{"id":2748,"scenic_name":"青海湖"},{"id":2749,"scenic_name":"西宁"},{"id":2750,"scenic_name":"天安门广场"}]
     * title : 川藏线自驾游
     */

    private String activity_icon;
    private String avatar;
    private String cover;
    private double discount;
    private String discount_price;
    private int finish_order;
    private int guide_id;
    private int is_activity;
    private boolean is_recommend;
    private String price;
    private int route_id;
    private String title;
    private List<String> label;
    /**
     * id : 2715
     * scenic_name : 二郎山
     */

    private List<SpotListEntity> spot_list;

    public void setActivity_icon(String activity_icon) {
        this.activity_icon = activity_icon;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public void setFinish_order(int finish_order) {
        this.finish_order = finish_order;
    }

    public void setGuide_id(int guide_id) {
        this.guide_id = guide_id;
    }

    public void setIs_activity(int is_activity) {
        this.is_activity = is_activity;
    }

    public void setIs_recommend(boolean is_recommend) {
        this.is_recommend = is_recommend;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public void setSpot_list(List<SpotListEntity> spot_list) {
        this.spot_list = spot_list;
    }

    public String getActivity_icon() {
        return activity_icon;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCover() {
        return cover;
    }

    public double getDiscount() {
        return discount;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public int getFinish_order() {
        return finish_order;
    }

    public int getGuide_id() {
        return guide_id;
    }

    public int getIs_activity() {
        return is_activity;
    }

    public boolean isIs_recommend() {
        return is_recommend;
    }

    public String getPrice() {
        return price;
    }

    public int getRoute_id() {
        return route_id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLabel() {
        return label;
    }

    public List<SpotListEntity> getSpot_list() {
        return spot_list;
    }

    public static class SpotListEntity {
        private int id;
        private String scenic_name;

        public void setId(int id) {
            this.id = id;
        }

        public void setScenic_name(String scenic_name) {
            this.scenic_name = scenic_name;
        }

        public int getId() {
            return id;
        }

        public String getScenic_name() {
            return scenic_name;
        }
    }

    public static class HomeRouteLists extends ResultList<HomeRoute> {
    }



}

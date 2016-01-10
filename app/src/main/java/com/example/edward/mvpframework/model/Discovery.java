package com.example.edward.mvpframework.model;

import java.util.List;

/**
 * Created by Edward on 16/1/10.
 */
public class Discovery {


    /**
     * list : [{"cover":"http://selftravel-image.qiniudn.com/user_cover_2015_05_19_acdbf1c90cb6645ade268af2c1f92d88.jpg","id":2,"latitude":39.999093,"longitude":116.273945,"name":"颐和园","route_num":1,"spot_type":2,"title":"皇家园林博物馆"},{"cover":"http://selftravel-image.qiniudn.com/user_cover_2015_05_19_wKgB3FEsn4aANUQGAA_9SVbvbPM99.jpeg","id":6,"latitude":39.936724,"longitude":116.386242,"name":"恭王府","route_num":1,"spot_type":1,"title":"一座恭王府，半部清代史"},{"cover":"http://selftravel-image.qiniudn.com/user_cover_2015_05_19_0GTL$3]6))_OKKPELXM3L2A.jpg","id":7,"latitude":39.940804,"longitude":116.403065,"name":"南锣鼓巷","route_num":1,"spot_type":3,"title":"在这里迷失北京"}]
     * type : 1
     */

    private int type;
    /**
     * cover : http://selftravel-image.qiniudn.com/user_cover_2015_05_19_acdbf1c90cb6645ade268af2c1f92d88.jpg
     * id : 2
     * latitude : 39.999093
     * longitude : 116.273945
     * name : 颐和园
     * route_num : 1
     * spot_type : 2
     * title : 皇家园林博物馆
     */

    private List<ListEntity> list;

    public void setType(int type) {
        this.type = type;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getType() {
        return type;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class DiscoveryLists extends ResultList<Discovery> {
    }

    public static class ListEntity {
        private String cover;
        private int id;
        private double latitude;
        private double longitude;
        private String name;
        private int route_num;
        private int spot_type;
        private String title;

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRoute_num(int route_num) {
            this.route_num = route_num;
        }

        public void setSpot_type(int spot_type) {
            this.spot_type = spot_type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public int getId() {
            return id;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getName() {
            return name;
        }

        public int getRoute_num() {
            return route_num;
        }

        public int getSpot_type() {
            return spot_type;
        }

        public String getTitle() {
            return title;
        }
    }
}

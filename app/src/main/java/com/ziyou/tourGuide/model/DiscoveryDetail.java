package com.ziyou.tourGuide.model;

import java.util.List;

/**
 * Created by Edward on 16/1/12.
 */
public class DiscoveryDetail {

    /**
     * cover : http://selftravel-image.qiniudn.com/user_cover_2015_05_28_gugong.jpg
     * name : 故宫
     * title : 世界最大的木质结构建筑群
     */

    private BannerEntity banner;
    /**
     * avatar : http://selftravel-image.qiniudn.com/Fg1bB6P7D-TQNByvMFjgUGYJAiOh?imageView2/0/w/300
     * cover : http://selftravel-image.qiniudn.com/17c2f55ece6e3ae6cc39e64425f6b681.jpg
     * discount : 0.2
     * discount_price : 19.6
     * finish_order : 51
     * guide_id : 564
     * is_activity : 0
     * label : ["文化","文化","摄影"]
     * price : 98.0
     * route_id : 378
     * spot_list : [{"id":2134,"scenic_name":"故宫"},{"id":2135,"scenic_name":"什刹海"}]
     * title : 穿梭于故宫各大殿，听听它的历史故事
     */

    private List<HomeRoute> list;

    public void setBanner(BannerEntity banner) {
        this.banner = banner;
    }

    public void setList(List<HomeRoute> list) {
        this.list = list;
    }

    public BannerEntity getBanner() {
        return banner;
    }

    public List<HomeRoute> getList() {
        return list;
    }

    public static class BannerEntity {
        private String cover;
        private String name;
        private String title;

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public String getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }
    }

}

package com.example.edward.mvpframework.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 15/11/14.
 */
public class HomeRoute implements Parcelable {

    /**
     * avatar : http://selftravel-image.qiniudn.com/FstMmHPSDyhNPh8KfYkkRwQSnvbt?imageView2/0/w/300
     * cover : http://selftravel-image.qiniudn.com/0f3fa073ffd218ae36e8c6a284306693.jpg
     * finish_order : 47
     * guide_id : 758
     * is_recommend : false
     * label : ["摄影","宗教"]
     * price : 85.0
     * route_id : 375
     * spot_list : [{"id":2126,"scenic_name":"雍和宫"},{"id":2127,"scenic_name":"孔庙·国子监"},{"id":2128,"scenic_name":"五道营"}]
     * title : 皇府家苑中的朝贡膜拜，京城拜佛最佳去处
     */

    private String avatar;
    private String cover;
    private int finish_order;
    private int guide_id;
    private boolean is_recommend;
    private String price;
    private int route_id;
    private String title;
    private List<String> label;
    private List<SpotListEntity> spot_list;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setFinishOrder(int finish_order) {
        this.finish_order = finish_order;
    }

    public void setGuideId(int guide_id) {
        this.guide_id = guide_id;
    }

    public void setIsRecommend(boolean is_recommend) {
        this.is_recommend = is_recommend;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRouteId(int route_id) {
        this.route_id = route_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public void setSpotList(List<SpotListEntity> spot_list) {
        this.spot_list = spot_list;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCover() {
        return cover;
    }

    public int getFinishOrder() {
        return finish_order;
    }

    public int getGuideId() {
        return guide_id;
    }

    public boolean getIsRecommend() {
        return is_recommend;
    }

    public String getPrice() {
        return price;
    }

    public int getRouteId() {
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
        /**
         * id : 2126
         * scenic_name : 雍和宫
         */

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.avatar);
        dest.writeString(this.cover);
        dest.writeInt(this.finish_order);
        dest.writeInt(this.guide_id);
        dest.writeByte(is_recommend ? (byte) 1 : (byte) 0);
        dest.writeString(this.price);
        dest.writeInt(this.route_id);
        dest.writeString(this.title);
        dest.writeStringList(this.label);
        dest.writeList(this.spot_list);
    }

    public HomeRoute() {
    }

    protected HomeRoute(Parcel in) {
        this.avatar = in.readString();
        this.cover = in.readString();
        this.finish_order = in.readInt();
        this.guide_id = in.readInt();
        this.is_recommend = in.readByte() != 0;
        this.price = in.readString();
        this.route_id = in.readInt();
        this.title = in.readString();
        this.label = in.createStringArrayList();
        this.spot_list = new ArrayList<SpotListEntity>();
        in.readList(this.spot_list, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<HomeRoute> CREATOR = new Parcelable.Creator<HomeRoute>() {
        public HomeRoute createFromParcel(Parcel source) {
            return new HomeRoute(source);
        }

        public HomeRoute[] newArray(int size) {
            return new HomeRoute[size];
        }
    };
}

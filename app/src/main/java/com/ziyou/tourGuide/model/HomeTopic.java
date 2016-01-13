package com.ziyou.tourGuide.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Edward on 15/11/13.
 */
public class HomeTopic implements Parcelable {

    /**
     * guide : 35
     * id : 1
     * image : http://selftravel-image.qiniudn.com/3616ec6e930e2d5cbce44daeab57508d.jpg
     * name : 北京
     * route : 35
     * type : 0
     */

    private int guide;
    private int id;
    private String image;
    private String name;
    private int route;
    private int type;

    public void setGuide(int guide) {
        this.guide = guide;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGuide() {
        return guide;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getRoute() {
        return route;
    }

    public int getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.guide);
        dest.writeInt(this.id);
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeInt(this.route);
        dest.writeInt(this.type);
    }

    public HomeTopic() {
    }

    protected HomeTopic(Parcel in) {
        this.guide = in.readInt();
        this.id = in.readInt();
        this.image = in.readString();
        this.name = in.readString();
        this.route = in.readInt();
        this.type = in.readInt();
    }

    public static class HomeTopicLists extends ResultList<HomeTopic> {
    }

    public static final Creator<HomeTopic> CREATOR = new Creator<HomeTopic>() {
        public HomeTopic createFromParcel(Parcel source) {
            return new HomeTopic(source);
        }

        public HomeTopic[] newArray(int size) {
            return new HomeTopic[size];
        }
    };

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HomeTopic{");
        sb.append("guide=").append(guide);
        sb.append(", id=").append(id);
        sb.append(", image='").append(image).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", route=").append(route);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}

package com.ziyou.tourGuide.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Edward on 15/11/9.
 */
public class HomeBanner implements Parcelable {

    /**
     * act_url :
     * btype : 2
     * id : 22
     * image : http://selftravel-image.qiniudn.com/user_cover_2015_06_25_qianmen.png
     * title : 城门故事--西五门
     */

    private String act_url;
    private String btype;
    private int id;
    private String image;
    private String title;

    public void setAct_url(String act_url) {
        this.act_url = act_url;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAct_url() {
        return act_url;
    }

    public String getBtype() {
        return btype;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.act_url);
        dest.writeString(this.btype);
        dest.writeInt(this.id);
        dest.writeString(this.image);
        dest.writeString(this.title);
    }

    public HomeBanner() {
    }

    protected HomeBanner(Parcel in) {
        this.act_url = in.readString();
        this.btype = in.readString();
        this.id = in.readInt();
        this.image = in.readString();
        this.title = in.readString();
    }

    public static final Creator<HomeBanner> CREATOR = new Creator<HomeBanner>() {
        public HomeBanner createFromParcel(Parcel source) {
            return new HomeBanner(source);
        }

        public HomeBanner[] newArray(int size) {
            return new HomeBanner[size];
        }
    };

    public static class HomeBannerLists extends ResultList<HomeBanner> {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HomeBanner{");
        sb.append("act_url='").append(act_url).append('\'');
        sb.append(", btype='").append(btype).append('\'');
        sb.append(", id=").append(id);
        sb.append(", image='").append(image).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

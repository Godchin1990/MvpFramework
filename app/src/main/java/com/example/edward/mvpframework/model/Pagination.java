package com.example.edward.mvpframework.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 15/11/17.
 */
public class Pagination implements Parcelable {

    /**
     * limit : 1
     * offset : 0
     * total : 1
     */
    private int default_limit;
    private int default_offset;

    private int limit;
    private int offset;
    private int total;

    public Pagination(int default_limit, int default_offset) {
        this.default_limit = default_limit;
        this.default_offset = default_offset;

        this.limit = default_limit;
        this.offset = default_offset;

    }


    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public Map<String,String> getMap(){
        Map<String,String> map = new HashMap<>();
        map.put("limit",limit+"");
        map.put("offset",offset+"");
        return map;
    }

    public void initPagination() {
        this.setLimit(default_limit);
        this.setOffset(default_offset);
    }

    public void update(int size) {
        offset = offset + size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.default_limit);
        dest.writeInt(this.default_offset);
        dest.writeInt(this.limit);
        dest.writeInt(this.offset);
        dest.writeInt(this.total);
    }

    protected Pagination(Parcel in) {
        this.default_limit = in.readInt();
        this.default_offset = in.readInt();
        this.limit = in.readInt();
        this.offset = in.readInt();
        this.total = in.readInt();
    }

    public static final Creator<Pagination> CREATOR = new Creator<Pagination>() {
        public Pagination createFromParcel(Parcel source) {
            return new Pagination(source);
        }

        public Pagination[] newArray(int size) {
            return new Pagination[size];
        }
    };
}

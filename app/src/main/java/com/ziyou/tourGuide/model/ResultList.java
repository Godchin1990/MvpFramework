
package com.ziyou.tourGuide.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultList<T> {

    @SerializedName("pagination")
    public Pagination pagination;

    @SerializedName("list")
    public List<T> list = new ArrayList<T>();

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultList{");
        sb.append("list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}

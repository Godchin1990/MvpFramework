package com.ziyou.tourGuide.model;

import java.util.List;

/**
 * Created by Edward on 16/1/25.
 */
public class RouteCommunity {

    /**
     * id : 354
     * is_me : 1
     * labels : []
     * suggest_price : 100
     * title : 川藏线自驾游
     */

    private int id;
    private int is_me;
    private String suggest_price;
    private String title;
    private List<String> labels;

    public void setId(int id) {
        this.id = id;
    }

    public void setIs_me(int is_me) {
        this.is_me = is_me;
    }

    public void setSuggest_price(String suggest_price) {
        this.suggest_price = suggest_price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public int getIs_me() {
        return is_me;
    }

    public String getSuggest_price() {
        return suggest_price;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLabels() {
        return labels;
    }

    public static class RouteCommunityList extends ResultList<RouteCommunity> {
    }
}

package com.ziyou.tourGuide.model;

import java.util.List;

/**
 * 已领路线列表Model
 * Created by Edward on 16/1/22.
 */
public class ReceiveRoute {

    /**
     * cover : http://selftravel-image.qiniudn.com/baf76006f0a865bd3bc73d5c7033edbb.jpg
     * id : 475
     * labels : []
     * price : 1
     * show_status : 1
     * start_date :
     * suggest_price : 1
     * title : 一天吃遍前门大街上的北京老字号
     */

    private String cover;
    private int id;
    private String price;
    private int show_status;
    private String start_date;
    private String suggest_price;
    private String title;
    private List<String> labels;

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setShow_status(int show_status) {
        this.show_status = show_status;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
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

    public String getCover() {
        return cover;
    }

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public int getShow_status() {
        return show_status;
    }

    public String getStart_date() {
        return start_date;
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

    public static class ReceiveRouteList extends ResultList<ReceiveRoute> {
    }

}

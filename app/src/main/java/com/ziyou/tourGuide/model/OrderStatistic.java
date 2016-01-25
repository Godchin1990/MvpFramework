package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/1/24.
 */
public class OrderStatistic {

    /**
     * deduct : 0.73
     * fetch_route : 2
     * fetch_sum : 0.33
     * fetcher_order : 5
     * finish_order : 14
     * finish_sum : 310.44
     * share_order : 0
     * share_route : 0
     * share_sum : 0.0
     * sum_input : 310.77
     */

    private double deduct;
    private int fetch_route;
    private double fetch_sum;
    private int fetcher_order;
    private int finish_order;
    private double finish_sum;
    private int share_order;
    private int share_route;
    private double share_sum;
    private double sum_input;

    public void setDeduct(double deduct) {
        this.deduct = deduct;
    }

    public void setFetch_route(int fetch_route) {
        this.fetch_route = fetch_route;
    }

    public void setFetch_sum(double fetch_sum) {
        this.fetch_sum = fetch_sum;
    }

    public void setFetcher_order(int fetcher_order) {
        this.fetcher_order = fetcher_order;
    }

    public void setFinish_order(int finish_order) {
        this.finish_order = finish_order;
    }

    public void setFinish_sum(double finish_sum) {
        this.finish_sum = finish_sum;
    }

    public void setShare_order(int share_order) {
        this.share_order = share_order;
    }

    public void setShare_route(int share_route) {
        this.share_route = share_route;
    }

    public void setShare_sum(double share_sum) {
        this.share_sum = share_sum;
    }

    public void setSum_input(double sum_input) {
        this.sum_input = sum_input;
    }

    public double getDeduct() {
        return deduct;
    }

    public int getFetch_route() {
        return fetch_route;
    }

    public double getFetch_sum() {
        return fetch_sum;
    }

    public int getFetcher_order() {
        return fetcher_order;
    }

    public int getFinish_order() {
        return finish_order;
    }

    public double getFinish_sum() {
        return finish_sum;
    }

    public int getShare_order() {
        return share_order;
    }

    public int getShare_route() {
        return share_route;
    }

    public double getShare_sum() {
        return share_sum;
    }

    public double getSum_input() {
        return sum_input;
    }
}

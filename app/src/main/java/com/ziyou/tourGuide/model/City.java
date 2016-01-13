package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/1/10.
 */
public class City {

    /**
     * code : beijing
     * id : 1
     * latitude : 39.927333
     * longitude : 116.390471
     * name : 北京
     */

    private String code;
    private int id;
    private double latitude;
    private double longitude;
    private String name;

    public void setCode(String code) {
        this.code = code;
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

    public String getCode() {
        return code;
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

    public static class CityLists extends ResultList<City> {
    }
}

package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/2/3.
 */
public class Voucher {

    /**
     * id : 18
     * create_time : 2015-07-03 11:24:13
     * valid_until : 2015-11-22 11:22:00
     * title : sss
     * price : 100
     * code : abc005
     * type : 3
     */

    private int id;
    private String create_time;
    private String valid_until;
    private String title;
    private String price;
    private String code;
    private int type;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setValid_until(String valid_until) {
        this.valid_until = valid_until;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getValid_until() {
        return valid_until;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    public int getType() {
        return type;
    }

    public static class VoucherList extends ResultList<Voucher> {
    }
}

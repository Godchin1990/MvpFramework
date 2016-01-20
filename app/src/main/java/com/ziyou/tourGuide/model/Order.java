package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/1/18.
 */
public class Order {

    /**
     * avatar : http://selftravel-image.qiniudn.com/Fl_lsHqht5L-ILvjmMm3xFLr6gVY?imageView2/0/w/300
     * create_time : 2015-12-24 16:06:55
     * id : 1092
     * title : 中国人民抗日战争纪念馆-抗战打响的地方
     * trade_status : 1
     * username : 大宝
     */
//    {
//        "avatar": "http://selftravel-image.qiniudn.com/Fp1NOGDigELcOYqtDTir5C_3ZTHx?imageView2/0/w/300",
//            "create_time": "2015-12-25 16:39:19",
//            "id": 1117,
//            "trade_status": 11,
//            "username": "剑心"
//    }


    private String avatar;
    private String create_time;
    private int id;
    private String title;
    private int trade_status;
    private String username;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTrade_status(int trade_status) {
        this.trade_status = trade_status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCreate_time() {
        return create_time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getTrade_status() {
        return trade_status;
    }

    public String getUsername() {
        return username;
    }

    public static class OrderList extends ResultList<Order> {
    }
}

package com.example.edward.mvpframework.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Edward on 16/1/10.
 */
public class RouteDetail implements Parcelable {

    /**
     * all_note :
     * all_time : 6
     * bring_num : 10.0
     * car_stay : 自驾
     * change_desc : 1.请最少提前1天预订；                             2.可以直接在网上下单支付全部费用；              3.如遇天气问题等不可抗力因素，本行程可能会有部分调整，街客会提前与您联系；                                   4.请准时到达见面地点，迟到10分钟不再等，已到的人先行出发；    1.请最少提前1天预订；
     * city : 成都
     * comment_num : 1.0
     * cover : http://selftravel-image.qiniudn.com/46432ca98b7f19a75ceeb9f1575d7dd1.jpg
     * desc :
     * discount : 0.2
     * discount_price : 0.02
     * expenses : 0
     * fee_desc : 无
     * guide_age : 100.0
     * guide_avatar : http://selftravel-image.qiniudn.com/Frt2NGeYzoRRd4-yr2jaJyEqDizI
     * guide_gender : 2.0
     * guide_id : 1520.0
     * guide_intro : 个人介绍为什么不少于20字，怎么凑，还不够，够了吧，这次够了吧
     * guide_level : 1.0
     * guide_name : 弟弟
     * guide_trade_num : 72.0
     * image_list : ["http://selftravel-image.qiniudn.com/42607107477fae9fa49780139ec60d74.jpg","http://selftravel-image.qiniudn.com/fdd1aa537cd1ad2cafd30441ebfcf985.jpg","http://selftravel-image.qiniudn.com/2b1c6bbbcd220a82af2fb900d957da3b.jpg","http://selftravel-image.qiniudn.com/ca259ac72a1cf3cebc40c976c4bd384a.jpg","http://selftravel-image.qiniudn.com/d7edd0456693126825b0815431b0daa5.jpg","http://selftravel-image.qiniudn.com/a7db9fd29c33bd0b968b87b7a4a61450.jpg","http://selftravel-image.qiniudn.com/69c5365cb753e72275f2581c4d057305.jpg","http://selftravel-image.qiniudn.com/8992cf69d08c4ecf6a2c81e68d1862fd.jpg","http://selftravel-image.qiniudn.com/6a5bb2daa46acbf38f9e98e5b894a829.jpg","http://selftravel-image.qiniudn.com/26c4ba16e0681ce753695864d8c998b2.jpg","http://selftravel-image.qiniudn.com/7a6860be480d6651c322a9905628733c.jpg","http://selftravel-image.qiniudn.com/20628771352c9a097da7ac9209c5e22f.jpg","http://selftravel-image.qiniudn.com/ad60987b49567827cc3267081b23eec7.jpg","http://selftravel-image.qiniudn.com/731485b84a1875ef9aeb93f93c823153.jpg","http://selftravel-image.qiniudn.com/39fef407e8871ce77c0e933bfa3b985e.jpg","http://selftravel-image.qiniudn.com/9f7eccb39002a67eae3200de8d2d820f.jpg","http://selftravel-image.qiniudn.com/e1176ac671b597c1623cad332d849fc1.jpg","http://selftravel-image.qiniudn.com/0acd82cfa3f33db46bd541166f76d629.jpg","http://selftravel-image.qiniudn.com/db8ea513a679cd15a5521b5eb444eadd.jpg","http://selftravel-image.qiniudn.com/e6edfe63fedf53dacd4629fba8d87358.jpg","http://selftravel-image.qiniudn.com/468d840ff79db45c17ac6b4b16b51f40.jpg","http://selftravel-image.qiniudn.com/361d6c3857cdab0bfa119d91d3c4bca2.jpg","http://selftravel-image.qiniudn.com/a9982835368bbb3ffceaf081aa0df1a0.jpg","http://selftravel-image.qiniudn.com/b63103a35ff5720ed9d2430129102319.jpg","http://selftravel-image.qiniudn.com/38e4778d6efc8d28999170954eb65e2c.jpg","http://selftravel-image.qiniudn.com/37e40c8289a1d5d6e29b9090ea9f4495.jpg","http://selftravel-image.qiniudn.com/01044388f0321a23d5449e8c1ca4f216.jpg","http://selftravel-image.qiniudn.com/5325f02f5a8acdeb8f805146f7f513c9.jpg","http://selftravel-image.qiniudn.com/5da4e4c691c284232afe59aafe2451a5.jpg","http://selftravel-image.qiniudn.com/5131e4470f446fa6156ff764ff5027d5.jpg","http://selftravel-image.qiniudn.com/83409ca2c51d99e781a24cc8c8afbc51.jpg","http://selftravel-image.qiniudn.com/126a8cc101e296c99294fb0dd1931e2d.jpg","http://selftravel-image.qiniudn.com/744f9e1125d35ce4532bc1bd3806ce9c.jpg","http://selftravel-image.qiniudn.com/435af6b1de737cd9801924d3bc3c9e09.jpg","http://selftravel-image.qiniudn.com/1d90d77e1c284623ca00559a39eb5818.jpg","http://selftravel-image.qiniudn.com/724955b6e6f7b8a994d814dc2c2ab77e.jpg","http://selftravel-image.qiniudn.com/86a1229a0f9ac09d73a81cbeab8c0f44.jpg","http://selftravel-image.qiniudn.com/8fafc0b544c3e20c28c75d19f914e3a8.jpg","http://selftravel-image.qiniudn.com/1324603d0d40e2f6a2613e85ed639ad7.jpg"]
     * info : 从川入藏，由藏达青，那一次次的隔世之感，那一天天的魂牵梦萦，那一路路的陵谷沧桑，让您感受这样的西藏。原始、神圣、自由、纯净、爱情……
     * is_activity : 0.0
     * label : []
     * language : 中文
     * latitude : 39.93029
     * longitude : 116.353348
     * low_bring_num : 1.0
     * meet_desc :
     * meet_image : http://selftravel-image.qiniudn.com/zg-jianmian.jpg
     * note : 1、证件：身份证、银行卡、部分现金等（最重要，没有身份证不能入藏）。
     2、户外用品：40L-60L背包、随身的小背包或腰包、雨衣、保温水壶-。
     3、衣物：抓绒冲锋衣一件、冲锋裤一条、保暖内衣一套、登山鞋或防水性比较好的旅游鞋、手套、换洗内衣裤若干、袜子若干等（行程中沿途温差比较大，建议带一些厚点的衣服）。
     4、器材：手机、相机、充电器、充电宝。
     5、防晒用品：高原紫外线极强，建议带上防晒霜（spf30以上）、润唇膏、太阳镜和帽子以免灼伤皮肤和眼睛。
     6、药品：晕车药、感冒药、消炎药、外伤药、红景天、葡萄糖…以及个人常用药品。
     7、洗漱用品：牙膏、牙刷、洗发露、沐浴露、毛巾等（根据自己的生活习惯携带）。

     * phone : 13264265406
     * preorder_desc : 1.请最少提前1天预订；                             2.可以直接在网上下单支付全部费用；              3.如遇天气问题等不可抗力因素，本行程可能会有部分调整，街客会提前与您联系；                                   4.请准时到达见面地点，迟到10分钟不再等，已到的人先行出发；
     * price : 0.1
     * route_star : 5.0
     * spots : {"1":[{"content":[{"content":"sdfdsfdsf","spot_image":"http://selftravel-image.qiniudn.com/f880aa7a7e137810e06dfd82ddb88881.jpg"}],"id":3,"intro":"","latitude":39.906313,"longitude":116.397915,"scenic_name":"天安门广场","ticket":"","time":"3","type":1}]}
     * start_time : []
     * suggest_price : 20
     * title : 川藏线自驾游
     * work_date : 8:00-18:00
     */

    private String all_note;
    private String all_time;
    private double bring_num;
    private String car_stay;
    private String change_desc;
    private String city;
    private double comment_num;
    private String cover;
    private String desc;
    private double discount;
    private String discount_price;
    private String expenses;
    private String fee_desc;
    private double guide_age;
    private String guide_avatar;
    private double guide_gender;
    private double guide_id;
    private String guide_intro;
    private double guide_level;
    private String guide_name;
    private double guide_trade_num;
//    image_list
    private List<String> image_list;
    private String info;
    private double is_activity;
    private List<String> label;
    private String language;
    private double latitude;
    private double longitude;

    public String getAll_note() {
        return all_note;
    }

    public void setAll_note(String all_note) {
        this.all_note = all_note;
    }

    public String getAll_time() {
        return all_time;
    }

    public void setAll_time(String all_time) {
        this.all_time = all_time;
    }

    public double getBring_num() {
        return bring_num;
    }

    public void setBring_num(double bring_num) {
        this.bring_num = bring_num;
    }

    public String getCar_stay() {
        return car_stay;
    }

    public void setCar_stay(String car_stay) {
        this.car_stay = car_stay;
    }

    public String getChange_desc() {
        return change_desc;
    }

    public void setChange_desc(String change_desc) {
        this.change_desc = change_desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getComment_num() {
        return comment_num;
    }

    public void setComment_num(double comment_num) {
        this.comment_num = comment_num;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getFee_desc() {
        return fee_desc;
    }

    public void setFee_desc(String fee_desc) {
        this.fee_desc = fee_desc;
    }

    public double getGuide_age() {
        return guide_age;
    }

    public void setGuide_age(double guide_age) {
        this.guide_age = guide_age;
    }

    public String getGuide_avatar() {
        return guide_avatar;
    }

    public void setGuide_avatar(String guide_avatar) {
        this.guide_avatar = guide_avatar;
    }

    public double getGuide_gender() {
        return guide_gender;
    }

    public void setGuide_gender(double guide_gender) {
        this.guide_gender = guide_gender;
    }

    public double getGuide_id() {
        return guide_id;
    }

    public void setGuide_id(double guide_id) {
        this.guide_id = guide_id;
    }

    public String getGuide_intro() {
        return guide_intro;
    }

    public void setGuide_intro(String guide_intro) {
        this.guide_intro = guide_intro;
    }

    public double getGuide_level() {
        return guide_level;
    }

    public void setGuide_level(double guide_level) {
        this.guide_level = guide_level;
    }

    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    public double getGuide_trade_num() {
        return guide_trade_num;
    }

    public void setGuide_trade_num(double guide_trade_num) {
        this.guide_trade_num = guide_trade_num;
    }

    public List<String> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<String> image_list) {
        this.image_list = image_list;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getIs_activity() {
        return is_activity;
    }

    public void setIs_activity(double is_activity) {
        this.is_activity = is_activity;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLow_bring_num() {
        return low_bring_num;
    }

    public void setLow_bring_num(double low_bring_num) {
        this.low_bring_num = low_bring_num;
    }

    public String getMeet_desc() {
        return meet_desc;
    }

    public void setMeet_desc(String meet_desc) {
        this.meet_desc = meet_desc;
    }

    public String getMeet_image() {
        return meet_image;
    }

    public void setMeet_image(String meet_image) {
        this.meet_image = meet_image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPreorder_desc() {
        return preorder_desc;
    }

    public void setPreorder_desc(String preorder_desc) {
        this.preorder_desc = preorder_desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getRoute_star() {
        return route_star;
    }

    public void setRoute_star(double route_star) {
        this.route_star = route_star;
    }

    public Map<Integer, ArrayList<RouteSpot>> getSpots() {
        return spots;
    }

    public void setSpots(Map<Integer, ArrayList<RouteSpot>> spots) {
        this.spots = spots;
    }

    public List<String> getStart_time() {
        return start_time;
    }

    public void setStart_time(List<String> start_time) {
        this.start_time = start_time;
    }

    public double getSuggest_price() {
        return suggest_price;
    }

    public void setSuggest_price(double suggest_price) {
        this.suggest_price = suggest_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    private double low_bring_num;
    private String meet_desc;
    private String meet_image;
    private String note;
    private String phone;
    private String preorder_desc;
    private String price;
    private double route_star;
    public Map<Integer ,ArrayList<RouteSpot>> spots;
    private List<String> start_time;
    private double suggest_price;
    private String title;
    private String work_date;

    /**
     * 路线点
     */
    class RouteSpot implements Parcelable {

        /**
         * content : [{"content":"sdfdsfdsf","spot_image":"http://selftravel-image.qiniudn.com/f880aa7a7e137810e06dfd82ddb88881.jpg"}]
         * id : 3
         * intro :
         * latitude : 39.906313
         * longitude : 116.397915
         * scenic_name : 天安门广场
         * ticket :
         * time : 3
         * type : 1
         */

        private int id;
        private String intro;
        private double latitude;
        private double longitude;
        private String scenic_name;
        private String ticket;
        private String time;
        private int type;
        /**
         * content : sdfdsfdsf
         * spot_image : http://selftravel-image.qiniudn.com/f880aa7a7e137810e06dfd82ddb88881.jpg
         */

        private List<ContentEntity> content;

        public void setId(int id) {
            this.id = id;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setScenic_name(String scenic_name) {
            this.scenic_name = scenic_name;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setContent(List<ContentEntity> content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public String getIntro() {
            return intro;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getScenic_name() {
            return scenic_name;
        }

        public String getTicket() {
            return ticket;
        }

        public String getTime() {
            return time;
        }

        public int getType() {
            return type;
        }

        public List<ContentEntity> getContent() {
            return content;
        }

        public class ContentEntity implements Parcelable {

            private String content;
            private String spot_image;

            public void setContent(String content) {
                this.content = content;
            }

            public void setSpot_image(String spot_image) {
                this.spot_image = spot_image;
            }

            public String getContent() {
                return content;
            }

            public String getSpot_image() {
                return spot_image;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.content);
                dest.writeString(this.spot_image);
            }

            public ContentEntity() {
            }

            protected ContentEntity(Parcel in) {
                this.content = in.readString();
                this.spot_image = in.readString();
            }

            public final Creator<ContentEntity> CREATOR = new Creator<ContentEntity>() {
                public ContentEntity createFromParcel(Parcel source) {
                    return new ContentEntity(source);
                }

                public ContentEntity[] newArray(int size) {
                    return new ContentEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.intro);
            dest.writeDouble(this.latitude);
            dest.writeDouble(this.longitude);
            dest.writeString(this.scenic_name);
            dest.writeString(this.ticket);
            dest.writeString(this.time);
            dest.writeInt(this.type);
            dest.writeList(this.content);
        }

        public RouteSpot() {
        }

        protected RouteSpot(Parcel in) {
            this.id = in.readInt();
            this.intro = in.readString();
            this.latitude = in.readDouble();
            this.longitude = in.readDouble();
            this.scenic_name = in.readString();
            this.ticket = in.readString();
            this.time = in.readString();
            this.type = in.readInt();
            this.content = new ArrayList<ContentEntity>();
            in.readList(this.content, List.class.getClassLoader());
        }

        public final Creator<RouteSpot> CREATOR = new Creator<RouteSpot>() {
            public RouteSpot createFromParcel(Parcel source) {
                return new RouteSpot(source);
            }

            public RouteSpot[] newArray(int size) {
                return new RouteSpot[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.all_note);
        dest.writeString(this.all_time);
        dest.writeDouble(this.bring_num);
        dest.writeString(this.car_stay);
        dest.writeString(this.change_desc);
        dest.writeString(this.city);
        dest.writeDouble(this.comment_num);
        dest.writeString(this.cover);
        dest.writeString(this.desc);
        dest.writeDouble(this.discount);
        dest.writeString(this.discount_price);
        dest.writeString(this.expenses);
        dest.writeString(this.fee_desc);
        dest.writeDouble(this.guide_age);
        dest.writeString(this.guide_avatar);
        dest.writeDouble(this.guide_gender);
        dest.writeDouble(this.guide_id);
        dest.writeString(this.guide_intro);
        dest.writeDouble(this.guide_level);
        dest.writeString(this.guide_name);
        dest.writeDouble(this.guide_trade_num);
        dest.writeStringList(this.image_list);
        dest.writeString(this.info);
        dest.writeDouble(this.is_activity);
        dest.writeStringList(this.label);
        dest.writeString(this.language);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.low_bring_num);
        dest.writeString(this.meet_desc);
        dest.writeString(this.meet_image);
        dest.writeString(this.note);
        dest.writeString(this.phone);
        dest.writeString(this.preorder_desc);
        dest.writeString(this.price);
        dest.writeDouble(this.route_star);
        dest.writeMap(this.spots);
        dest.writeStringList(this.start_time);
        dest.writeDouble(this.suggest_price);
        dest.writeString(this.title);
        dest.writeString(this.work_date);
    }

    public RouteDetail() {
    }

    protected RouteDetail(Parcel in) {
        this.all_note = in.readString();
        this.all_time = in.readString();
        this.bring_num = in.readDouble();
        this.car_stay = in.readString();
        this.change_desc = in.readString();
        this.city = in.readString();
        this.comment_num = in.readDouble();
        this.cover = in.readString();
        this.desc = in.readString();
        this.discount = in.readDouble();
        this.discount_price = in.readString();
        this.expenses = in.readString();
        this.fee_desc = in.readString();
        this.guide_age = in.readDouble();
        this.guide_avatar = in.readString();
        this.guide_gender = in.readDouble();
        this.guide_id = in.readDouble();
        this.guide_intro = in.readString();
        this.guide_level = in.readDouble();
        this.guide_name = in.readString();
        this.guide_trade_num = in.readDouble();
        this.image_list = in.createStringArrayList();
        this.info = in.readString();
        this.is_activity = in.readDouble();
        this.label = in.createStringArrayList();
        this.language = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.low_bring_num = in.readDouble();
        this.meet_desc = in.readString();
        this.meet_image = in.readString();
        this.note = in.readString();
        this.phone = in.readString();
        this.preorder_desc = in.readString();
        this.price = in.readString();
        this.route_star = in.readDouble();
        in.readMap(this.spots,RouteSpot.class.getClassLoader());
        this.start_time = in.createStringArrayList();
        this.suggest_price = in.readDouble();
        this.title = in.readString();
        this.work_date = in.readString();
    }

    public static final Parcelable.Creator<RouteDetail> CREATOR = new Parcelable.Creator<RouteDetail>() {
        public RouteDetail createFromParcel(Parcel source) {
            return new RouteDetail(source);
        }

        public RouteDetail[] newArray(int size) {
            return new RouteDetail[size];
        }
    };
}

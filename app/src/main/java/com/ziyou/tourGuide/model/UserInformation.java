package com.ziyou.tourGuide.model;

import java.util.List;

/**
 * Created by Edward on 16/1/15.
 */
public class UserInformation {

    /**
     * phone : 18613519122
     * is_use_route : 0
     * im_username : 13313444381
     * status : 1
     * nickname : nv
     * im_password : 4RRLW0
     * userid : 1216
     * label : []
     * type : 2
     * intro :
     * city : 北京
     * card_status : 0
     * level : 1
     * source : 街客
     * qiniu_avatar : http://selftravel-image.qiniudn.com/FpF4u80SfOJZzwG-K-wEvG-YMOOW?imageView2/0/w/300
     * age : 26
     * gender : 1
     * id_card_status : 2
     * identity_num : 16457717
     */

    private String phone;
    private int is_use_route;
    private String im_username;
    private int status;
    private String nickname;
    private String im_password;
    private int userid;
//  0: 游客 1 达人 2 导游
    private int type;
    private String intro;
    private String city;
    private int card_status;
    private int level;
    private String source;
    private String qiniu_avatar;
    private int age;
    private int gender;
    private int id_card_status;
    private String identity_num;
    private List<?> label;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIs_use_route(int is_use_route) {
        this.is_use_route = is_use_route;
    }

    public void setIm_username(String im_username) {
        this.im_username = im_username;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIm_password(String im_password) {
        this.im_password = im_password;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCard_status(int card_status) {
        this.card_status = card_status;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setQiniu_avatar(String qiniu_avatar) {
        this.qiniu_avatar = qiniu_avatar;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setId_card_status(int id_card_status) {
        this.id_card_status = id_card_status;
    }

    public void setIdentity_num(String identity_num) {
        this.identity_num = identity_num;
    }

    public void setLabel(List<?> label) {
        this.label = label;
    }

    public String getPhone() {
        return phone;
    }

    public int getIs_use_route() {
        return is_use_route;
    }

    public String getIm_username() {
        return im_username;
    }

    public int getStatus() {
        return status;
    }

    public String getNickname() {
        return nickname;
    }

    public String getIm_password() {
        return im_password;
    }

    public int getUserid() {
        return userid;
    }

    public int getType() {
        return type;
    }

    public String getIntro() {
        return intro;
    }

    public String getCity() {
        return city;
    }

    public int getCard_status() {
        return card_status;
    }

    public int getLevel() {
        return level;
    }

    public String getSource() {
        return source;
    }

    public String getQiniu_avatar() {
        return qiniu_avatar;
    }

    public int getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public int getId_card_status() {
        return id_card_status;
    }

    public String getIdentity_num() {
        return identity_num;
    }

    public List<?> getLabel() {
        return label;
    }
}

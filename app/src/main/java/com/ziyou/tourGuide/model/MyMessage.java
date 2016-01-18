package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/1/17.
 */
public class MyMessage {

    /**
     * content : nv预约了您的「中国人民抗日战争纪念馆-抗战打响的地方」，2015-12-24，数量1，请打开街客旅行app尽快确认，如有疑问可电话联系nv，13313444381【街客旅行】
     * create_time : 2015-12-24 16:07:14
     * from_user : 大宝
     * id : 1696
     * status : 0
     */

    private String content;
    private String create_time;
    private String from_user;
    private int id;
    private int status;

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getFrom_user() {
        return from_user;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public static class MyMessageList extends ResultList<MyMessage> {
    }
}

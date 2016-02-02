package com.ziyou.tourGuide.event;

/**
 * Created by Edward on 16/1/12.
 */
public class ClickEvent {
    private String tag;
    private Object param;
    private String from;
    private String to;
    public ClickEvent(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setParam(Object param){
        this.param = param;
    }

    public Object getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "tag='" + tag + '\'' +
                ", param=" + param +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

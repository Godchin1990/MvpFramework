package com.ziyou.tourGuide.event;

/**
 * Created by Edward on 16/1/12.
 */
public class ClickEvent {
    private String tag;
    private Object param;
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
}

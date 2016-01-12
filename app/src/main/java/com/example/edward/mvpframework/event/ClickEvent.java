package com.example.edward.mvpframework.event;

/**
 * Created by Edward on 16/1/12.
 */
public class ClickEvent<T> {
    private T param;
    public ClickEvent() {
    }
    public void setParam(T param){
        this.param = param;
    }

    public T getParam() {
        return param;
    }
}

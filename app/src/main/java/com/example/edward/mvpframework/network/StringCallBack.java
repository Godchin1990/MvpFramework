package com.example.edward.mvpframework.network;

/**
 * Created by Edward on 16/1/1.
 */
public interface StringCallBack<T> {
    void onSuccess(String data, T tag);
    void onFail();
}

package com.example.edward.mvpframework.network;

import android.net.Uri;

import com.example.edward.mvpframework.model.ResponseData;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Edward on 16/1/1.
 */
public class NetworkHelper {
    private static String TAG;
    {
        TAG = getClass().getSimpleName();
    }

    private static volatile NetworkHelper networkHelper;
    private Gson gson;

    private NetworkHelper(){
        gson = new Gson();
    }
    public static NetworkHelper getInstance(){
        if(networkHelper==null){
            synchronized (NetworkHelper.class){
                if(networkHelper==null){
                    networkHelper = new NetworkHelper();
                }
            }
        }
        return networkHelper;
    }

    /**
     * send get request
     * @param url
     * @param params
     * @param cb
     * @param tag
     */
    public <T> void sendGetStringRequest(String url,Map<String,String> params, final StringCallBack cb, final T tag){
        //add params for get request
        Uri.Builder builder = Uri.parse(url).buildUpon();
        if (params!=null){
            for (Map.Entry<String, String> entry :
                    params.entrySet()) {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        //call okhttp
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(builder.toString())
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e){

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                ResponseData responseData = gson.fromJson(string, ResponseData.class);
//                Log.d(TAG,string);

                cb.onSuccess(gson.toJson(responseData.getData()),tag);
            }

        });
    }

    public void sendGetImageRequest(String url) {

    }
}

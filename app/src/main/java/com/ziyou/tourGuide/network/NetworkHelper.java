package com.ziyou.tourGuide.network;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.ziyou.tourGuide.helper.TokenHelper;
import com.ziyou.tourGuide.model.TokenInfomation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
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

    private NetworkHelper() {
        gson = new Gson();
    }

    public static NetworkHelper getInstance() {
        if (networkHelper == null) {
            synchronized (NetworkHelper.class) {
                if (networkHelper == null) {
                    networkHelper = new NetworkHelper();
                }
            }
        }
        return networkHelper;
    }

    /**
     * send get request
     *
     * @param url
     * @param params
     * @param cb
     * @param tag
     */
    public <T> void sendGetStringRequest(String url, Map<String, String> params, final StringCallBack cb, final T tag) {
        //add params for get request
        Uri.Builder builder = Uri.parse(url).buildUpon();
        if (params != null) {
            for (Map.Entry<String, String> entry :
                    params.entrySet()) {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        //call okhttp
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(builder.toString())
                .headers(getHeaders())
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                parseResponse(string,cb,tag);
            }

        });
    }

    private <T> void parseResponse(String string, StringCallBack cb, T tag) {
        Log.d(TAG, string);
        //第一种解析方式
        try {
            JSONObject jsonObject = new JSONObject(string);
            int code = jsonObject.getInt("code");
            if (code == 0) {
                JSONObject data = jsonObject.getJSONObject("data");
                cb.onSuccess(data.toString(), tag);
            } else {
                String message = jsonObject.getString("message");
                cb.onFail(code, message, null);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //第二种解析方式
//      ResponseData responseData = gson.fromJson(string, ResponseData.class);
//      cb.onSuccess(gson.toJson(responseData.getData()),tag);
    }

    private void parseResponse(Response response) {




    }

    public <T> void sendPostStringRequest(String url, Map<String, String> params, final StringCallBack cb, final T tag) {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        if (params != null) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        //call okhttp
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url).post(builder.build())
                .headers(getHeaders())
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                parseResponse(string, cb, tag);
            }

        });
    }

    /**
     * 得到头信息
     *
     * @return
     */
    public Headers getHeaders() {
        Map<String, String> headerMap = new HashMap<>();
        TokenInfomation tokenInformation = TokenHelper.getInstance().getTokenInformation();
        if (tokenInformation != null) {
            headerMap.put("Authorization", "Token " + tokenInformation.getAccess_token());
        }
//        String uid = AppUtils.getUid(mContext);
//        if (!TextUtils.isEmpty(uid)) {
//            headers.put("x-uid", uid);
//        }

        return Headers.of(headerMap);
    }
}

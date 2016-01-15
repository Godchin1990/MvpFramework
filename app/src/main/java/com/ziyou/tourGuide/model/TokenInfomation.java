package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/1/15.
 */
public class TokenInfomation {

    /**
     * token_type :
     * expire_in : 1455419298
     * access_token : d0380bd34351df0d264d655cc61935ab9e173a6a
     * refresh_token : 30bd7b7a236d8d8ac599cd291d0b71a286e2ad84
     */

    private String token_type;
    private int expire_in;
    private String access_token;
    private String refresh_token;

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setExpire_in(int expire_in) {
        this.expire_in = expire_in;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpire_in() {
        return expire_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }
}

package com.app.agoralivestream;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("token")
    private String token;
    @SerializedName("uid")
    private int uid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}

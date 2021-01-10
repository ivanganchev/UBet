package com.example.ubet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("token")
    @Expose
    String token;

    public UserResponse(String username) {
        this.token = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String username) {
        this.token = username;
    }
}

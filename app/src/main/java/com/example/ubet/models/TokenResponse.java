package com.example.ubet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("message")
    @Expose
    String message;

    public TokenResponse(String username, String message) {
        this.token = username;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String username) {
        this.token = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

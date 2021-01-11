package com.example.ubet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("live")
    @Expose
    List<Game> live;
    @SerializedName("upcoming")
    @Expose
    List<Game> upcoming;

    public Response() {

    }

    public List<Game> getLive() {
        return live;
    }

    public void setLive(List<Game> live) {
        this.live = live;
    }

    public List<Game> getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(List<Game> upcoming) {
        this.upcoming = upcoming;
    }
}

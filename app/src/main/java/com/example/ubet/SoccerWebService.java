package com.example.ubet;

import com.example.ubet.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerWebService {

    @GET("/ivanganchev/FakeJsonSoccer/db")
    Call<Response> getGames();
}

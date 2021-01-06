package com.example.ubet;

import com.example.ubet.models.Response;
import com.example.ubet.models.User;
import com.example.ubet.models.UserResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SoccerWebService {

    @GET("/ivanganchev/FakeJsonSoccer/db")
    Call<Response> getGames();

    @POST("/register")
    Call<UserResponse> register(@Body RequestBody body);

    @POST("/authenticate")
    Call<String> authenticate();

    @POST("/login")
    Call<String> login();
}

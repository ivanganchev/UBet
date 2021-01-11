package com.example.ubet;

import com.example.ubet.models.Response;
import com.example.ubet.models.TokenResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SoccerWebService {

    @GET("/matches")
    Call<Response> getGames();

    @POST("/register")
    Call<TokenResponse> register(@Body RequestBody body);

    @POST("/authenticate")
    Call<String> authenticate();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/login")
    Call<TokenResponse> login(@Body RequestBody body);
}

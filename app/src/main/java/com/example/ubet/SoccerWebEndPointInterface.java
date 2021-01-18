package com.example.ubet;

import com.example.ubet.models.Bet;
import com.example.ubet.models.Response;
import com.example.ubet.models.TokenResponse;
import com.example.ubet.models.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SoccerWebEndPointInterface {

    @GET("/matches")
    Call<Response> getGames(@Header("Authorization") String auth);

    @POST("/register")
    Call<TokenResponse> register(@Body RequestBody body);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/login")
    Call<TokenResponse> login(@Body RequestBody body);

    @POST("/bet")
    Call<String> placeBet(@Body RequestBody body, @Header("Authorization") String auth);

    @GET("/bets")
    Call<ArrayList<Bet>> getBets(@Header("Authorization") String auth, @Query("finished") String isFinished);

    @POST("/deposit")
    Call<String> deposit(@Body RequestBody body, @Header("Authorization") String auth);

    @GET("/user")
    Call<User> getUserInfo(@Header("Authorization") String auth);
}

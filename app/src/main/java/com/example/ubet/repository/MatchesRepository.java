package com.example.ubet.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ubet.Constants;
import com.example.ubet.SoccerWebService;
import com.example.ubet.models.Response;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatchesRepository {
    private String token;
    public MutableLiveData<Response> getMatches(String token) {
        final MutableLiveData<Response> mutableLiveData = new MutableLiveData<>();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SoccerWebService api = retrofit.create(SoccerWebService.class);
        Call<Response> call = api.getGames();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("MainActivty", "Something went wrong");
            }
        });

        return mutableLiveData;
    }

}

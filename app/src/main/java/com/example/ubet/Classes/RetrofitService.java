package com.example.ubet.Classes;

import com.example.ubet.Constants;
import com.example.ubet.SoccerWebEndPointInterface;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {
    Retrofit retrofit;

    public SoccerWebEndPointInterface getInterface() {
        build();
        return retrofit.create(SoccerWebEndPointInterface.class);
    }

    private void build() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

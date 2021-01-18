package com.example.ubet.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ubet.Classes.RetrofitService;
import com.example.ubet.Constants;
import com.example.ubet.SoccerWebEndPointInterface;
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
    RetrofitService retrofitService = new RetrofitService();

    public MutableLiveData<Response> getMatches(String token) {

        final MutableLiveData<Response> mutableLiveData = new MutableLiveData<>();

        SoccerWebEndPointInterface api = retrofitService.getInterface();
        Call<Response> call = api.getGames("Bearer " + token);

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

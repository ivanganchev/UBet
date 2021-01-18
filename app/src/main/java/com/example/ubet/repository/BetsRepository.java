package com.example.ubet.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ubet.Classes.RetrofitService;
import com.example.ubet.Constants;
import com.example.ubet.SoccerWebEndPointInterface;
import com.example.ubet.models.Bet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BetsRepository {
    RetrofitService retrofitService = new RetrofitService();

    public MutableLiveData<String> addBet(RequestBody body, String token) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

        SoccerWebEndPointInterface api = retrofitService.getInterface();
        Call<String> call = api.placeBet(body, "Bearer " + token);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("MainActivty", "Something went wrong");
            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<ArrayList<Bet>> getBets(String token, String areFinished) {
        final MutableLiveData<ArrayList<Bet>> mutableLiveData = new MutableLiveData<>();

        SoccerWebEndPointInterface api = retrofitService.getInterface();
        Call<ArrayList<Bet>> call = api.getBets("Bearer " + token, areFinished);

        call.enqueue(new Callback<ArrayList<Bet>>() {
            @Override
            public void onResponse(Call<ArrayList<Bet>> call, retrofit2.Response<ArrayList<Bet>> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Bet>> call, Throwable t) {
                Log.e("MainActivty", "Something went wrong");
            }
        });

        return mutableLiveData;
    }

}

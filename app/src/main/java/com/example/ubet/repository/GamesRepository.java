package com.example.ubet.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ubet.Constants;
import com.example.ubet.SoccerWebService;
import com.example.ubet.models.Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GamesRepository {
    public MutableLiveData<Response> getMatches() {
        final MutableLiveData<Response> mutableLiveData = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
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

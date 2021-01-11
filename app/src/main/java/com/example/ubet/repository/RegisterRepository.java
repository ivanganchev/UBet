package com.example.ubet.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ubet.Constants;
import com.example.ubet.SoccerWebService;
import com.example.ubet.models.TokenResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterRepository {
    public MutableLiveData<TokenResponse> register(RequestBody body) {
        final MutableLiveData<TokenResponse> mutableLiveData = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SoccerWebService api = retrofit.create(SoccerWebService.class);
        Call<TokenResponse> call = api.register(body);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, retrofit2.Response<TokenResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("MainActivty", "Something went wrong");
            }
        });
        return mutableLiveData;
    }
}

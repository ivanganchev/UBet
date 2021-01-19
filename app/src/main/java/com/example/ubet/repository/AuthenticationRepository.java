package com.example.ubet.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ubet.Classes.RetrofitService;
import com.example.ubet.Constants;
import com.example.ubet.SoccerWebEndPointInterface;
import com.example.ubet.models.TokenResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthenticationRepository {
    RetrofitService retrofitService = new RetrofitService();

    public MutableLiveData<TokenResponse> register(RequestBody body) {
        final MutableLiveData<TokenResponse> mutableLiveData = new MutableLiveData<>();

        SoccerWebEndPointInterface api = retrofitService.getInterface();
        Call<TokenResponse> call = api.register(body);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, retrofit2.Response<TokenResponse> response) {
                if(response.code() != 200) {
                    Gson gson = new Gson();
                    try {
                        TokenResponse tRes = gson.fromJson(response.errorBody().string(), TokenResponse.class);
                        mutableLiveData.setValue(tRes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("LoginActivity", "Something went wrong");
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<TokenResponse> login(RequestBody body) {
        final MutableLiveData<TokenResponse> mutableLiveData = new MutableLiveData<>();

        SoccerWebEndPointInterface api = retrofitService.getInterface();
        Call<TokenResponse> call = api.login(body);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, retrofit2.Response<TokenResponse> response) {
                if(response.code() != 200) {
                    Gson gson = new Gson();
                    try {
                        TokenResponse tRes = gson.fromJson(response.errorBody().string(), TokenResponse.class);
                        mutableLiveData.setValue(tRes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("LoginActivity", "Something went wrong");
            }
        });
        return mutableLiveData;
    }
}

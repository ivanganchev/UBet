package com.example.ubet.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ubet.Classes.RetrofitService;
import com.example.ubet.SoccerWebEndPointInterface;
import com.example.ubet.models.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class UserRepository {
    RetrofitService retrofitService = new RetrofitService();

    public MutableLiveData<String> deposit(RequestBody body, String token) {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

        SoccerWebEndPointInterface api = retrofitService.getInterface();
        Call<String> call = api.deposit(body, "Bearer " + token);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                mutableLiveData.setValue(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("MainActivty", "Something went wrong");
            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<User> getUserInfo(String token) {
        final MutableLiveData<User> mutableLiveData = new MutableLiveData<>();

        SoccerWebEndPointInterface api = retrofitService.getInterface();
        Call<User> call = api.getUserInfo("Bearer " + token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("MainActivty", "Something went wrong");
            }
        });

        return mutableLiveData;
    }

}

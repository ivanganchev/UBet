package com.example.ubet.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ubet.Constants;
import com.example.ubet.SoccerWebService;
import com.example.ubet.models.Response;
import com.example.ubet.models.User;
import com.example.ubet.models.UserResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterRepository {
    public MutableLiveData<UserResponse> register(RequestBody body) {
        final MutableLiveData<UserResponse> mutableLiveData = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SoccerWebService api = retrofit.create(SoccerWebService.class);
        Call<UserResponse> call = api.register(body);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("MainActivty", "Something went wrong");
            }
        });
        return mutableLiveData;
    }
}

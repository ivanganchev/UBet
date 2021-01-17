package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.TokenResponse;
import com.example.ubet.repository.AuthenticationRepository;

import okhttp3.RequestBody;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<TokenResponse> mutableLiveData;
    private AuthenticationRepository repo;

    public RegisterViewModel() {
        repo = new AuthenticationRepository();
    }

    public LiveData<TokenResponse> register(RequestBody body) {
        mutableLiveData = repo.register(body);

        return mutableLiveData;
    }

}

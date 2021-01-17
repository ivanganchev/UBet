package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.TokenResponse;
import com.example.ubet.repository.AuthenticationRepository;

import okhttp3.RequestBody;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<TokenResponse> mutableLiveData;
    private AuthenticationRepository repo;

    public LoginViewModel() {
        repo = new AuthenticationRepository();
    }

    public LiveData<TokenResponse> login(RequestBody body) {  
        mutableLiveData = repo.login(body);

        return mutableLiveData;
    }

}

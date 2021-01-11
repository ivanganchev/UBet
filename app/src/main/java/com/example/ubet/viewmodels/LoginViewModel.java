package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.TokenResponse;
import com.example.ubet.repository.LoginRepository;
import com.example.ubet.repository.RegisterRepository;

import okhttp3.RequestBody;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<TokenResponse> mutableLiveData;
    private LoginRepository repo;

    public LoginViewModel() {
        repo = new LoginRepository();
    }

    public LiveData<TokenResponse> login(RequestBody body) {  
        mutableLiveData = new MutableLiveData<TokenResponse>();
        loginRequest(body);

        return mutableLiveData;
    }

    private void loginRequest(RequestBody body) {
        mutableLiveData = repo.login(body);
    }
}

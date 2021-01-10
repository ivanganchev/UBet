package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.TokenResponse;
import com.example.ubet.repository.RegisterRepository;

import okhttp3.RequestBody;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<TokenResponse> mutableLiveData;
    private RegisterRepository repo;

    public RegisterViewModel() {
        repo = new RegisterRepository();
    }

    public LiveData<TokenResponse> register(RequestBody body) {
        mutableLiveData = repo.register(body);

        return mutableLiveData;
    }

}

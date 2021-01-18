package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.Response;
import com.example.ubet.models.User;
import com.example.ubet.repository.MatchesRepository;
import com.example.ubet.repository.UserRepository;

import okhttp3.RequestBody;

public class UserViewModel extends ViewModel {
    private MutableLiveData<String> mutableLiveDataDeposit;
    private MutableLiveData<User> mutableLiveDataInfo;
    private UserRepository repo;

    public UserViewModel() {
        repo = new UserRepository();
    }

    public LiveData<String> deposit(RequestBody body, String token) {
        mutableLiveDataDeposit = repo.deposit(body, token);
        return mutableLiveDataDeposit;
    }

    public LiveData<User> getUserInfo(String token) {
        mutableLiveDataInfo = repo.getUserInfo(token);
        return mutableLiveDataInfo;
    }
}

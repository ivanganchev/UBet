package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.Response;
import com.example.ubet.models.UserResponse;
import com.example.ubet.repository.GamesRepository;
import com.example.ubet.repository.RegisterRepository;

import okhttp3.RequestBody;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<UserResponse> mutableLiveData;
    private RegisterRepository repo;

    public RegisterViewModel() {
        repo = new RegisterRepository();
    }

    //Type LiveData provides state of app saving because it's immutable and cannot be changed
    public LiveData<UserResponse> register(RequestBody body) {
        if(mutableLiveData == null)  {
            mutableLiveData = repo.register(body);
        }

        return mutableLiveData;
    }

}

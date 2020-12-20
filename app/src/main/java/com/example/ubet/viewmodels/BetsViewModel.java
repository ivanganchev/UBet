package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.Classes.UserBet;

public class BetsViewModel extends ViewModel {
    private final MutableLiveData<UserBet> userBetMutableLiveData = new MutableLiveData<UserBet>();

    public void setUserBet(UserBet userBet) {
        this.userBetMutableLiveData.setValue(userBet);
    }

    public LiveData<UserBet> getUserBet() {
        return userBetMutableLiveData;
    }
}

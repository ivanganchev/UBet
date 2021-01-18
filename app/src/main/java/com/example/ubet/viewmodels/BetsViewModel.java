package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.Bet;
import com.example.ubet.repository.BetsRepository;

import java.util.ArrayList;

import okhttp3.RequestBody;

public class BetsViewModel extends ViewModel {
    private MutableLiveData<String> mutableLiveDataBet;
    private MutableLiveData<ArrayList<Bet>> mutableLiveDataBets;
    private BetsRepository repo;

    public BetsViewModel() {
        repo = new BetsRepository();
    }

    public LiveData<String> addBet(RequestBody body, String token) {
        mutableLiveDataBet = repo.addBet(body, token);
        return mutableLiveDataBet;
    }

    public LiveData<ArrayList<Bet>> getBets(String token, String areFinished) {
        mutableLiveDataBets = repo.getBets(token, areFinished);
        return mutableLiveDataBets;
    }
}

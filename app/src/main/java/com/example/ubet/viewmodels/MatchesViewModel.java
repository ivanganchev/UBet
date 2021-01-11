package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.Response;
import com.example.ubet.repository.MatchesRepository;

public class MatchesViewModel extends ViewModel {

    private MutableLiveData<Response> mutableLiveData;
    private MatchesRepository repo;

    public MatchesViewModel() {
        repo = new MatchesRepository();
    }

    public LiveData<Response> getMatches(String token) {
        if(mutableLiveData == null)  {
            mutableLiveData = repo.getMatches(token);
        }

        return mutableLiveData;
    }
}

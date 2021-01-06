package com.example.ubet.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ubet.models.Response;
import com.example.ubet.repository.GamesRepository;

public class MatchesViewModel extends ViewModel {

    private MutableLiveData<Response> mutableLiveData;
    private GamesRepository repo;

    public MatchesViewModel() {
        repo = new GamesRepository();
    }

    //Type LiveData provides state of app saving because it's immutable and cannot be changed
    public LiveData<Response> getMatches() {
        if(mutableLiveData == null)  {
            mutableLiveData = repo.getMatches();
        }

        return mutableLiveData;
    }
}

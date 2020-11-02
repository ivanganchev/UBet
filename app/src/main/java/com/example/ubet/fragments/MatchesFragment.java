package com.example.ubet.fragments;

import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.MainActivity;
import com.example.ubet.R;
import com.example.ubet.adapters.MatchesAdapter;
import com.example.ubet.models.Response;
import com.example.ubet.viewmodels.MainActivityViewModel;

public class MatchesFragment extends Fragment {
    MainActivityViewModel viewModel;
    TextView textView;
    MatchesAdapter adapter;
    RecyclerView rvMatches;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);

        rvMatches = (RecyclerView) view.findViewById(R.id.matchesRecyclerView);

        viewModel = new MainActivityViewModel();

        viewModel.getMatches().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                adapter = new MatchesAdapter(response.getGames());
                rvMatches.setAdapter(adapter);
                rvMatches.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        return view;
    }

}

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

import com.example.ubet.MainActivity;
import com.example.ubet.R;
import com.example.ubet.models.Response;
import com.example.ubet.viewmodels.MainActivityViewModel;

public class MatchesFragment extends Fragment {
    MainActivityViewModel viewModel;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);

        viewModel = new MainActivityViewModel();
        textView = (TextView) view.findViewById(R.id.firstTeam);

        viewModel.getMatches().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                textView.setText(response.getGames().get(0).getFirstTeam());
            }
        });

        return view;
    }

}

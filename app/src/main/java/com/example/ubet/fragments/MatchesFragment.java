package com.example.ubet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.R;
import com.example.ubet.Section;
import com.example.ubet.adapters.MatchesMainAdapter;
import com.example.ubet.models.Response;
import com.example.ubet.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {
    MainActivityViewModel viewModel;
    TextView textView;
    MatchesMainAdapter adapter;
    RecyclerView rvMatches;
    List<Section> sections;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);
        sections = new ArrayList<Section>();

        rvMatches = (RecyclerView) view.findViewById(R.id.matchesRecyclerView);

        viewModel = new MainActivityViewModel();

        viewModel.getMatches().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
               sections.add(new Section("Live", response.getGames()));

                adapter = new MatchesMainAdapter(sections);
                rvMatches.setAdapter(adapter);
                rvMatches.setLayoutManager(new LinearLayoutManager(getContext()));
                rvMatches.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            }
        });

        return view;
    }

}

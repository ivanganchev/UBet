package com.example.ubet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.Classes.HeaderItem;
import com.example.ubet.R;
import com.example.ubet.Section;
import com.example.ubet.adapters.MatchesAdapter;
import com.example.ubet.models.Game;
import com.example.ubet.models.Response;
import com.example.ubet.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {
    MainActivityViewModel viewModel;
    TextView textView;
    RecyclerView rvMatches;
    List<Object> list;
    MatchesAdapter currentMatchesAdapter;
    List<HeaderItem> headers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);

        headers = new ArrayList<HeaderItem>();
        headers.add(new HeaderItem("Live"));
        headers.add(new HeaderItem("Upcoming"));

        list = new ArrayList<Object>();

        rvMatches = (RecyclerView) view.findViewById(R.id.matchesRecyclerView);

        viewModel = new MainActivityViewModel();

        viewModel.getMatches().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                setObjectsList(response.getGames(), response.getGames(), headers);
                currentMatchesAdapter = new MatchesAdapter(list);
                rvMatches.setAdapter(currentMatchesAdapter);
                rvMatches.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        return view;
    }


    private void setObjectsList(List<Game> liveGames, List<Game> upcomingGames,  List<HeaderItem> headers) {

        for(int count = 0; count < headers.size(); count++) {
            list.add(headers.get(count));
            for(int i = 0; i < liveGames.size(); i++) {
                list.add(liveGames.get(i));
            }
        }

    }
}

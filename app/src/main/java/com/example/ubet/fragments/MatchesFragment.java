package com.example.ubet.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.Classes.HeaderItem;
import com.example.ubet.R;
import com.example.ubet.Section;
import com.example.ubet.adapters.MatchesAdapter;
import com.example.ubet.customLayout.BottomSheetLayout;
import com.example.ubet.models.Game;
import com.example.ubet.models.Response;
import com.example.ubet.viewmodels.MainActivityViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {
    MainActivityViewModel viewModel;
    TextView textView;
    RecyclerView rvMatches;
    List<Object> list;
    List<Game> liveGames;
    List<Game> upcomingGames;
    MatchesAdapter currentMatchesAdapter;
    List<HeaderItem> headers;
    boolean layoutUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);

        headers = new ArrayList<HeaderItem>();
        headers.add(new HeaderItem("Live"));
        headers.add(new HeaderItem("Upcoming"));

        list = new ArrayList<Object>();
        layoutUp = false;
        rvMatches = (RecyclerView) view.findViewById(R.id.matchesRecyclerView);


        BottomSheetLayout bottomSheet = new BottomSheetLayout();


        viewModel = new MainActivityViewModel();

        viewModel.getMatches().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                setObjectsList(response.getGames(), response.getGames(), headers);
                liveGames = response.getGames();
                upcomingGames = response.getGames();
                currentMatchesAdapter = new MatchesAdapter(list, new MatchesAdapter.ClickListener() {
                    @Override
                    public void onCoefClick(View buttonClicked, int position, MatchesAdapter.ButtonType type) {
                        Log.e("","");
                        if(bottomSheet.isAdded()) {
                            return;
                        }

                        showBottomSheet(type, position, response.getGames(), bottomSheet);
                    }
                });

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

    private void showBottomSheet(MatchesAdapter.ButtonType type, int positionInList,  List<Game> games, BottomSheetLayout bottomSheet) {
        Bundle bundle = new Bundle();
        int exactPosition = 0;
        List<Game> clickedGamesList = null;
        if (positionInList <= liveGames.size() && positionInList > 0) {
            exactPosition = positionInList - 1;
            clickedGamesList = new ArrayList<>(liveGames);
        } else if (positionInList > liveGames.size()) {
            exactPosition = positionInList - liveGames.size() - 2;
            clickedGamesList = new ArrayList<>(upcomingGames);
        }
        switch(type) {
            case FIRSTTEAMCOEF:
                bundle.putString("team", clickedGamesList.get(exactPosition).getFirstTeam());
                bundle.putDouble("coef", clickedGamesList.get(exactPosition).getFirstTeamCoef());
                break;
            case SECONDTEAMCOEF:
                bundle.putString("team", clickedGamesList.get(exactPosition).getSecondTeam());
                bundle.putDouble("coef", clickedGamesList.get(exactPosition).getSecondTeamCoef());
                break;
            case DRAW:
                bundle.putString("team", "Draw");
                bundle.putDouble("coef", clickedGamesList.get(exactPosition).getDrawCoef());
                break;
            default:
                break;
        }

        bottomSheet.setArguments(bundle);
        bottomSheet.show(getFragmentManager(), "ModalBottomSheet");
    }

}

package com.example.ubet.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.Classes.HeaderItem;
import com.example.ubet.Classes.UserBet;
import com.example.ubet.MainActivity;
import com.example.ubet.R;
import com.example.ubet.adapters.MatchesAdapter;
import com.example.ubet.customLayout.BottomSheetLayout;
import com.example.ubet.models.Game;
import com.example.ubet.models.Response;
import com.example.ubet.viewmodels.BetsViewModel;
import com.example.ubet.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment  {
    MainActivityViewModel mainActivityViewModel;
    TextView textView;
    RecyclerView rvMatches;
    List<Object> list;
    List<Game> liveGames;
    List<Game> upcomingGames;
    MatchesAdapter currentMatchesAdapter;
    List<HeaderItem> headers;
    ImageView userBetsButton;
    boolean layoutUp;
    private BetsViewModel betsViewModel;
    List<UserBet> userBets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);

        headers = new ArrayList<HeaderItem>();
        headers.add(new HeaderItem("Live"));
        headers.add(new HeaderItem("Upcoming"));

        list = new ArrayList<Object>();
        layoutUp = false;
        rvMatches = (RecyclerView) view.findViewById(R.id.matchesRecyclerView);
        userBetsButton = (ImageView) view.findViewById(R.id.userBetsButton);
        userBets = new ArrayList<UserBet>();

        BottomSheetLayout bottomSheet = new BottomSheetLayout();
        FragmentManager fm = getChildFragmentManager();

        ArrayList<UserBet> userBets = new ArrayList<UserBet>();

        betsViewModel = new ViewModelProvider(requireActivity()).get(BetsViewModel.class);
        betsViewModel.getUserBet().observe(getViewLifecycleOwner(), new Observer<UserBet>() {
                    @Override
                    public void onChanged(UserBet userBet) {
                        userBets.add(userBet);
                    }
                });

                userBetsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rotateOpen(v);
                        UserBetsDialogFragment betsFragment = UserBetsDialogFragment.newInstance(userBets);
                        betsFragment.show(getChildFragmentManager(), "Bets Fragment");

                    }
                });

        mainActivityViewModel = new MainActivityViewModel();
        mainActivityViewModel.getMatches().observe(getViewLifecycleOwner(), new Observer<Response>() {
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

    private void showBottomSheet(MatchesAdapter.ButtonType type, int positionInList, List<Game> games, BottomSheetLayout bottomSheet) {
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

    public void rotateOpen(View view) {
        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotate);
    }

    public void rotateClose(View view) {
        RotateAnimation rotate = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotate);
    }

    public void isDialogOpen(UserBetsDialogFragment dialogFragment, View v) {

        if (dialogFragment != null
                && dialogFragment.getDialog() != null
                && dialogFragment.getDialog().isShowing()
                && !dialogFragment.isRemoving()) {
        } else {
            rotateClose(v);
        }
    }

}

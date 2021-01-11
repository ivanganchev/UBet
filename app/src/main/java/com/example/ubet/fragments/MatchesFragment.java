package com.example.ubet.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ubet.Classes.HeaderItem;
import com.example.ubet.Classes.UserBet;
import com.example.ubet.R;
import com.example.ubet.adapters.MatchesAdapter;
import com.example.ubet.customLayout.BottomSheetLayout;
import com.example.ubet.models.Game;
import com.example.ubet.models.Response;
import com.example.ubet.viewmodels.BetsViewModel;
import com.example.ubet.viewmodels.MatchesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment  {
    MatchesViewModel matchesViewModel;
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
    ImageView soccerBall;
    RelativeLayout rvRelativeLayout;
    SwipeRefreshLayout matchesSwipeRefreshLayout;
    BottomSheetLayout bottomSheet;

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
        soccerBall = (ImageView) view.findViewById(R.id.soccerBall);
        rvRelativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayoutRecyclerView);
        matchesSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.matchesSwipeRefreshLayout);


        FragmentManager fm = getChildFragmentManager();

        ArrayList<UserBet> userBets = new ArrayList<UserBet>();

        soccerBall.setVisibility(View.VISIBLE);
        loadingAnimation(soccerBall);

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

        matchesViewModel = new MatchesViewModel();
        String token = getToken();
        matchesViewModel.getMatches(token).observe(getViewLifecycleOwner(), new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                soccerBall.clearAnimation();
                soccerBall.setVisibility(View.GONE);
                rvRelativeLayout.setVisibility(View.VISIBLE);

                setGames(response);
                setMatchesAdapter();

                matchesSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        matchesViewModel.getMatches(token).observe(getViewLifecycleOwner(), new Observer<Response>() {
                            @Override
                            public void onChanged(Response response) {
                                matchesSwipeRefreshLayout.setRefreshing(false);
                                currentMatchesAdapter.clearItems();

                                setGames(response);
                                setMatchesAdapter();
                            }
                        });
                    }
                });
            }
        });

        return view;
    }

    private void setGames(Response response) {
        setObjectsList(response.getLive(), response.getUpcoming(), headers);
        liveGames = response.getLive();
        upcomingGames = response.getUpcoming();
    }

    private void setMatchesAdapter() {
        currentMatchesAdapter = new MatchesAdapter(list, new MatchesAdapter.ClickListener() {
            @Override
            public void onCoefClick(View buttonClicked, int position, MatchesAdapter.ButtonType type) {
                bottomSheet = new BottomSheetLayout();
                showBottomSheet(type, position, bottomSheet);
            }
        });
        rvMatches.setAdapter(currentMatchesAdapter);
        rvMatches.scheduleLayoutAnimation();
        rvMatches.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setObjectsList(List<Game> liveGames, List<Game> upcomingGames,  List<HeaderItem> headers) {

        List<List<Game>> games = new ArrayList<List<Game>>();
        games.add(liveGames);
        games.add(upcomingGames);

        for(int count = 0; count < headers.size(); count++) {
            list.add(headers.get(count));
            for(int i = 0; i < liveGames.size(); i++) {
                list.add(games.get(count).get(i));
            }
        }
    }

    private void showBottomSheet(MatchesAdapter.ButtonType type, int positionInList, BottomSheetLayout bottomSheet) {
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
        bottomSheet.show(getChildFragmentManager(), "ModalBottomSheet");
    }

    private void rotateOpen(View view) {
        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotate);
    }

    private void rotateClose(View view) {
        RotateAnimation rotate = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotate);
    }

    private void isDialogOpen(UserBetsDialogFragment dialogFragment, View v) {

        if (dialogFragment != null
                && dialogFragment.getDialog() != null
                && dialogFragment.getDialog().isShowing()
                && !dialogFragment.isRemoving()) {
        } else {
            rotateClose(v);
        }
    }

    private void loadingAnimation(View view) {
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1500);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setFillAfter(true);

        view.startAnimation(rotate);
    }

    private String getToken() {
        SharedPreferences prefs = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String token = prefs.getString("token", null);
        return token;
    }

}

package com.example.ubet.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.Classes.UserBet;
import com.example.ubet.R;
import com.example.ubet.adapters.UserBetsAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UserBetsDialogFragment extends DialogFragment {

    TextView userBetTeam;
    RecyclerView userBetsRecyclerView;
    List<UserBet> userBets;

    public UserBetsDialogFragment() {

    }

    public static UserBetsDialogFragment newInstance(ArrayList<UserBet> userBets) {
        UserBetsDialogFragment frag = new UserBetsDialogFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("userBets", userBets);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.user_bets_dialog_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userBets = getArguments().getParcelableArrayList("userBets");
        UserBetsAdapter adapter = new UserBetsAdapter(userBets);
        userBetsRecyclerView = (RecyclerView) view.findViewById(R.id.activeBetsRecyclerView);
        userBetsRecyclerView.setAdapter(adapter);
        userBetsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();

        setFragmenSize();
    }

    private void setFragmenSize() {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixelsWidth = (int) (300 * scale + 0.5f);
        int pixelsHeight = (int) (400 * scale + 0.5f);
        Window window = getDialog().getWindow();
        window.setLayout(pixelsWidth, pixelsHeight);
        window.setGravity(Gravity.TOP|Gravity.RIGHT);
    }

}

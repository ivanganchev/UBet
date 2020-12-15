package com.example.ubet.fragments;

import android.app.Dialog;
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

import com.example.ubet.R;

import org.w3c.dom.Text;

public class UserBetsDialogFragment extends DialogFragment {

    TextView userBetTeam;

    public UserBetsDialogFragment() {

    }

    public static UserBetsDialogFragment newInstance() {
        UserBetsDialogFragment frag = new UserBetsDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", "Manchester");
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.TOP|Gravity.RIGHT);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        double height = getContext().getResources().getDisplayMetrics().heightPixels * 0.5;
        double width = getContext().getResources().getDisplayMetrics().widthPixels * 0.8;

        getDialog().getWindow().setLayout((int) width, (int) height);
        return inflater.inflate(R.layout.user_bets_dialog_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}

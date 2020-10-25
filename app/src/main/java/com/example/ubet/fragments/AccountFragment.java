package com.example.ubet.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ubet.MainActivity;
import com.example.ubet.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    Button showAccountInfoButton;
    Button showHistoryButton;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        showAccountInfoButton = (Button) view.findViewById(R.id.accountInfoButton);
        showHistoryButton = (Button) view.findViewById(R.id.historyButton);

        showAccountInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistoryButton.setBackgroundResource(R.drawable.account_unactive_button);
                showAccountInfoButton.setBackgroundResource(R.drawable.account_menu_button);

                // tok trqbva da se opravq fragmenta
                Toast.makeText(getActivity(), "account info", Toast.LENGTH_LONG).show();
            }
        });

        showHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistoryButton.setBackgroundResource(R.drawable.account_menu_button);
                showAccountInfoButton.setBackgroundResource(R.drawable.account_unactive_button);

                // tok trqbva da se opravq fragmenta
                Toast.makeText(getActivity(), "history button", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

//    public void showAccountInfoFragment(View view) {
//        Toast.makeText(getActivity(), "accountInfo", Toast.LENGTH_LONG).show();
//    }
//
//    public void showHistoryFragment(View view) {
//        Toast.makeText(getActivity(), "historyInfo", Toast.LENGTH_LONG).show();
//    }
}
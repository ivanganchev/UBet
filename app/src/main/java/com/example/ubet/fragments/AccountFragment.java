package com.example.ubet.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ubet.MainActivity;
import com.example.ubet.R;
import com.example.ubet.models.User;
import com.example.ubet.viewmodels.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    Button showAccountInfoButton;
    Button showHistoryButton;
    TextView usernameTextView;
    TextView emailTextView;
    TextView balanceTextView;

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

        LinearLayout canvas = (LinearLayout) view.findViewById(R.id.canvas);
        LinearLayout matchHistoryLayout = (LinearLayout) inflater.inflate(R.layout.account_match_history_layout, container, false);
        LinearLayout infoLayout = (LinearLayout) inflater.inflate(R.layout.account_info_layout, container, false);

        showAccountInfoButton = (Button) view.findViewById(R.id.accountInfoButton);
        showHistoryButton = (Button) view.findViewById(R.id.historyButton);

        showHistoryButton.setBackgroundResource(R.drawable.account_unactive_button);
        showAccountInfoButton.setBackgroundResource(R.drawable.account_menu_button);
        canvas.addView(infoLayout);

        usernameTextView = (TextView) view.findViewById(R.id.usernameTextView);
        emailTextView = (TextView) view.findViewById(R.id.emailTextView);
        balanceTextView = (TextView) view.findViewById(R.id.balanceTextView);



        UserViewModel userViewModel = new UserViewModel();
        userViewModel.getUserInfo(getToken()).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                usernameTextView.setText(user.getUsername().toString());
                emailTextView.setText(user.getEmail().toString());
                balanceTextView.setText(Double.toString(user.getBalance()));
                setCurrentMoney(user.getBalance());
            }
        });

        Button addMoneyButton = (Button) view.findViewById(R.id.addMoneyButton);
        addMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getChildFragmentManager();
                MoneyAddingDialogFragment moneyAddingDialogFragment = MoneyAddingDialogFragment.newInstance();
                moneyAddingDialogFragment.show(fm, "");
            }
        });


        showAccountInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistoryButton.setBackgroundResource(R.drawable.account_unactive_button);
                showAccountInfoButton.setBackgroundResource(R.drawable.account_menu_button);
                canvas.removeAllViews();
                canvas.addView(infoLayout);
            }
        });

        showHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistoryButton.setBackgroundResource(R.drawable.account_menu_button);
                showAccountInfoButton.setBackgroundResource(R.drawable.account_unactive_button);
                canvas.removeAllViews();
                canvas.addView(matchHistoryLayout);
            }
        });

        return view;
    }

    private String getToken() {
        SharedPreferences prefs = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String token = prefs.getString("token", null);
        return token;
    }

    private void setCurrentMoney(double amount) {
        SharedPreferences sharedPrefs = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("balance", Double.toString(amount));
        editor.commit();
    }
}
package com.example.ubet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ubet.LoginActivity;
import com.example.ubet.MainActivity;
import com.example.ubet.R;

public class LoginFragment extends Fragment {

    Button registerButton;
    Button loginSubmit;

    EditText usernameInput;
    EditText passwordInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        registerButton = (Button) view.findViewById(R.id.registerButton);
        loginSubmit = (Button) view.findViewById(R.id.loginSubmit);

        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameInput = view.findViewById(R.id.loginUsernameInput);
                passwordInput = view.findViewById(R.id.loginPasswordInput);

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                Toast.makeText(getActivity(), username +"==="+ password, Toast.LENGTH_LONG).show();

                // tuk shte trqbva da si slojim logikata za logina.
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.id.loginLayout, new RegisterFragment());
            }
        });

        return view;
    }

    private void replaceFragment(int layout, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }
}

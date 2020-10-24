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

import com.example.ubet.MainActivity;
import com.example.ubet.R;

public class RegisterFragment extends Fragment {
    Button registerSubmit;
    Button loginButton;

    EditText usernameInput;
    EditText emailInput;
    EditText passwordInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        registerSubmit = view.findViewById(R.id.registerSubmit);
        loginButton = view.findViewById(R.id.loginButton);

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameInput = view.findViewById(R.id.registerUsernameInput);
                emailInput = view.findViewById(R.id.registerEmailInput);
                passwordInput = view.findViewById(R.id.registerPasswordInput);

                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                Toast.makeText(getActivity(), username, Toast.LENGTH_LONG).show();

                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(R.id.loginLayout, new LoginFragment());
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

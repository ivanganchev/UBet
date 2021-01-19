  package com.example.ubet.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ubet.LoginActivity;
import com.example.ubet.MainActivity;
import com.example.ubet.R;
import com.example.ubet.models.TokenResponse;
import com.example.ubet.viewmodels.LoginViewModel;
import com.example.ubet.viewmodels.RegisterViewModel;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginFragment extends Fragment {

    Button registerButton;
    Button loginSubmit;

    EditText usernameInput;
    EditText passwordInput;
    LoginViewModel loginViewModel;

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

                String requestBody = getRequestBody(username, password);

                final RequestBody finalizedBody = RequestBody.create(MediaType.parse("application/json"), requestBody);

                loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
                loginViewModel.login(finalizedBody).observe(getViewLifecycleOwner(), new Observer<TokenResponse>() {
                    @Override
                    public void onChanged(TokenResponse tokenResponse) {
                        if(tokenResponse.getToken() == null) {
                            Toast.makeText(getContext(), tokenResponse.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            saveToken(tokenResponse.getToken());
                            Toast.makeText(getContext(), tokenResponse.getMessage(), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                        }

                    }
                });
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

    private String getRequestBody(String username, String password) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        String strRequestBody = new Gson().toJson(params);
        return strRequestBody;
    }

    private void saveToken(String token) {
        SharedPreferences sharedPrefs = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("token", token);
        editor.commit();
    }
}

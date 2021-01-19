package com.example.ubet.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ubet.MainActivity;
import com.example.ubet.R;
import com.example.ubet.models.TokenResponse;
import com.example.ubet.viewmodels.RegisterViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegisterFragment extends Fragment {
    Button registerSubmit;
    Button loginButton;

    EditText usernameInput;
    EditText emailInput;
    EditText passwordInput;
    RegisterViewModel registerViewModel;

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
                List<EditText> registerInputs = new ArrayList<EditText>();
                registerInputs.add(usernameInput);
                registerInputs.add(emailInput);
                registerInputs.add(passwordInput);

                String requestBody = getRequestBody(username, password, email);

                final RequestBody finalizedBody = RequestBody.create(MediaType.parse("application/json"), requestBody);

                registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
                registerViewModel.register(finalizedBody).observe(getViewLifecycleOwner(), new Observer<TokenResponse>() {
                    @Override
                    public void onChanged(TokenResponse tokenResponse) {
                        Toast.makeText(getContext(), tokenResponse.getMessage(), Toast.LENGTH_LONG).show();
                        for(int i = 0; i < registerInputs.size(); i++) {
                            if(registerInputs.get(i).getText().toString().equals("")) {
                                shakeAnimation(registerInputs.get(i));
                            }
                        }
                        if(!username.equals("") && !email.equals("") && !password.equals("") && tokenResponse.getToken() != null) {
                            saveToken(tokenResponse.getToken());
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                        }
                    }
                });

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

    private String getRequestBody(String username, String password, String email) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        String strRequestBody = new Gson().toJson(params);
        return strRequestBody;
    }

    private void shakeAnimation(View view) {
        Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake_animation);
        view.startAnimation(shake);
    }

    private void saveToken(String token) {
        SharedPreferences sharedPrefs = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString("token", token);
        editor.commit();
    }
}

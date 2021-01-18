package com.example.ubet.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;

import com.example.ubet.R;
import com.example.ubet.models.Bet;
import com.example.ubet.viewmodels.UserViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MoneyAddingDialogFragment extends DialogFragment {
    private EditText moneyEditText;
    private Button addMoneyButton;
    private Button successButton;
    LinearLayout addMoneyLayout;
    LinearLayout successLayout;
    UserViewModel userViewModel;

    public static MoneyAddingDialogFragment newInstance() {
        MoneyAddingDialogFragment fr = new MoneyAddingDialogFragment();
        return fr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.rounded_add_money_dialog_background);
        return inflater.inflate(R.layout.add_money_dialog_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new UserViewModel();
        addMoneyLayout = (LinearLayout) view.findViewById(R.id.addMoneyLayout);
        successLayout = (LinearLayout) view.findViewById(R.id.successLayout);
        moneyEditText = (EditText) view.findViewById(R.id.addMoneyEditText);
        addMoneyButton = (Button) view.findViewById(R.id.addMoneyButtonDialog);
        successButton = (Button) view.findViewById(R.id.successButton);

        addMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moneyEditText.getText().toString().equals("")) {
                    shakeAnimation(moneyEditText);
                } else {
                    double amount = Double.parseDouble(moneyEditText.getText().toString());
                    try {
                        final RequestBody finalizedBody = RequestBody.create(MediaType.parse("application/json"), getRequestBody(amount));
                        userViewModel.deposit(finalizedBody, getToken()).observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                addMoneyLayout.setVisibility(View.GONE);
                                successLayout.setVisibility(View.VISIBLE);
                                fadeInAnimation(successLayout);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
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
        window.setGravity(Gravity.CENTER);
    }

    private void fadeInAnimation(View view) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);
        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        view.setAnimation(animation);
    }

    private void shakeAnimation(View view) {
        Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake_animation);
        view.startAnimation(shake);
    }

    private String getToken() {
        SharedPreferences prefs = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String token = prefs.getString("token", null);
        return token;
    }

    private String getRequestBody(double amount) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", amount);

        String strRequestBody = jsonObject.toString();
        return strRequestBody;
    }

}

package com.example.ubet.customLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ubet.Classes.UserBet;
import com.example.ubet.R;
import com.example.ubet.fragments.MatchesFragment;
import com.example.ubet.fragments.UserBetsDialogFragment;
import com.example.ubet.models.Bet;
import com.example.ubet.viewmodels.BetsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BottomSheetLayout extends BottomSheetDialogFragment {
    SeekBar betBar;
    TickerView tickerViewBet;
    TickerView tickerViewWin;
    TextView teamBetCoef;
    Button betButton;
    double currentBet;
    double money;
    double moneyWin;
    BetsViewModel betsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bet_layout, container, false);
        betBar = (SeekBar) v.findViewById(R.id.betBar);
        tickerViewBet = v.findViewById(R.id.moneyBet);
        tickerViewWin = v.findViewById(R.id.moneyWin);
        teamBetCoef = v.findViewById(R.id.coefMultiplier);
        betButton = v.findViewById(R.id.betButton);
        betsViewModel = new BetsViewModel();

        currentBet = 0;
        money = 0;
        String teamBet = getArguments().getString("teamName");
        double teamCoef = getArguments().getDouble("coef");
        int teamInGame = getArguments().getInt("teamInGame");
        int teamId = getArguments().getInt("teamId");

        teamBetCoef.setText(Double.toString(teamCoef));

        betBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                money = (double) Math.round(progress * 0.01 * Double.parseDouble(getMoney()));

                DecimalFormat df = new DecimalFormat("###.#");

                moneyWin = money * teamCoef;
                if(money > 0) {
                    betButton.setEnabled(true);
                    betButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.main_gradient_color_cornered));
                } else {
                    betButton.setEnabled(false);
                    betButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disabled_color));
                }

                tickerViewBet.setCharacterLists(TickerUtils.provideNumberList());
                tickerViewBet.setText("" + df.format(money) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                DecimalFormat df = new DecimalFormat("###.##");

                tickerViewWin.setCharacterLists(TickerUtils.provideNumberList());
                tickerViewWin.setText("" + df.format(moneyWin) + "");
            }
        });

        betButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestBody = null;
                try {
                    requestBody = getRequestBody(teamId, teamInGame, money, teamCoef);
                    final RequestBody finalizedBody = RequestBody.create(MediaType.parse("application/json"), requestBody);
                    String token = getToken();

                    betsViewModel.addBet(finalizedBody, token).observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String message = jsonObject.getString("message");
                                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    private String getRequestBody(int matchId, int teamInGame, double money, double teamCoef) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("matchId", matchId);
        jsonObject.put("team", teamInGame);
        jsonObject.put("odd", teamCoef);
        jsonObject.put("amount", money);

        String strRequestBody = jsonObject.toString();
        return strRequestBody;
    }

    private String getToken() {

        SharedPreferences prefs = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String token = prefs.getString("token", null);
        return token;
    }

    private String getMoney(){
        SharedPreferences prefs = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String money = prefs.getString("balance", null);
        return money;
    }
}

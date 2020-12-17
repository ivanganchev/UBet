package com.example.ubet.customLayout;

import android.content.DialogInterface;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.ubet.Classes.UserBet;
import com.example.ubet.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BottomSheetLayout extends BottomSheetDialogFragment {
    SeekBar betBar;
    TickerView tickerViewBet;
    TickerView tickerViewWin;
    TextView teamBetCoef;
    Button betButton;
    double currentBet;
    double money;
    double moneyWin;
    List<UserBet> userBets;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bet_layout, container, false);
        betBar = (SeekBar) v.findViewById(R.id.betBar);
        tickerViewBet = v.findViewById(R.id.moneyBet);
        tickerViewWin = v.findViewById(R.id.moneyWin);
        teamBetCoef = v.findViewById(R.id.coefMultiplier);
        betButton = v.findViewById(R.id.betButton);
        userBets = new ArrayList<UserBet>();

        currentBet = 0;
        money = 0;
        String teamBet = getArguments().getString("team");
        double teamCoef = getArguments().getDouble("coef");

        teamBetCoef.setText(Double.toString(teamCoef));

        betBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                money = (double) Math.round(progress * 0.01 * 888);

                DecimalFormat df = new DecimalFormat("###.#");

                tickerViewBet.setCharacterLists(TickerUtils.provideNumberList());
                tickerViewBet.setText("" + df.format(money) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                moneyWin = money * teamCoef;
                if(money > 0) {
                    betButton.setEnabled(true);
                    betButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.main_gradient_color_cornered));
                } else {
                    betButton.setEnabled(false);
                    betButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disabled_color));
                }
                DecimalFormat df = new DecimalFormat("###.##");

                tickerViewWin.setCharacterLists(TickerUtils.provideNumberList());
                tickerViewWin.setText("" + df.format(moneyWin) + "");
            }
        });

        betButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        View v = getLayoutInflater().inflate(R.layout.bet_layout, null);
        TickerView tickerView = v.findViewById(R.id.moneyBet);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        tickerView.setText("");
        Log.e("","");
    }
}

package com.example.ubet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.Classes.UserBet;
import com.example.ubet.R;

import java.util.List;

public class UserBetsAdapter extends RecyclerView.Adapter<UserBetsAdapter.ViewHolder> {

    List<UserBet> userBets;

    public UserBetsAdapter(List<UserBet> userBets) {
      this.userBets = userBets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.user_bets_adapter_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserBet userBet = userBets.get(position);
        holder.teamCoef.setText(String.format("%f" ,userBet.getBetCoef()));
        holder.teamName.setText(userBet.getTeamName());

    }

    @Override
    public int getItemCount() {
        return userBets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView teamName;
        public TextView teamCoef;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            teamName = (TextView) itemView.findViewById(R.id.userBetTeam);
            teamCoef = (TextView) itemView.findViewById(R.id.userMoneyBet);
        }
    }


}

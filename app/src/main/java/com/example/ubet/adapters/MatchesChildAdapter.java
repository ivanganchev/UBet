package com.example.ubet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.R;
import com.example.ubet.models.Game;

import org.w3c.dom.Text;

import java.util.List;

public class MatchesChildAdapter extends RecyclerView.Adapter<MatchesChildAdapter.ViewHolder> {
    List<Game> games;

    public MatchesChildAdapter(List<Game> games) {
        this.games = games;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.matches_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = games.get(position);

        TextView firstTeam = holder.firstTeam;
        firstTeam.setText(game.getFirstTeam());
        TextView secondTeam = holder.secondTeam;
        secondTeam.setText(game.getSecondTeam());
        TextView firstTeamCoef = holder.firstTeamCoef;
        firstTeamCoef.setText(Double.toString(game.getFirstTeamCoef()));
        TextView secondTeamCoef = holder.secondTeamCoef;
        secondTeamCoef.setText(Double.toString(game.getSecondTeamCoef()));

    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstTeam;
        TextView secondTeam;
        TextView firstTeamCoef;
        TextView secondTeamCoef;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstTeam = itemView.findViewById(R.id.firstTeam);
            secondTeam = itemView.findViewById(R.id.secondTeam);
            firstTeamCoef = itemView.findViewById(R.id.firstTeamCoef);
            secondTeamCoef = itemView.findViewById(R.id.secondTeamCoef);
        }
    }
}

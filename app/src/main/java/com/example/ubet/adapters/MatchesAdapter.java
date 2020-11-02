package com.example.ubet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.R;
import com.example.ubet.models.Game;
import com.example.ubet.models.Response;

import java.util.List;

public class MatchesAdapter  extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private List<Game> response;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstTeam;
        public TextView secondTeam;

        public ViewHolder(View itemView) {
            super(itemView);

            firstTeam = (TextView) itemView.findViewById(R.id.firstTeam);
            secondTeam = (TextView) itemView.findViewById(R.id.secondTeam);
        }
    }

    public MatchesAdapter(List<Game> response) {
        this.response = response;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_adapter_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = response.get(position);

        TextView firstTeam = holder.firstTeam;
        firstTeam.setText(game.getFirstTeam());
        TextView secondTeam = holder.secondTeam;
        secondTeam.setText(game.getSecondTeam());

    }

    @Override
    public int getItemCount() {
        return response.size();
    }
}

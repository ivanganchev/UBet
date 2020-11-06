package com.example.ubet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.R;
import com.example.ubet.Section;
import com.example.ubet.fragments.MatchesFragment;
import com.example.ubet.models.Game;

import org.w3c.dom.Text;

import java.util.List;

public class MatchesMainAdapter  extends RecyclerView.Adapter<MatchesMainAdapter.ViewHolder> {

    List<Section> sections;

    public MatchesMainAdapter(List<Section> sections) {
        this.sections = sections;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.matches_adapter_section_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Section section = sections.get(position);
        String sectionHeader = section.getHeader();
        List<Game> games = section.getGames();

        holder.header.setText(sectionHeader);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.childRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        holder.childRecyclerView.setLayoutManager(linearLayoutManager);
        MatchesChildAdapter childAdapter = new MatchesChildAdapter(games);
        holder.childRecyclerView.setAdapter(childAdapter);

    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView header;
        RecyclerView childRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            header = itemView.findViewById(R.id.rvHeader);
            childRecyclerView = itemView.findViewById(R.id.rvChildren);
        }
    }
}

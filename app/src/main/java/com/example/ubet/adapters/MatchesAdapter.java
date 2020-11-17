package com.example.ubet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.Classes.HeaderItem;
import com.example.ubet.R;
import com.example.ubet.models.Game;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    List<Object> listItems;

    public MatchesAdapter(List<Object> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_HEADER) {
            View v = inflater.inflate(R.layout.header, parent, false);
            return new VHHeader(v);
        } else {
            View v = inflater.inflate(R.layout.matches_adapter_layout, parent, false);
            return new VHItem(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHHeader) {
            HeaderItem currentItem = (HeaderItem) listItems.get(position);
            VHHeader vhHeader = (VHHeader) holder;
            vhHeader.header.setText(currentItem.getHeader());
            if(currentItem.getHeader().equals("Live")) {
                vhHeader.headerImage.setImageResource(R.drawable.live_dot);
            } else {
                vhHeader.headerImage.setImageResource(R.drawable.soccer_ball);
            }

        } else if (holder instanceof VHItem) {
            Game game =(Game) listItems.get(position);

            VHItem vhItem = (VHItem) holder;

            TextView firstTeam = vhItem.firstTeam;
            firstTeam.setText(game.getFirstTeam());
            TextView secondTeam = vhItem.secondTeam;
            secondTeam.setText(game.getSecondTeam());
            TextView firstTeamCoef = vhItem.firstTeamCoef;
            firstTeamCoef.setText(Double.toString(game.getFirstTeamCoef()));
            TextView secondTeamCoef = vhItem.secondTeamCoef;
            secondTeamCoef.setText(Double.toString(game.getSecondTeamCoef()));
            TextView drawCoef = vhItem.drawCoef;
            drawCoef.setText(Double.toString(game.getDrawCoef()));
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (listItems.get(position) instanceof HeaderItem)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    class VHHeader extends RecyclerView.ViewHolder{
        TextView header;
        ImageView headerImage;

        public VHHeader(@NonNull View itemView) {
            super(itemView);

            header = itemView.findViewById(R.id.rvHeader);
            headerImage = itemView.findViewById(R.id.headerImage);
        }
    }

    class VHItem extends RecyclerView.ViewHolder{

        TextView firstTeam;
        TextView secondTeam;
        TextView firstTeamCoef;
        TextView secondTeamCoef;
        TextView drawCoef;

        public VHItem(@NonNull View itemView) {
            super(itemView);

            firstTeam = itemView.findViewById(R.id.firstTeam);
            secondTeam = itemView.findViewById(R.id.secondTeam);
            firstTeamCoef = itemView.findViewById(R.id.firstTeamCoef);
            secondTeamCoef = itemView.findViewById(R.id.secondTeamCoef);
            drawCoef = itemView.findViewById(R.id.drawTeamCoef);
        }
    }

}

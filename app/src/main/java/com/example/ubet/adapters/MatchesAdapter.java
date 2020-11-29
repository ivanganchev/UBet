package com.example.ubet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubet.Classes.HeaderItem;
import com.example.ubet.R;
import com.example.ubet.fragments.MatchesFragment;
import com.example.ubet.models.Game;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    List<Object> listItems;
    ClickListener clickListener;

    public MatchesAdapter(List<Object> listItems, ClickListener clickListener) {
        this.listItems = listItems;
        this.clickListener = clickListener;
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

    class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView firstTeam;
        TextView secondTeam;
        TextView firstTeamCoef;
        TextView secondTeamCoef;
        TextView drawCoef;
        LinearLayout firstTeamLayout;
        LinearLayout secondTeamLayout;
        LinearLayout drawLayout;

        public VHItem(@NonNull View itemView) {
            super(itemView);

            //itemView.setOnClickListener(this);
            firstTeam = itemView.findViewById(R.id.firstTeam);
            secondTeam = itemView.findViewById(R.id.secondTeam);
            firstTeamCoef = itemView.findViewById(R.id.firstTeamCoef);
            secondTeamCoef = itemView.findViewById(R.id.secondTeamCoef);
            drawCoef = itemView.findViewById(R.id.drawTeamCoef);
            firstTeamLayout = itemView.findViewById(R.id.firstTeamLayout);
            firstTeamLayout.setOnClickListener(this);
            secondTeamLayout = itemView.findViewById(R.id.secondTeamLayout);
            secondTeamLayout.setOnClickListener(this);
            drawLayout = itemView.findViewById(R.id.drawLayout);
            drawLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            String viewIdName = v.getResources().getResourceEntryName(v.getId());
            ButtonType type;

            if(viewIdName.equals("firstTeamLayout")) {
                type = ButtonType.FIRSTTEAMCOEF;
            } else if (viewIdName.equals("secondTeamLayout")) {
                type = ButtonType.SECONDTEAMCOEF;
            } else {
                type = ButtonType.DRAW;
            }

            clickListener.onCoefClick(v, this.getLayoutPosition(), type);
        }
    }

    public interface ClickListener {
        public void onCoefClick(View buttonClicked, int positon, ButtonType type);
    }

    public enum ButtonType {
        FIRSTTEAMCOEF,
        SECONDTEAMCOEF,
        DRAW
    }
}

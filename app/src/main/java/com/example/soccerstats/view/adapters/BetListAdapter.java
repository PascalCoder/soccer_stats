package com.example.soccerstats.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Bet;

import java.util.List;

public class BetListAdapter extends RecyclerView.Adapter<BetListAdapter.CustomViewHolder> {

    private List<Bet> dataSet;
    private OnItemClickListener listener;

    public BetListAdapter() {
    }

    public BetListAdapter(List<Bet> myBets) {
        this.dataSet = myBets;
    }

    @NonNull
    @Override
    public BetListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bets_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BetListAdapter.CustomViewHolder customViewHolder, int i) {
        customViewHolder.tvHomeTeam.setText(dataSet.get(i).getHomeTeam());
        customViewHolder.tvHomeTeamScore.setText(dataSet.get(i).getHomeTeamScore());
        customViewHolder.tvAwayTeam.setText(dataSet.get(i).getAwayTeam());
        customViewHolder.tvAwayTeamScore.setText(dataSet.get(i).getAwayTeamScore());
    }

    @Override
    public int getItemCount() {
        return (dataSet != null ? dataSet.size() : 0);
    }

    public void setBets(List<Bet> bets) {
        this.dataSet = bets;
        notifyDataSetChanged();
    }

    public Bet getBetAt(int position) {
        return dataSet.get(position);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvHomeTeam, tvAwayTeam, tvHomeTeamScore, tvAwayTeamScore;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHomeTeam = itemView.findViewById(R.id.tv_team_1);
            tvHomeTeamScore = itemView.findViewById(R.id.tv_team_1_score);
            tvAwayTeam = itemView.findViewById(R.id.tv_team_2);
            tvAwayTeamScore = itemView.findViewById(R.id.tv_team_2_score);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(dataSet.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Bet bet);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

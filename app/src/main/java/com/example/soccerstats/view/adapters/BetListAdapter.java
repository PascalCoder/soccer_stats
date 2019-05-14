package com.example.soccerstats.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Bet;

import java.util.List;

public class BetListAdapter extends RecyclerView.Adapter<BetListAdapter.CustomViewHolder> {

    List<Bet> dataSet;

    public BetListAdapter(List<Bet> myBets){
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

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

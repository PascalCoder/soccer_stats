package com.example.soccerstats.view.adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.soccerstats.R;
import com.example.soccerstats.model.rounds.Match;
import com.example.soccerstats.model.rounds.MatchData;
import com.example.soccerstats.model.rounds.MatchList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TeamStatsAdapter extends RecyclerView.Adapter<TeamStatsAdapter.CustomViewHolder> {

    public static MatchList dataSet;
    public static List<Match> lastFiveMatches;
    public static Calendar calendar;

    public TeamStatsAdapter(MatchData matchData){
        this.dataSet = matchData.getData();
        /*lastFiveMatches = new ArrayList<>(dataSet.getMatchList()
                .subList(dataSet.getMatchList().size()-5, dataSet.getMatchList().size()));
        Collections.reverse(lastFiveMatches);*/
    }

    @NonNull
    @Override
    public TeamStatsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.details_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeamStatsAdapter.CustomViewHolder customViewHolder, int position) {

        String matchResult = dataSet.getMatchList().get(position).getMatchResult();
        customViewHolder.tvHomeTeam.setText(dataSet.getMatchList().get(position).getHomeTeam());
        customViewHolder.tvAwayTeam.setText(dataSet.getMatchList().get(position).getAwayTeam());

        int homeScore, awayScore;
        ColorStateList defaultColor = customViewHolder.tvHomeTeam.getTextColors();

        if(matchResult.length() > 1) {
            homeScore = Integer.parseInt(matchResult.substring(0, matchResult.indexOf('-')));
            customViewHolder.tvHomeTeamScore.setText(matchResult.substring(0, matchResult.indexOf('-')));
            awayScore = Integer.parseInt(matchResult.substring(matchResult.indexOf('-') + 1));
            customViewHolder.tvAwayTeamScore.setText(matchResult.substring(matchResult.indexOf('-') + 1));
        }else{
            homeScore = awayScore = 0;
            customViewHolder.tvHomeTeamScore.setText(" ");
            customViewHolder.tvAwayTeamScore.setText(" ");
        }

        if(homeScore > awayScore){
            customViewHolder.tvHomeTeam.setTextColor(Color.rgb(0, 133, 119));
        }else if(awayScore > homeScore){
            customViewHolder.tvAwayTeam.setTextColor(Color.rgb(0, 133, 119));
        }else if(customViewHolder.tvHomeTeamScore.getText().equals(" ")){
            customViewHolder.tvHomeTeam.setTextColor(defaultColor);
            customViewHolder.tvAwayTeam.setTextColor(defaultColor);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.getMatchList().size(); //lastFiveMatches.size()
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvHomeTeam, tvAwayTeam, tvHomeTeamScore, tvAwayTeamScore;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHomeTeam = itemView.findViewById(R.id.tv_home_team);
            tvAwayTeam = itemView.findViewById(R.id.tv_away_team);
            tvHomeTeamScore = itemView.findViewById(R.id.tv_home_team_score);
            tvAwayTeamScore = itemView.findViewById(R.id.tv_away_team_score);
        }
    }
}

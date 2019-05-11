package com.example.soccerstats.view.adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.rounds.Match;
import com.example.soccerstats.model.rounds.MatchData;
import com.example.soccerstats.model.rounds.MatchList;
import com.example.soccerstats.view.activities.BetActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TeamStatsAdapter extends RecyclerView.Adapter<TeamStatsAdapter.CustomViewHolder> {

    private static String TAG = TeamStatsAdapter.class.getSimpleName();
    public static MatchList dataSet;
    public static List<Match> lastFiveMatches;
    public static Calendar calendar;
    public static Date date;
    public static Match match;

    public TeamStatsAdapter(MatchData matchData){
        this.dataSet = matchData.getData();
        /*lastFiveMatches = new ArrayList<>(dataSet.getMatchList()
                .subList(dataSet.getMatchList().size()-5, dataSet.getMatchList().size()));
        Collections.reverse(lastFiveMatches);*/
        calendar = Calendar.getInstance();
        date = new Date();
        Log.d(TAG, "TeamStatsAdapter: " + Calendar.DATE +"\n" + date);
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
        customViewHolder.btnBet.setVisibility(View.GONE);
        customViewHolder.tvMatchDay.setVisibility(View.GONE);

        int homeScore, awayScore;
        ColorStateList defaultColor = customViewHolder.tvHomeTeamScore.getTextColors();

        //Split the result String into home and away scores
        if(matchResult.length() > 1) {
            homeScore = Integer.parseInt(matchResult.substring(0, matchResult.indexOf('-')));
            customViewHolder.tvHomeTeamScore.setText(matchResult.substring(0, matchResult.indexOf('-')));
            awayScore = Integer.parseInt(matchResult.substring(matchResult.indexOf('-') + 1));
            customViewHolder.tvAwayTeamScore.setText(matchResult.substring(matchResult.indexOf('-') + 1));
        }else{
            homeScore = awayScore = 0;
            customViewHolder.tvHomeTeamScore.setText(" ");
            customViewHolder.tvAwayTeamScore.setText(" ");
            customViewHolder.btnBet.setVisibility(View.VISIBLE); //bet button only visible for upcoming games
            customViewHolder.tvMatchDay.setText(dataSet.getMatchList().get(position).getMatchDate());
            customViewHolder.tvMatchDay.setVisibility(View.VISIBLE);
        }

        //Change Team's name color based on score
        if(homeScore > awayScore && homeScore != awayScore){
            customViewHolder.tvHomeTeam.setTextColor(Color.rgb(0, 133, 119));
        }else if(awayScore > homeScore && homeScore != awayScore){
            customViewHolder.tvAwayTeam.setTextColor(Color.rgb(0, 133, 119));
        }else if(customViewHolder.tvHomeTeamScore.getText().equals(" ") || homeScore == awayScore){
            customViewHolder.tvHomeTeam.setTextColor(defaultColor);
            customViewHolder.tvAwayTeam.setTextColor(defaultColor);
        }

        customViewHolder.btnBet.setOnClickListener(v ->{
            Match match = dataSet.getMatchList().get(customViewHolder.getAdapterPosition());
            /*String homeTeam = dataSet.getMatchList().get(customViewHolder.getAdapterPosition()).getHomeTeam();
            String awayTeam = dataSet.getMatchList().get(customViewHolder.getAdapterPosition()).getAwayTeam();
            String matchDay = dataSet.getMatchList().get(customViewHolder.getAdapterPosition()).getMatchDate();
            String name = dataSet.getMatchList().get(customViewHolder.getAdapterPosition()).getName(); // Ex: Round 2
            String identifier = dataSet.getMatchList().get(customViewHolder.getAdapterPosition()).getIdentifier();
            Toast.makeText(v.getContext(), "Position: " + customViewHolder.getAdapterPosition() + " " + homeTeam, Toast.LENGTH_SHORT).show();*/

            Intent intent = new Intent(v.getContext(), BetActivity.class);
            intent.putExtra("match", match);

            v.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return dataSet.getMatchList().size(); //lastFiveMatches.size()
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvHomeTeam, tvAwayTeam, tvHomeTeamScore, tvAwayTeamScore, tvMatchDay;
        Button btnBet;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHomeTeam = itemView.findViewById(R.id.tv_home_team);
            tvAwayTeam = itemView.findViewById(R.id.tv_away_team);
            tvHomeTeamScore = itemView.findViewById(R.id.tv_home_team_score);
            tvAwayTeamScore = itemView.findViewById(R.id.tv_away_team_score);
            tvMatchDay = itemView.findViewById(R.id.tv_match_day);
            btnBet = itemView.findViewById(R.id.btn_bet);

        }
    }
}

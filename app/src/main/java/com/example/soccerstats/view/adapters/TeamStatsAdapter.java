package com.example.soccerstats.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.rounds.Match;
import com.example.soccerstats.model.rounds.MatchData;
import com.example.soccerstats.model.rounds.MatchList;
import com.example.soccerstats.view.activities.BetActivity;
import com.example.soccerstats.view.activities.TeamDetailsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TeamStatsAdapter extends RecyclerView.Adapter<TeamStatsAdapter.CustomViewHolder> {

    private static String TAG = TeamStatsAdapter.class.getSimpleName();
    public static MatchList dataSet;
    public static List<Match> lastFiveMatches;
    public static Calendar calendar;
    public static Date date;
    public static Match match;

    private Context context;

    private int lastPosition = -1;

    //public static LocalDateTime TODAY = LocalDateTime.ofInstant((new Date()).toInstant(), ZoneId.systemDefault());
    static final Date TODAY = new Date();

    public TeamStatsAdapter(MatchData matchData){
        this.dataSet = matchData.getData();
        /*lastFiveMatches = new ArrayList<>(dataSet.getMatchList()
                .subList(dataSet.getMatchList().size()-5, dataSet.getMatchList().size()));
        Collections.reverse(lastFiveMatches);*/
        calendar = Calendar.getInstance();
        date = new Date();
        //Log.d(TAG, "TeamStatsAdapter: " + Calendar.DATE +"\n" + date);
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

        Date matchDate = getDateFromString(dataSet.getMatchList().get(position).getMatchDate());

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

            customViewHolder.tvMatchDay.setText(matchDate.toString());
            customViewHolder.tvMatchDay.setVisibility(View.VISIBLE);

            //The betting button is disabled at the time the game starts
            if(matchDate.compareTo(TODAY) <= 0 ){
                //Toast.makeText(context, "" + matchDate.compareTo(TODAY) + "\n" + matchDate, Toast.LENGTH_SHORT).show();
                customViewHolder.btnBet.setEnabled(false);
            }

        }

        //Change Team's name color based on score
        if(homeScore > awayScore && homeScore != awayScore){
            customViewHolder.tvHomeTeam.setTextColor(Color.rgb(0, 133, 119));
        }else if(awayScore > homeScore && homeScore != awayScore){
            customViewHolder.tvAwayTeam.setTextColor(Color.rgb(0, 133, 119));
        }else if(customViewHolder.tvHomeTeamScore.getText().equals("") || homeScore == awayScore){
            customViewHolder.tvHomeTeam.setTextColor(defaultColor);
            customViewHolder.tvAwayTeam.setTextColor(defaultColor);
        }

        setAnimation(customViewHolder.cardView, position);

        customViewHolder.btnBet.setOnClickListener(v ->{
            Match match = dataSet.getMatchList().get(customViewHolder.getAdapterPosition());

            Intent intent = new Intent(v.getContext(), BetActivity.class);
            intent.putExtra("match", match);
            intent.putExtra("league", TeamDetailsActivity.league);

            v.getContext().startActivity(intent);

        });

    }

    private void setAnimation(View viewToAnimate, int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private Date getDateFromString(String strDate){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        parser.setTimeZone(TimeZone.getDefault());

        Date parsedDate = null;

        try {
            parsedDate = parser.parse(strDate);
        }catch (ParseException pe){
            pe.printStackTrace();
        }

        return parsedDate;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CustomViewHolder holder) {
        //super.onViewDetachedFromWindow(holder);
        ((CustomViewHolder)holder).itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return dataSet.getMatchList().size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvHomeTeam, tvAwayTeam, tvHomeTeamScore, tvAwayTeamScore, tvMatchDay;
        Button btnBet;
        CardView cardView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            tvHomeTeam = itemView.findViewById(R.id.tv_home_team);
            tvAwayTeam = itemView.findViewById(R.id.tv_away_team);
            tvHomeTeamScore = itemView.findViewById(R.id.tv_home_team_score);
            tvAwayTeamScore = itemView.findViewById(R.id.tv_away_team_score);
            tvMatchDay = itemView.findViewById(R.id.tv_match_day);
            btnBet = itemView.findViewById(R.id.btn_bet);

            cardView = itemView.findViewById(R.id.details_cv);

        }
    }
}

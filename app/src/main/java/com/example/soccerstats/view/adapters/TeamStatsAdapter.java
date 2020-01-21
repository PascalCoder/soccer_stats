package com.example.soccerstats.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.soccerstats.R;
import com.example.soccerstats.model.rounds.Match;
import com.example.soccerstats.model.rounds.MatchData;
import com.example.soccerstats.model.rounds.MatchList;
import com.example.soccerstats.view.activities.BetActivity;
import com.example.soccerstats.view.activities.TeamStatsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.example.soccerstats.util.Constants.LEAGUE_CONST;
import static com.example.soccerstats.util.Constants.MATCH_CONST;
import static com.example.soccerstats.util.Constants.POSITION_CONST;
import static com.example.soccerstats.util.Constants.ROUND_ID_CONST;

public class TeamStatsAdapter extends RecyclerView.Adapter<TeamStatsAdapter.CustomViewHolder> {

    private static final int REQUEST_CODE = 1;
    //private static String TAG = TeamStatsAdapter.class.getSimpleName();
    private MatchList dataSet;
    //public static List<Match> lastFiveMatches;
    private Calendar calendar;
    private Date date;
    public static Match match;

    private Context context;

    private int lastPosition = -1;

    //public static LocalDateTime TODAY = LocalDateTime.ofInstant((new Date()).toInstant(), ZoneId.systemDefault());
    private static final Date TODAY = new Date();

    public TeamStatsAdapter(MatchData matchData){
        this.dataSet = matchData.getData();
        /*lastFiveMatches = new ArrayList<>(dataSet.getMatchList()
                .subList(dataSet.getMatchList().size()-5, dataSet.getMatchList().size()));
        Collections.reverse(lastFiveMatches);*/
        calendar = Calendar.getInstance();
        date = new Date();
        //Log.d(TAG, "TeamStatsAdapter: " + Calendar.DATE +"\n" + date);
        //context = ;
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
        if(homeScore > awayScore){
            customViewHolder.tvHomeTeam.setTextColor(Color.rgb(0, 133, 119));
        }else if(awayScore > homeScore){
            customViewHolder.tvAwayTeam.setTextColor(Color.rgb(0, 133, 119));
        }else {
            customViewHolder.tvHomeTeam.setTextColor(defaultColor);
            customViewHolder.tvAwayTeam.setTextColor(defaultColor);
        }

        setAnimation(customViewHolder.cardView, position);

        customViewHolder.btnBet.setOnClickListener(v ->{
            Match match = dataSet.getMatchList().get(customViewHolder.getAdapterPosition());

            Intent intent = new Intent(v.getContext(), BetActivity.class);
            intent.putExtra(MATCH_CONST, match);
            intent.putExtra(LEAGUE_CONST, TeamStatsActivity.league);
            intent.putExtra(ROUND_ID_CONST, match.getIdentifier());
            intent.putExtra(POSITION_CONST, customViewHolder.getAdapterPosition());

            ((Activity)v.getContext()).startActivityForResult(intent, REQUEST_CODE);
            //customViewHolder.btnBet.setEnabled(false);
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
        (holder).itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return dataSet.getMatchList().size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvHomeTeam, tvAwayTeam, tvHomeTeamScore, tvAwayTeamScore, tvMatchDay;
        Button btnBet;
        CardView cardView;

        CustomViewHolder(@NonNull View itemView) {
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

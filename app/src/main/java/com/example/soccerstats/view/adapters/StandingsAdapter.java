package com.example.soccerstats.view.adapters;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.soccerstats.R;
import com.example.soccerstats.model.standings.StandingList;
import com.example.soccerstats.model.standings.StandingsData;
import com.example.soccerstats.view.activities.StandingsActivity;
import com.example.soccerstats.view.activities.TeamStatsActivity;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.CustomViewHolder> {

    public static final String TAG = StandingsAdapter.class.getSimpleName();

    public static StandingList dataSet;

    private Context context;

    static String teamIdentifier;

    private int lastPosition = -1;

    public StandingsAdapter(StandingsData standingsData) {
        this.dataSet = standingsData.getStandingList();
    }

    @NonNull
    @Override
    public StandingsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsAdapter.CustomViewHolder customViewHolder, int position) {

        int goalDiff = dataSet.getStandings().get(position).getOverall().getGoalDifference();

        String goalDiffStr = (goalDiff > 0) ? "+" + goalDiff : "" + goalDiff;

        customViewHolder.tvPosition.setText("" + dataSet.getStandings().get(position).getPosition());
        customViewHolder.tvTeamName.setText(dataSet.getStandings().get(position).getTeam());
        customViewHolder.tvPoints.setText("" + dataSet.getStandings().get(position).getOverall().getPoints());
        customViewHolder.tvGoalDiff.setText(goalDiffStr);
        if (goalDiff > 0) {
            customViewHolder.tvGoalDiff.setTextColor(Color.rgb(0, 133, 119));
        } else if (goalDiff < 0) {
            customViewHolder.tvGoalDiff.setTextColor(Color.RED);
        }

        customViewHolder.tvId.setText(dataSet.getStandings().get(position).getTeamIdentifier());

        setAnimation(customViewHolder.cardView, position);

        teamIdentifier = dataSet.getStandings().get(position).getTeamIdentifier();
        Log.d(TAG, "onBindViewHolder: " + dataSet.getStandings().get(position).getTeamIdentifier());
    }

    private void setAnimation(View viewToAnimate, int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CustomViewHolder holder) {
        //super.onViewDetachedFromWindow(holder);
        ((CustomViewHolder)holder).itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return dataSet.getStandings().size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvPosition, tvTeamName, tvPoints, tvGoalDiff, tvId;
        CardView cardView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            tvPosition = itemView.findViewById(R.id.tv_position);
            tvTeamName = itemView.findViewById(R.id.tv_team_name);
            tvPoints = itemView.findViewById(R.id.tv_points);
            tvGoalDiff = itemView.findViewById(R.id.tv_goal_diff);

            tvId = itemView.findViewById(R.id.tv_id);

            cardView = itemView.findViewById(R.id.card_view);

            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + tvId.getText().toString());
                    Intent intent = new Intent(context, TeamStatsActivity.class);
                    intent.putExtra("id", tvId.getText().toString());
                    intent.putExtra("team", tvTeamName.getText().toString());
                    intent.putExtra("league", StandingsActivity.league);

                    context.startActivity(intent);
                }
            });
        }
    }
}

package com.example.soccerstats.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerstats.R;
import com.example.soccerstats.model.rounds.MatchData;
import com.example.soccerstats.network.RetrofitHelper;
import com.example.soccerstats.view.adapters.TeamStatsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.soccerstats.util.Constants.ID_CONST;
import static com.example.soccerstats.util.Constants.TEAM_CONST;
import static com.example.soccerstats.util.LeaguesConstants.CURRENT_SEASON;

public class TeamStatsActivity extends AppCompatActivity {

    //public static final String TAG = TeamStatsActivity.class.getSimpleName();
    public static String league;

    @BindView(R.id.details_recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.tv_name)
    public TextView teamName;

    static String teamId;
    static String teamNameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        teamId = intent.getStringExtra(ID_CONST);
        teamNameStr = intent.getStringExtra(TEAM_CONST);
        teamName.setText(teamNameStr);

        RetrofitHelper.initializeRetrofit();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        league = StandingsActivity.league;

        getTeamResults();

    }

    public void getTeamResults(){
        RetrofitHelper.soccerApi.getTeamStats(league, CURRENT_SEASON, teamId).enqueue(new Callback<MatchData>() {
            @Override
            public void onResponse(@NonNull Call<MatchData> call, @NonNull Response<MatchData> response) {
                if(response.body() != null)
                    recyclerView.setAdapter(new TeamStatsAdapter(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<MatchData> call, @NonNull Throwable t) {

            }
        });
    }
}

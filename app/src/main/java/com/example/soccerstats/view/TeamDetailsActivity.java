package com.example.soccerstats.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Leagues;
import com.example.soccerstats.model.RetrofitHelper;
import com.example.soccerstats.model.rounds.MatchData;
import com.example.soccerstats.view.adapters.TeamStatsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDetailsActivity extends AppCompatActivity {

    public static final String TAG = TeamDetailsActivity.class.getSimpleName();
    public static final String BASE_URL = "http://soccer.sportsopendata.net/";
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
        teamId = intent.getStringExtra("id");
        teamNameStr = intent.getStringExtra("team");
        teamName.setText(teamNameStr);

        RetrofitHelper.initializeRetrofit();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        league = MainActivity.LEAGUE;
        Log.d(TAG, "onCreate: " + MainActivity.LEAGUE);

        getTeamResults();

    }

    public void getTeamResults(){
        RetrofitHelper.soccerApi.getTeamStats(league, Leagues.CURRENT_SEASON, teamId).enqueue(new Callback<MatchData>() {
            @Override
            public void onResponse(Call<MatchData> call, Response<MatchData> response) {
                recyclerView.setAdapter(new TeamStatsAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<MatchData> call, Throwable t) {

            }
        });
    }
}

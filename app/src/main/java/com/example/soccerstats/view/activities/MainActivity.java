package com.example.soccerstats.view.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Leagues;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindViews({R.id.cv_liga, R.id.cv_premier_league, R.id.cv_bundesliga, R.id.cv_serie_a})
    List<CardView> cardViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        cardViewList.get(0).setOnClickListener(v -> getLeagueStandings(Leagues.LIGA, v.getContext()));

        cardViewList.get(1).setOnClickListener(v -> getLeagueStandings(Leagues.PREMIER_LEAGUE, v.getContext()));

        cardViewList.get(2).setOnClickListener(v -> getLeagueStandings(Leagues.BUNDESLIGA, v.getContext()));

        cardViewList.get(3).setOnClickListener(v -> getLeagueStandings(Leagues.SERIE_A, v.getContext()));

    }

    void getLeagueStandings(String league, Context context){
        Intent intent = new Intent(context, StandingsActivity.class);
        intent.putExtra("league", league);
        startActivity(intent);
    }
}

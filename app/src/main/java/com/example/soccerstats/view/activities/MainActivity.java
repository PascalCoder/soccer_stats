package com.example.soccerstats.view.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Leagues;
import com.example.soccerstats.view.fragments.LeagueFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.cv_liga)
    CardView cvLiga;
    @BindView(R.id.cv_premier_league)
    CardView cvPremLeague;
    @BindView(R.id.cv_bundesliga)
    CardView cvBundesliga;
    @BindView(R.id.cv_serie_a)
    CardView cvSerieA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        cvLiga.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), StandingsActivity.class);
            intent.putExtra("league", Leagues.LIGA);
            startActivity(intent);
        });

        cvPremLeague.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), StandingsActivity.class);
            intent.putExtra("league", Leagues.PREMIER_LEAGUE);
            startActivity(intent);
        });

        cvBundesliga.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), StandingsActivity.class);
            intent.putExtra("league", Leagues.BUNDESLIGA);
            startActivity(intent);
        });

        cvSerieA.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), StandingsActivity.class);
            intent.putExtra("league", Leagues.SERIE_A);
            startActivity(intent);
        });
    }
}

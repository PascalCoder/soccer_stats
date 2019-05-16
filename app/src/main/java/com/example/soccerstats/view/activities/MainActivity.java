package com.example.soccerstats.view.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

    void getLeagueStandings(String league, Context context) {
        Intent intent = new Intent(context, StandingsActivity.class);
        intent.putExtra("league", league);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bet_menu, menu);

        return true; //super.onCreateOptionsMenu(bet_menu)
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_1:
                //Toast.makeText(this, "Item was clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, BetListActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

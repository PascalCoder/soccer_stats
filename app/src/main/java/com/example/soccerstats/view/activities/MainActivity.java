package com.example.soccerstats.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.soccerstats.R;
import com.example.soccerstats.model.standings.Standing;
import com.example.soccerstats.util.LeaguesConstants;
import com.example.soccerstats.viewmodel.MainActivityViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mStandingListViewModel;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindViews({R.id.cv_liga, R.id.cv_premier_league, R.id.cv_bundesliga, R.id.cv_serie_a})
    List<CardView> cardViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mStandingListViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        subscribeObservers();

        setSupportActionBar(toolbar);

        cardViewList.get(0).setOnClickListener(v -> getLeagueStandings(LeaguesConstants.LIGA, v.getContext()));

        cardViewList.get(1).setOnClickListener(v -> getLeagueStandings(LeaguesConstants.PREMIER_LEAGUE, v.getContext()));

        cardViewList.get(2).setOnClickListener(v -> getLeagueStandings(LeaguesConstants.BUNDESLIGA, v.getContext()));

        cardViewList.get(3).setOnClickListener(v -> getLeagueStandings(LeaguesConstants.SERIE_A, v.getContext()));

    }

    private void subscribeObservers(){
        mStandingListViewModel.getStandings().observe(this, new Observer<List<Standing>>() {
            @Override
            public void onChanged(@Nullable List<Standing> standings) {

            }
        });
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

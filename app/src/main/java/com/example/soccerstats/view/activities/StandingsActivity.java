package com.example.soccerstats.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.soccerstats.R;
import com.example.soccerstats.util.LeaguesConstants;
import com.example.soccerstats.view.fragments.LeagueFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StandingsActivity extends AppCompatActivity {

    public static String league;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        ButterKnife.bind(this);

        league = getIntent().getStringExtra("league");

        String title = "";
        switch (league){
            case "liga":
                title = "LA " + LeaguesConstants.LIGA_TITLE;
                break;
            case "premier-league":
                title = LeaguesConstants.PREMIER_LEAGUE_TITLE;
                break;
            case "bundesliga":
                title = LeaguesConstants.BUNDESLIGA_TITLE;
                break;
            case "serie-a":
                title = LeaguesConstants.SERIE_A_TITLE;
                break;
        }
        toolbar.setTitle(title + "  Standings");
        setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        bundle.putString("league", league);

        LeagueFragment fragment = LeagueFragment.newInstance();
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment).commit(); //.addToBackStack(null)

    }
}

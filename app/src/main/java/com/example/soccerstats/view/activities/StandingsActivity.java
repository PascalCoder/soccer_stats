package com.example.soccerstats.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.soccerstats.R;
import com.example.soccerstats.view.fragments.LeagueFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.soccerstats.util.Constants.LEAGUE_CONST;
import static com.example.soccerstats.util.Constants.STANDINGS_CONST;
import static com.example.soccerstats.util.LeaguesConstants.BUNDESLIGA;
import static com.example.soccerstats.util.LeaguesConstants.BUNDESLIGA_TITLE;
import static com.example.soccerstats.util.LeaguesConstants.LIGA;
import static com.example.soccerstats.util.LeaguesConstants.LIGA_TITLE;
import static com.example.soccerstats.util.LeaguesConstants.PREMIER_LEAGUE;
import static com.example.soccerstats.util.LeaguesConstants.PREMIER_LEAGUE_TITLE;
import static com.example.soccerstats.util.LeaguesConstants.SERIE_A;
import static com.example.soccerstats.util.LeaguesConstants.SERIE_A_TITLE;

public class StandingsActivity extends AppCompatActivity {

    public static String league;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        ButterKnife.bind(this);

        league = getIntent().getStringExtra(LEAGUE_CONST);

        String title = "";
        switch (league){
            case LIGA:
                title = "LA " + LIGA_TITLE;
                break;
            case PREMIER_LEAGUE:
                title = PREMIER_LEAGUE_TITLE;
                break;
            case BUNDESLIGA:
                title = BUNDESLIGA_TITLE;
                break;
            case SERIE_A:
                title = SERIE_A_TITLE;
                break;
        }
        toolbar.setTitle(title + " " + STANDINGS_CONST);
        setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        bundle.putString(LEAGUE_CONST, league);

        LeagueFragment fragment = LeagueFragment.newInstance();
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment).commit(); //.addToBackStack(null)

    }
}

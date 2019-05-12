package com.example.soccerstats.view.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Leagues;
import com.example.soccerstats.view.fragments.LeagueFragment;

public class StandingsActivity extends AppCompatActivity {

    static String league;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);

        league = getIntent().getStringExtra("league");

        Bundle bundle = new Bundle();
        bundle.putString("league", league);

        LeagueFragment fragment = LeagueFragment.newInstance();
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment).commit(); //.addToBackStack(null)

        Toast.makeText(this, "Liga clicked", Toast.LENGTH_SHORT).show();
    }
}

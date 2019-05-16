package com.example.soccerstats.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Bet;
import com.example.soccerstats.model.rounds.Match;
import com.example.soccerstats.viewmodel.BetViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BetActivity extends AppCompatActivity {

    private BetViewModel betViewModel;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_h_team)
    TextView tvHomeTeam;
    @BindView(R.id.tv_a_team)
    TextView tvAwayTeam;
    @BindView(R.id.et_h_score)
    EditText etHomeScore;
    @BindView(R.id.et_a_score)
    EditText etAwayScore;
    @BindView(R.id.btn_submit_bet)
    Button btnSubmit;
    @BindView(R.id.np_h_score)
    NumberPicker numberPickerHome;
    @BindView(R.id.np_a_score)
    NumberPicker numberPickerAway;

    static Bet bet;
    static String league;

    public static List<Bet> allBets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        numberPickerHome.setMinValue(0);
        numberPickerHome.setMaxValue(15);
        numberPickerAway.setMinValue(0);
        numberPickerAway.setMaxValue(15);

        Intent intent = getIntent();
        Match match = intent.getParcelableExtra("match");
        league = intent.getStringExtra("league");

        bet = new Bet();
        bet.setMatchId(match.getIdentifier());
        bet.setHomeTeam(match.getHomeTeam());
        bet.setAwayTeam(match.getAwayTeam());
        bet.setMatchLeague(league);
        bet.setMatchDate(match.getMatchDate());

        tvHomeTeam.setText(match.getHomeTeam());
        tvAwayTeam.setText(match.getAwayTeam());

        betViewModel = ViewModelProviders.of(this).get(BetViewModel.class);
        betViewModel.getAllBets().observe(this, new Observer<List<Bet>>() {
            @Override
            public void onChanged(@Nullable List<Bet> bets) {
                //Toast.makeText(BetListActivity.this, "oChange", Toast.LENGTH_SHORT).show();
                if(bets != null){
                    allBets = new ArrayList<>(bets);
                    Toast.makeText(BetActivity.this, "" + allBets.size(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSubmit.setOnClickListener(v -> {

            bet.setHomeTeamScore(numberPickerHome.getValue() + "");
            bet.setAwayTeamScore(numberPickerAway.getValue() + "");
            if (numberPickerHome.getValue() > numberPickerAway.getValue()) {
                bet.setWinningTeam(bet.getHomeTeam());
            } else if (numberPickerAway.getValue() > numberPickerHome.getValue()) {
                bet.setWinningTeam(bet.getAwayTeam());
            } else {
                bet.setWinningTeam("");
            }

            if (isBetAlreadyPlaced(allBets, bet)) {
                Toast.makeText(this, "This bet has already been placed!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                betViewModel.insert(bet);
            }

            Toast.makeText(this, "Submitted! ", Toast.LENGTH_SHORT).show();
            Log.d(BetActivity.class.getSimpleName(), "onCreate: " + bet.getMatchDate());

            onBackPressed();
        });

    }

    public boolean isBetAlreadyPlaced(List<Bet> betList, Bet bet) {
        for (int i = 0; i < betList.size(); i++) {
            if (betList.get(i).equals(bet)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bet_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_1:
                Intent intent = new Intent(this, BetListActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

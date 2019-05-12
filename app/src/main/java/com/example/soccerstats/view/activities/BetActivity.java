package com.example.soccerstats.view.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        ButterKnife.bind(this);

        betViewModel = ViewModelProviders.of(this).get(BetViewModel.class);

        numberPickerHome.setMinValue(0);
        numberPickerHome.setMaxValue(15);
        numberPickerAway.setMinValue(0);
        numberPickerAway.setMaxValue(15);

        Intent intent = getIntent();
        Match match = intent.getParcelableExtra("match");
        Toast.makeText(this, "Well done! " + match.getHomeTeam(), Toast.LENGTH_SHORT).show();
        bet = new Bet();
        bet.setMatchId(match.getIdentifier());
        bet.setHomeTeam(match.getHomeTeam());
        bet.setAwayTeam(match.getAwayTeam());

        tvHomeTeam.setText(match.getHomeTeam());
        tvAwayTeam.setText(match.getAwayTeam());

        betViewModel.getAllBets().observe(this, new Observer<List<Bet>>() {
            @Override
            public void onChanged(@Nullable List<Bet> bets) {
                Toast.makeText(BetActivity.this, "onChanged: Changed", Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmit.setOnClickListener(v -> {
            /*if(etHomeScore.getText().toString().equals(" ")
                    || etHomeScore.getText().toString() == null || etHomeScore.getText().toString().equals("")){
                Toast.makeText(this, "Please provide your scores!", Toast.LENGTH_SHORT).show();
                return;
            }else if(etAwayScore.getText().toString().equals(" ")
                    || etAwayScore.getText().toString() == null || etAwayScore.getText().toString().equals("")){
                Toast.makeText(this, "Please provide your scores!", Toast.LENGTH_SHORT).show();
                return;
            }else{
                *//*bet.setHomeTeamScore(etHomeScore.getText().toString());
                bet.setAwayTeamScore(etAwayScore.getText().toString());*//*

                bet.setHomeTeamScore(numberPickerHome.getValue() + "");
                bet.setAwayTeamScore(numberPickerAway.getValue() + "");

                betViewModel.insert(bet);

                Toast.makeText(this, "Submitted! " + numberPickerHome.getValue(), Toast.LENGTH_SHORT).show();
            }*/
            bet.setHomeTeamScore(numberPickerHome.getValue() + "");
            bet.setAwayTeamScore(numberPickerAway.getValue() + "");

            Toast.makeText(this, "Submitted! " + numberPickerHome.getValue(), Toast.LENGTH_SHORT).show();

            onBackPressed();
        });

    }
}

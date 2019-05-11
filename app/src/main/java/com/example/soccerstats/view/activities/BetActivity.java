package com.example.soccerstats.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Bet;
import com.example.soccerstats.model.rounds.Match;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BetActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Match match = intent.getParcelableExtra("match");
        Toast.makeText(this, "Well done! " + match.getHomeTeam(), Toast.LENGTH_SHORT).show();
        Bet bet = new Bet();
        bet.setMatchId(match.getIdentifier());
        bet.setHomeTeam(match.getHomeTeam());
        bet.setAwayTeam(match.getAwayTeam());

        tvHomeTeam.setText(match.getHomeTeam());
        tvAwayTeam.setText(match.getAwayTeam());

        btnSubmit.setOnClickListener(v -> {
            if(etHomeScore.getText().toString().equals(" ")
                    || etHomeScore.getText().toString() == null || etHomeScore.getText().toString().equals("")){
                Toast.makeText(this, "Please provide your scores!", Toast.LENGTH_SHORT).show();
                return;
            }else if(etAwayScore.getText().toString().equals(" ")
                    || etAwayScore.getText().toString() == null || etAwayScore.getText().toString().equals("")){
                Toast.makeText(this, "Please provide your scores!", Toast.LENGTH_SHORT).show();
                return;
            }else{
                bet.setHomeTeamScore(etHomeScore.getText().toString());
                bet.setAwayTeamScore(etAwayScore.getText().toString());
                Toast.makeText(this, "Submitted!" + etHomeScore.getText().toString(), Toast.LENGTH_SHORT).show();
            }
            onBackPressed();
        });

    }
}

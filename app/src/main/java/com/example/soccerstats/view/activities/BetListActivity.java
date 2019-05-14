package com.example.soccerstats.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.soccerstats.R;
import com.example.soccerstats.view.adapters.BetListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BetListActivity extends AppCompatActivity {

    @BindView(R.id.rv_bet_list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_list);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BetListAdapter(BetActivity.allBets));
    }
}

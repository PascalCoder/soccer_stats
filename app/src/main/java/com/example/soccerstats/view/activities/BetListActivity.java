package com.example.soccerstats.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.Bet;
import com.example.soccerstats.view.adapters.BetListAdapter;
import com.example.soccerstats.viewmodel.BetViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BetListActivity extends AppCompatActivity {

    @BindView(R.id.rv_bet_list)
    RecyclerView recyclerView;

    private BetViewModel betViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_list);
        ButterKnife.bind(this);

        /*List<Bet> dummyBets = new ArrayList<>();
        Bet bet1 = new Bet();
        bet1.setHomeTeam("Liverpool");
        bet1.setAwayTeam("Man United");
        bet1.setHomeTeamScore("2");
        bet1.setAwayTeamScore("1");
        bet1.setMatchLeague(Leagues.PREMIER_LEAGUE);
        Bet bet2 = new Bet();
        bet2.setHomeTeam("Real Madrid");
        bet2.setAwayTeam("Barcellona");
        bet2.setHomeTeamScore("3");
        bet2.setAwayTeamScore("2");
        bet2.setMatchLeague(Leagues.LIGA);

        dummyBets.add(bet1);
        dummyBets.add(bet2);*/

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(new BetListAdapter(dummyBets)); //BetActivity.allBets
        recyclerView.setHasFixedSize(true);
        BetListAdapter adapter = new BetListAdapter();
        recyclerView.setAdapter(adapter);

        betViewModel = ViewModelProviders.of(this).get(BetViewModel.class);
        betViewModel.getAllBets().observe(this, new Observer<List<Bet>>() {
            @Override
            public void onChanged(@Nullable List<Bet> bets) {
                //Toast.makeText(BetListActivity.this, "onChange", Toast.LENGTH_SHORT).show();
                adapter.setBets(bets);
            }
        });
        /*LiveData<List<Bet>> betsData = betViewModel.getAllBets();
        Toast.makeText(this, "" + betsData, Toast.LENGTH_SHORT).show();
        List<Bet> allBets = betsData.getValue();
        Toast.makeText(this, "Size: " + (allBets != null? allBets.size() : 0), Toast.LENGTH_SHORT).show();
*/
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT) {
                    betViewModel.delete(adapter.getBetAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(BetListActivity.this, "Bet deleted!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new BetListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Bet bet) {
                Intent intent = new Intent(BetListActivity.this, BetActivity.class);
                intent.putExtra("bet_id", bet.getId());
                intent.putExtra("bet", bet);
            }
        });
    }
}

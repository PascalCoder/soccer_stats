package com.example.soccerstats.view.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.network.RetrofitHelper;
import com.example.soccerstats.model.standings.StandingsData;
import com.example.soccerstats.util.LeaguesConstants;
import com.example.soccerstats.view.adapters.StandingsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PremierLeagueFragment extends Fragment {

    public static final String TAG = PremierLeagueFragment.class.getSimpleName();

    RecyclerView recyclerView;

    public PremierLeagueFragment() {
        // Required empty public constructor
    }

    public static PremierLeagueFragment newInstance(){return new PremierLeagueFragment();}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_premier_league, container, false);

        RetrofitHelper.initializeRetrofit();
        recyclerView = rootView.findViewById(R.id.prem_league_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPremierLeagueStandings();

        return rootView;
    }

    private void getPremierLeagueStandings() {

        RetrofitHelper.soccerApi.getStandings(LeaguesConstants.PREMIER_LEAGUE, LeaguesConstants.CURRENT_SEASON).enqueue(new Callback<StandingsData>() {
            @Override
            public void onResponse(Call<StandingsData> call, Response<StandingsData> response) {
                Log.d(TAG, "onResponse: " + response.body().getStandingList().getStandings().get(0).getTeam());
                recyclerView.setAdapter(new StandingsAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<StandingsData> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

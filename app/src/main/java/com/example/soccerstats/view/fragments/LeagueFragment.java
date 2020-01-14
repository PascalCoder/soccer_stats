package com.example.soccerstats.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.soccerstats.R;
import com.example.soccerstats.model.RetrofitHelper;
import com.example.soccerstats.model.standings.StandingsData;
import com.example.soccerstats.util.LeaguesConstants;
import com.example.soccerstats.view.adapters.StandingsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeagueFragment extends Fragment {

    RecyclerView recyclerView;

    private static final String TAG = LeagueFragment.class.getSimpleName();
    public static String league;

    public LeagueFragment() {
        // Required empty public constructor
    }

    public static LeagueFragment newInstance() {
        return new LeagueFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_league, container, false);

        RetrofitHelper.initializeRetrofit();
        recyclerView = rootView.findViewById(R.id.league_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        league = getArguments().getString("league");

        getLeagueStandings();

        return rootView;
    }

    private void getLeagueStandings() {
        RetrofitHelper.soccerApi.getStandings(league, LeaguesConstants.CURRENT_SEASON).enqueue(new Callback<StandingsData>() {
            @Override
            public void onResponse(Call<StandingsData> call, Response<StandingsData> response) {

                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().getStandingList().getStandings().get(0).getTeam());
                    recyclerView.setAdapter(new StandingsAdapter(response.body()));
                }
            }

            @Override
            public void onFailure(Call<StandingsData> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

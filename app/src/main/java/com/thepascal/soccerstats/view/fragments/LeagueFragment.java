package com.thepascal.soccerstats.view.fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thepascal.soccerstats.R;
import com.thepascal.soccerstats.network.RetrofitHelper;
import com.thepascal.soccerstats.model.standings.StandingsData;
import com.thepascal.soccerstats.util.LeaguesConstants;
import com.thepascal.soccerstats.view.adapters.StandingsAdapter;

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

        if (getArguments() != null){
            league = getArguments().getString("league");
        } else {
            league = LeaguesConstants.PREMIER_LEAGUE;
        }

        getLeagueStandings();

        return rootView;
    }

    private void getLeagueStandings() {
        RetrofitHelper.soccerApi.getStandings(league, LeaguesConstants.CURRENT_SEASON).enqueue(new Callback<StandingsData>() {
            @Override
            public void onResponse(@NonNull Call<StandingsData> call, @NonNull Response<StandingsData> response) {

                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().getStandingList().getStandings().get(0).getTeam());
                    recyclerView.setAdapter(new StandingsAdapter(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<StandingsData> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

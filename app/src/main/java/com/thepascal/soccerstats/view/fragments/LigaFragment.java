package com.thepascal.soccerstats.view.fragments;


import android.os.Bundle;
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
public class LigaFragment extends Fragment {

    RecyclerView recyclerView;

    //public static final String BASE_URL = "http://soccer.sportsopendata.net/";
    public static final String TAG = LigaFragment.class.getSimpleName();

    public LigaFragment() {
        // Required empty public constructor
    }

    public static LigaFragment newInstance(){return new LigaFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_liga, container, false);

        RetrofitHelper.initializeRetrofit();
        recyclerView = rootView.findViewById(R.id.liga_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getLigaStandings();

        return rootView;
    }

    private void getLigaStandings(){
        RetrofitHelper.soccerApi.getStandings(LeaguesConstants.LIGA, LeaguesConstants.CURRENT_SEASON).enqueue(new Callback<StandingsData>() {
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

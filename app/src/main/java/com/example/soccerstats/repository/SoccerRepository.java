package com.example.soccerstats.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.soccerstats.model.rounds.Match;

import java.util.List;

public class SoccerRepository {

    private SoccerDao matchDao;
    private LiveData<List<Match>> allMatches;

    public SoccerRepository(Application application){
        SoccerDatabase database = SoccerDatabase.getInstance(application);
        matchDao = database.matchDao();
        allMatches = matchDao.getAllMatches();
    }

    public LiveData<List<Match>> getAllMatches(){
        return allMatches;
    }
}

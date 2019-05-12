package com.example.soccerstats.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.soccerstats.model.rounds.Match;
import com.example.soccerstats.repository.SoccerRepository;

import java.util.List;

public class SoccerViewModel extends AndroidViewModel {

    private SoccerRepository repository;
    private LiveData<List<Match>> allMatches;

    public SoccerViewModel(@NonNull Application application) {
        super(application);

        repository = new SoccerRepository(application);
        allMatches = repository.getAllMatches();
    }

    public LiveData<List<Match>> getAllMatches(){
        return allMatches;
    }
}

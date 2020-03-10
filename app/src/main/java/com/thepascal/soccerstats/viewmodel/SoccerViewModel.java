package com.thepascal.soccerstats.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thepascal.soccerstats.model.rounds.Match;
import com.thepascal.soccerstats.repository.SoccerRepository;

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

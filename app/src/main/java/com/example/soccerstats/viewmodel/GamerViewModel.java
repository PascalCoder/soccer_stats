package com.example.soccerstats.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.soccerstats.model.Gamer;
import com.example.soccerstats.repository.GamerRepository;

import java.util.List;

public class GamerViewModel extends AndroidViewModel {

    private GamerRepository repository;
    private LiveData<List<Gamer>> allGamers;

    public GamerViewModel(@NonNull Application application) {
        super(application);

        repository = new GamerRepository(application);
        allGamers = repository.getAllGamers();
    }

    public void insert(Gamer gamer){
        repository.insert(gamer);
    }

    public void update(Gamer bet){
        repository.update(bet);
    }

    public void delete(Gamer bet){
        repository.delete(bet);
    }

    public void deleteAllGamers(){
        repository.deleteAllGamers();
    }

    public LiveData<List<Gamer>> getAllGamers(){
        return allGamers;
    }
}

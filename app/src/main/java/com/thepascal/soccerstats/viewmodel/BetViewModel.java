package com.thepascal.soccerstats.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thepascal.soccerstats.model.Bet;
import com.thepascal.soccerstats.repository.BetRepository;

import java.util.List;

public class BetViewModel extends AndroidViewModel {

    private BetRepository repository;
    private LiveData<List<Bet>> allBets;

    public BetViewModel(@NonNull Application application) {
        super(application);

        repository = new BetRepository(application);
        allBets = repository.getAllBets();
    }

    public void insert(Bet bet){
        repository.insert(bet);
    }

    public void update(Bet bet){
        repository.update(bet);
    }

    public void delete(Bet bet){
        repository.delete(bet);
    }

    public void deleteAllBets(){
        repository.deleteAllBets();
    }

    public LiveData<List<Bet>> getAllBets(){
        return allBets;
    }
}

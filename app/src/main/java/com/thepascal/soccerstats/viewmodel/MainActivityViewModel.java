package com.thepascal.soccerstats.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thepascal.soccerstats.model.standings.Standing;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Standing>> mStandings = new MutableLiveData<>();

    public MainActivityViewModel() {

    }

    public LiveData<List<Standing>> getStandings() {
        return mStandings;
    }
}

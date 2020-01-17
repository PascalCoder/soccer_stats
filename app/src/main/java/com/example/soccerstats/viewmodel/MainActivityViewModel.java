package com.example.soccerstats.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.soccerstats.model.standings.Standing;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Standing>> mStandings = new MutableLiveData<>();

    public MainActivityViewModel() {

    }

    public LiveData<List<Standing>> getStandings() {
        return mStandings;
    }
}

package com.example.soccerstats.model.standings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StandingsData {

    @SerializedName("data")
    @Expose
    private StandingList standingList;

    public StandingList getStandingList() {
        return standingList;
    }

    public void setStandingList(StandingList standingList) {
        this.standingList = standingList;
    }
}

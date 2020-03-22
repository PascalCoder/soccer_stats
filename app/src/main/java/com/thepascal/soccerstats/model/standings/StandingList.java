package com.thepascal.soccerstats.model.standings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingList {

    @SerializedName("standings")
    @Expose
    private List<Standing> standings;

    public List<Standing> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing> standings) {
        this.standings = standings;
    }
}

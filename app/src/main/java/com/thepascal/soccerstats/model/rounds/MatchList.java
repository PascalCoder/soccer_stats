package com.thepascal.soccerstats.model.rounds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchList {

    @SerializedName("rounds")
    @Expose
    private List<Match> matchList = null;

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }
}
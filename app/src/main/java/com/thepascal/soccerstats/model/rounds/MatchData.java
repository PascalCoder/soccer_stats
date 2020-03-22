package com.thepascal.soccerstats.model.rounds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchData {

    @SerializedName("data")
    @Expose
    private MatchList data;

    public MatchList getData() {
        return data;
    }

    public void setData(MatchList data) {
        this.data = data;
    }
}

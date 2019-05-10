package com.example.soccerstats.model.teams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamsData {

    @SerializedName("data")
    @Expose
    private TeamList teamList;

    public TeamList getTeamList() {
        return teamList;
    }

    public void setTeamList(TeamList teamList) {
        this.teamList = teamList;
    }
}

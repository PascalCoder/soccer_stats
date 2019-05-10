package com.example.soccerstats.model.rounds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("identifier")
    @Expose
    private String identifier;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("match_slug")
    @Expose
    private String matchSlug;

    @SerializedName("date_match")
    @Expose
    private String matchDate;

    @SerializedName("home_team")
    @Expose
    private String homeTeam;

    @SerializedName("away_team")
    @Expose
    private String awayTeam;

    @SerializedName("match_result")
    @Expose
    private String matchResult;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatchSlug() {
        return matchSlug;
    }

    public void setMatchSlug(String matchSlug) {
        this.matchSlug = matchSlug;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }
}

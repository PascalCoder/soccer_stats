package com.thepascal.soccerstats.model.standings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home {

    @SerializedName("wins")
    @Expose
    private Integer wins;

    @SerializedName("draws")
    @Expose
    private Integer draws;

    @SerializedName("losts")
    @Expose
    private Integer losses;

    @SerializedName("scores")
    @Expose
    private Integer scores;

    @SerializedName("conceded")
    @Expose
    private Integer conceded;

    @SerializedName("points")
    @Expose
    private Integer points;

    @SerializedName("matches_played")
    @Expose
    private Integer matchesPlayed;

    @SerializedName("goal_difference")
    @Expose
    private Integer goalDifference;

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Integer getConceded() {
        return conceded;
    }

    public void setConceded(Integer conceded) {
        this.conceded = conceded;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }
}

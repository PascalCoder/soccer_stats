package com.example.soccerstats.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "bet")
public class Bet implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    //@Ignore
    private Gamer gamer;
    private String homeTeam;
    private String awayTeam;
    private String homeTeamScore;
    private String awayTeamScore;
    private String matchId;
    private String matchLeague;
    private String winningTeam;
    private String matchDate;

    public Bet(){}

    public Bet(String homeTeam, String awayTeam, String homeTeamScore, String awayTeamScore, String matchLeague) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.matchLeague = matchLeague;
    }

    protected Bet(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        gamer = in.readParcelable(Gamer.class.getClassLoader());
        homeTeam = in.readString();
        awayTeam = in.readString();
        homeTeamScore = in.readString();
        awayTeamScore = in.readString();
        matchId = in.readString();
    }

    public static final Creator<Bet> CREATOR = new Creator<Bet>() {
        @Override
        public Bet createFromParcel(Parcel in) {
            return new Bet(in);
        }

        @Override
        public Bet[] newArray(int size) {
            return new Bet[size];
        }
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
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

    public String getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(String homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public String getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(String awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchLeague() {
        return matchLeague;
    }

    public void setMatchLeague(String matchLeague) {
        this.matchLeague = matchLeague;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeParcelable(gamer, flags);
        dest.writeString(homeTeam);
        dest.writeString(awayTeam);
        dest.writeString(homeTeamScore);
        dest.writeString(awayTeamScore);
        dest.writeString(matchId);
    }

    @Override
    public String toString() {
        return "Bet{" +
                "gamer=" + gamer.toString() +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeamScore='" + homeTeamScore + '\'' +
                ", awayTeamScore='" + awayTeamScore + '\'' +
                ", matchId='" + matchId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return Objects.equals(homeTeam, bet.homeTeam) &&
                Objects.equals(awayTeam, bet.awayTeam) &&
                Objects.equals(matchLeague, bet.matchLeague) &&
                Objects.equals(matchDate, bet.matchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, matchLeague, matchDate);
    }
}

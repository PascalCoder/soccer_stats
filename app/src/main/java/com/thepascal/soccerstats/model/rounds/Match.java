package com.thepascal.soccerstats.model.rounds;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "match_table")
public class Match implements Parcelable {

    @SerializedName("identifier")
    @Expose
    @PrimaryKey
    @NonNull
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

    public Match(String identifier, String homeTeam, String awayTeam, String matchResult) {
        this.identifier = identifier;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchResult = matchResult;
    }

    protected Match(Parcel in) {
        identifier = in.readString();
        name = in.readString();
        matchSlug = in.readString();
        matchDate = in.readString();
        homeTeam = in.readString();
        awayTeam = in.readString();
        matchResult = in.readString();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(identifier);
        dest.writeString(name);
        dest.writeString(matchSlug);
        dest.writeString(matchDate);
        dest.writeString(homeTeam);
        dest.writeString(awayTeam);
        dest.writeString(matchResult);
    }
}

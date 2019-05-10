package com.example.soccerstats.model.teams;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "team")
public class Team {

    @SerializedName("identifier")
    @Expose
    @PrimaryKey
    private String identifier;

    @SerializedName("team_slug")
    @Expose
    private String teamSlug;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("flag")
    @Expose
    private String flag;

    @SerializedName("team_foundation")
    @Expose
    private String teamFoundation;

    @SerializedName("team_website")
    @Expose
    private String teamWebsite;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTeamSlug() {
        return teamSlug;
    }

    public void setTeamSlug(String teamSlug) {
        this.teamSlug = teamSlug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTeamFoundation() {
        return teamFoundation;
    }

    public void setTeamFoundation(String teamFoundation) {
        this.teamFoundation = teamFoundation;
    }

    public String getTeamWebsite() {
        return teamWebsite;
    }

    public void setTeamWebsite(String teamWebsite) {
        this.teamWebsite = teamWebsite;
    }
}

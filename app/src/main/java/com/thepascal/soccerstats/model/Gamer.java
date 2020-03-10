package com.thepascal.soccerstats.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "gamer")
public class Gamer implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private String username;
    private String password;
    private int previousScore;
    private int currentScore;
    private int betPlaced;
    private int points;
    private List<Bet> betList = new ArrayList<>();

    public Gamer(){}

    protected Gamer(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        username = in.readString();
        password = in.readString();
        previousScore = in.readInt();
        currentScore = in.readInt();
        betPlaced = in.readInt();
        points = in.readInt();
    }

    public static final Creator<Gamer> CREATOR = new Creator<Gamer>() {
        @Override
        public Gamer createFromParcel(Parcel in) {
            return new Gamer(in);
        }

        @Override
        public Gamer[] newArray(int size) {
            return new Gamer[size];
        }
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPreviousScore() {
        return previousScore;
    }

    public void setPreviousScore(int previousScore) {
        this.previousScore = previousScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getBetPlaced() {
        return betPlaced;
    }

    public void setBetPlaced(int betPlaced) {
        this.betPlaced = betPlaced;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Bet> getBetList() {
        return betList;
    }

    public void setBetList(List<Bet> betList) {
        this.betList = betList;
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
        dest.writeString(username);
        dest.writeString(password);
        dest.writeInt(previousScore);
        dest.writeInt(currentScore);
        dest.writeInt(betPlaced);
        dest.writeInt(points);
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", previousScore=" + previousScore +
                ", currentScore=" + currentScore +
                ", betPlaced=" + betPlaced +
                ", points=" + points +
                ", betList=" + betList.toString() +
                '}';
    }
}

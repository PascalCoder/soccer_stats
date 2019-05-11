package com.example.soccerstats.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.soccerstats.model.Bet;
import com.example.soccerstats.model.rounds.Match;

import java.util.List;

@Dao
public interface MatchDao {

    @Insert
    void insert(Match match);

    @Update
    void update(Match match);

    @Delete
    void delete(Match match);

    @Query("DELETE FROM match_table") void deleteAllMatches();

    @Query("SELECT * FROM match_table")
    LiveData<List<Match>> getAllMatches();
}

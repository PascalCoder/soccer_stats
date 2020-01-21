package com.example.soccerstats.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.soccerstats.model.rounds.Match;

import java.util.List;

@Dao
public interface SoccerDao {

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

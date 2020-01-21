package com.example.soccerstats.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.soccerstats.model.Gamer;

import java.util.List;

@Dao
public interface GamerDao {

    @Insert
    void insert(Gamer gamer);

    @Update
    void update(Gamer gamer);

    @Delete
    void delete(Gamer gamer);

    @Query("DELETE FROM gamer") void deleteAllGames();

    @Query("SELECT * FROM gamer")
    LiveData<List<Gamer>> getAllGames();
}

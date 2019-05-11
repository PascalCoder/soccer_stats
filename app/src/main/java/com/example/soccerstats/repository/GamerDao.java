package com.example.soccerstats.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

package com.example.soccerstats.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.soccerstats.model.Bet;

import java.util.List;

@Dao
public interface BetDao {

    @Insert void insert(Bet bet);

    @Update void update(Bet bet);

    @Delete void delete(Bet bet);

    @Query("DELETE FROM bet") void deleteAllBets();

    @Query("SELECT * FROM bet")
    LiveData<List<Bet>> getAllBets();

}

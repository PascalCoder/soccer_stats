package com.thepascal.soccerstats.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thepascal.soccerstats.model.Bet;

import java.util.List;

@Dao
public interface BetDao {

    @Insert
    void insert(Bet bet);

    @Update
    void update(Bet bet);

    @Delete
    void delete(Bet bet);

    @Query("DELETE FROM bet") void deleteAllBets();

    @Query("SELECT * FROM bet")
    LiveData<List<Bet>> getAllBets();

}

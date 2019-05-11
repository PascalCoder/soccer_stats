package com.example.soccerstats.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.soccerstats.model.Bet;
import com.example.soccerstats.model.Gamer;
import com.example.soccerstats.model.rounds.Match;

@Database(entities = {Bet.class, Gamer.class, Match.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class, GamerConverter.class})
public abstract class SoccerDatabase extends RoomDatabase {

    private static SoccerDatabase instance;

    public abstract BetDao betDao();

    public abstract GamerDao gamerDao();

    public static synchronized SoccerDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                                        SoccerDatabase.class, "soccer_database")
                                        .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}

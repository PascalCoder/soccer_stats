package com.thepascal.soccerstats.repository;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import com.thepascal.soccerstats.model.Bet;
import com.thepascal.soccerstats.model.Gamer;
import com.thepascal.soccerstats.model.rounds.Match;
import com.thepascal.soccerstats.util.LeaguesConstants;

@Database(entities = {Bet.class, Gamer.class, Match.class}, version = 3, exportSchema = false) //, Standing.class
@TypeConverters({Converters.class, GamerConverter.class})
public abstract class SoccerDatabase extends RoomDatabase {

    private static SoccerDatabase instance;

    public abstract BetDao betDao();

    public abstract GamerDao gamerDao();

    public abstract SoccerDao matchDao();

    public static synchronized SoccerDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                                        SoccerDatabase.class, "soccer_database")
                                        .fallbackToDestructiveMigration()
                                        .addCallback(roomDatabaseCallback)
                                        .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void>{
        private BetDao betDao;

        private PopulateAsyncTask(SoccerDatabase db){
            betDao = db.betDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            betDao.insert(new Bet("Liverpool", "Chelsea", "3", "2", LeaguesConstants.PREMIER_LEAGUE));
            betDao.insert(new Bet("Real Madrid", "Valencia", "4", "2", LeaguesConstants.PREMIER_LEAGUE));

            return null;
        }
    }
}

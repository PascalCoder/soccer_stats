package com.thepascal.soccerstats.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thepascal.soccerstats.model.Gamer;

import java.util.List;

public class GamerRepository {

    private GamerDao gamerDao;
    private LiveData<List<Gamer>> allGamers;

    public GamerRepository(Application application){
        SoccerDatabase database = SoccerDatabase.getInstance(application);
        gamerDao = database.gamerDao();
        allGamers = gamerDao.getAllGames();
    }

    public void insert(Gamer gamer){
        new GamerRepository.InsertGamerAsyncTask(gamerDao).execute(gamer);
    }

    public void update(Gamer gamer){
        new GamerRepository.UpdateGamerAsyncTask(gamerDao).execute(gamer);
    }

    public void delete(Gamer gamer){
        new GamerRepository.DeleteGamerAsyncTask(gamerDao).execute(gamer);
    }

    public void deleteAllGamers(){
        new GamerRepository.DeleteAllGamersAsyncTask(gamerDao).execute();
    }

    public LiveData<List<Gamer>> getAllGamers(){
        return allGamers;
    }

    private static class InsertGamerAsyncTask extends AsyncTask<Gamer, Void, Void> {
        private GamerDao gamerDao;

        private InsertGamerAsyncTask(GamerDao gamerDao){
            this.gamerDao = gamerDao;
        }

        @Override
        protected Void doInBackground(Gamer... gamers) {
            gamerDao.insert(gamers[0]);
            return null;
        }
    }

    private static class UpdateGamerAsyncTask extends AsyncTask<Gamer, Void, Void>{
        private GamerDao gamerDao;

        private UpdateGamerAsyncTask(GamerDao gamerDao){
            this.gamerDao = gamerDao;
        }

        @Override
        protected Void doInBackground(Gamer... gamers) {
            gamerDao.update(gamers[0]);
            return null;
        }
    }

    private static class DeleteGamerAsyncTask extends AsyncTask<Gamer, Void, Void>{
        private GamerDao gamerDao;

        private DeleteGamerAsyncTask(GamerDao gamerDao){
            this.gamerDao = gamerDao;
        }

        @Override
        protected Void doInBackground(Gamer... gamers) {
            gamerDao.delete(gamers[0]);
            return null;
        }
    }

    private static class DeleteAllGamersAsyncTask extends AsyncTask<Void, Void, Void>{
        private GamerDao gamerDao;

        private DeleteAllGamersAsyncTask(GamerDao gamerDao){
            this.gamerDao = gamerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            gamerDao.deleteAllGames();
            return null;
        }
    }
}


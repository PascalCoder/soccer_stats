package com.example.soccerstats.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.soccerstats.model.Bet;

import java.util.List;

public class BetRepository {

    private BetDao betDao;
    private LiveData<List<Bet>> allBets;

    public BetRepository(Application application){
        SoccerDatabase database = SoccerDatabase.getInstance(application);
        betDao = database.betDao();
        allBets = betDao.getAllBets();
    }

    public void insert(Bet bet){
        new InsertBetAsyncTask(betDao).execute(bet);
    }

    public void update(Bet bet){
        new UpdateBetAsyncTask(betDao).execute(bet);
    }

    public void delete(Bet bet){
        new DeleteBetAsyncTask(betDao).execute(bet);
    }

    public void deleteAllBets(){
        new DeleteAllBetsAsyncTask(betDao).execute();
    }

    public LiveData<List<Bet>> getAllBets(){
        return allBets;
    }

    private static class InsertBetAsyncTask extends AsyncTask<Bet, Void, Void>{
        private BetDao betDao;

        private InsertBetAsyncTask(BetDao betDao){
            this.betDao = betDao;
        }

        @Override
        protected Void doInBackground(Bet... bets) {
            betDao.insert(bets[0]);
            return null;
        }
    }

    private static class UpdateBetAsyncTask extends AsyncTask<Bet, Void, Void>{
        private BetDao betDao;

        private UpdateBetAsyncTask(BetDao betDao){
            this.betDao = betDao;
        }

        @Override
        protected Void doInBackground(Bet... bets) {
            betDao.update(bets[0]);
            return null;
        }
    }

    private static class DeleteBetAsyncTask extends AsyncTask<Bet, Void, Void>{
        private BetDao betDao;

        private DeleteBetAsyncTask(BetDao betDao){
            this.betDao = betDao;
        }

        @Override
        protected Void doInBackground(Bet... bets) {
            betDao.delete(bets[0]);
            return null;
        }
    }

    private static class DeleteAllBetsAsyncTask extends AsyncTask<Void, Void, Void>{
        private BetDao betDao;

        private DeleteAllBetsAsyncTask(BetDao betDao){
            this.betDao = betDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            betDao.deleteAllBets();
            return null;
        }
    }
}

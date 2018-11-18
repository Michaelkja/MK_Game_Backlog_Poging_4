package com.example.micha.mk_game_backlog_poging_4;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class GameRepository {
    private GameDao gameDao;
    private LiveData<List<Game>> allGames;

    public GameRepository(Application application) {
        GameDatabase database = GameDatabase.getInstance(application);
        gameDao = database.gameDao();
        allGames = gameDao.getAllGames();
    }

    public void insert (Game game) {
        new InsertGameAsyncTask(gameDao).execute(game);
    }
    public void update (Game game) {
        new UpdateGameAsyncTask(gameDao).execute(game);
    }
    public void delete (Game game) {
        new DeleteGameAsyncTask(gameDao).execute(game);
    }
    public LiveData<List<Game>> getAllGames() {
        return allGames;
    }

    private static class InsertGameAsyncTask extends AsyncTask<Game, Void, Void> {
        private GameDao gameDao;

        private InsertGameAsyncTask(GameDao gameDao) {
            this.gameDao = gameDao;
        }
        @Override
        protected Void doInBackground(Game... games) {
            gameDao.insert(games[0]);
            return null;
        }
    }

    private static class UpdateGameAsyncTask extends AsyncTask<Game, Void, Void> {
        private GameDao gameDao;

        private UpdateGameAsyncTask(GameDao gameDao) {
            this.gameDao = gameDao;
        }
        @Override
        protected Void doInBackground(Game... games) {
            gameDao.update(games[0]);
            return null;
        }
    }

    private static class DeleteGameAsyncTask extends AsyncTask<Game, Void, Void> {
        private GameDao gameDao;

        private DeleteGameAsyncTask(GameDao gameDao) {
            this.gameDao = gameDao;
        }
        @Override
        protected Void doInBackground(Game... games) {
            gameDao.delete(games[0]);
            return null;
        }
    }


}

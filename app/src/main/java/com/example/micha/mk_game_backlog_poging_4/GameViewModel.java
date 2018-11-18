package com.example.micha.mk_game_backlog_poging_4;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    private GameRepository repository;
    private LiveData<List<Game>> allGames;

    public GameViewModel(@NonNull Application application) {
        super(application);
        repository = new GameRepository(application);
        allGames = repository.getAllGames();
    }

    public void insert(Game game) {
        repository.insert(game);
    }

    public void update(Game game) {
        repository.update(game);
    }
    public void delete(Game game) {
        repository.delete(game);
    }

    public LiveData<List<Game>> getAllGames() {
        return allGames;
    }
}

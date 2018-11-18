package com.example.micha.mk_game_backlog_poging_4;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Game_Table")
public class Game {

    @Ignore
    private static String [] status = {"Want to Play", "Playing", "Stalled", "Dropped"};

    public static String [] getStatus() {
        return status;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String GameTitle;

    private String GamePlatform;

    private String GameDescription;

    private String GameStatus;

    public Game(String GameTitle, String GamePlatform, String GameDescription, String GameStatus) {
        this.GameTitle       =         GameTitle;
        this.GamePlatform    =      GamePlatform;
        this.GameDescription =   GameDescription;
        this.GameStatus =        GameStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getGameTitle() {
        return GameTitle;
    }

    public String getGamePlatform() {
        return GamePlatform;
    }

    public String getGameDescription() {
        return GameDescription;
    }

    public String getGameStatus() {
        return GameStatus;
    }

    public void setGameTitle(String gameTitle) {
        GameTitle = gameTitle;
    }

    public void setGamePlatform(String gamePlatform) {
        GamePlatform = gamePlatform;
    }

    public void setGameDescription(String gameDescription) {
        GameDescription = gameDescription;
    }

    public void setGameStatus(String gameStatus) {
        GameStatus = gameStatus;
    }
}

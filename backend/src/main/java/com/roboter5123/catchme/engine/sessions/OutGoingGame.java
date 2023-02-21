package com.roboter5123.catchme.engine.sessions;
import com.roboter5123.catchme.games.Game;

public class OutGoingGame {

    private String gameName;
    private String sessionCode;

    public OutGoingGame(Game game) {

        this.sessionCode = game.getSessionCode();
        this.gameName = game.getClass().getSimpleName().toLowerCase();

    }

    public String getGameName() {

        return gameName;
    }

    public void setGameName(String gameName) {

        this.gameName = gameName;
    }

    public String getSessionCode() {

        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {

        this.sessionCode = sessionCode;
    }
}

package com.roboter5123.catchme.engine;
import java.util.List;

public class Session {

    private Player host;
    private List<Player> players;
    private Game game;
    private String gameName;
    private String sessionCode;

    public Session(Game game, String sessionCode) {

        this.sessionCode = sessionCode;
        this.gameName = game.getClass().getSimpleName();
        this.game = game;
    }

    public Session() {


    }

    public Player getHost() {

        return host;
    }

    public void setHost(Player host) {

        this.host = host;
    }

    public List<Player> getPlayers() {

        return players;
    }

    public void setPlayers(List<Player> players) {

        this.players = players;
    }

    public Game getGame() {

        return game;
    }

    public void setGame(Game game) {

        this.game = game;
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
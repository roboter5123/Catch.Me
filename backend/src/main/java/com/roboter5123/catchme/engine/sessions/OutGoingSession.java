package com.roboter5123.catchme.engine.sessions;
public class OutGoingSession {

    private String gameName;
    private String sessionCode;

    public OutGoingSession(String sessionCode, String gameName) {

        this.sessionCode = sessionCode;
        this.gameName = gameName;

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

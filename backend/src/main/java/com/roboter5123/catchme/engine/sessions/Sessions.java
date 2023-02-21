package com.roboter5123.catchme.engine.sessions;
import com.roboter5123.catchme.engine.messages.IncomingMessage;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;

import java.util.*;

public class Sessions{

    private final Map<String, Session> sessionMap;
    private static Sessions instance;

    public static Sessions getInstance(){

        if (Sessions.instance == null){

            Sessions.instance = new Sessions();
        }

        return Sessions.instance;
    }

    private Sessions() {

        this.sessionMap = new HashMap<>();
    }

    public int size() {

        return sessionMap.size();
    }

    public boolean isEmpty() {

        return sessionMap.isEmpty();
    }

    public boolean contains(String key) {

        return sessionMap.containsKey(key);
    }

    public Session add(String key, Session value) {

        return sessionMap.put(key, value);
    }

    public Session remove(String key) {

        return sessionMap.remove(key);
    }

    public void clear() {

        sessionMap.clear();
    }

    public Session get(String gameCode) {

        return sessionMap.get(gameCode);
    }

    public OutGoingMessage joinGame(String gameCode) {

        return sessionMap.get(gameCode).joinGame();
    }

    public OutGoingMessage advanceSession(String gameCode, IncomingMessage message) {

        return sessionMap.get(gameCode).advanceGame(message);
    }
}

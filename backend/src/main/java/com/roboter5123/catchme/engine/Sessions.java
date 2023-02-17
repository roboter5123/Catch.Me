package com.roboter5123.catchme.engine;
import java.util.*;

public class Sessions{

    private Map<String, Session> sessionMap;
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
}

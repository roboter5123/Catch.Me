package com.roboter5123.catchme.controller;
import com.roboter5123.catchme.engine.Session;
import com.roboter5123.catchme.games.Chat;

public class SessionFactory {

    private SessionFactory() {
//        Hiding the implicit public constructor
    }

    public static Session createSession(String sessionType){

       Session session;

       switch (sessionType) {
           case "chat" -> session = new Session(new Chat());
           default -> session = new Session();
       }
       return session;
   }
}

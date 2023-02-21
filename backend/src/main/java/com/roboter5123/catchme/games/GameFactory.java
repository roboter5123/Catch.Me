package com.roboter5123.catchme.games;
import com.roboter5123.catchme.games.chat.Chat;

public class GameFactory {

    private GameFactory() {
//        Hiding the implicit public constructor
    }

    public static Game createSession(String sessionType, String sessionCode){

       Game game;

       switch (sessionType) {
           case "chat" -> game = new Chat(sessionCode);
           default -> game = new Chat(sessionCode);
       }
       return game;
   }
}

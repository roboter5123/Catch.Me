package com.roboter5123.catchme.engine.controller;
import com.roboter5123.catchme.configurations.GamesConfiguration;
import com.roboter5123.catchme.engine.sessions.OutGoingGame;
import com.roboter5123.catchme.engine.sessions.Sessions;
import com.roboter5123.catchme.games.Game;
import com.roboter5123.catchme.games.GameFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class SessionController {

    Sessions sessions;
    Random random;

    public SessionController() {

        this.sessions = Sessions.getInstance();
        this.random  = new Random();
    }

    @PostMapping("/session")
    @CrossOrigin()
    @ResponseBody
    public OutGoingGame createSession(@RequestBody String sessionType){

        String sessionCode;
        sessionCode = createNonExistentSessionCode();
        Game game = GameFactory.createSession(sessionType, sessionCode);
        sessions.add(sessionCode, game);
        return game.out();
    }

    private String createNonExistentSessionCode() {

        StringBuilder sessionCode = new StringBuilder();
        int asciiA = 65;
        int asciiZ = 90;

        do {

            int[] codeInts = new int[4];

            for (int i = 0; i < codeInts.length; i++) {

                codeInts[i] = random.nextInt(asciiA, asciiZ);
                sessionCode.append((char) codeInts[i]);
            }

        }while (sessions.contains(sessionCode.toString()));

        return sessionCode.toString();
    }

    @GetMapping("/session/{sessionCode}")
    @CrossOrigin()
    @ResponseBody
    public OutGoingGame lookupSession(@PathVariable String sessionCode){

        return sessions.get(sessionCode).out();
    }

    @GetMapping("games")
    @CrossOrigin()
    @ResponseBody
    public String[] lookupGames(){

        return GamesConfiguration.games;
    }
}

package com.roboter5123.catchme.engine.controller;
import com.roboter5123.catchme.configurations.GamesConfiguration;
import com.roboter5123.catchme.engine.sessions.OutGoingSession;
import com.roboter5123.catchme.engine.sessions.Session;
import com.roboter5123.catchme.engine.sessions.Sessions;
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
    @ResponseBody
    public Session createSession(@RequestBody String sessionType){

        String sessionCode;
        sessionCode = createNonExistentSessionCode();
        Session session = SessionFactory.createSession(sessionType, sessionCode);
        sessions.add(sessionCode, session);
        return session;
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
    @ResponseBody
    public OutGoingSession lookupSession(@PathVariable String sessionCode){

        return sessions.get(sessionCode).out();
    }

    @GetMapping("games")
    @ResponseBody
    public String[] lookupGames(){

        return GamesConfiguration.games;
    }
}

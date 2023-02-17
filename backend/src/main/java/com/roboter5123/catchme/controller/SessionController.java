package com.roboter5123.catchme.controller;
import com.roboter5123.catchme.engine.Session;
import com.roboter5123.catchme.engine.Sessions;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
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
    public String createSession(@RequestBody String sessionType){

        String sessionCode;
        sessionCode = createNonExistentSessionCode();
        Session session = SessionFactory.createSession(sessionType);
        sessions.add(sessionCode, session);
        return "{\"sessionCode\":\"" + sessionCode + "\"}";
    }

    private String createNonExistentSessionCode() {

        String sessionCode;
        do {

            byte[] codeBytes = new byte[4];
            random.nextBytes(codeBytes);
            sessionCode = new String(codeBytes, StandardCharsets.UTF_8);

        }while (sessions.contains(sessionCode));
        return sessionCode;
    }

    @GetMapping("/session")
    @ResponseBody
    public boolean lookupSession(String sessionCode){

        return sessions.contains(sessionCode);
    }
}

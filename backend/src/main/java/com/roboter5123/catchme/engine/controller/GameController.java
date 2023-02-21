package com.roboter5123.catchme.engine.controller;
import com.roboter5123.catchme.engine.messages.IncomingMessage;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;
import com.roboter5123.catchme.engine.sessions.Sessions;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GameController {

    Sessions sessions;

    public GameController() {

        this.sessions = Sessions.getInstance();
    }

    @SubscribeMapping("/{gameCode}")
    public OutGoingMessage joinGame(@DestinationVariable String gameCode){

        return sessions.joinGame(gameCode);
    }

    @MessageMapping("/{gameCode}")
    @SendTo("/topic/{gameCode}")
    public OutGoingMessage advanceSession(@RequestBody IncomingMessage message, @DestinationVariable String gameCode ){

        return sessions.advanceSession(gameCode, message);
    }


}

package com.roboter5123.catchme.engine.controller;
import com.roboter5123.catchme.games.Game;
import com.roboter5123.catchme.engine.messages.IncomingMessage;
import com.roboter5123.catchme.engine.sessions.Sessions;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

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
    public OutGoingMessage test(IncomingMessage incomingMessage, @DestinationVariable String gameCode ){

        Game game = sessions.get(gameCode).getGame();
        return game.changeStatus(incomingMessage);
    }
}

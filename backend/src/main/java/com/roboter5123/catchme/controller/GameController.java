package com.roboter5123.catchme.controller;
import com.roboter5123.catchme.engine.Game;
import com.roboter5123.catchme.engine.Message;
import com.roboter5123.catchme.engine.Sessions;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    Sessions sessions;

    public GameController() {

        this.sessions = Sessions.getInstance();
    }

    @MessageMapping("/{gameCode}")
    @SendTo("/topic/{gameCode}")
    public Message test(Message message, @DestinationVariable String gameCode ){

        Game game = sessions.get(gameCode).getGame();
        game.changeStatus(message);
        return game.getStatus();
    }
}

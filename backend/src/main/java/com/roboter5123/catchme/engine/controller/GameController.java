package com.roboter5123.catchme.engine.controller;
import com.roboter5123.catchme.engine.Game;
import com.roboter5123.catchme.engine.Message;
import com.roboter5123.catchme.engine.Sessions;
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

    @SubscribeMapping("/topic/abc")
    public void joinGame(){

        System.out.println("connected");
//        return sessions.get(gameCode).getGame().getStatus();
    }

    @MessageMapping("/{gameCode}")
    @SendTo("/topic/{gameCode}")
    public Message test(Message message, @DestinationVariable String gameCode ){

        Game game = sessions.get(gameCode).getGame();
        game.changeStatus(message);
        return game.getStatus();
    }
}

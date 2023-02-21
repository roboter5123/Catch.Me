package com.roboter5123.catchme.games.chat;
import com.roboter5123.catchme.engine.Player;
import com.roboter5123.catchme.engine.messages.IncomingMessage;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;

public class ChatMessage implements OutGoingMessage {

    private Player player;
    private String message;

    public ChatMessage(IncomingMessage incomingMessage) {

        this.player = incomingMessage.getPlayer();
        this.message = incomingMessage.getCommand().getArgs()[0];
    }

    public ChatMessage() {


    }

    public ChatMessage(OutGoingJoinMessage message) {

        ChatMessage chatMessage = message.getMessages().get(message.getMessages().size()-1);
        this.player = chatMessage.getPlayer();
        this.message= chatMessage.getMessage();
    }

    public Player getPlayer() {

        return player;
    }

    public void setPlayer(Player player) {

        this.player = player;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}

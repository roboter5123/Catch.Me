package com.roboter5123.catchme.games.chat;
import com.roboter5123.catchme.engine.Player;
import com.roboter5123.catchme.engine.messages.IncomingMessage;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;
import com.roboter5123.catchme.engine.sessions.OutGoingGame;
import com.roboter5123.catchme.games.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chat implements Game {

    private String sessionCode;
    private List<ChatMessage> messages;
    private final HashMap<String, Player> players;

    private Player server;

    public Chat(String sessionCode) {

        this.messages = new ArrayList<>();
        this.sessionCode = sessionCode;
        players = new HashMap<>();
        server = new Player();
        server.setName("Server");
    }

    @Override
    public OutGoingMessage changeStatus(IncomingMessage incomingMessage) {

        if (incomingMessage.getCommand().getType().equals("addPlayer")) {

            return addPlayer(incomingMessage);
        }

        ChatMessage incomingChatMessage = new ChatMessage(incomingMessage);
        messages.add(incomingChatMessage);
        return incomingChatMessage;
    }

    private OutGoingMessage addPlayer(IncomingMessage incomingMessage) {

        Player player = incomingMessage.getPlayer();
        this.players.put(player.getName(), player);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setPlayer(server);
        chatMessage.setMessage(player.getName() + " has joined!");
        return chatMessage;
    }

    @Override
    public OutGoingMessage joinGame() {

        OutGoingJoinMessage message = new OutGoingJoinMessage();
        message.setMessages(this.messages);
        return message;
    }

    @Override
    public OutGoingMessage leaveGame() {

        return null;
    }

    @Override
    public OutGoingGame out() {

        return new OutGoingGame(this);
    }

    public List<ChatMessage> getMessages() {

        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {

        this.messages = messages;
    }

    @Override
    public String getSessionCode() {

        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {

        this.sessionCode = sessionCode;
    }
}

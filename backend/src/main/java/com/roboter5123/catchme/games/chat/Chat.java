package com.roboter5123.catchme.games.chat;
import com.roboter5123.catchme.engine.messages.IncomingMessage;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;
import com.roboter5123.catchme.games.Game;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Game {

    List<ChatMessage> messages;

    public Chat() {

        messages = new ArrayList<>();
    }

    @Override
    public OutGoingMessage changeStatus(IncomingMessage incomingMessage) {

        ChatMessage incomingChatMessage = new ChatMessage(incomingMessage);
        messages.add(incomingChatMessage);
        return incomingChatMessage;
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

    public List<ChatMessage> getMessages() {

        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {

        this.messages = messages;
    }
}

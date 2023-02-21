package com.roboter5123.catchme.games.chat;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;

import java.util.List;

public class OutGoingJoinMessage implements OutGoingMessage {

    private List<ChatMessage> messages;

    public List<ChatMessage> getMessages() {

        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {

        this.messages = messages;
    }
}

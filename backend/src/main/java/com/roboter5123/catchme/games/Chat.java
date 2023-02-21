package com.roboter5123.catchme.games;
import com.roboter5123.catchme.engine.messages.IncomingMessage;
import com.roboter5123.catchme.engine.messages.OutGoingMessage;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Game {

    List<String> messages;

    public Chat() {

        messages = new ArrayList<>();
    }

    public OutGoingMessage getStatus(){

        OutGoingMessage outGoingMessage = new OutGoingMessage();
        StringBuilder messageBody = new StringBuilder();

        for (String s : messages) {

            messageBody.append(s).append("\n");
        }

        outGoingMessage.setBody(messageBody.toString());
        return outGoingMessage;
    }

    @Override
    public OutGoingMessage changeStatus(IncomingMessage incomingMessage) {

        switch (incomingMessage.getCommand()) {
            case "send" -> {
                messages.add(incomingMessage.getArgs());
                OutGoingMessage outGoingMessage = new OutGoingMessage();
                outGoingMessage.setBody(incomingMessage.getArgs());
                return outGoingMessage;
            }
            default -> {return new OutGoingMessage();}
        }
    }

    @Override
    public OutGoingMessage joinGame() {

        return getStatus();
    }

}
